package com.rightsguard.automation;

import android.app.Service;
import android.content.Intent;
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

import com.google.android.material.button.MaterialButton;

/**
 * 悬浮窗服务
 */
public class FloatingWindowService extends Service {
    
    private WindowManager windowManager;
    private View floatingView;
    private WindowManager.LayoutParams params;
    
    private MaterialButton btnStart;
    private MaterialButton btnStop;
    private MaterialButton btnHide;
    private MaterialButton btnDump; // ⚠️ 开发专用,正式版将移除
    private TextView tvStatus;
    private View statusIndicator;
    
    private boolean isRunning = false;
    
    @Override
    public void onCreate() {
        super.onCreate();
        
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
        btnStart = floatingView.findViewById(R.id.btn_float_start);
        btnStop = floatingView.findViewById(R.id.btn_float_stop);
        btnHide = floatingView.findViewById(R.id.btn_float_hide);
        btnDump = floatingView.findViewById(R.id.btn_float_dump); // ⚠️ 开发专用
        tvStatus = floatingView.findViewById(R.id.tv_float_status);
        statusIndicator = floatingView.findViewById(R.id.view_float_status_indicator);

        // 设置监听器
        btnStart.setOnClickListener(v -> startAutomation());
        btnStop.setOnClickListener(v -> stopAutomation());
        btnHide.setOnClickListener(v -> hideFloatingWindow());

        // ⚠️ Dump按钮监听器 (开发专用,正式版将移除)
        btnDump.setOnClickListener(v -> dumpCurrentUI());
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
        View dragHandle = floatingView.findViewById(R.id.view_drag_handle);
        
        dragHandle.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;
            
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                        
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(floatingView, params);
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
    
    private void hideFloatingWindow() {
        stopSelf();
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
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatingView != null && windowManager != null) {
            windowManager.removeView(floatingView);
        }
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

