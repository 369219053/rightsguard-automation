package com.rightsguard.automation;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;

/**
 * 悬浮窗服务
 */
public class FloatingWindowService extends Service {

    private static final String CHANNEL_ID = "FloatingWindowService";
    private static final int NOTIFICATION_ID = 1001;

    private WindowManager windowManager;
    private View floatingView;
    private WindowManager.LayoutParams params;

    private Button btnStart;
    private Button btnStop;
    private Button btnMinimize;
    private Button btnTestCoordinate; // 测试坐标按钮
    private Button btnDump; // ⚠️ 开发专用,正式版将移除
    private Button btnMinimizedDump; // 最小化状态的Dump按钮
    private TextView tvStatus;
    private View statusIndicator;

    private View layoutFull; // 完整状态布局
    private View layoutMinimized; // 最小化状态布局

    // 坐标测试相关
    private View coordinateTesterView;
    private WindowManager.LayoutParams testerParams;
    private boolean isTesterShowing = false;
    private int lastClickX = 0;
    private int lastClickY = 0;

    private boolean isRunning = false;
    private boolean isMinimized = false;

    @Override
    public void onCreate() {
        super.onCreate();

        // 🔧 注意: 悬浮窗服务不需要前台Service
        // 因为它有悬浮窗权限,系统会保持它运行
        // 而且targetSDK=35时,前台Service启动会失败

        // 创建悬浮窗
        createFloatingWindow();
    }


    private void createFloatingWindow() {
        // 获取WindowManager
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        
        // 加载布局
        floatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_window, null);
        
        // 设置窗口参数
        params = new WindowManager.LayoutParams();
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        
        params.format = PixelFormat.TRANSLUCENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 100;
        params.y = 100;
        
        // 添加到窗口
        windowManager.addView(floatingView, params);
        
        // 初始化视图
        initViews();
        
        // 设置拖动
        setupDrag();
    }
    
    private void initViews() {
        // 获取布局
        layoutFull = floatingView.findViewById(R.id.layout_full);
        layoutMinimized = floatingView.findViewById(R.id.layout_minimized);

        // 完整状态的控件
        btnStart = floatingView.findViewById(R.id.btn_float_start);
        btnStop = floatingView.findViewById(R.id.btn_float_stop);
        btnMinimize = floatingView.findViewById(R.id.btn_float_minimize);
        btnTestCoordinate = floatingView.findViewById(R.id.btn_float_test_coordinate);
        btnDump = floatingView.findViewById(R.id.btn_float_dump); // ⚠️ 开发专用
        tvStatus = floatingView.findViewById(R.id.tv_float_status);
        statusIndicator = floatingView.findViewById(R.id.view_float_status_indicator);

        // 最小化状态的控件
        btnMinimizedDump = floatingView.findViewById(R.id.btn_minimized_dump);

        // 设置监听器
        btnStart.setOnClickListener(v -> startAutomation());
        btnStop.setOnClickListener(v -> stopAutomation());
        btnMinimize.setOnClickListener(v -> toggleMinimize());
        btnTestCoordinate.setOnClickListener(v -> showCoordinateTester());
        btnDump.setOnClickListener(v -> dumpCurrentUI());

        // 最小化状态的Dump按钮
        btnMinimizedDump.setOnClickListener(v -> dumpCurrentUI());
        // 注意: layoutMinimized的点击和拖动在setupDrag()中处理
    }

    /**
     * ⚠️ Dump当前UI结构 (开发专用,正式版将移除)
     */
    private void dumpCurrentUI() {
        AutomationAccessibilityService service = AutomationAccessibilityService.getInstance();
        if (service != null) {
            service.dumpCurrentUI();
            Toast.makeText(this, "🔍 正在Dump UI结构...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "❌ 无障碍服务未启动", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void setupDrag() {
        // 完整状态:整个布局可拖动,但按钮不拦截
        layoutFull.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;
            private boolean isDragging = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        isDragging = false;
                        return false; // 不拦截,让按钮可以响应

                    case MotionEvent.ACTION_MOVE:
                        float deltaX = event.getRawX() - initialTouchX;
                        float deltaY = event.getRawY() - initialTouchY;

                        // 如果移动距离超过10像素,认为是拖动
                        if (Math.abs(deltaX) > 10 || Math.abs(deltaY) > 10) {
                            isDragging = true;
                            params.x = initialX + (int) deltaX;
                            params.y = initialY + (int) deltaY;
                            windowManager.updateViewLayout(floatingView, params);
                            return true; // 拦截拖动事件
                        }
                        return false;

                    case MotionEvent.ACTION_UP:
                        if (isDragging) {
                            return true; // 拖动结束,拦截事件
                        }
                        return false; // 不是拖动,让按钮响应点击
                }
                return false;
            }
        });

        // 最小化状态下,整个圆点可拖动(但点击时恢复)
        layoutMinimized.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;
            private boolean isDragging = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        isDragging = false;
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        float deltaX = event.getRawX() - initialTouchX;
                        float deltaY = event.getRawY() - initialTouchY;

                        // 如果移动距离超过10像素,认为是拖动
                        if (Math.abs(deltaX) > 10 || Math.abs(deltaY) > 10) {
                            isDragging = true;
                            params.x = initialX + (int) deltaX;
                            params.y = initialY + (int) deltaY;
                            windowManager.updateViewLayout(floatingView, params);
                        }
                        return true;

                    case MotionEvent.ACTION_UP:
                        // 如果没有拖动,认为是点击,恢复悬浮窗
                        if (!isDragging) {
                            toggleMinimize();
                        }
                        return true;
                }
                return false;
            }
        });
    }
    
    private void startAutomation() {
        // TODO: 实现自动化启动逻辑
        isRunning = true;
        updateStatus();
        Toast.makeText(this, "自动化已启动", Toast.LENGTH_SHORT).show();
    }
    
    private void stopAutomation() {
        // TODO: 实现自动化停止逻辑
        isRunning = false;
        updateStatus();
        Toast.makeText(this, "自动化已停止", Toast.LENGTH_SHORT).show();
    }
    
    /**
     * 切换最小化/恢复状态
     */
    private void toggleMinimize() {
        isMinimized = !isMinimized;

        if (isMinimized) {
            // 最小化
            layoutFull.setVisibility(View.GONE);
            layoutMinimized.setVisibility(View.VISIBLE);
            Toast.makeText(this, "已最小化,点击圆点恢复", Toast.LENGTH_SHORT).show();
        } else {
            // 恢复
            layoutFull.setVisibility(View.VISIBLE);
            layoutMinimized.setVisibility(View.GONE);
        }
    }

    /**
     * 显示悬浮窗
     */
    public static void show(Service context) {
        Intent intent = new Intent(context, FloatingWindowService.class);
        context.startService(intent);
    }

    /**
     * 隐藏悬浮窗
     */
    public static void hide(Service context) {
        Intent intent = new Intent(context, FloatingWindowService.class);
        context.stopService(intent);
    }
    
    private void updateStatus() {
        if (isRunning) {
            tvStatus.setText("运行中");
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
        } else {
            tvStatus.setText("空闲");
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
        }
    }
    
    /**
     * 显示坐标测试器
     */
    private void showCoordinateTester() {
        if (isTesterShowing) {
            return;
        }

        // 创建坐标测试界面
        coordinateTesterView = LayoutInflater.from(this).inflate(R.layout.layout_coordinate_tester, null);

        // 设置窗口参数
        testerParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            testerParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            testerParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        testerParams.format = PixelFormat.TRANSLUCENT;
        testerParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        testerParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        testerParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        testerParams.gravity = Gravity.TOP | Gravity.START;

        // 添加到窗口
        windowManager.addView(coordinateTesterView, testerParams);
        isTesterShowing = true;

        // 初始化坐标测试界面
        initCoordinateTester();

        Toast.makeText(this, "📍 点击屏幕任意位置查看坐标", Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化坐标测试器
     */
    private void initCoordinateTester() {
        View rootView = coordinateTesterView.findViewById(R.id.coordinate_tester_root);
        View displayCard = coordinateTesterView.findViewById(R.id.coordinate_display_card);
        TextView tvX = coordinateTesterView.findViewById(R.id.tv_coordinate_x);
        TextView tvY = coordinateTesterView.findViewById(R.id.tv_coordinate_y);
        Button btnUse = coordinateTesterView.findViewById(R.id.btn_use_coordinate);
        Button btnCancel = coordinateTesterView.findViewById(R.id.btn_cancel_coordinate);
        Button btnClose = coordinateTesterView.findViewById(R.id.btn_close_tester);

        // 点击根视图获取坐标
        rootView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                lastClickX = (int) event.getRawX();
                lastClickY = (int) event.getRawY();

                // 显示坐标
                tvX.setText("X: " + lastClickX);
                tvY.setText("Y: " + lastClickY);
                displayCard.setVisibility(View.VISIBLE);

                Toast.makeText(this, "✅ 坐标: (" + lastClickX + ", " + lastClickY + ")", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        // 使用坐标
        btnUse.setOnClickListener(v -> {
            saveCoordinate(lastClickX, lastClickY);
            hideCoordinateTester();
            Toast.makeText(this, "✅ 已保存坐标: (" + lastClickX + ", " + lastClickY + ")", Toast.LENGTH_LONG).show();
        });

        // 取消
        btnCancel.setOnClickListener(v -> {
            displayCard.setVisibility(View.GONE);
        });

        // 关闭测试器
        btnClose.setOnClickListener(v -> {
            hideCoordinateTester();
        });
    }

    /**
     * 隐藏坐标测试器
     */
    private void hideCoordinateTester() {
        if (coordinateTesterView != null && windowManager != null && isTesterShowing) {
            windowManager.removeView(coordinateTesterView);
            coordinateTesterView = null;
            isTesterShowing = false;
        }
    }

    /**
     * 保存坐标到SharedPreferences
     */
    private void saveCoordinate(int x, int y) {
        SharedPreferences prefs = getSharedPreferences("automation_config", MODE_PRIVATE);
        prefs.edit()
                .putInt("more_button_x", x)
                .putInt("more_button_y", y)
                .apply();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideCoordinateTester();
        if (floatingView != null && windowManager != null) {
            windowManager.removeView(floatingView);
        }
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 返回START_STICKY,确保Service被系统杀死后会自动重启
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

