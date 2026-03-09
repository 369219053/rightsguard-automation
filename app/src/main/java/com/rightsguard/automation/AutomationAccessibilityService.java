package com.rightsguard.automation;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 自动化无障碍服务
 * 用于自动化控制权利卫士应用
 */
public class AutomationAccessibilityService extends AccessibilityService {

    private static final String TAG = "AutomationService";
    private static final String TARGET_PACKAGE = "com.unitrust.tsa";
    private static final String TARGET_ACTIVITY = "cn.tsa.activity.SplashActivity";
    private static final String SCREEN_RECORD_BUTTON_ID = "com.unitrust.tsa:id/screen_record_layout";
    private static final String REMARK_INPUT_ID = "com.unitrust.tsa:id/ed_remark";
    private static final String START_BUTTON_ID = "com.unitrust.tsa:id/rl_btn";
    private static final String CONFIRM_BUTTON_ID = "com.unitrust.tsa:id/confirm_button";
    private static final String CONFIRM_BUTTON_TWO_ID = "com.unitrust.tsa:id/confirm_button_two"; // 取证环境检测结果页面的"开始取证"按钮

    // 应用验真界面相关
    private static final String VERIFY_BUTTON_TEXT = "立即验证";
    private static final String DOUYIN_APP_TEXT = "抖音";

    // 系统录屏权限弹窗相关
    private static final String SYSTEM_UI_PACKAGE = "com.android.systemui";
    private static final String SCREEN_SHARE_MODE_SPINNER_ID = "com.android.systemui:id/real_screen_share_mode_spinner";
    private static final String CONTINUE_BUTTON_ID = "android:id/button1";

    // 抖音包名
    private static final String DOUYIN_PACKAGE = "com.ss.android.ugc.aweme";

    // 夸克浏览器包名
    private static final String QUARK_BROWSER_PACKAGE = "com.quark.browser";

    private static AutomationAccessibilityService instance;
    private boolean isRunning = false;
    private boolean hasClickedScreenRecord = false;
    private boolean hasSelectedDouyin = false; // 是否已勾选抖音
    private String remark = "";
    private String infringementUrl = ""; // 侵权链接
    private String videoKeywords = ""; // 🆕 视频文案关键词
    private int videoDurationSeconds = 60; // 🆕 视频时长(秒),默认60秒

    // 🧪 测试模式标志
    private boolean isTestMode = false; // 是否为测试模式(跳过权利卫士+录屏，直接打开抖音→历史→作者主页)

    // 权利卫士取证阶段标志位
    private boolean isRightsGuardEvidencePhase = false; // 是否处于权利卫士取证阶段(权利卫士打开抖音后)
    private boolean hasStartedDouyinAutomation = false; // 是否已开始抖音自动化
    private boolean hasClickedDouyinMe = false; // 是否已点击抖音"我"按钮
    private boolean hasClickedDouyinMore = false; // 是否已点击抖音"更多"按钮
    private boolean hasClickedDouyinSettings = false; // 是否已点击抖音"设置"按钮
    private boolean hasScrolledToAboutSection = false; // 是否已滑动到"关于"部分

    // 日志收集
    private static final StringBuilder logBuilder = new StringBuilder();
    private static final int MAX_LOG_LENGTH = 50000; // 最大日志长度
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        logD("无障碍服务已创建");

        // 🔧 注意: AccessibilityService是系统管理的特殊Service,不需要调用startForeground()
        // 系统会自动保持它运行,调用startForeground()反而会导致崩溃

        // 启动悬浮窗服务
        logD("📞 即将启动悬浮窗服务...");
        startFloatingWindow();
    }



    /**
     * 启动悬浮窗服务
     */
    private void startFloatingWindow() {
        try {
            android.content.Intent intent = new android.content.Intent(this, FloatingWindowService.class);
            startService(intent);
            logD("✅ 悬浮窗服务已启动");
        } catch (Exception e) {
            logE("启动悬浮窗服务失败: " + e.getMessage());
        }
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (!isRunning) {
            return;
        }

        String packageName = event.getPackageName() != null ? event.getPackageName().toString() : "";
        int eventType = event.getEventType();

        // 处理系统UI的录屏权限弹窗
        if (SYSTEM_UI_PACKAGE.equals(packageName)) {
            if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED ||
                eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
                handleSystemScreenShareDialog();
            }
            return;
        }

        // 🆕 处理抖音事件(仅在权利卫士取证阶段)
        if (DOUYIN_PACKAGE.equals(packageName) && isRightsGuardEvidencePhase) {
            if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                // 只在窗口状态变化时触发一次
                if (!hasStartedDouyinAutomation) {
                    hasStartedDouyinAutomation = true;
                    startDouyinAutomation();
                }
            }
            return;
        }

        // 只处理权利卫士应用的事件
        if (!TARGET_PACKAGE.equals(packageName)) {
            return;
        }

        Log.d(TAG, "收到事件: " + AccessibilityEvent.eventTypeToString(eventType));

        // 处理窗口状态变化事件
        if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            handleWindowStateChanged(event);
        }

        // 处理窗口内容变化事件
        if (eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            handleWindowContentChanged();

            // 检查是否是应用验真界面
            handleAppVerificationDialog();
        }
    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "服务被中断");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;

        // 停止悬浮窗服务
        stopFloatingWindow();

        Log.d(TAG, "服务已销毁");
    }

    /**
     * 停止悬浮窗服务
     */
    private void stopFloatingWindow() {
        try {
            android.content.Intent intent = new android.content.Intent(this, FloatingWindowService.class);
            stopService(intent);
            logD("✅ 悬浮窗服务已停止");
        } catch (Exception e) {
            logE("停止悬浮窗服务失败: " + e.getMessage());
        }
    }

    /**
     * 获取服务实例
     */
    public static AutomationAccessibilityService getInstance() {
        return instance;
    }

    /**
     * 检查服务是否可用
     */
    public static boolean isServiceAvailable() {
        return instance != null;
    }

    /**
     * 设置备注内容
     */
    public void setRemark(String remark) {
        this.remark = remark != null ? remark : "";
        logD("📝 设置备注: " + this.remark);
    }

    /**
     * 设置侵权链接
     */
    public void setInfringementUrl(String url) {
        this.infringementUrl = url != null ? url : "";
        logD("📝 设置侵权链接: " + this.infringementUrl);
    }

    /**
     * 🆕 设置视频关键词
     */
    public void setVideoKeywords(String keywords) {
        this.videoKeywords = keywords != null ? keywords : "";
        logD("📝 设置视频关键词: " + this.videoKeywords);
    }

    /**
     * 🆕 设置视频时长
     */
    public void setVideoDurationSeconds(int seconds) {
        this.videoDurationSeconds = seconds > 0 ? seconds : 60;
        logD("📝 设置视频时长: " + this.videoDurationSeconds + "秒");
    }

    /**
     * 启动自动化
     */
    public void startAutomation() {
        logD("🚀 启动自动化");
        isRunning = true;
        hasClickedScreenRecord = false;
        hasSelectedDouyin = false;

        // 🆕 步骤1: 打开侵权链接(通过WebView)
        if (infringementUrl != null && !infringementUrl.isEmpty()) {
            openInfringementUrl(infringementUrl);
            // ⚠️ 注意: 不要在这里立即打开权利卫士!
            // 需要等待WebView加载完成并点击"打开App"后,再打开权利卫士
            // 后续流程在onWebViewPageLoaded()中完成
        } else {
            logD("⚠️ 未设置侵权链接,跳过打开步骤");
            // 如果没有侵权链接,直接打开权利卫士
            clearClipboard();
            minimizeCurrentApp();
            delayedOpenApp();
        }
    }

    /**
     * 🧪 启动测试模式
     * 跳过权利卫士+录屏流程，直接: 打开抖音→我→观看历史→点击视频→作者主页→店铺账号→图片→放大截图
     */
    public void startTestMode() {
        logD("🧪 启动测试模式...");
        isTestMode = true;
        isRunning = true;

        new Thread(() -> {
            try {
                // Step1: 打开抖音
                logD("📱 测试模式 Step1: 打开抖音");
                switchToDouyin();

                // 等待抖音启动，轮询验证（最多10秒）
                logD("⏱️ 等待抖音到前台（最多10秒）...");
                boolean douyinInFront = false;
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    android.view.accessibility.AccessibilityNodeInfo root = getRootInActiveWindow();
                    if (root != null) {
                        // 判断当前包名是否为抖音
                        android.view.accessibility.AccessibilityWindowInfo win = null;
                        java.util.List<android.view.accessibility.AccessibilityWindowInfo> windows = getWindows();
                        for (android.view.accessibility.AccessibilityWindowInfo w : windows) {
                            if (w.isActive()) { win = w; break; }
                        }
                        // 通过根节点包名判断
                        String pkg = "";
                        try {
                            android.view.accessibility.AccessibilityNodeInfo focused = root.findFocus(
                                android.view.accessibility.AccessibilityNodeInfo.FOCUS_ACCESSIBILITY);
                            if (focused != null) pkg = focused.getPackageName() != null ? focused.getPackageName().toString() : "";
                        } catch (Exception ignored) {}

                        // 兜底：检查根节点子树有没有抖音的特征节点
                        if (root.getChildCount() > 0) {
                            CharSequence rootPkg = root.getPackageName();
                            if (rootPkg != null && rootPkg.toString().contains("aweme")) {
                                douyinInFront = true;
                                logD("✅ 第" + (i+1) + "秒检测到抖音已到前台");
                                break;
                            }
                        }
                        root.recycle();
                    }
                    logD("⏳ 第" + (i+1) + "秒：等待抖音...");
                }

                if (!douyinInFront) {
                    logE("❌ 10秒内抖音未到前台，测试模式终止");
                    isTestMode = false;
                    isRunning = false;
                    return;
                }

                // Step2: 点击"我"按钮
                // 注意：URL Scheme打开抖音后落在全屏视频信息流，底部导航栏默认隐藏
                // 需要先点击一次视频区域让底部导航栏显现，再点击"我"按钮
                logD("📱 测试模式 Step2: 唤出底部导航栏（点击视频上方区域）");
                clickByCoordinates(540, 800); // 点击视频上半部分，不触发暂停/播放区，只让导航栏显现
                Thread.sleep(1000);          // 等待导航栏动画出现

                logD("📱 测试模式 Step2: 点击'我'按钮");
                clickMeButton();

                // 等待"我"页面加载（3秒，确保页面内容完全渲染）
                Thread.sleep(3000);

                // Step3: 点击"观看历史"
                logD("📺 测试模式 Step3: 点击'观看历史'");
                clickViewHistory();
                // clickViewHistory内部会调用findAndClickVideoInHistory
                // findAndClickVideoInHistory在isTestMode=true时会跳过视频播放直接跳到navigateToAuthorProfile

            } catch (Exception e) {
                logE("测试模式启动失败: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 延迟打开应用
     */
    private void delayedOpenApp() {
        DelayThread thread = new DelayThread();
        thread.start();
    }

    /**
     * 延迟线程
     */
    private static class DelayThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                if (instance != null) {
                    instance.openTargetApp();
                }
            } catch (Exception e) {
                Log.e(TAG, "延迟打开应用失败: " + e.getMessage());
            }
        }
    }

    /**
     * 最小化当前应用(返回桌面)
     */
    private void minimizeCurrentApp() {
        try {
            Log.d(TAG, "最小化当前应用");
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
            Log.d(TAG, "已返回桌面");
        } catch (Exception e) {
            Log.e(TAG, "最小化失败: " + e.getMessage(), e);
        }
    }

    /**
     * 停止自动化
     */
    public void stopAutomation() {
        Log.d(TAG, "停止自动化");
        isRunning = false;
        isTestMode = false; // 重置测试模式标志
    }

    /**
     * 打开目标应用
     */
    private void openTargetApp() {
        try {
            Log.d(TAG, "尝试打开权利卫士应用: " + TARGET_PACKAGE);

            // 使用显式Intent启动
            Intent intent = new Intent();
            intent.setClassName(TARGET_PACKAGE, TARGET_ACTIVITY);
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
            Log.d(TAG, "成功启动权利卫士应用");

        } catch (Exception e) {
            Log.e(TAG, "启动权利卫士失败: " + e.getMessage(), e);
        }
    }

    /**
     * 检查是否正在运行
     */
    public boolean isAutomationRunning() {
        return isRunning;
    }

    /**
     * 处理窗口状态变化
     */
    private void handleWindowStateChanged(AccessibilityEvent event) {
        String className = event.getClassName() != null ? event.getClassName().toString() : "";
        Log.d(TAG, "窗口切换: " + className);

        // 检测到主界面,尝试点击录屏取证按钮
        if (!hasClickedScreenRecord) {
            ClickScreenRecordThread thread = new ClickScreenRecordThread();
            thread.start();
        }

        // 检测到录屏界面,填充备注并点击开始录屏
        if (className.equals("cn.tsa.rights.viewer.screen.ScreenRecorderActivity")) {
            Log.d(TAG, "检测到录屏界面");
            FillRemarkAndStartThread thread = new FillRemarkAndStartThread();
            thread.start();
        }
    }

    /**
     * 处理窗口内容变化
     */
    private void handleWindowContentChanged() {
        // 检测到主界面,尝试点击录屏取证按钮
        if (!hasClickedScreenRecord) {
            ClickScreenRecordThread thread = new ClickScreenRecordThread();
            thread.start();
        }

        // 检测"取证环境检测结果"页面,点击"开始取证"按钮
        handleEnvironmentCheckResult();
    }

    /**
     * 点击录屏取证按钮的线程
     */
    private static class ClickScreenRecordThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000); // 等待界面加载
                if (instance != null && !instance.hasClickedScreenRecord) {
                    instance.clickScreenRecordButton();
                }
            } catch (Exception e) {
                Log.e(TAG, "点击录屏取证失败: " + e.getMessage());
            }
        }
    }

    /**
     * 点击录屏取证按钮
     */
    private void clickScreenRecordButton() {
        try {
            Log.d(TAG, "尝试点击录屏取证按钮");

            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                Log.e(TAG, "无法获取根节点");
                return;
            }

            // 通过Resource ID查找按钮
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> nodes =
                rootNode.findAccessibilityNodeInfosByViewId(SCREEN_RECORD_BUTTON_ID);

            if (nodes != null && !nodes.isEmpty()) {
                android.view.accessibility.AccessibilityNodeInfo buttonNode = nodes.get(0);

                // 执行点击
                boolean clicked = buttonNode.performAction(
                    android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                );

                if (clicked) {
                    Log.d(TAG, "成功点击录屏取证按钮");
                    hasClickedScreenRecord = true;
                } else {
                    Log.e(TAG, "点击录屏取证按钮失败");
                }

                // 释放资源
                buttonNode.recycle();
            } else {
                Log.e(TAG, "未找到录屏取证按钮");
            }

            rootNode.recycle();

        } catch (Exception e) {
            Log.e(TAG, "点击录屏取证按钮异常: " + e.getMessage(), e);
        }
    }

    /**
     * 填充备注并点击开始录屏的线程
     */
    private static class FillRemarkAndStartThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000); // 等待界面加载
                if (instance != null) {
                    instance.fillRemarkAndStart();

                    // 等待一下,检查是否有"知道了"弹窗
                    Thread.sleep(1000);
                    instance.clickConfirmDialogIfExists();
                }
            } catch (Exception e) {
                Log.e(TAG, "填充备注并开始录屏失败: " + e.getMessage());
            }
        }
    }

    /**
     * 填充备注并点击开始录屏
     */
    private void fillRemarkAndStart() {
        try {
            Log.d(TAG, "开始填充备注并点击开始录屏");

            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                Log.e(TAG, "无法获取根节点");
                return;
            }

            // 1. 填充备注
            if (remark != null && !remark.isEmpty()) {
                java.util.List<android.view.accessibility.AccessibilityNodeInfo> remarkNodes =
                    rootNode.findAccessibilityNodeInfosByViewId(REMARK_INPUT_ID);

                if (remarkNodes != null && !remarkNodes.isEmpty()) {
                    android.view.accessibility.AccessibilityNodeInfo remarkNode = remarkNodes.get(0);

                    // 设置焦点
                    remarkNode.performAction(
                        android.view.accessibility.AccessibilityNodeInfo.ACTION_FOCUS
                    );

                    // 填充文本
                    android.os.Bundle arguments = new android.os.Bundle();
                    arguments.putCharSequence(
                        android.view.accessibility.AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                        remark
                    );
                    boolean filled = remarkNode.performAction(
                        android.view.accessibility.AccessibilityNodeInfo.ACTION_SET_TEXT,
                        arguments
                    );

                    if (filled) {
                        Log.d(TAG, "成功填充备注: " + remark);
                    } else {
                        Log.e(TAG, "填充备注失败");
                    }

                    remarkNode.recycle();
                } else {
                    Log.e(TAG, "未找到备注输入框");
                }
            }

            // 等待一下,确保备注填充完成
            Thread.sleep(500);

            // 2. 点击开始录屏按钮
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> startNodes =
                rootNode.findAccessibilityNodeInfosByViewId(START_BUTTON_ID);

            if (startNodes != null && !startNodes.isEmpty()) {
                android.view.accessibility.AccessibilityNodeInfo startNode = startNodes.get(0);

                // 执行点击
                boolean clicked = startNode.performAction(
                    android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                );

                if (clicked) {
                    Log.d(TAG, "成功点击开始录屏按钮");
                } else {
                    Log.e(TAG, "点击开始录屏按钮失败");
                }

                startNode.recycle();
            } else {
                Log.e(TAG, "未找到开始录屏按钮");
            }

            rootNode.recycle();

        } catch (Exception e) {
            Log.e(TAG, "填充备注并开始录屏异常: " + e.getMessage(), e);
        }
    }

    /**
     * 点击"知道了"确认按钮(如果存在)
     */
    private void clickConfirmDialogIfExists() {
        try {
            Log.d(TAG, "检查是否有确认弹窗");

            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                Log.d(TAG, "无法获取根节点");
                return;
            }

            // 查找"知道了"按钮
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> confirmNodes =
                rootNode.findAccessibilityNodeInfosByViewId(CONFIRM_BUTTON_ID);

            if (confirmNodes != null && !confirmNodes.isEmpty()) {
                android.view.accessibility.AccessibilityNodeInfo confirmNode = confirmNodes.get(0);

                // 执行点击
                boolean clicked = confirmNode.performAction(
                    android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                );

                if (clicked) {
                    Log.d(TAG, "成功点击'知道了'按钮");
                } else {
                    Log.e(TAG, "点击'知道了'按钮失败");
                }

                confirmNode.recycle();
            } else {
                Log.d(TAG, "未找到'知道了'按钮,可能弹窗未出现");
            }

            rootNode.recycle();

        } catch (Exception e) {
            Log.e(TAG, "点击确认按钮异常: " + e.getMessage(), e);
        }
    }



    /**
     * 处理系统录屏权限弹窗
     */
    private void handleSystemScreenShareDialog() {
        try {
            java.util.List<android.view.accessibility.AccessibilityWindowInfo> windows = getWindows();

            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                logD("⚠️ 无法获取当前活动窗口的根节点");
                return;
            }

            // 优先使用文字查找"立即开始"按钮
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> startButtonNodes =
                rootNode.findAccessibilityNodeInfosByText("立即开始");

            if (startButtonNodes != null && !startButtonNodes.isEmpty()) {
                for (android.view.accessibility.AccessibilityNodeInfo node : startButtonNodes) {
                    // 检查是否是可点击的按钮
                    if (node.isClickable() || node.getClassName().toString().contains("Button")) {
                        logD("✅ 找到'立即开始'按钮,准备点击");
                        boolean clicked = node.performAction(
                            android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                        );

                        if (clicked) {
                            logD("🎉 成功点击'立即开始'按钮,录屏即将开始!");
                        } else {
                            logE("❌ 点击'立即开始'按钮失败");
                        }

                        node.recycle();
                        rootNode.recycle();
                        return;
                    }
                    node.recycle();
                }
            }

            // 如果没有找到"立即开始"按钮,检查是否在截图权限对话框
            // 只有在对话框中才输出日志,避免日志污染
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> wholeScreenNodes =
                rootNode.findAccessibilityNodeInfosByText("整个屏幕");

            if (wholeScreenNodes != null && !wholeScreenNodes.isEmpty()) {
                // 在截图权限对话框中
                logD("✅ 检测到截图权限对话框,已经是'整个屏幕'模式");
                scanSystemDialogButtons(rootNode);
                findAndClickStartButton(rootNode);
            }

            // 如果没有找到"立即开始"按钮,查找"单个应用"下拉框
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> spinnerNodes =
                rootNode.findAccessibilityNodeInfosByViewId(SCREEN_SHARE_MODE_SPINNER_ID);

            if (spinnerNodes != null && !spinnerNodes.isEmpty()) {
                android.view.accessibility.AccessibilityNodeInfo spinnerNode = spinnerNodes.get(0);
                CharSequence text = spinnerNode.getText();

                // 如果当前是"单个应用",点击打开下拉菜单
                if (text != null && text.toString().contains("单个应用")) {
                    logD("找到'单个应用'下拉框,准备点击");
                    boolean clicked = spinnerNode.performAction(
                        android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                    );

                    if (clicked) {
                        logD("✅ 成功点击'单个应用'下拉框,等待下拉菜单展开...");
                        // 等待下拉菜单完全展开,然后点击"整个屏幕"
                        new Thread(() -> {
                            try {
                                Thread.sleep(1500); // 增加等待时间到1.5秒
                                clickWholeScreenOption();
                            } catch (Exception e) {
                                logE("等待下拉菜单异常: " + e.getMessage());
                            }
                        }).start();

                        // 重要: 点击成功后立即返回,不要继续执行后面的代码
                        rootNode.recycle();
                        return;
                    }
                }

                spinnerNode.recycle();
            }

            rootNode.recycle();

        } catch (Exception e) {
            Log.e(TAG, "处理系统录屏权限弹窗异常: " + e.getMessage(), e);
        }
    }

    /**
     * 点击"整个屏幕"选项
     */
    private void clickWholeScreenOption() {
        try {
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                Log.d(TAG, "无法获取根节点");
                return;
            }

            // 查找包含"整个屏幕"文字的节点
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> nodes =
                rootNode.findAccessibilityNodeInfosByText("整个屏幕");

            if (nodes != null && !nodes.isEmpty()) {
                for (android.view.accessibility.AccessibilityNodeInfo node : nodes) {
                    logD("找到'整个屏幕'文本节点");

                    // 如果节点本身可点击,直接点击
                    if (node.isClickable()) {
                        logD("'整个屏幕'节点可点击,准备点击");
                        boolean clicked = node.performAction(
                            android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                        );

                        if (clicked) {
                            logD("✅ 成功点击'整个屏幕'选项,等待界面更新...");
                            // 点击成功后,等待更长时间让界面更新
                            scheduleCheckStartButton();
                        } else {
                            logE("❌ 点击'整个屏幕'失败");
                        }

                        node.recycle();
                        break;
                    }

                    // 如果节点不可点击,尝试点击父节点
                    android.view.accessibility.AccessibilityNodeInfo parent = node.getParent();
                    if (parent != null) {
                        Log.d(TAG, "尝试点击'整个屏幕'的父节点");

                        // 尝试点击父节点
                        if (parent.isClickable()) {
                            Log.d(TAG, "父节点可点击,准备点击");
                            boolean clicked = parent.performAction(
                                android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                            );

                            if (clicked) {
                                Log.d(TAG, "成功点击'整个屏幕'父节点,等待按钮变为'立即开始'");
                                // 点击成功后,主动触发检测
                                scheduleCheckStartButton();
                            }

                            parent.recycle();
                            node.recycle();
                            break;
                        }

                        // 如果父节点也不可点击,尝试祖父节点
                        android.view.accessibility.AccessibilityNodeInfo grandParent = parent.getParent();
                        if (grandParent != null && grandParent.isClickable()) {
                            Log.d(TAG, "祖父节点可点击,准备点击");
                            boolean clicked = grandParent.performAction(
                                android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                            );

                            if (clicked) {
                                Log.d(TAG, "成功点击'整个屏幕'祖父节点,等待按钮变为'立即开始'");
                                // 点击成功后,主动触发检测
                                scheduleCheckStartButton();
                            }

                            grandParent.recycle();
                        }

                        if (grandParent != null) {
                            grandParent.recycle();
                        }
                        parent.recycle();
                    }

                    node.recycle();
                    break;
                }
            } else {
                Log.d(TAG, "未找到'整个屏幕'选项");
            }

            rootNode.recycle();

        } catch (Exception e) {
            Log.e(TAG, "点击'整个屏幕'选项异常: " + e.getMessage(), e);
        }
    }

    /**
     * 延迟检测"立即开始"按钮
     */
    private void scheduleCheckStartButton() {
        new Thread(() -> {
            try {
                // 等待界面更新
                logD("⏳ 等待1秒,让界面完全更新...");
                Thread.sleep(1000);

                // 尝试3次,每次间隔更长
                for (int i = 0; i < 3; i++) {
                    logD("🔍 第" + (i + 1) + "次检测'立即开始'按钮");

                    // 获取根节点并扫描按钮
                    android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
                    if (rootNode != null) {
                        logD("📋 扫描当前界面的按钮:");
                        scanSystemDialogButtons(rootNode);

                        // 查找并点击"立即开始"按钮
                        findAndClickStartButton(rootNode);

                        rootNode.recycle();
                    } else {
                        logE("❌ 无法获取根节点");
                    }

                    // 等待更长时间再重试
                    if (i < 2) {
                        Thread.sleep(800);
                    }
                }

                logD("✅ 完成3次检测尝试");

            } catch (Exception e) {
                logE("延迟检测'立即开始'按钮异常: " + e.getMessage());
            }
        }).start();
    }

    /**
     * 查找并点击开始按钮 (通过类型查找)
     */
    private void findAndClickStartButton(android.view.accessibility.AccessibilityNodeInfo node) {
        if (node == null) {
            logD("⚠️ findAndClickStartButton: node为null");
            return;
        }

        // 如果已经选择了抖音,不再自动点击"立即验证"按钮
        if (hasSelectedDouyin) {
            return;
        }

        try {
            String className = node.getClassName() != null ? node.getClassName().toString() : "";

            // 如果是Button类型且可点击
            if (className.contains("Button") && node.isClickable()) {
                CharSequence text = node.getText();
                CharSequence desc = node.getContentDescription();
                String viewId = node.getViewIdResourceName();

                String textStr = text != null ? text.toString() : "";
                String descStr = desc != null ? desc.toString() : "";

                // 策略1: 直接点击ID为button1的按钮(系统对话框的确认按钮)
                if (viewId != null && viewId.endsWith("button1")) {
                    logD(String.format("🎯 找到系统对话框确认按钮: 文本='%s' 描述='%s' ID='%s'",
                        textStr.isEmpty() ? "(无)" : textStr,
                        descStr.isEmpty() ? "(无)" : descStr,
                        viewId));

                    boolean clicked = node.performAction(
                        android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                    );

                    if (clicked) {
                        logD("🎉 成功点击确认按钮,录屏即将开始!");
                        return;
                    } else {
                        logE("❌ 点击确认按钮失败");
                    }
                }

                // 策略2: 如果文本或描述包含"开始"、"Start"
                if (textStr.contains("开始") || textStr.contains("Start") ||
                    descStr.contains("开始") || descStr.contains("Start")) {

                    logD(String.format("🎯 找到'开始'按钮: 文本='%s' 描述='%s' ID='%s'",
                        textStr.isEmpty() ? "(无)" : textStr,
                        descStr.isEmpty() ? "(无)" : descStr,
                        viewId != null ? viewId : "(无)"));

                    boolean clicked = node.performAction(
                        android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                    );

                    if (clicked) {
                        logD("🎉 成功点击'立即开始'按钮,录屏即将开始!");
                        return;
                    }
                }

                // 策略3: 如果ID包含"start"、"confirm"、"ok"、"positive"等关键词
                if (viewId != null) {
                    String lowerViewId = viewId.toLowerCase();
                    if (lowerViewId.contains("start") || lowerViewId.contains("confirm") ||
                        lowerViewId.contains("ok") || lowerViewId.contains("positive")) {

                        logD(String.format("🎯 找到可能的开始按钮(通过ID): ID='%s'", viewId));

                        boolean clicked = node.performAction(
                            android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                        );

                        if (clicked) {
                            logD("✅ 成功点击按钮(通过ID匹配)!");
                            return;
                        }
                    }
                }
            }

            // 递归查找子节点
            int childCount = node.getChildCount();
            for (int i = 0; i < childCount; i++) {
                android.view.accessibility.AccessibilityNodeInfo child = node.getChild(i);
                if (child != null) {
                    findAndClickStartButton(child);
                    child.recycle();
                }
            }
        } catch (Exception e) {
            logE("查找Button异常: " + e.getMessage());
        }
    }

    /**
     * 扫描系统录屏对话框中的按钮 (调试用,只扫描Button类型)
     */
    private void scanSystemDialogButtons(android.view.accessibility.AccessibilityNodeInfo node) {
        if (node == null) return;

        try {
            String className = node.getClassName() != null ? node.getClassName().toString() : "";

            // 只输出Button、Spinner等关键控件
            if (className.contains("Button") || className.contains("Spinner")) {
                CharSequence text = node.getText();
                CharSequence contentDesc = node.getContentDescription();
                String viewId = node.getViewIdResourceName();
                boolean isClickable = node.isClickable();

                String textStr = text != null ? text.toString() : "(无)";
                String descStr = contentDesc != null ? contentDesc.toString() : "(无)";
                String idStr = viewId != null ? viewId.substring(viewId.lastIndexOf('/') + 1) : "(无)";

                logD(String.format("  🔘 [%s] 文本='%s' 描述='%s' ID='%s'",
                    className.substring(className.lastIndexOf('.') + 1),
                    textStr,
                    descStr,
                    idStr));
            }

            // 递归扫描子节点
            int childCount = node.getChildCount();
            for (int i = 0; i < childCount; i++) {
                android.view.accessibility.AccessibilityNodeInfo child = node.getChild(i);
                if (child != null) {
                    scanSystemDialogButtons(child);
                    child.recycle();
                }
            }
        } catch (Exception e) {
            // 忽略异常,继续扫描
        }
    }

    /**
     * 添加日志
     */
    private static void addLog(String level, String message) {
        String timestamp = dateFormat.format(new Date());
        String logLine = String.format("[%s] [%s] %s\n", timestamp, level, message);

        synchronized (logBuilder) {
            logBuilder.append(logLine);

            // 如果日志太长,删除前面的部分
            if (logBuilder.length() > MAX_LOG_LENGTH) {
                logBuilder.delete(0, logBuilder.length() - MAX_LOG_LENGTH);
            }
        }

        // 🆕 同时将日志写入文件,防止APP被杀掉后日志丢失
        saveLogToFile(logLine);
    }

    /**
     * 将日志保存到文件
     */
    private static void saveLogToFile(String logLine) {
        try {
            // 获取外部存储目录
            java.io.File logDir = new java.io.File(
                android.os.Environment.getExternalStorageDirectory(),
                "权利卫士取证/logs"
            );

            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            // 日志文件名:按日期命名
            String dateStr = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(new java.util.Date());
            java.io.File logFile = new java.io.File(logDir, "automation_" + dateStr + ".log");

            // 追加写入日志
            java.io.FileWriter writer = new java.io.FileWriter(logFile, true);
            writer.write(logLine);
            writer.close();

        } catch (Exception e) {
            // 忽略日志保存失败的异常,避免影响主流程
            Log.e(TAG, "保存日志到文件失败: " + e.getMessage());
        }
    }

    /**
     * 记录调试日志
     */
    private static void logD(String message) {
        Log.d(TAG, message);
        addLog("DEBUG", message);
    }

    /**
     * 记录错误日志
     */
    private static void logE(String message) {
        Log.e(TAG, message);
        addLog("ERROR", message);
    }

    /**
     * 获取所有日志
     */
    public static String getLogs() {
        synchronized (logBuilder) {
            String logs = logBuilder.toString();
            return logs != null ? logs : "";
        }
    }

    /**
     * 清空日志
     */
    public static void clearLogs() {
        synchronized (logBuilder) {
            logBuilder.setLength(0);
        }
    }

    /**
     * 处理"取证环境检测结果"页面
     */
    private void handleEnvironmentCheckResult() {
        try {
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                return;
            }

            // 查找"取证环境检测结果"文本,确认是环境检测结果页面
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> titleNodes =
                rootNode.findAccessibilityNodeInfosByText("取证环境检测结果");

            if (titleNodes == null || titleNodes.isEmpty()) {
                rootNode.recycle();
                return;
            }

            logD("🎯 检测到'取证环境检测结果'页面");

            // 查找"开始取证"按钮 (ID: confirm_button_two)
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> buttonNodes =
                rootNode.findAccessibilityNodeInfosByViewId(CONFIRM_BUTTON_TWO_ID);

            if (buttonNodes != null && !buttonNodes.isEmpty()) {
                android.view.accessibility.AccessibilityNodeInfo button = buttonNodes.get(0);

                logD("✅ 找到'开始取证'按钮,准备点击...");

                // 等待一下再点击
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                boolean clicked = button.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);

                if (clicked) {
                    logD("✅ 成功点击'开始取证'按钮");
                    // 🆕 设置权利卫士取证阶段标志位,并重置所有抖音自动化标志位
                    isRightsGuardEvidencePhase = true;
                    hasStartedDouyinAutomation = false;  // 重置:允许再次触发抖音自动化
                    hasClickedDouyinMe = false;          // 重置:允许再次点击"我"
                    hasClickedDouyinMore = false;        // 重置:允许再次点击"更多"
                    hasClickedDouyinSettings = false;    // 重置:允许再次点击"设置"
                    hasScrolledToAboutSection = false;   // 重置:允许再次滑动
                    logD("🎯 进入权利卫士取证阶段,将监听抖音事件");
                } else {
                    logE("❌ 点击'开始取证'按钮失败");
                }

                button.recycle();
            } else {
                logE("❌ 未找到'开始取证'按钮 (ID: confirm_button_two)");
            }

            rootNode.recycle();

        } catch (Exception e) {
            logE("处理'取证环境检测结果'页面失败: " + e.getMessage());
        }
    }

    /**
     * 处理应用验真对话框
     */
    private void handleAppVerificationDialog() {
        if (hasSelectedDouyin) {
            return; // 已经处理过了
        }

        try {
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                return;
            }

            // 查找"应用验真"文本,确认是应用验真界面
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> titleNodes =
                rootNode.findAccessibilityNodeInfosByText("应用验真");

            if (titleNodes == null || titleNodes.isEmpty()) {
                rootNode.recycle();
                return;
            }

            logD("🎯 检测到应用验真界面");

            // 最简单的方案: 直接查找ID为rl_douyin的容器并点击
            android.view.accessibility.AccessibilityNodeInfo douyinIcon = null;

            java.util.List<android.view.accessibility.AccessibilityNodeInfo> douyinContainerNodes =
                rootNode.findAccessibilityNodeInfosByViewId("com.unitrust.tsa:id/rl_douyin");

            if (douyinContainerNodes != null && !douyinContainerNodes.isEmpty()) {
                douyinIcon = douyinContainerNodes.get(0);
                logD("✅ 找到抖音容器(ID: rl_douyin)");
            } else {
                logE("未找到抖音容器(ID: rl_douyin)");
            }

            if (douyinIcon != null) {

                // 直接点击容器
                logD("🔧 点击抖音容器");

                boolean clicked = douyinIcon.performAction(
                    android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                );

                logD("  点击结果: " + (clicked ? "成功" : "失败"));

                // 等待一下,让界面更新
                try { Thread.sleep(500); } catch (Exception e) {}

                if (clicked) {
                    logD("✅ 成功点击抖音容器");
                    hasSelectedDouyin = true;

                    // 随机延迟1-3秒,然后点击"立即验证"
                    new Thread(() -> {
                        try {
                            // 生成1000-3000ms的随机延迟
                            int randomDelay = 1000 + new java.util.Random().nextInt(2000);
                            logD("⏳ 等待 " + (randomDelay / 1000.0) + " 秒后点击'立即验证'...");
                            Thread.sleep(randomDelay);
                            clickVerifyButton();
                        } catch (Exception e) {
                            logE("点击立即验证失败: " + e.getMessage());
                        }
                    }).start();
                } else {
                    logE("❌ 点击抖音容器失败");
                }

                douyinIcon.recycle();
            } else {
                logE("未找到抖音容器");
            }

            rootNode.recycle();

        } catch (Exception e) {
            logE("处理应用验真对话框异常: " + e.getMessage());
        }
    }

    /**
     * 在节点中查找抖音的ImageButton
     */
    private android.view.accessibility.AccessibilityNodeInfo findDouyinImageButton(
        android.view.accessibility.AccessibilityNodeInfo node) {

        if (node == null) {
            return null;
        }

        try {
            String className = node.getClassName() != null ? node.getClassName().toString() : "";
            String viewId = node.getViewIdResourceName();

            // 只查找ID包含"douyin"的ImageButton(但不是btn_douyin这个圆圈)
            if ((className.contains("ImageView") || className.contains("ImageButton")) &&
                viewId != null && viewId.toLowerCase().contains("douyin")) {

                logD("  找到抖音图标: ClassName=" + className + ", ViewID=" + viewId);
                return node;
            }

            // 递归查找子节点
            int childCount = node.getChildCount();
            for (int i = 0; i < childCount; i++) {
                android.view.accessibility.AccessibilityNodeInfo child = node.getChild(i);
                if (child != null) {
                    android.view.accessibility.AccessibilityNodeInfo result = findDouyinImageButton(child);
                    if (result != null) {
                        return result;
                    }
                }
            }
        } catch (Exception e) {
            logE("查找抖音ImageButton异常: " + e.getMessage());
        }

        return null;
    }

    /**
     * 点击"立即验证"按钮
     */
    private void clickVerifyButton() {
        try {
            // 先截屏保存应用验真页面
            logD("📸 准备截屏保存应用验真页面...");

            // 使用CountDownLatch等待截屏完成
            final java.util.concurrent.CountDownLatch latch = new java.util.concurrent.CountDownLatch(1);
            final boolean[] screenshotSuccess = {false};

            takeScreenshotBeforeVerify(new ScreenshotCallback() {
                @Override
                public void onSuccess() {
                    screenshotSuccess[0] = true;
                    latch.countDown();
                }

                @Override
                public void onFailure() {
                    screenshotSuccess[0] = false;
                    latch.countDown();
                }
            });

            // 等待截屏完成,最多等待3秒
            try {
                boolean completed = latch.await(3, java.util.concurrent.TimeUnit.SECONDS);
                if (completed) {
                    if (screenshotSuccess[0]) {
                        logD("✅ 截屏完成,准备点击'立即验证'");
                    } else {
                        logE("⚠️ 截屏失败,继续点击'立即验证'");
                    }
                } else {
                    logE("⚠️ 截屏超时,继续点击'立即验证'");
                }
            } catch (InterruptedException e) {
                logE("等待截屏被中断: " + e.getMessage());
            }

            // 再等待500ms确保界面稳定
            Thread.sleep(500);

            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                logE("无法获取根节点");
                return;
            }

            // 直接通过ID查找"立即验证"按钮
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> buttonNodes =
                rootNode.findAccessibilityNodeInfosByViewId("com.unitrust.tsa:id/confirm_button");

            if (buttonNodes != null && !buttonNodes.isEmpty()) {
                logD("🎯 找到'立即验证'按钮,准备点击...");

                android.view.accessibility.AccessibilityNodeInfo button = buttonNodes.get(0);

                boolean clicked = button.performAction(
                    android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                );

                if (clicked) {
                    logD("🎉 成功点击'立即验证'按钮!");
                } else {
                    logE("❌ 点击'立即验证'失败");
                }

                button.recycle();
            } else {
                logE("未找到'立即验证'按钮(ID: confirm_button)");
            }

            rootNode.recycle();

        } catch (Exception e) {
            logE("点击立即验证按钮异常: " + e.getMessage());
        }
    }

    /**
     * 截屏回调接口
     */
    private interface ScreenshotCallback {
        default void onSuccess() {}
        default void onSuccess(android.graphics.Bitmap bitmap) {
            onSuccess();
        }
        void onFailure();
    }

    /**
     * 截屏保存应用验真页面
     */
    private void takeScreenshotBeforeVerify(final ScreenshotCallback callback) {
        takeScreenshotWithPrefix("应用验真", callback);
    }

    /**
     * 截屏(不保存,只返回Bitmap)
     * 用于OCR识别等场景
     */
    private void takeScreenshot(final ScreenshotCallback callback) {
        takeScreenshotWithPrefix(null, callback);
    }

    /**
     * 截屏保存页面(支持自定义前缀)
     */
    private void takeScreenshotWithPrefix(final String prefix, final ScreenshotCallback callback) {
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                // Android 11 (API 30) 及以上使用新的截屏API
                logD("📱 当前Android版本: " + android.os.Build.VERSION.SDK_INT + " (支持截屏API)");
                logD("🔧 开始执行截屏...");

                takeScreenshot(
                    android.view.Display.DEFAULT_DISPLAY,
                    getMainExecutor(),
                    new android.accessibilityservice.AccessibilityService.TakeScreenshotCallback() {
                        @Override
                        public void onSuccess(android.accessibilityservice.AccessibilityService.ScreenshotResult screenshotResult) {
                            try {
                                logD("✅ 截屏API调用成功!");

                                // 获取截图的Bitmap
                                android.graphics.Bitmap bitmap = android.graphics.Bitmap.wrapHardwareBuffer(
                                    screenshotResult.getHardwareBuffer(),
                                    screenshotResult.getColorSpace()
                                );

                                if (bitmap != null) {
                                    logD("📐 截图尺寸: " + bitmap.getWidth() + "x" + bitmap.getHeight());

                                    // 转换为可变的Bitmap,供后续使用 (修复ML Kit错误 + 避免Hardware Bitmap被回收)
                                    android.graphics.Bitmap mutableBitmap = bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, true);

                                    // 回收原始的Hardware Bitmap
                                    bitmap.recycle();

                                    // 如果需要保存,则保存截图
                                    if (prefix != null && !prefix.isEmpty()) {
                                        saveScreenshotWithPrefix(mutableBitmap, prefix);
                                    }

                                    // 传递可变的Bitmap给回调 (由回调负责回收)
                                    if (callback != null) {
                                        callback.onSuccess(mutableBitmap);
                                    }
                                } else {
                                    logE("❌ 获取Bitmap失败");
                                    if (callback != null) {
                                        callback.onFailure();
                                    }
                                }

                                screenshotResult.getHardwareBuffer().close();

                            } catch (Exception e) {
                                logE("❌ 保存截图失败: " + e.getMessage());
                                e.printStackTrace();
                                if (callback != null) {
                                    callback.onFailure();
                                }
                            }
                        }

                        @Override
                        public void onFailure(int errorCode) {
                            logE("❌ 截屏API失败,错误码: " + errorCode);
                            if (callback != null) {
                                callback.onFailure();
                            }
                        }
                    }
                );
            } else {
                logE("⚠️ 当前Android版本: " + android.os.Build.VERSION.SDK_INT + " (不支持截屏API,需要Android 11+ / API 30+)");
                if (callback != null) {
                    callback.onFailure();
                }
            }

        } catch (Exception e) {
            logE("❌ 截屏异常: " + e.getMessage());
            e.printStackTrace();
            if (callback != null) {
                callback.onFailure();
            }
        }
    }

    /**
     * 保存截图到文件
     */
    private void saveScreenshot(android.graphics.Bitmap bitmap) {
        saveScreenshotWithPrefix(bitmap, "应用验真");
    }

    /**
     * 保存截图到文件(支持自定义前缀)
     */
    private void saveScreenshotWithPrefix(android.graphics.Bitmap bitmap, String prefix) {
        try {
            // 使用前缀和备注作为文件名的一部分
            String fileName = prefix + "_" + remark.replace(":", "_") + "_" +
                            new java.text.SimpleDateFormat("yyyyMMdd_HHmmss", java.util.Locale.CHINA)
                                .format(new java.util.Date()) + ".png";

            // Android 10+ 使用MediaStore保存到公共相册
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                logD("📱 使用MediaStore保存到公共相册 (Android 10+)");

                android.content.ContentValues values = new android.content.ContentValues();
                values.put(android.provider.MediaStore.Images.Media.DISPLAY_NAME, fileName);
                values.put(android.provider.MediaStore.Images.Media.MIME_TYPE, "image/png");
                values.put(android.provider.MediaStore.Images.Media.RELATIVE_PATH,
                          android.os.Environment.DIRECTORY_PICTURES + "/权利卫士取证");

                android.content.ContentResolver resolver = getContentResolver();
                android.net.Uri imageUri = resolver.insert(
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    values
                );

                if (imageUri != null) {
                    java.io.OutputStream os = resolver.openOutputStream(imageUri);
                    if (os != null) {
                        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, os);
                        os.flush();
                        os.close();

                        logD("✅ 截图已保存到相册: Pictures/权利卫士取证/" + fileName);
                        logD("📂 可以在相册中查看");
                    }
                } else {
                    logE("❌ 创建MediaStore URI失败");
                }

            } else {
                // Android 9及以下,保存到公共Pictures目录
                logD("📱 保存到公共Pictures目录 (Android 9及以下)");

                java.io.File picturesDir = new java.io.File(
                    android.os.Environment.getExternalStoragePublicDirectory(
                        android.os.Environment.DIRECTORY_PICTURES
                    ),
                    "权利卫士取证"
                );

                if (!picturesDir.exists()) {
                    picturesDir.mkdirs();
                }

                java.io.File file = new java.io.File(picturesDir, fileName);

                java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
                bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();

                // 通知系统扫描新文件,让相册能看到
                android.content.Intent mediaScanIntent = new android.content.Intent(
                    android.content.Intent.ACTION_MEDIA_SCANNER_SCAN_FILE
                );
                mediaScanIntent.setData(android.net.Uri.fromFile(file));
                sendBroadcast(mediaScanIntent);

                logD("✅ 截图已保存: " + file.getAbsolutePath());
                logD("📂 可以在相册中查看");
            }

        } catch (Exception e) {
            logE("❌ 保存截图文件失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Dump当前UI结构
     * ⚠️ 此功能仅用于开发调试,正式发布版本将移除!
     */
    public void dumpCurrentUI() {
        try {
            logD("🔍 开始Dump UI结构...");

            // 构建dump文本
            StringBuilder sb = new StringBuilder();
            sb.append("=== UI结构 Dump (所有窗口) ===\n");
            sb.append("时间: ").append(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                                      java.util.Locale.CHINA).format(new java.util.Date())).append("\n\n");

            // 🆕 获取所有窗口
            java.util.List<android.view.accessibility.AccessibilityWindowInfo> windows = getWindows();
            if (windows != null && !windows.isEmpty()) {
                logD("📱 找到 " + windows.size() + " 个窗口");
                sb.append("窗口总数: ").append(windows.size()).append("\n\n");

                // 遍历每个窗口
                for (int i = 0; i < windows.size(); i++) {
                    android.view.accessibility.AccessibilityWindowInfo window = windows.get(i);

                    sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
                    sb.append("🪟 窗口 #").append(i + 1).append("\n");
                    sb.append("类型: ").append(getWindowTypeName(window.getType())).append("\n");
                    sb.append("层级: ").append(window.getLayer()).append("\n");
                    sb.append("活动: ").append(window.isActive() ? "是" : "否").append("\n");
                    sb.append("聚焦: ").append(window.isFocused() ? "是" : "否").append("\n");
                    sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");

                    // 获取窗口的根节点
                    android.view.accessibility.AccessibilityNodeInfo rootNode = window.getRoot();
                    if (rootNode != null) {
                        sb.append("包名: ").append(rootNode.getPackageName()).append("\n\n");

                        // 递归遍历UI树
                        dumpNode(rootNode, sb, 0);

                        // 释放资源
                        rootNode.recycle();
                    } else {
                        sb.append("⚠️ 无法获取此窗口的根节点\n\n");
                    }
                }
            } else {
                // 如果getWindows()失败,回退到原来的方法
                logD("⚠️ 无法获取窗口列表,使用getRootInActiveWindow()");
                android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
                if (rootNode == null) {
                    logE("❌ 无法获取UI结构: rootNode为null");
                    return;
                }

                sb.append("包名: ").append(rootNode.getPackageName()).append("\n\n");
                dumpNode(rootNode, sb, 0);
                rootNode.recycle();
            }

            // 显示dump结果
            showDumpResult(sb.toString());

            logD("✅ Dump完成");

        } catch (Exception e) {
            logE("❌ Dump UI结构失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取窗口类型名称
     */
    private String getWindowTypeName(int type) {
        switch (type) {
            case android.view.accessibility.AccessibilityWindowInfo.TYPE_APPLICATION:
                return "应用窗口";
            case android.view.accessibility.AccessibilityWindowInfo.TYPE_INPUT_METHOD:
                return "输入法窗口";
            case android.view.accessibility.AccessibilityWindowInfo.TYPE_SYSTEM:
                return "系统窗口";
            case android.view.accessibility.AccessibilityWindowInfo.TYPE_ACCESSIBILITY_OVERLAY:
                return "无障碍覆盖层";
            default:
                return "未知类型(" + type + ")";
        }
    }

    /**
     * 递归遍历节点
     */
    private void dumpNode(android.view.accessibility.AccessibilityNodeInfo node,
                         StringBuilder sb, int depth) {
        if (node == null) return;

        try {
            // 缩进
            for (int i = 0; i < depth; i++) {
                sb.append(i == depth - 1 ? "├─ " : "│   ");
            }

            // 节点类名
            CharSequence className = node.getClassName();
            sb.append("**[").append(className != null ? className : "Unknown").append("]**");

            // 状态标记
            sb.append(" `");
            if (node.isClickable()) sb.append("clickable ");
            if (node.isEnabled()) sb.append("enabled ");
            if (node.isVisibleToUser()) sb.append("visible ");
            if (node.isFocusable()) sb.append("focusable ");
            sb.append("`\n");

            // Resource ID
            String viewId = node.getViewIdResourceName();
            if (viewId != null && !viewId.isEmpty()) {
                for (int i = 0; i < depth; i++) sb.append("│   ");
                sb.append("  🆔 **ID**: `").append(viewId).append("`\n");
            }

            // 文本内容
            CharSequence text = node.getText();
            if (text != null && text.length() > 0) {
                for (int i = 0; i < depth; i++) sb.append("│   ");
                sb.append("  📝 **Text**: \"").append(text).append("\"\n");
            }

            // 内容描述
            CharSequence desc = node.getContentDescription();
            if (desc != null && desc.length() > 0) {
                for (int i = 0; i < depth; i++) sb.append("│   ");
                sb.append("  💬 **Desc**: \"").append(desc).append("\"\n");
            }

            // 位置和大小
            android.graphics.Rect bounds = new android.graphics.Rect();
            node.getBoundsInScreen(bounds);
            int width = bounds.right - bounds.left;
            int height = bounds.bottom - bounds.top;

            for (int i = 0; i < depth; i++) sb.append("│   ");
            sb.append("  📐 **Bounds**: [").append(bounds.left).append(",").append(bounds.top)
              .append("] → [").append(bounds.right).append(",").append(bounds.bottom)
              .append("] (").append(width).append("x").append(height).append(")\n");

            // 遍历子节点
            int childCount = node.getChildCount();
            for (int i = 0; i < childCount; i++) {
                android.view.accessibility.AccessibilityNodeInfo child = node.getChild(i);
                if (child != null) {
                    dumpNode(child, sb, depth + 1);
                    child.recycle();
                }
            }

        } catch (Exception e) {
            logE("遍历节点异常: " + e.getMessage());
        }
    }

    /**
     * 显示dump结果 - 保存为.md文件并分享
     */
    private void showDumpResult(String dumpText) {
        try {
            // 保存为.md文件
            java.io.File file = saveDumpToFile(dumpText);
            if (file == null) {
                logE("保存Dump文件失败");
                return;
            }

            logD("✅ Dump文件已保存: " + file.getAbsolutePath());

            // 分享文件
            shareDumpFile(file);

        } catch (Exception e) {
            logE("显示dump结果失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 保存Dump到文件
     */
    private java.io.File saveDumpToFile(String dumpText) {
        try {
            // 使用应用私有的外部存储目录(不需要权限,Android 11+兼容)
            java.io.File appDir = new java.io.File(getExternalFilesDir(null), "UIDump");

            // 确保目录存在
            if (!appDir.exists()) {
                boolean created = appDir.mkdirs();
                logD("📁 创建目录: " + appDir.getAbsolutePath() + " - " + (created ? "成功" : "失败"));
            }

            // 生成文件名(带时间戳)
            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss",
                java.util.Locale.CHINA).format(new java.util.Date());
            String fileName = "UI_Dump_" + timestamp + ".md";

            java.io.File file = new java.io.File(appDir, fileName);

            // 写入文件
            java.io.FileWriter writer = new java.io.FileWriter(file);
            writer.write("# UI结构 Dump\n\n");
            writer.write("**生成时间**: " + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                java.util.Locale.CHINA).format(new java.util.Date()) + "\n\n");
            writer.write("---\n\n");
            writer.write(dumpText);
            writer.close();

            logD("✅ 文件已保存: " + file.getAbsolutePath());
            logD("📂 文件大小: " + (file.length() / 1024) + " KB");

            return file;

        } catch (Exception e) {
            logE("❌ 保存文件失败: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 分享Dump文件
     */
    private void shareDumpFile(java.io.File file) {
        try {
            // 使用FileProvider获取URI
            android.net.Uri fileUri = androidx.core.content.FileProvider.getUriForFile(
                this,
                "com.rightsguard.automation.fileprovider",
                file);

            // 创建分享Intent
            android.content.Intent shareIntent = new android.content.Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/markdown");
            shareIntent.putExtra(android.content.Intent.EXTRA_STREAM, fileUri);
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "UI结构Dump");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "权利卫士UI结构分析文件");
            shareIntent.addFlags(android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);

            // 创建选择器
            android.content.Intent chooser = android.content.Intent.createChooser(shareIntent, "分享Dump文件");
            chooser.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(chooser);

            logD("📤 已打开分享对话框");

        } catch (Exception e) {
            logE("分享文件失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 打开侵权链接(通过WebView)
     * 新方案: 在APP内部使用WebView打开抖音链接,避免跳转到外部浏览器导致无障碍服务被杀掉
     * 流程: 打开WebView -> 加载抖音链接 -> 无障碍服务自动点击"打开App" -> 跳转到抖音 -> 观看视频 -> 返回
     */
    private void openInfringementUrl(String url) {
        try {
            logD("🌐 准备通过WebView打开侵权链接: " + url);

            // 步骤1: 启动WebView Activity
            android.content.Intent intent = new android.content.Intent(this, WebViewActivity.class);
            intent.putExtra("url", url);
            intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);

            logD("🎯 启动WebView Activity...");
            startActivity(intent);

            logD("✅ WebView Activity已启动,等待页面加载...");

        } catch (Exception e) {
            logE("❌ 打开侵权链接失败: " + e.getMessage());
            logE("异常类型: " + e.getClass().getName());
            e.printStackTrace();
        }
    }

    /**
     * WebView页面加载完成的回调
     */
    /**
     * 当WebView检测到抖音URL Scheme时调用
     */
    public void onDouyinSchemeDetected(String schemeUrl) {
        new Thread(() -> {
            try {
                logD("🎯 检测到抖音URL Scheme: " + schemeUrl);

                // 关闭WebView Activity
                logD("🔧 关闭WebView Activity...");
                android.content.Intent closeIntent = new android.content.Intent("com.rightsguard.automation.CLOSE_WEBVIEW");
                sendBroadcast(closeIntent);
                Thread.sleep(500);

                // 🆕 直接使用URL Scheme打开抖音APP
                logD("📱 使用URL Scheme打开抖音APP: " + schemeUrl);
                try {
                    android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_VIEW);
                    intent.setData(android.net.Uri.parse(schemeUrl));
                    intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    logD("✅ 已发送打开抖音的Intent");
                } catch (Exception e) {
                    logE("❌ 使用URL Scheme打开抖音失败: " + e.getMessage());
                    // 如果URL Scheme失败,尝试直接打开抖音APP
                    switchToDouyin();
                }

                // 等待抖音启动并加载视频,观看3秒
                logD("⏱️ 等待抖音启动并加载视频...");
                Thread.sleep(2000); // 等待2秒让抖音启动

                logD("✅ 抖音已打开,侵权视频正在显示");
                logD("👀 观看侵权视频3秒...");
                Thread.sleep(3000); // 观看3秒

                logD("✅ 观看完成,准备最小化应用");

                // 🆕 步骤: 清空剪贴板,避免打开抖音时弹出"打开看看"
                clearClipboard();

                // 🆕 步骤: 最小化当前应用(返回桌面)
                minimizeCurrentApp();

                // 🆕 步骤: 延迟打开权利卫士
                delayedOpenApp();

            } catch (Exception e) {
                logE("❌ 处理抖音URL Scheme失败: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    public void onWebViewPageLoaded() {
        new Thread(() -> {
            try {
                logD("📄 WebView页面加载完成,开始查找'打开App'按钮...");

                // 等待2秒让页面完全渲染
                Thread.sleep(2000);

                // 查找并点击"打开App"按钮
                boolean foundButton = false;
                for (int i = 0; i < 10; i++) {
                    android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
                    if (rootNode != null) {
                        String packageName = rootNode.getPackageName() != null ?
                            rootNode.getPackageName().toString() : "";

                        logD("🔍 第" + (i+1) + "次查找,当前包名: " + packageName);

                        // 查找"打开App"按钮
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> buttons =
                            rootNode.findAccessibilityNodeInfosByText("打开App");

                        if (buttons != null && !buttons.isEmpty()) {
                            logD("✅ 找到 " + buttons.size() + " 个'打开App'节点");

                            for (int j = 0; j < buttons.size(); j++) {
                                android.view.accessibility.AccessibilityNodeInfo button = buttons.get(j);
                                String text = button.getText() != null ? button.getText().toString() : "";

                                if (button.isClickable() && text.equals("打开App")) {
                                    boolean clicked = button.performAction(
                                        android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);

                                    if (clicked) {
                                        logD("✅ 成功点击'打开App'按钮!");
                                        foundButton = true;

                                        // 🔑 关键: 立即把我们的APP拉回前台,防止无障碍服务被杀掉!
                                        logD("🔑 立即把我们的APP拉回前台,防止服务被杀掉...");
                                        bringAppToFront();
                                        Thread.sleep(1000); // 等待1秒确保我们的APP在前台

                                        break;
                                    }
                                }
                            }

                            for (int j = 0; j < buttons.size(); j++) {
                                buttons.get(j).recycle();
                            }

                            if (foundButton) {
                                rootNode.recycle();
                                break;
                            }
                        }

                        rootNode.recycle();
                    }

                    if (!foundButton) {
                        Thread.sleep(500);
                    }
                }

                if (!foundButton) {
                    logE("❌ 未找到'打开App'按钮");
                    return;
                }

                // 关闭WebView Activity
                logD("🔧 关闭WebView Activity...");
                android.content.Intent closeIntent = new android.content.Intent("com.rightsguard.automation.CLOSE_WEBVIEW");
                sendBroadcast(closeIntent);
                Thread.sleep(500);

                // 现在切换到抖音
                logD("📱 切换到抖音...");
                switchToDouyin();

                // 等待抖音启动并加载视频
                logD("⏱️ 等待抖音启动并加载视频(3秒)...");
                Thread.sleep(3000);

                logD("✅ 抖音已打开,侵权视频应该正在显示");

                // 🆕 步骤: 清空剪贴板,避免打开抖音时弹出"打开看看"
                clearClipboard();

                // 🆕 步骤: 最小化当前应用(返回桌面)
                minimizeCurrentApp();

                // 🆕 步骤: 延迟打开权利卫士
                delayedOpenApp();

            } catch (Exception e) {
                logE("❌ WebView页面处理失败: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 把我们的APP拉回前台
     */
    private void bringAppToFront() {
        try {
            android.content.Intent intent = new android.content.Intent(this, MainActivity.class);
            intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK |
                          android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            logD("✅ 已把我们的APP拉回前台");
        } catch (Exception e) {
            logE("拉回APP到前台失败: " + e.getMessage());
        }
    }

    /**
     * 切换到抖音APP
     */
    private void switchToDouyin() {
        // 方案1: URL Scheme 打开抖音（与正版流程 onDouyinSchemeDetected 一致，不依赖包可见性）
        try {
            android.content.Intent schemeIntent = new android.content.Intent(android.content.Intent.ACTION_VIEW);
            schemeIntent.setData(android.net.Uri.parse("snssdk1128://"));
            schemeIntent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(schemeIntent);
            logD("✅ 已通过URL Scheme切换到抖音APP (方案1)");
            return;
        } catch (Exception e) {
            logD("⚠️ URL Scheme打开抖音失败，尝试方案2: " + e.getMessage());
        }

        // 方案2: getLaunchIntentForPackage (需要 <queries> 声明，Android 11+)
        try {
            android.content.Intent intent = getPackageManager().getLaunchIntentForPackage(DOUYIN_PACKAGE);
            if (intent != null) {
                intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK |
                              android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                logD("✅ 已切换到抖音APP (方案2)");
                return;
            }
        } catch (Exception e) {
            logD("⚠️ getLaunchIntentForPackage失败: " + e.getMessage());
        }

        // 方案3: 直接构造 MAIN/LAUNCHER Intent
        try {
            logD("⚠️ 尝试方案3: 直接构造MAIN Intent...");
            android.content.Intent fallback = new android.content.Intent(android.content.Intent.ACTION_MAIN);
            fallback.addCategory(android.content.Intent.CATEGORY_LAUNCHER);
            fallback.setPackage(DOUYIN_PACKAGE);
            fallback.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK |
                              android.content.Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            startActivity(fallback);
            logD("✅ 已切换到抖音APP (方案3)");
        } catch (Exception e) {
            logE("❌ 切换到抖音全部方案均失败: " + e.getMessage());
        }
    }

    /**
     * 通过坐标点击屏幕
     * 需要Android 7.0 (API 24)+
     */
    private void clickByCoordinates(int x, int y) {
        try {
            // 检查Android版本
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                logE("❌ 坐标点击需要Android 7.0+,当前版本不支持");
                return;
            }

            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                logE("❌ rootNode为null,无法点击坐标");
                return;
            }

            // 使用全局手势点击
            android.graphics.Path path = new android.graphics.Path();
            path.moveTo(x, y);

            android.accessibilityservice.GestureDescription.Builder builder =
                new android.accessibilityservice.GestureDescription.Builder();
            android.accessibilityservice.GestureDescription.StrokeDescription stroke =
                new android.accessibilityservice.GestureDescription.StrokeDescription(path, 0, 100);
            builder.addStroke(stroke);

            boolean result = dispatchGesture(builder.build(), null, null);

            if (result) {
                logD("✅ 坐标点击成功: (" + x + ", " + y + ")");
            } else {
                logE("❌ 坐标点击失败: (" + x + ", " + y + ")");
            }

        } catch (Exception e) {
            logE("坐标点击异常: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 智能返回到抖音首页并点击"我"
     */
    private void returnToDouyinHomeAndClickMe() {
        try {
            // 第一次点击抖音左上角返回按钮
            logD("🔙 点击抖音左上角返回按钮...");
            boolean clicked = clickDouyinBackButton();

            if (!clicked) {
                logE("❌ 未找到抖音返回按钮,尝试使用系统返回键...");
                performGlobalAction(GLOBAL_ACTION_BACK);
            }

            Thread.sleep(500);

            // 检查是否到达首页(通过查找底部导航栏)
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                logE("❌ rootNode为null,无法检测当前应用");
                return;
            }

            boolean hasBottomNav = checkForBottomNavigation(rootNode);

            if (hasBottomNav) {
                // 已到达首页,直接点击"我"
                logD("✅ 已到达首页,准备点击'我'按钮...");
            } else {
                // 未到达首页,再点击一次返回按钮
                logD("⚠️ 未到达首页,再次点击返回按钮...");
                clicked = clickDouyinBackButton();

                if (!clicked) {
                    logE("❌ 未找到抖音返回按钮,尝试使用系统返回键...");
                    performGlobalAction(GLOBAL_ACTION_BACK);
                }

                Thread.sleep(500);

                // 再次检查是否到达首页
                rootNode = getRootInActiveWindow();
                if (rootNode != null) {
                    hasBottomNav = checkForBottomNavigation(rootNode);
                    if (hasBottomNav) {
                        logD("✅ 已到达首页");
                    } else {
                        logD("⚠️ 仍未到达首页,但继续执行...");
                    }
                }
            }

            // 点击"我"按钮
            logD("👤 点击'我'按钮...");
            clickMeButton();
            Thread.sleep(1000);

            logD("✅ 已进入'我'页面");

        } catch (Exception e) {
            logE("❌ 返回首页并点击'我'失败: " + e.getMessage());
        }
    }

    /**
     * 点击抖音左上角返回按钮
     */
    private boolean clickDouyinBackButton() {
        try {
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                return false;
            }

            // 方法1: 通过ID查找返回按钮 (最准确)
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> backNodes =
                rootNode.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/back_btn");

            if (backNodes != null && !backNodes.isEmpty()) {
                for (android.view.accessibility.AccessibilityNodeInfo node : backNodes) {
                    if (node.isClickable()) {
                        boolean clicked = node.performAction(
                            android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                        );
                        if (clicked) {
                            logD("✅ 成功点击抖音返回按钮(通过ID查找)");
                            rootNode.recycle();
                            return true;
                        }
                    }
                }
            }

            // 方法2: 通过content-desc查找"返回"按钮
            backNodes = rootNode.findAccessibilityNodeInfosByText("返回");

            if (backNodes != null && !backNodes.isEmpty()) {
                for (android.view.accessibility.AccessibilityNodeInfo node : backNodes) {
                    // 查找可点击的节点或父节点
                    android.view.accessibility.AccessibilityNodeInfo clickableNode = node;
                    while (clickableNode != null && !clickableNode.isClickable()) {
                        clickableNode = clickableNode.getParent();
                    }

                    if (clickableNode != null && clickableNode.isClickable()) {
                        boolean clicked = clickableNode.performAction(
                            android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                        );
                        if (clicked) {
                            logD("✅ 成功点击抖音返回按钮(通过文本查找)");
                            rootNode.recycle();
                            return true;
                        }
                    }
                }
            }

            rootNode.recycle();

            // 方法3: 使用坐标点击左上角(备用方案)
            // 抖音返回按钮坐标: [18,114] → [162,258], 中心点约 (90, 186)
            logD("⚠️ 未找到返回按钮,使用坐标点击...");
            clickByCoordinates(90, 186);
            return true;

        } catch (Exception e) {
            logE("点击抖音返回按钮失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 检查是否有底部导航栏
     */
    private boolean checkForBottomNavigation(android.view.accessibility.AccessibilityNodeInfo rootNode) {
        if (rootNode == null) {
            return false;
        }

        try {
            // 查找包含"我"文本的节点
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> meNodes = rootNode.findAccessibilityNodeInfosByText("我");
            if (meNodes != null && !meNodes.isEmpty()) {
                // 检查是否在屏幕底部 (y坐标 > 2000)
                for (android.view.accessibility.AccessibilityNodeInfo node : meNodes) {
                    android.graphics.Rect rect = new android.graphics.Rect();
                    node.getBoundsInScreen(rect);
                    if (rect.top > 2000) {
                        logD("✅ 检测到底部导航栏");
                        return true;
                    }
                }
            }

            logD("⚠️ 未检测到底部导航栏");
            return false;
        } catch (Exception e) {
            logE("检查底部导航栏失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 点击"我"按钮 (使用坐标)
     */
    private void clickMeButton() {
        try {
            // 抖音"我"按钮坐标: [864,2201] → [1080,2346], 中心点 (972, 2273)
            logD("👤 准备点击'我'按钮 (坐标: 972, 2273)...");
            clickByCoordinates(972, 2273);
        } catch (Exception e) {
            logE("点击'我'按钮失败: " + e.getMessage());
        }
    }

    /**
     * 清空剪贴板
     * 避免打开抖音时弹出"打开看看"页面
     */
    private void clearClipboard() {
        try {
            android.content.ClipboardManager clipboard =
                (android.content.ClipboardManager) getSystemService(android.content.Context.CLIPBOARD_SERVICE);

            if (clipboard != null) {
                // 复制一个空文本到剪贴板
                android.content.ClipData clip = android.content.ClipData.newPlainText("", "");
                clipboard.setPrimaryClip(clip);
                logD("🧹 已清空剪贴板,避免打开抖音时弹出'打开看看'");
            }
        } catch (Exception e) {
            logE("清空剪贴板失败: " + e.getMessage());
        }
    }

    /**
     * 🆕 随机延迟1-2秒,模拟真人操作
     */
    private void randomDelay() {
        try {
            // 生成1000-2000毫秒的随机延迟
            int delayMs = 1000 + new java.util.Random().nextInt(1000);
            logD("⏱️ 随机延迟 " + delayMs + "ms (模拟真人操作)");
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            logE("随机延迟失败: " + e.getMessage());
        }
    }

    /**
     * 🆕 开始抖音自动化(权利卫士取证阶段)
     */
    private void startDouyinAutomation() {
        new Thread(() -> {
            try {
                logD("🎯 权利卫士已打开抖音,开始自动化流程...");

                // 等待抖音完全启动
                logD("⏱️ 等待抖音完全启动(3秒)...");
                Thread.sleep(3000);

                // 检测是否在首页
                android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
                if (rootNode != null) {
                    boolean hasBottomNav = checkForBottomNavigation(rootNode);

                    if (!hasBottomNav) {
                        logD("⚠️ 当前不在首页,尝试返回首页...");
                        // 点击返回按钮
                        clickDouyinBackButton();
                        Thread.sleep(1000);

                        // 再次检测
                        rootNode = getRootInActiveWindow();
                        if (rootNode != null) {
                            hasBottomNav = checkForBottomNavigation(rootNode);
                            if (hasBottomNav) {
                                logD("✅ 已返回首页");
                            } else {
                                logD("⚠️ 仍未到达首页,继续执行...");
                            }
                        }
                    } else {
                        logD("✅ 当前已在首页");
                    }
                }

                // 步骤1: 点击"我"按钮
                logD("📱 步骤1: 点击'我'按钮...");
                randomDelay();
                clickMeButton();
                hasClickedDouyinMe = true;

                // 等待"我"页面加载完成
                logD("⏱️ 等待'我'页面加载完成(1秒)...");
                Thread.sleep(1000);

                // 步骤2: 点击"更多"按钮
                logD("📱 步骤2: 点击'更多'按钮...");
                randomDelay();
                clickDouyinMoreButton();

                // 步骤3: 点击"设置"按钮
                logD("📱 步骤3: 点击'设置'按钮...");
                randomDelay();
                clickDouyinSettingsButton();

                // 步骤4: 滑动到"关于"部分
                logD("📱 步骤4: 滑动到'关于'部分...");
                randomDelay();
                scrollToAboutSection();

                // 步骤5: 点击"资质证照"按钮
                logD("📱 步骤5: 点击'资质证照'按钮...");
                // 不需要随机延迟,直接点击
                clickQualificationButton();

                // 注意: 流程会继续执行,不在这里结束

            } catch (Exception e) {
                logE("抖音自动化失败: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 🆕 点击抖音"更多"按钮(三条杠)
     */
    private void clickDouyinMoreButton() {
        try {
            logD("🔍 尝试点击抖音'更多'按钮...");

            // 方法1: 尝试通过content-desc查找"更多"按钮
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode != null) {
                java.util.List<android.view.accessibility.AccessibilityNodeInfo> moreNodes =
                    rootNode.findAccessibilityNodeInfosByText("更多");

                if (moreNodes != null && !moreNodes.isEmpty()) {
                    for (android.view.accessibility.AccessibilityNodeInfo node : moreNodes) {
                        if (node.isClickable()) {
                            boolean clicked = node.performAction(
                                android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                            );
                            if (clicked) {
                                logD("✅ 成功点击'更多'按钮(通过文本查找)");
                                hasClickedDouyinMore = true;
                                rootNode.recycle();
                                return;
                            }
                        }
                    }
                }
                rootNode.recycle();
            }

            // 方法2: 使用坐标点击(备用方案)
            logD("⚠️ 未找到'更多'按钮,使用坐标点击...");
            clickByCoordinates(984, 192);
            hasClickedDouyinMore = true;

        } catch (Exception e) {
            logE("点击'更多'按钮失败: " + e.getMessage());
        }
    }

    /**
     * 🆕 点击抖音"设置"按钮
     */
    private void clickDouyinSettingsButton() {
        try {
            logD("🔍 尝试点击抖音'设置'按钮...");

            // 方法1: 尝试通过文本查找"设置"按钮
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode != null) {
                java.util.List<android.view.accessibility.AccessibilityNodeInfo> settingsNodes =
                    rootNode.findAccessibilityNodeInfosByText("设置");

                if (settingsNodes != null && !settingsNodes.isEmpty()) {
                    for (android.view.accessibility.AccessibilityNodeInfo node : settingsNodes) {
                        // 查找可点击的父节点
                        android.view.accessibility.AccessibilityNodeInfo clickableNode = node;
                        while (clickableNode != null && !clickableNode.isClickable()) {
                            clickableNode = clickableNode.getParent();
                        }

                        if (clickableNode != null && clickableNode.isClickable()) {
                            boolean clicked = clickableNode.performAction(
                                android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                            );
                            if (clicked) {
                                logD("✅ 成功点击'设置'按钮(通过文本查找)");
                                hasClickedDouyinSettings = true;
                                rootNode.recycle();
                                return;
                            }
                        }
                    }
                }
                rootNode.recycle();
            }

            // 方法2: 使用坐标点击(备用方案)
            logD("⚠️ 未找到'设置'按钮,使用坐标点击...");
            clickByCoordinates(627, 186);
            hasClickedDouyinSettings = true;

        } catch (Exception e) {
            logE("点击'设置'按钮失败: " + e.getMessage());
        }
    }

    /**
     * 🆕 滑动到"关于"部分
     */
    private void scrollToAboutSection() {
        try {
            logD("🔍 准备滑动到'关于'部分...");

            // 使用无障碍服务的滑动手势API
            // 创建滑动手势路径
            android.graphics.Path path = new android.graphics.Path();
            // 起始点: 屏幕中下部 (540, 1700)
            path.moveTo(540, 1700);
            // 结束点: 屏幕中上部 (540, 700) - 滑动1000像素,让"资质证照"显示在屏幕中间
            path.lineTo(540, 700);

            // 创建手势描述
            android.accessibilityservice.GestureDescription.StrokeDescription strokeDescription =
                new android.accessibilityservice.GestureDescription.StrokeDescription(
                    path,
                    0,      // 开始时间
                    400     // 持续时间400ms
                );

            android.accessibilityservice.GestureDescription.Builder builder =
                new android.accessibilityservice.GestureDescription.Builder();
            builder.addStroke(strokeDescription);
            android.accessibilityservice.GestureDescription gesture = builder.build();

            // 执行手势
            boolean dispatched = dispatchGesture(
                gesture,
                new android.accessibilityservice.AccessibilityService.GestureResultCallback() {
                    @Override
                    public void onCompleted(android.accessibilityservice.GestureDescription gestureDescription) {
                        super.onCompleted(gestureDescription);
                        logD("✅ 滑动手势执行成功");

                        // 🆕 滑动完成后等待1秒,然后截屏
                        new Thread(() -> {
                            try {
                                Thread.sleep(1000); // 等待页面稳定
                                logD("📸 准备截屏保存抖音设置页面...");
                                takeScreenshotWithPrefix("抖音设置", new ScreenshotCallback() {
                                    @Override
                                    public void onSuccess() {
                                        logD("✅ 抖音设置页面截屏成功");
                                    }

                                    @Override
                                    public void onFailure() {
                                        logE("❌ 抖音设置页面截屏失败");
                                    }
                                });
                            } catch (Exception e) {
                                logE("截屏失败: " + e.getMessage());
                            }
                        }).start();
                    }

                    @Override
                    public void onCancelled(android.accessibilityservice.GestureDescription gestureDescription) {
                        super.onCancelled(gestureDescription);
                        logE("❌ 滑动手势被取消");
                    }
                },
                null
            );

            if (dispatched) {
                logD("✅ 已发送滑动手势");
                hasScrolledToAboutSection = true;
            } else {
                logE("❌ 滑动手势发送失败");
            }

        } catch (Exception e) {
            logE("滑动到'关于'部分失败: " + e.getMessage());
        }
    }

    /**
     * 🆕 点击"资质证照"按钮
     */
    private void clickQualificationButton() {
        try {
            logD("🔍 准备截图并点击'资质证照'按钮...");

            // 等待滑动完成和页面稳定
            Thread.sleep(1000); // 等待1秒即可

            // 🆕 先截图保存"设置"页面(显示"资质证照"按钮)
            logD("📸 准备截屏保存抖音设置页面...");
            takeScreenshotWithPrefix("抖音设置", new ScreenshotCallback() {
                @Override
                public void onSuccess() {
                    logD("✅ 抖音设置页面截屏成功");
                }

                @Override
                public void onFailure() {
                    logE("❌ 抖音设置页面截屏失败");
                }
            });

            // 等待截屏完成
            Thread.sleep(1000);

            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                logE("❌ 无法获取根节点");
                return;
            }

            // 方法1: 优先通过文本查找"资质证照"按钮(最准确!)
            // 直接查找包含"资质证照"文本的节点
            logD("🔍 开始查找'资质证照'按钮...");
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> textNodes =
                rootNode.findAccessibilityNodeInfosByText("资质证照");

            if (textNodes != null && !textNodes.isEmpty()) {
                logD("📋 找到 " + textNodes.size() + " 个包含'资质证照'文本的节点");

                for (android.view.accessibility.AccessibilityNodeInfo node : textNodes) {
                    // 获取节点文本,验证是否完全匹配
                    CharSequence nodeText = node.getText();
                    android.graphics.Rect bounds = new android.graphics.Rect();
                    node.getBoundsInScreen(bounds);

                    logD("  节点文本: " + (nodeText != null ? nodeText.toString() : "null"));
                    logD("  节点位置: [" + bounds.left + "," + bounds.top + "] → [" + bounds.right + "," + bounds.bottom + "]");

                    // 只处理文本完全匹配"资质证照"的节点
                    if (nodeText != null && "资质证照".equals(nodeText.toString())) {
                        logD("✅ 找到完全匹配的'资质证照'文本节点");

                        // 查找可点击的父节点
                        android.view.accessibility.AccessibilityNodeInfo clickableNode = node;
                        int parentLevel = 0;
                        while (clickableNode != null && !clickableNode.isClickable() && parentLevel < 5) {
                            clickableNode = clickableNode.getParent();
                            parentLevel++;
                        }

                        if (clickableNode != null && clickableNode.isClickable()) {
                            // 获取父节点的位置信息
                            android.graphics.Rect parentBounds = new android.graphics.Rect();
                            clickableNode.getBoundsInScreen(parentBounds);

                            String parentId = clickableNode.getViewIdResourceName();
                            logD("  可点击父节点ID: " + (parentId != null ? parentId : "null"));
                            logD("  父节点层级: " + parentLevel);
                            logD("  父节点位置: [" + parentBounds.left + "," + parentBounds.top + "] → [" + parentBounds.right + "," + parentBounds.bottom + "]");

                            boolean clicked = clickableNode.performAction(
                                android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                            );
                            if (clicked) {
                                logD("✅ 成功点击'资质证照'按钮(通过文本查找)");

                                // 等待资质证照页面加载,然后截屏,再点击"营业执照"
                                new Thread(() -> {
                                    try {
                                        Thread.sleep(1000); // 等待页面加载
                                        logD("📸 准备截屏保存资质证照页面...");
                                        takeScreenshotWithPrefix("资质证照", new ScreenshotCallback() {
                                            @Override
                                            public void onSuccess() {
                                                logD("✅ 资质证照页面截屏成功");

                                                // 截屏成功后,点击"营业执照"
                                                new Thread(() -> {
                                                    try {
                                                        Thread.sleep(1000); // 等待截屏完成
                                                        clickBusinessLicenseButton();
                                                    } catch (Exception e) {
                                                        logE("点击营业执照失败: " + e.getMessage());
                                                    }
                                                }).start();
                                            }

                                            @Override
                                            public void onFailure() {
                                                logE("❌ 资质证照页面截屏失败");
                                            }
                                        });
                                    } catch (Exception e) {
                                        logE("截屏失败: " + e.getMessage());
                                    }
                                }).start();

                                rootNode.recycle();
                                return;
                            }
                        } else {
                            logE("❌ 未找到可点击的父节点");
                        }
                    }
                }
            } else {
                logE("❌ 未找到包含'资质证照'文本的节点,可能滑动距离不够或页面未加载完成");
            }

            rootNode.recycle();

        } catch (Exception e) {
            logE("点击'资质证照'按钮失败: " + e.getMessage());
        }
    }

    /**
     * 🔙 智能返回到"我"的首页
     * 最多返回4次,每次返回后检测是否到达"我"页面
     * 如果检测到"我"页面,立即停止返回
     */
    private void smartReturnToMePage() {
        try {
            logD("🔙 开始智能返回到'我'的首页...");

            // 先停留1.5秒,让用户看清楚营业执照页面
            logD("⏱️ 停留1.5秒,查看营业执照页面...");
            Thread.sleep(1500);

            int maxReturnTimes = 4; // 最多返回4次

            for (int i = 1; i <= maxReturnTimes; i++) {
                // 执行返回操作
                logD("🔙 第" + i + "次返回...");
                performGlobalAction(GLOBAL_ACTION_BACK);

                // 每次返回后等待300ms,让页面稳定
                Thread.sleep(300);

                // 检测是否已经在"我"页面
                if (isOnMePage()) {
                    logD("✅ 第" + i + "次返回后到达'我'页面");

                    // 再按一次返回键,关闭右侧的"更多"菜单
                    logD("🔙 关闭右侧'更多'菜单...");
                    Thread.sleep(300);
                    performGlobalAction(GLOBAL_ACTION_BACK);
                    Thread.sleep(500);

                    // 点击"我的订单"
                    clickMyOrderButton();

                    // 注意: 流程会继续执行,不在这里结束
                    return;
                }
            }

            // 最后再检测一次
            if (isOnMePage()) {
                logD("✅ 成功返回到'我'页面");

                // 再按一次返回键,关闭右侧的"更多"菜单
                logD("🔙 关闭右侧'更多'菜单...");
                Thread.sleep(300);
                performGlobalAction(GLOBAL_ACTION_BACK);
                Thread.sleep(500);

                // 点击"我的订单"
                clickMyOrderButton();

                // 注意: 流程会继续执行,不在这里结束
            } else {
                logE("⚠️ 返回" + maxReturnTimes + "次后仍未到达'我'页面");
            }

        } catch (Exception e) {
            logE("智能返回失败: " + e.getMessage());
        }
    }

    /**
     * 🔍 检测是否在"我"页面
     * 通过查找"获赞"、"关注"、"粉丝"等特征元素判断
     */
    private boolean isOnMePage() {
        try {
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                logD("⚠️ 无法获取根节点");
                return false;
            }

            // 查找"获赞"文本节点
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> likeNodes =
                rootNode.findAccessibilityNodeInfosByText("获赞");
            logD("🔍 查找'获赞': " + (likeNodes != null ? likeNodes.size() : 0) + "个");

            // 查找"关注"文本节点
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> followNodes =
                rootNode.findAccessibilityNodeInfosByText("关注");
            logD("🔍 查找'关注': " + (followNodes != null ? followNodes.size() : 0) + "个");

            // 查找"粉丝"文本节点
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> fansNodes =
                rootNode.findAccessibilityNodeInfosByText("粉丝");
            logD("🔍 查找'粉丝': " + (fansNodes != null ? fansNodes.size() : 0) + "个");

            rootNode.recycle();

            // 如果找到"获赞"或"关注"或"粉丝",说明在"我"页面
            boolean isOnMe = (likeNodes != null && !likeNodes.isEmpty()) ||
                            (followNodes != null && !followNodes.isEmpty()) ||
                            (fansNodes != null && !fansNodes.isEmpty());

            if (isOnMe) {
                logD("🎯 检测到'我'页面特征元素");
            } else {
                logD("❌ 未检测到'我'页面特征元素");
            }

            return isOnMe;

        } catch (Exception e) {
            logE("检测'我'页面失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 🆕 点击"观看历史"按钮
     */
    private void clickViewHistory() {
        new Thread(() -> {
            try {
                logD("📺 准备点击'观看历史'按钮...");

                // 等待页面稳定
                Thread.sleep(1000);

                android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
                if (rootNode == null) {
                    logE("❌ 无法获取根节点");
                    return;
                }

                // 通过文本查找"观看历史"按钮
                logD("🔍 开始查找'观看历史'按钮...");

                java.util.List<android.view.accessibility.AccessibilityNodeInfo> textNodes =
                    rootNode.findAccessibilityNodeInfosByText("观看历史");

                if (textNodes != null && !textNodes.isEmpty()) {
                    logD("📋 找到 " + textNodes.size() + " 个包含'观看历史'文本的节点");

                    for (android.view.accessibility.AccessibilityNodeInfo node : textNodes) {
                        // 获取节点文本,验证是否完全匹配
                        CharSequence nodeText = node.getText();
                        android.graphics.Rect bounds = new android.graphics.Rect();
                        node.getBoundsInScreen(bounds);

                        logD("  节点文本: " + (nodeText != null ? nodeText.toString() : "null"));
                        logD("  节点位置: [" + bounds.left + "," + bounds.top + "] → [" + bounds.right + "," + bounds.bottom + "]");

                        // 只处理文本完全匹配"观看历史"的节点
                        if (nodeText != null && "观看历史".equals(nodeText.toString())) {
                            logD("✅ 找到完全匹配的'观看历史'文本节点");

                            // 查找可点击的父节点
                            android.view.accessibility.AccessibilityNodeInfo clickableNode = node;
                            int parentLevel = 0;
                            while (clickableNode != null && !clickableNode.isClickable() && parentLevel < 5) {
                                clickableNode = clickableNode.getParent();
                                parentLevel++;
                            }

                            if (clickableNode != null && clickableNode.isClickable()) {
                                // 获取父节点的位置信息
                                android.graphics.Rect parentBounds = new android.graphics.Rect();
                                clickableNode.getBoundsInScreen(parentBounds);

                                String parentId = clickableNode.getViewIdResourceName();
                                logD("  可点击父节点ID: " + (parentId != null ? parentId : "null"));
                                logD("  父节点层级: " + parentLevel);
                                logD("  父节点位置: [" + parentBounds.left + "," + parentBounds.top + "] → [" + parentBounds.right + "," + parentBounds.bottom + "]");

                                // 点击父节点
                                boolean clicked = clickableNode.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);
                                if (clicked) {
                                    logD("✅ 成功点击'观看历史'按钮");

                                    // 等待观看历史页面加载
                                    Thread.sleep(2000);

                                    // 🆕 在观看历史中查找并点击侵权视频
                                    findAndClickVideoInHistory();
                                } else {
                                    logE("❌ 点击'观看历史'按钮失败");
                                }

                                rootNode.recycle();
                                return;
                            } else {
                                logE("❌ 未找到可点击的父节点");
                            }
                        }
                    }

                    logE("❌ 未找到完全匹配的'观看历史'文本节点");
                } else {
                    logE("❌ 未找到'观看历史'按钮");
                }

                rootNode.recycle();

            } catch (Exception e) {
                logE("点击'观看历史'按钮失败: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 🆕 在观看历史中查找并点击侵权视频
     */
    private void findAndClickVideoInHistory() {
        try {
            logD("🔍 准备在观看历史中查找侵权视频...");
            logD("📝 视频关键词: " + videoKeywords);

            if (videoKeywords == null || videoKeywords.isEmpty()) {
                logE("❌ 未设置视频关键词,无法匹配视频");
                logD("💡 提示: 请在取证信息的第4行输入视频文案关键词");
                return;
            }

            // ★ 轮询等待观看历史页面内容加载（最多8次，每次800ms，共6.4秒）
            logD("⏳ 等待观看历史页面内容加载...");
            android.view.accessibility.AccessibilityNodeInfo rootNode = null;
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> allNodes = new java.util.ArrayList<>();
            int videoCount = 0;
            for (int waitAttempt = 0; waitAttempt < 8; waitAttempt++) {
                Thread.sleep(800);

                if (rootNode != null) {
                    rootNode.recycle();
                    allNodes.clear();
                }
                rootNode = getRootInActiveWindow();
                if (rootNode == null) {
                    logD("⏳ 第" + (waitAttempt + 1) + "次: 根节点为空,继续等待...");
                    continue;
                }

                findAllNodes(rootNode, allNodes);
                videoCount = 0;
                for (android.view.accessibility.AccessibilityNodeInfo n : allNodes) {
                    CharSequence d = n.getContentDescription();
                    if (d != null && d.toString().contains("点赞")) {
                        videoCount++;
                    }
                }

                if (videoCount > 0) {
                    logD("✅ 第" + (waitAttempt + 1) + "次检测到 " + videoCount + " 个视频节点,开始匹配...");
                    break;
                } else {
                    logD("⏳ 第" + (waitAttempt + 1) + "次: 找到 0 个视频节点,继续等待...");
                    allNodes.clear();
                }
            }

            if (rootNode == null) {
                logE("❌ 无法获取根节点");
                return;
            }

            if (videoCount == 0) {
                logE("❌ 等待超时,观看历史页面仍未加载到视频节点");
                logD("💡 提示: 请检查观看历史是否有记录,或者网络是否正常");
                rootNode.recycle();
                return;
            }

            // 开始遍历已加载的视频节点列表（allNodes 已在上面轮询中填充）
            int matchedCount = 0;

            for (android.view.accessibility.AccessibilityNodeInfo node : allNodes) {
                // 获取Content Description
                CharSequence desc = node.getContentDescription();
                if (desc == null || desc.toString().isEmpty()) {
                    continue;
                }

                String descStr = desc.toString();

                // 只统计包含"点赞"的节点(这是视频的特征)
                if (!descStr.contains("点赞")) {
                    continue;
                }

                videoCount++;

                // 记录前3个视频的信息,便于调试
                if (videoCount <= 3) {
                    logD("📹 视频" + videoCount + ": " + descStr.substring(0, Math.min(30, descStr.length())) + "...");
                }

                // 检查是否包含视频关键词
                if (descStr.contains(videoKeywords)) {
                    matchedCount++;
                    logD("✅ 找到匹配的视频!");
                    logD("  视频描述: " + descStr.substring(0, Math.min(50, descStr.length())) + "...");

                    // 获取节点位置
                    android.graphics.Rect bounds = new android.graphics.Rect();
                    node.getBoundsInScreen(bounds);
                    logD("  视频位置: [" + bounds.left + "," + bounds.top + "] → [" + bounds.right + "," + bounds.bottom + "]");
                    logD("  节点类名: " + node.getClassName());
                    logD("  节点可点击: " + node.isClickable());

                    // 尝试点击视频节点
                    boolean clicked = node.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);
                    if (clicked) {
                        logD("✅ 成功点击侵权视频");

                        // 等待视频播放页面加载
                        Thread.sleep(2000);

                        if (isTestMode) {
                            logD("🧪 测试模式: 跳过视频播放，直接进入作者主页流程");
                            navigateToAuthorProfile();
                        } else {
                            // 🆕 播放视频并截图
                            playVideoAndTakeScreenshots();
                        }

                        logD("🎉 抖音自动化流程完成!");
                        rootNode.recycle();
                        return;
                    } else {
                        logD("⚠️ 直接点击失败,尝试查找可点击的父节点...");

                        // 查找可点击的父节点
                        android.view.accessibility.AccessibilityNodeInfo clickableNode = node;
                        int parentLevel = 0;
                        while (clickableNode != null && !clickableNode.isClickable() && parentLevel < 5) {
                            clickableNode = clickableNode.getParent();
                            parentLevel++;
                        }

                        if (clickableNode != null && clickableNode.isClickable()) {
                            logD("  找到可点击的父节点,层级: " + parentLevel);
                            clicked = clickableNode.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);
                            if (clicked) {
                                logD("✅ 成功点击侵权视频(通过父节点)");
                                Thread.sleep(2000);

                                if (isTestMode) {
                                    logD("🧪 测试模式: 跳过视频播放，直接进入作者主页流程");
                                    navigateToAuthorProfile();
                                } else {
                                    // 🆕 播放视频并截图
                                    playVideoAndTakeScreenshots();
                                }

                                logD("🎉 抖音自动化流程完成!");
                                rootNode.recycle();
                                return;
                            }
                        }

                        // 如果父节点也点击失败,使用坐标点击
                        logD("⚠️ 父节点点击也失败,尝试使用坐标点击...");

                        // 计算视频中心点坐标
                        int centerX = (bounds.left + bounds.right) / 2;
                        int centerY = (bounds.top + bounds.bottom) / 2;
                        logD("  视频中心点坐标: (" + centerX + ", " + centerY + ")");

                        // 使用坐标点击
                        clickByCoordinates(centerX, centerY);
                        logD("✅ 已执行坐标点击侵权视频");
                        Thread.sleep(2000);

                        if (isTestMode) {
                            logD("🧪 测试模式: 跳过视频播放，直接进入作者主页流程");
                            navigateToAuthorProfile();
                        } else {
                            // 🆕 播放视频并截图
                            playVideoAndTakeScreenshots();
                        }

                        logD("🎉 抖音自动化流程完成!");
                    }

                    rootNode.recycle();
                    return;
                }
            }

            logE("❌ 未找到匹配的视频");
            logD("📊 统计: 共找到 " + videoCount + " 个视频,匹配 " + matchedCount + " 个");
            logD("💡 提示: 请检查视频关键词是否正确: " + videoKeywords);

            rootNode.recycle();

        } catch (Exception e) {
            logE("在观看历史中查找视频失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 🆕 播放视频并智能截图(人脸检测)
     * 逻辑:
     * 1. 从第1秒开始,每隔5帧(约0.2秒)截图并检测人脸
     * 2. 检测到人脸后保存图片,然后等待间隔时间
     * 3. 继续扫描,直到保存足够数量的图片
     * 4. 截图数量: <30秒截4张, ≥30秒截5张
     * 5. 间隔时间: <60秒间隔2秒, ≥60秒间隔4秒
     */
    private void playVideoAndTakeScreenshots() {
        FaceDetectionHelper faceDetector = null;
        // 兜底帧: 开始/中间/结尾 (索引 0/1/2)
        android.graphics.Bitmap[] fallbackBitmaps = new android.graphics.Bitmap[3];
        try {
            logD("🎬 开始播放视频并智能截图...");
            logD("📝 视频时长: " + videoDurationSeconds + "秒");

            // 初始化人脸检测器
            faceDetector = new FaceDetectionHelper();

            // 根据视频时长确定参数
            int targetCount = videoDurationSeconds < 30 ? 4 : 5;
            int intervalSeconds = 1; // 统一间隔1秒

            logD("🎯 目标截图数量: " + targetCount + "张");
            logD("⏱️ 截图间隔时间: " + intervalSeconds + "秒 (统一1秒)");

            // 兜底帧的目标时间点: 开始(1秒)、中间(1/2)、结尾(最后1秒)
            double[] fallbackTargetTimes = {
                1.0,
                videoDurationSeconds / 2.0,
                Math.max(1.0, videoDurationSeconds - 1.0)
            };
            boolean[] fallbackCaptured = {false, false, false};

            // 🔑 记录视频真实开始时间(用系统时钟,彻底解决时间漂移问题)
            long startRealTimeMs = System.currentTimeMillis();
            int savedCount = 0;
            int screenshotIndex = 1;

            logD("⏱️ 开始基于真实时钟扫描 (视频时长: " + videoDurationSeconds + "秒)");

            // 开始扫描并截图(退出条件: 保存足够数量 OR 真实时间超过视频时长)
            while (savedCount < targetCount) {
                // 计算当前真实播放进度(以系统时钟为准)
                double realElapsedSec = (System.currentTimeMillis() - startRealTimeMs) / 1000.0;

                // 视频已播放完毕,退出循环
                if (realElapsedSec >= videoDurationSeconds) {
                    logD("⏱️ 视频已播放完毕 (" + String.format("%.1f", realElapsedSec) + "秒),停止扫描");
                    break;
                }

                // 等待约0.2秒再截图
                Thread.sleep(200);

                // sleep之后重新计算真实时间(更准确)
                final double finalRealElapsedSec = (System.currentTimeMillis() - startRealTimeMs) / 1000.0;
                final int finalScreenshotIndex = screenshotIndex;

                // 使用同步标志位
                final boolean[] screenshotSuccess = {false};
                final boolean[] hasFace = {false};
                final android.graphics.Bitmap[] capturedBitmap = {null};

                // 截图
                takeScreenshot(new ScreenshotCallback() {
                    @Override
                    public void onSuccess(android.graphics.Bitmap bitmap) {
                        screenshotSuccess[0] = true;
                        capturedBitmap[0] = bitmap;
                    }

                    @Override
                    public void onFailure() {
                        screenshotSuccess[0] = false;
                    }
                });

                // 等待截图完成
                Thread.sleep(300);

                if (screenshotSuccess[0] && capturedBitmap[0] != null) {
                    // 检查是否需要缓存兜底帧 (开始/中间/结尾),基于真实时间
                    for (int i = 0; i < 3; i++) {
                        if (!fallbackCaptured[i] && finalRealElapsedSec >= fallbackTargetTimes[i]) {
                            // 回收旧的兜底帧,缓存新的
                            if (fallbackBitmaps[i] != null && !fallbackBitmaps[i].isRecycled()) {
                                fallbackBitmaps[i].recycle();
                            }
                            fallbackBitmaps[i] = capturedBitmap[0].copy(android.graphics.Bitmap.Config.ARGB_8888, false);
                            fallbackCaptured[i] = true;
                            logD("📦 缓存兜底帧[" + i + "]: " + String.format("%.1f", finalRealElapsedSec) + "秒");
                        }
                    }

                    // 检测人脸
                    hasFace[0] = faceDetector.detectFace(capturedBitmap[0]);

                    if (hasFace[0]) {
                        // 检测到人脸,保存图片
                        String screenshotName = "侵权视频_" + String.format("%.1f", finalRealElapsedSec) + "秒_人脸";
                        logD("📸 截图 " + finalScreenshotIndex + "/" + targetCount + ": " + screenshotName + " ✅ 检测到人脸!");

                        // 保存图片
                        saveBitmapToGallery(capturedBitmap[0], screenshotName);

                        savedCount++;
                        screenshotIndex++;
                        // 不额外等待,ML Kit处理时间已足够作为间隔,立即继续扫描下一帧
                    } else {
                        // 没检测到人脸,继续扫描
                        logD("🔍 " + String.format("%.1f", finalRealElapsedSec) + "秒: 未检测到人脸,继续扫描...");
                        capturedBitmap[0].recycle(); // 释放bitmap
                    }
                }
                // 注意: 不再手动累加currentTime, System.currentTimeMillis()自动追踪真实时间
            }

            logD("✅ 智能截图完成! 共保存 " + savedCount + " 张图片");

            // 等待视频播放完成(基于真实时钟计算剩余时间)
            long videoEndTimeMs = startRealTimeMs + (long)(videoDurationSeconds * 1000L);
            long remainingMs = videoEndTimeMs - System.currentTimeMillis();
            if (remainingMs > 0) {
                logD("⏱️ 等待视频播放完成,剩余 " + String.format("%.1f", remainingMs / 1000.0) + " 秒...");
                Thread.sleep(remainingMs);
            }

            // 🆕 兜底逻辑: 人脸截图不足目标数量时,用缓存帧补足到目标数量
            if (savedCount < targetCount) {
                logD("⚠️ 人脸截图数量不足(已有" + savedCount + "张,目标" + targetCount + "张),用兜底帧补足...");
                String[] fallbackLabels = {"开始", "中间", "结尾"};
                for (int i = 0; i < 3 && savedCount < targetCount; i++) {
                    if (fallbackBitmaps[i] != null && !fallbackBitmaps[i].isRecycled()) {
                        String name = "侵权视频_" + fallbackLabels[i] + "_" + String.format("%.1f", fallbackTargetTimes[i]) + "秒_兜底";
                        saveBitmapToGallery(fallbackBitmaps[i], name);
                        savedCount++;
                        logD("📸 兜底截图已补充: " + name + " (现共" + savedCount + "张)");
                    } else {
                        logD("⚠️ 兜底帧[" + i + "] 未捕获,跳过");
                    }
                }
                logD("✅ 兜底补充完成,最终共 " + savedCount + " 张截图");
            }

            logD("✅ 视频播放和截图完成!");

            // 🆕 步骤: 点击暂停视频
            Thread.sleep(500);
            pauseDouyinVideo();

            // 🆕 步骤: 打开评论区
            Thread.sleep(1000);
            openDouyinComments();

            // 🆕 步骤: 等待评论区加载后截取评论取证截图
            Thread.sleep(2000);
            captureCommentEvidence();
            // captureCommentEvidence() 内部已调用 closeDouyinComments()

            // 🆕 步骤: 检查购物车链接并截图（在进入作者主页前）
            checkAndCaptureShoppingCart();

            // 🆕 步骤: 进入侵权作者主页
            navigateToAuthorProfile();

        } catch (Exception e) {
            logE("播放视频并截图失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 释放人脸检测器
            if (faceDetector != null) {
                faceDetector.release();
            }
            // 释放所有兜底帧
            for (android.graphics.Bitmap bmp : fallbackBitmaps) {
                if (bmp != null && !bmp.isRecycled()) {
                    bmp.recycle();
                }
            }
        }
    }

    /**
     * 🆕 点击暂停抖音视频
     * 点击 qde (播放/暂停覆盖层)
     */
    private void pauseDouyinVideo() {
        try {
            logD("⏸️ 准备点击暂停视频...");
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                logE("❌ pauseDouyinVideo: 无法获取根节点");
                return;
            }

            // 优先通过ID查找播放/暂停覆盖层
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> nodes =
                rootNode.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/qde");

            if (nodes != null && !nodes.isEmpty()) {
                for (android.view.accessibility.AccessibilityNodeInfo node : nodes) {
                    if (node.isVisibleToUser()) {
                        boolean clicked = node.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);
                        if (clicked) {
                            logD("✅ 已点击暂停 (通过ID: qde)");
                            rootNode.recycle();
                            return;
                        }
                    }
                }
            }

            // 兜底: 通过desc查找
            nodes = rootNode.findAccessibilityNodeInfosByText("播放视频");
            if (nodes != null && !nodes.isEmpty()) {
                android.view.accessibility.AccessibilityNodeInfo node = nodes.get(0);
                boolean clicked = node.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);
                if (clicked) {
                    logD("✅ 已点击暂停 (通过desc: 播放视频)");
                    rootNode.recycle();
                    return;
                }
            }

            // 兜底: 点击屏幕中间坐标
            logD("⚠️ 未找到暂停按钮节点，使用坐标点击屏幕中央");
            clickByCoordinates(540, 1100);
            logD("✅ 已点击暂停 (坐标点击)");
            rootNode.recycle();

        } catch (Exception e) {
            logE("❌ 点击暂停失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 🆕 打开抖音评论区
     * 点击 err (评论按钮)
     */
    private void openDouyinComments() {
        try {
            logD("💬 准备打开评论区...");
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                logE("❌ openDouyinComments: 无法获取根节点");
                return;
            }

            // 优先通过ID查找评论按钮
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> nodes =
                rootNode.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/err");

            if (nodes != null && !nodes.isEmpty()) {
                for (android.view.accessibility.AccessibilityNodeInfo node : nodes) {
                    if (node.isVisibleToUser() && node.isClickable()) {
                        boolean clicked = node.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);
                        if (clicked) {
                            logD("✅ 已打开评论区 (通过ID: err)");
                            rootNode.recycle();
                            return;
                        }
                    }
                }
            }

            // 兜底: 通过desc模糊查找"评论"
            nodes = rootNode.findAccessibilityNodeInfosByText("评论");
            if (nodes != null) {
                for (android.view.accessibility.AccessibilityNodeInfo node : nodes) {
                    if (node.isClickable() && node.isVisibleToUser()) {
                        boolean clicked = node.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);
                        if (clicked) {
                            logD("✅ 已打开评论区 (通过text: 评论)");
                            rootNode.recycle();
                            return;
                        }
                    }
                }
            }

            logE("❌ 未找到评论按钮");
            rootNode.recycle();

        } catch (Exception e) {
            logE("❌ 打开评论区失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // =====================================================================
    //  评论区取证
    // =====================================================================

    /**
     * 🆕 评论区取证入口
     * 策略：先滚动到底部（加载所有评论），再从底部向上扫描截图
     */
    private void captureCommentEvidence() {
        try {
            logD("💬 开始评论区取证...");

            // Step1: 读取评论总数，决定目标张数 + 最大滚动次数
            int totalComments = getCommentTotalCount();
            int targetScreenshots;
            int maxScrolls;
            if (totalComments == 0) {
                // 读取失败时保守处理：默认3张
                targetScreenshots = 3;
                maxScrolls = 10;
                logD("📊 评论总数读取失败，保守默认目标截图: 3张，最大滚动: 10次");
            } else if (totalComments <= 10) {
                targetScreenshots = 1;
                maxScrolls = 3;   // 10条以内，最多滚3次
            } else if (totalComments <= 30) {
                targetScreenshots = 2;
                maxScrolls = 6;   // 30条以内，最多滚6次
            } else {
                targetScreenshots = 3;
                maxScrolls = 10;  // 30条以上，最多滚10次
            }
            logD("📊 评论总数: " + totalComments + "，目标截图: " + targetScreenshots + "张，最大滚动: " + maxScrolls + "次");

            // Step2: 70+ 购买意图关键词
            String[] purchaseKeywords = {
                // === 已购买类 ===
                "买了","买过","买到了","已购","已买","入手了","入手",
                "下单了","已下单","刚下单","付款了","已付款",
                "收到了","收到货","到货了","到手了","拿到了",
                "回购","复购","又买了","再买","买了好几","买了两",
                "购入了","已购入","刚买","昨天买的","前天买的","买了一",
                // === 想购买类 ===
                "想买","想入手","想下单","好想买","特别想买",
                "准备买","打算买","计划买","考虑买","考虑入手",
                "在哪买","哪里买","去哪买","哪里有卖","怎么买",
                "求链接","发链接","链接发一下","购买链接","求购",
                "多少钱","什么价","价格","多少一个","多少一件","多少一套",
                "能买吗","可以买吗","有货吗","还有货吗","有库存吗",
                "同款在哪","哪里同款","哪里可以买",
                // === 使用体验类（暗示已购）===
                "好用","超好用","非常好用","真的好用","用了","用过",
                "效果好","效果不错","用起来","买对了","没买错",
                "值得买","值得入手","强烈推荐","推荐买",
                "后悔没早买","早买就好了","买亏了",
                "质量好","质量不错","质量差","不好用",
                // === 询问产品类（购买前咨询）===
                "安全吗","有没有毒","有没有甲醛","有异味吗",
                "质量怎么样","耐用吗","好不好","靠谱吗",
                "什么牌子","哪个牌子","正品吗","假的吗",
                "适合","推荐吗","值不值","划算吗","值得吗"
            };

            // ★ Step3: 强制先向下滚动一次（不管评论多少，确保评论区已加载）
            logD("⬇️ 先向下滚动一次，加载评论...");
            boolean canScrollMore = scrollCommentList();
            Thread.sleep(800);

            // Step4: 滚动到底部，沿途扫描截图（到底立即关闭，最多 maxScrolls 次兜底）
            int capturedCount = 0;

            for (int scroll = 0; scroll <= maxScrolls; scroll++) {

                // 第2次起才额外滚动；到底立即跳出关闭评论区
                if (scroll > 0) {
                    if (!canScrollMore) {
                        logD("⏹️ 评论已到底部，立即关闭评论区");
                        break;
                    }
                    canScrollMore = scrollCommentList();
                    Thread.sleep(800);
                }

                // 扫描当前可见评论节点
                android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
                if (rootNode == null) {
                    logE("❌ 评论扫描: 无法获取根节点");
                    break;
                }

                java.util.List<android.view.accessibility.AccessibilityNodeInfo> contentNodes =
                    rootNode.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/content");

                boolean foundInThisScroll = false;

                if (contentNodes != null) {
                    for (android.view.accessibility.AccessibilityNodeInfo node : contentNodes) {
                        if (!node.isVisibleToUser()) continue;

                        CharSequence textSeq = node.getText();
                        if (textSeq == null) continue;
                        String text = textSeq.toString();

                        // 关键词匹配
                        String matchedKeyword = null;
                        for (String kw : purchaseKeywords) {
                            if (text.contains(kw)) {
                                matchedKeyword = kw;
                                break;
                            }
                        }

                        if (matchedKeyword != null) {
                            logD("🔑 关键词匹配: [" + matchedKeyword + "] 评论: " + text);
                            foundInThisScroll = true;
                            break; // 一屏只截一张
                        }
                    }
                    for (android.view.accessibility.AccessibilityNodeInfo n : contentNodes) {
                        if (n != null) n.recycle();
                    }
                }
                rootNode.recycle();

                // 找到匹配则截图（沿途截图，不影响继续滚动到底）
                if (foundInThisScroll && capturedCount < targetScreenshots) {
                    capturedCount++;
                    final int idx = capturedCount;
                    logD("📸 截取购买意图评论截图 (" + idx + "/" + targetScreenshots + ")...");
                    takeScreenshotWithPrefix("评论取证_" + idx, new ScreenshotCallback() {
                        @Override public void onSuccess() { logD("✅ 评论截图" + idx + "保存成功"); }
                        @Override public void onFailure() { logE("❌ 评论截图" + idx + "保存失败"); }
                    });
                    Thread.sleep(600);
                }
            }

            // Step5: 兜底 — 如果一张都没截到，截一张当前状态
            if (capturedCount == 0) {
                logD("⚠️ 未找到购买意图评论，截兜底截图...");
                takeScreenshotWithPrefix("评论取证_兜底", new ScreenshotCallback() {
                    @Override public void onSuccess() { logD("✅ 评论兜底截图保存成功"); }
                    @Override public void onFailure() { logE("❌ 评论兜底截图保存失败"); }
                });
                Thread.sleep(600);
            }

            logD("🎉 评论取证完成，共截图: " + Math.max(capturedCount, 1) + "张");

            // Step6: 关闭评论区
            Thread.sleep(500);
            closeDouyinComments();

        } catch (Exception e) {
            logE("❌ 评论区取证失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 读取评论总数（从 title TextView 解析 "X条评论"）
     */
    private int getCommentTotalCount() {
        try {
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) return 0;

            java.util.List<android.view.accessibility.AccessibilityNodeInfo> titleNodes =
                rootNode.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/title");

            if (titleNodes != null) {
                for (android.view.accessibility.AccessibilityNodeInfo node : titleNodes) {
                    CharSequence textSeq = node.getText();
                    if (textSeq != null) {
                        String text = textSeq.toString(); // 例如 "10条评论"
                        // 提取数字
                        String numStr = text.replaceAll("[^0-9]", "");
                        if (!numStr.isEmpty()) {
                            int count = Integer.parseInt(numStr);
                            logD("📊 评论总数: " + count + " (原文: " + text + ")");
                            rootNode.recycle();
                            return count;
                        }
                    }
                }
                for (android.view.accessibility.AccessibilityNodeInfo n : titleNodes) {
                    if (n != null) n.recycle();
                }
            }
            rootNode.recycle();
        } catch (Exception e) {
            logE("读取评论总数失败: " + e.getMessage());
        }
        return 0; // 读不到则默认0（按≤10条处理，截1张）
    }

    /**
     * 滚动评论列表（向上滑动，让更多评论进入视野）
     */
    /**
     * ★ 将评论区一直滚动到底部（最多滚20次，直到无法继续为止）
     */
    private void scrollCommentToBottom() {
        try {
            int maxScrolls = 20;
            for (int i = 0; i < maxScrolls; i++) {
                boolean canScroll = false;

                // 优先用 RecyclerView ACTION_SCROLL_FORWARD
                android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
                if (rootNode != null) {
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> listNodes =
                        rootNode.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/rmw");
                    if (listNodes != null && !listNodes.isEmpty()) {
                        android.view.accessibility.AccessibilityNodeInfo listNode = listNodes.get(0);
                        canScroll = listNode.performAction(
                            android.view.accessibility.AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                        for (android.view.accessibility.AccessibilityNodeInfo n : listNodes) n.recycle();
                    }
                    rootNode.recycle();
                }

                if (!canScroll) {
                    // ACTION_SCROLL_FORWARD 返回 false → 已到底部；用手势再滑一下确认
                    android.graphics.Path path = new android.graphics.Path();
                    path.moveTo(540, 1800);
                    path.lineTo(540, 1000);
                    android.accessibilityservice.GestureDescription gesture =
                        new android.accessibilityservice.GestureDescription.Builder()
                            .addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(path, 0, 300))
                            .build();
                    dispatchGesture(gesture, null, null);
                    Thread.sleep(500);
                    logD("⬇️ 已滚动到评论底部（第" + (i + 1) + "次）");
                    break;
                }

                logD("⬇️ 评论向下滚动第 " + (i + 1) + " 次...");
                Thread.sleep(500); // 等待每次滚动加载
            }
            logD("✅ 评论区已拉到底部");
        } catch (Exception e) {
            logE("滚动到评论底部失败: " + e.getMessage());
        }
    }

    /**
     * 向上滚动评论列表一屏（从底部往上翻，查找更多关键词）
     */
    private void scrollCommentUp() {
        try {
            // 优先用 RecyclerView ACTION_SCROLL_BACKWARD
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode != null) {
                java.util.List<android.view.accessibility.AccessibilityNodeInfo> listNodes =
                    rootNode.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/rmw");
                if (listNodes != null && !listNodes.isEmpty()) {
                    android.view.accessibility.AccessibilityNodeInfo listNode = listNodes.get(0);
                    boolean scrolled = listNode.performAction(
                        android.view.accessibility.AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD);
                    if (scrolled) {
                        logD("⬆️ 评论向上滚动成功 (ACTION_SCROLL_BACKWARD)");
                        for (android.view.accessibility.AccessibilityNodeInfo n : listNodes) n.recycle();
                        rootNode.recycle();
                        return;
                    }
                    for (android.view.accessibility.AccessibilityNodeInfo n : listNodes) n.recycle();
                }
                rootNode.recycle();
            }

            // 兜底：手势向下滑（让列表向上滚动）
            android.graphics.Path path = new android.graphics.Path();
            path.moveTo(540, 1000);
            path.lineTo(540, 1800);
            android.accessibilityservice.GestureDescription gesture =
                new android.accessibilityservice.GestureDescription.Builder()
                    .addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(path, 0, 350))
                    .build();
            boolean dispatched = dispatchGesture(gesture, null, null);
            logD(dispatched ? "⬆️ 评论向上手势滚动成功" : "❌ 评论向上手势滚动失败");

        } catch (Exception e) {
            logE("向上滚动评论列表失败: " + e.getMessage());
        }
    }

    /**
     * 滚动评论列表向下一屏。
     * @return true=滚动成功（还有内容）；false=已到底部，无法继续滚动
     */
    private boolean scrollCommentList() {
        try {
            // 先尝试 RecyclerView 的 ACTION_SCROLL_FORWARD
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode != null) {
                java.util.List<android.view.accessibility.AccessibilityNodeInfo> listNodes =
                    rootNode.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/rmw");
                if (listNodes != null && !listNodes.isEmpty()) {
                    android.view.accessibility.AccessibilityNodeInfo listNode = listNodes.get(0);
                    boolean scrolled = listNode.performAction(
                        android.view.accessibility.AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                    for (android.view.accessibility.AccessibilityNodeInfo n : listNodes) n.recycle();
                    rootNode.recycle();
                    if (scrolled) {
                        logD("✅ 评论列表 ACTION_SCROLL_FORWARD 成功");
                        return true;
                    } else {
                        // ACTION_SCROLL_FORWARD 返回 false → 已到底部，无法继续滚动
                        logD("⏹️ 评论列表已到底部（ACTION_SCROLL_FORWARD 返回 false）");
                        return false;
                    }
                }
                rootNode.recycle();
            }

            // rmw 节点未找到时，兜底：手势向上滑（无法确认是否到底，返回 true 继续尝试）
            android.graphics.Path path = new android.graphics.Path();
            path.moveTo(540, 1800);
            path.lineTo(540, 1000);
            android.accessibilityservice.GestureDescription.StrokeDescription stroke =
                new android.accessibilityservice.GestureDescription.StrokeDescription(path, 0, 350);
            android.accessibilityservice.GestureDescription gesture =
                new android.accessibilityservice.GestureDescription.Builder().addStroke(stroke).build();
            boolean dispatched = dispatchGesture(gesture, null, null);
            logD(dispatched ? "✅ 评论手势滚动成功" : "❌ 评论手势滚动失败");
            return dispatched;

        } catch (Exception e) {
            logE("滚动评论列表失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 关闭抖音评论区（优先点击 back_btn 关闭按钮，兜底按返回键）
     */
    private void closeDouyinComments() {
        try {
            logD("🔙 关闭评论区...");
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode != null) {
                // Dump确认：关闭按钮ID = back_btn，Desc = "关闭"
                String[] closeIds = {
                    "com.ss.android.ugc.aweme:id/back_btn",
                    "com.ss.android.ugc.aweme:id/ej4",
                    "com.ss.android.ugc.aweme:id/cl_"
                };
                for (String id : closeIds) {
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> closeNodes =
                        rootNode.findAccessibilityNodeInfosByViewId(id);
                    if (closeNodes != null && !closeNodes.isEmpty()) {
                        android.view.accessibility.AccessibilityNodeInfo closeNode = closeNodes.get(0);
                        if (closeNode.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK)) {
                            logD("✅ 已关闭评论区 (ID: " + id + ")");
                            for (android.view.accessibility.AccessibilityNodeInfo n : closeNodes) n.recycle();
                            rootNode.recycle();
                            return;
                        }
                        for (android.view.accessibility.AccessibilityNodeInfo n : closeNodes) n.recycle();
                    }
                }
                rootNode.recycle();
            }
            // 兜底：按返回键
            performGlobalAction(GLOBAL_ACTION_BACK);
            logD("✅ 已按返回键关闭评论区");
        } catch (Exception e) {
            logE("关闭评论区失败: " + e.getMessage());
        }
    }

    // =====================================================================
    //  购物车取证
    // =====================================================================

    /**
     * 🛒 检查视频页面是否存在购物车/产品链接，如果有则点击、截图、关闭
     * 检测依据：Dump确认 p+8 容器为可点击产品区域，内含 3h5（"已售X"文本）
     * 调用时机：关闭评论区之后、进入作者主页之前
     */
    private void checkAndCaptureShoppingCart() {
        try {
            logD("🛒 检查是否存在购物车/产品链接...");
            Thread.sleep(800); // 等待评论区关闭动画稳定

            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                logD("⚠️ 购物车检查: 无法获取根节点，跳过");
                return;
            }

            boolean shoppingCartFound = false;

            // === 检测方法1: 查找 3h5 节点（含"已售"文字），确认商品链接存在 ===
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> soldNodes =
                rootNode.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/3h5");
            if (soldNodes != null && !soldNodes.isEmpty()) {
                for (android.view.accessibility.AccessibilityNodeInfo soldNode : soldNodes) {
                    CharSequence text = soldNode.getText();
                    if (text != null && text.toString().contains("已售")) {
                        logD("🎯 检测到购物车产品区域（" + text + "），准备点击...");
                        shoppingCartFound = true;
                        // 向上找可点击的父节点（即 p+8 容器）并点击
                        android.view.accessibility.AccessibilityNodeInfo clickTarget =
                            findClickableParent(soldNode);
                        if (clickTarget != null) {
                            clickTarget.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);
                            logD("✅ 点击购物车产品链接成功（via 已售节点父容器）");
                            clickTarget.recycle();
                        } else {
                            // 无法找到父节点时直接点击该节点所在坐标
                            android.graphics.Rect bounds = new android.graphics.Rect();
                            soldNode.getBoundsInScreen(bounds);
                            int cx = (bounds.left + bounds.right) / 2;
                            int cy = (bounds.top + bounds.bottom) / 2;
                            android.graphics.Path tapPath = new android.graphics.Path();
                            tapPath.moveTo(cx, cy);
                            tapPath.lineTo(cx, cy);
                            android.accessibilityservice.GestureDescription tapGesture =
                                new android.accessibilityservice.GestureDescription.Builder()
                                    .addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(tapPath, 0, 100))
                                    .build();
                            dispatchGesture(tapGesture, null, null);
                            logD("✅ 坐标点击购物车区域: (" + cx + ", " + cy + ")");
                        }
                        for (android.view.accessibility.AccessibilityNodeInfo n : soldNodes) n.recycle();
                        rootNode.recycle();
                        break;
                    }
                }
                if (!shoppingCartFound) {
                    for (android.view.accessibility.AccessibilityNodeInfo n : soldNodes) n.recycle();
                }
            }

            // === 检测方法2: 直接查找 p+8 容器（备用） ===
            if (!shoppingCartFound) {
                java.util.List<android.view.accessibility.AccessibilityNodeInfo> cartNodes =
                    rootNode.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/p+8");
                if (cartNodes != null && !cartNodes.isEmpty()) {
                    for (android.view.accessibility.AccessibilityNodeInfo node : cartNodes) {
                        if (node.isVisibleToUser() && node.isClickable()) {
                            logD("🎯 检测到购物车容器（p+8），准备点击...");
                            shoppingCartFound = true;
                            node.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);
                            logD("✅ 点击购物车容器成功");
                            for (android.view.accessibility.AccessibilityNodeInfo n : cartNodes) n.recycle();
                            rootNode.recycle();
                            break;
                        }
                    }
                    if (!shoppingCartFound) {
                        for (android.view.accessibility.AccessibilityNodeInfo n : cartNodes) n.recycle();
                    }
                }
            }

            // === 检测方法3: 通过Desc包含"购物车"节点 ===
            if (!shoppingCartFound) {
                android.view.accessibility.AccessibilityNodeInfo cartNode =
                    findNodeByDescContains(rootNode, "购物车");
                if (cartNode != null) {
                    logD("🎯 检测到购物车节点（Desc含'购物车'），准备点击...");
                    shoppingCartFound = true;
                    cartNode.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);
                    logD("✅ 点击购物车节点成功");
                    cartNode.recycle();
                    rootNode.recycle();
                }
            }

            if (!shoppingCartFound) {
                logD("ℹ️ 未检测到购物车/产品链接，跳过此步骤");
                rootNode.recycle();
                return;
            }

            // 等待购物车/产品页面加载
            logD("⏱️ 等待购物车页面加载(2.5秒)...");
            Thread.sleep(2500);

            // 截图1：产品页初始状态
            logD("📸 截取购物车页面取证截图1...");
            takeScreenshotWithPrefix("购物车取证_1", new ScreenshotCallback() {
                @Override public void onSuccess() { logD("✅ 购物车截图1保存成功"); }
                @Override public void onFailure() { logE("❌ 购物车截图1保存失败"); }
            });
            Thread.sleep(700);

            // 向下滚动，查看更多商品信息
            logD("⬇️ 向下滚动查看更多购物车内容...");
            android.graphics.Path scrollPath = new android.graphics.Path();
            scrollPath.moveTo(540, 1600);
            scrollPath.lineTo(540, 900);
            android.accessibilityservice.GestureDescription scrollGesture =
                new android.accessibilityservice.GestureDescription.Builder()
                    .addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(
                        scrollPath, 0, 350))
                    .build();
            dispatchGesture(scrollGesture, null, null);
            Thread.sleep(800);

            // 截图2：滚动后的商品详情
            logD("📸 截取购物车页面取证截图2...");
            takeScreenshotWithPrefix("购物车取证_2", new ScreenshotCallback() {
                @Override public void onSuccess() { logD("✅ 购物车截图2保存成功"); }
                @Override public void onFailure() { logE("❌ 购物车截图2保存失败"); }
            });
            Thread.sleep(700);

            // ★ OCR识别循环：先强制滚动几次展示内容，然后每次截图OCR识别内容区"进店"
            // 注意：底部固定导航栏也有"进店"文字（y≈2270），需过滤，只认内容区（y < 2100）
            logD("⬇️ 开始OCR识别循环，寻找内容区【进店】按钮...");
            boolean enterShopClicked = false;
            final int MAX_SHOP_SCROLL = 15;
            // 强制先滚动至少MIN_SCROLL次，确保录屏过程中有下拉动作，且能越过底部bar区域
            final int MIN_SCROLL_BEFORE_DETECT = 2;
            for (int shopScroll = 0; shopScroll < MAX_SHOP_SCROLL && !enterShopClicked; shopScroll++) {
                final int currentScroll = shopScroll;

                // 每次循环都先滚动（包括第0次），让录屏能看到下拉过程
                {
                    logD("⬇️ 第" + shopScroll + "次下滑...");
                    android.graphics.Path shopScrollPath = new android.graphics.Path();
                    shopScrollPath.moveTo(540, 1600);
                    shopScrollPath.lineTo(540, 900);
                    android.accessibilityservice.GestureDescription shopScrollGesture =
                        new android.accessibilityservice.GestureDescription.Builder()
                            .addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(
                                shopScrollPath, 0, 350))
                            .build();
                    dispatchGesture(shopScrollGesture, null, null);
                    Thread.sleep(800);
                }

                // 截图（不存相册，仅用于OCR）
                final android.graphics.Bitmap[] ocrBitmapHolder = {null};
                takeScreenshot(new ScreenshotCallback() {
                    @Override
                    public void onSuccess(android.graphics.Bitmap bitmap) {
                        ocrBitmapHolder[0] = bitmap;
                    }
                    @Override
                    public void onFailure() { /* bitmap保持null */ }
                });
                Thread.sleep(600); // 等待截图回调

                if (ocrBitmapHolder[0] == null) {
                    logD("⚠️ 第" + currentScroll + "次截图失败，继续下一轮");
                    continue;
                }

                // OCR识别"进店" —— 使用全量接口，自行过滤底部导航栏的结果
                // 底部导航栏"进店": x ≈ 78（左侧小图标），内容区"进店": x ≈ 985（右侧按钮）
                final int[] enterX = {-1};
                final int[] enterY = {-1};
                final boolean[] ocrDone = {false};
                OcrHelper shopOcr = new OcrHelper(message -> logD(message));
                shopOcr.findAllTextPositions(ocrBitmapHolder[0], "进店", new OcrHelper.OcrMultiCallback() {
                    @Override
                    public void onSuccess(java.util.List<OcrHelper.TextMatch> matches) {
                        logD("📋 OCR共找到 " + matches.size() + " 个【进店】:");
                        for (OcrHelper.TextMatch m : matches) {
                            logD("   → x=" + m.center.x + " y=" + m.center.y + " (文字: " + m.text + ")");
                        }
                        // 过滤规则：
                        //   1. 文字必须精确等于"进店"（排除"进店 客服 购物车"这种合并块）
                        //   2. x > 500（内容区按钮在右侧，底部导航栏整体中心 x≈210）
                        for (OcrHelper.TextMatch m : matches) {
                            String trimmed = m.text.trim();
                            boolean exactMatch = trimmed.equals("进店");
                            boolean inContentArea = m.center.x > 500;
                            logD("   → 过滤检查: text='" + trimmed + "' exactMatch=" + exactMatch
                                    + " x=" + m.center.x + " inContentArea=" + inContentArea);
                            if (exactMatch && inContentArea) {
                                enterX[0] = m.center.x;
                                enterY[0] = m.center.y;
                                logD("✅ 选中内容区【进店】坐标=(" + enterX[0] + "," + enterY[0] + ")");
                                break;
                            }
                        }
                        if (enterX[0] < 0) {
                            logD("⚠️ 未找到有效的内容区【进店】（底部导航栏合并块已过滤），继续滚动...");
                        }
                        ocrDone[0] = true;
                        shopOcr.release();
                        ocrBitmapHolder[0].recycle();
                    }
                    @Override
                    public void onFailure(String error) {
                        logD("⏳ 第" + (currentScroll + 1) + "次未识别到【进店】，继续滚动...");
                        ocrDone[0] = true;
                        shopOcr.release();
                        ocrBitmapHolder[0].recycle();
                    }
                });
                Thread.sleep(1500); // 等待OCR回调

                if (enterX[0] > 0 && enterY[0] > 0) {
                    // 找到内容区"进店"！先截图3，再坐标点击
                    Thread.sleep(200);
                    logD("📸 截取购物车页面取证截图3（店铺信息）...");
                    takeScreenshotWithPrefix("购物车取证_3", new ScreenshotCallback() {
                        @Override public void onSuccess() { logD("✅ 购物车截图3保存成功"); }
                        @Override public void onFailure() { logE("❌ 购物车截图3保存失败"); }
                    });
                    Thread.sleep(700);

                    logD("🏪 点击【进店】坐标=(" + enterX[0] + "," + enterY[0] + ")...");
                    clickByCoordinates(enterX[0], enterY[0]);
                    enterShopClicked = true;
                    logD("✅ 已点击【进店】");
                }
            }

            if (!enterShopClicked) {
                logE("❌ 滚动" + MAX_SHOP_SCROLL + "次后OCR仍未找到【进店】，跳过截图3和店铺截图");
            }

            if (enterShopClicked) {
                // 智能等待店铺页面加载（检测 hnd 或 hmw 出现，最多等5秒）
                logD("⏱️ 智能等待店铺页面加载...");
                boolean shopPageLoaded = false;
                for (int waitCount = 0; waitCount < 25; waitCount++) {
                    Thread.sleep(200);
                    android.view.accessibility.AccessibilityNodeInfo waitRoot = getRootInActiveWindow();
                    if (waitRoot != null) {
                        // 检测方式1：店铺顶部信息栏 ID = hnd
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> hndNodes =
                            waitRoot.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/hnd");
                        if (hndNodes != null && !hndNodes.isEmpty()) {
                            for (android.view.accessibility.AccessibilityNodeInfo n : hndNodes) n.recycle();
                            shopPageLoaded = true;
                            logD("✅ 检测到店铺页面标志【hnd】，页面已加载 (等待" + (waitCount * 200) + "ms)");
                        }
                        // 检测方式2：店铺统计数据行 ID = hmw
                        if (!shopPageLoaded) {
                            java.util.List<android.view.accessibility.AccessibilityNodeInfo> hmwNodes =
                                waitRoot.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/hmw");
                            if (hmwNodes != null && !hmwNodes.isEmpty()) {
                                for (android.view.accessibility.AccessibilityNodeInfo n : hmwNodes) n.recycle();
                                shopPageLoaded = true;
                                logD("✅ 检测到店铺页面标志【hmw】，页面已加载 (等待" + (waitCount * 200) + "ms)");
                            }
                        }
                        waitRoot.recycle();
                    }
                    if (shopPageLoaded) break;
                }

                if (!shopPageLoaded) {
                    logD("⚠️ 等待5秒仍未检测到店铺页面标志，强制等待后截图");
                    Thread.sleep(500);
                } else {
                    // 头部已加载，额外等待1.5秒让下方商品列表也渲染完成
                    logD("⏳ 店铺头部已加载，等待1.5秒让商品列表渲染完成...");
                    Thread.sleep(1500);
                }

                // 截图：店铺主页
                logD("📸 截取店铺主页取证截图...");
                takeScreenshotWithPrefix("购物车取证_店铺", new ScreenshotCallback() {
                    @Override public void onSuccess() { logD("✅ 店铺主页截图保存成功"); }
                    @Override public void onFailure() { logE("❌ 店铺主页截图保存失败"); }
                });
                Thread.sleep(700);

                logD("✅ 店铺主页截图完成，后续操作继续在店铺页进行");

                // === 点击店铺信息卡片区域，进入店铺详情页 ===
                // 从dump分析：hk8内的店铺名称ViewGroup坐标约 [201,327]->[829,445]，中心≈(400,386)
                // 注意：顶部hmz(84,175)是导航栏logo，不触发跳转；必须点击内容区店铺卡片
                logD("🏪 点击店铺信息卡片，进入店铺详情页...");
                clickByCoordinates(400, 386);

                // 详情页是全屏 WebView（dump确认无可访问子节点），无法用无障碍ID检测加载
                // 使用 OCR 轮询检测页面特征文字，任意命中即确认页面已渲染（最多等5秒）
                logD("⏳ 等待店铺详情页（WebView）加载...");
                final String[] detailKeywords = {"店铺口碑", "资质证照", "店铺人气", "店铺详情"};
                boolean detailPageLoaded = false;
                for (int detailWait = 0; detailWait < 5 && !detailPageLoaded; detailWait++) {
                    Thread.sleep(1000);
                    final boolean[] ocrDone = {false};
                    final String[] matchedKw = {null};
                    takeScreenshot(new ScreenshotCallback() {
                        @Override
                        public void onSuccess(android.graphics.Bitmap bitmap) {
                            if (bitmap == null) { ocrDone[0] = true; return; }
                            OcrHelper detailOcr = new OcrHelper(message -> logD(message));
                            detailOcr.findAnyTextPosition(bitmap, detailKeywords, new OcrHelper.OcrAnyCallback() {
                                @Override
                                public void onSuccess(String keyword) {
                                    matchedKw[0] = keyword;
                                    ocrDone[0] = true;
                                    detailOcr.release();
                                    bitmap.recycle();
                                }
                                @Override
                                public void onFailure(String error) {
                                    ocrDone[0] = true;
                                    detailOcr.release();
                                    bitmap.recycle();
                                }
                            });
                        }
                        @Override
                        public void onFailure() { ocrDone[0] = true; }
                    });
                    // 等OCR完成（最多2秒）
                    long ocrStart = System.currentTimeMillis();
                    while (!ocrDone[0] && System.currentTimeMillis() - ocrStart < 2000) {
                        Thread.sleep(100);
                    }
                    if (matchedKw[0] != null) {
                        logD("✅ 店铺详情页已检测到关键词[" + matchedKw[0] + "]（第" + (detailWait + 1) + "秒），准备截图");
                        detailPageLoaded = true;
                    } else {
                        logD("⌛ 第" + (detailWait + 1) + "秒：详情页内容未就绪，继续等待...");
                    }
                }
                if (!detailPageLoaded) {
                    logD("⚠️ 5秒后仍未检测到详情页内容，强制继续截图");
                }

                // 截图：店铺详情页
                logD("📸 截取店铺详情页取证截图...");
                takeScreenshotWithPrefix("购物车取证_详情", new ScreenshotCallback() {
                    @Override public void onSuccess() { logD("✅ 店铺详情页截图保存成功"); }
                    @Override public void onFailure() { logE("❌ 店铺详情页截图保存失败"); }
                });
                Thread.sleep(700);
                logD("✅ 店铺详情页截图完成");

                // ─────────────────────────────────────────────────────────
                // 步骤：OCR找"资质证照"→点击→等加载→截图
                // 详情页是全量WebView，用OCR定位"资质证照"文字坐标，手势点击
                // ─────────────────────────────────────────────────────────
                logD("🔍 OCR查找'资质证照'入口...");
                final boolean[] zizhiFound = {false};
                final int[] zizhiX = {-1};
                final int[] zizhiY = {-1};
                final boolean[] zizhiOcrDone = {false};
                takeScreenshot(new ScreenshotCallback() {
                    @Override
                    public void onSuccess(android.graphics.Bitmap bitmap) {
                        if (bitmap == null) { zizhiOcrDone[0] = true; return; }
                        OcrHelper zizhiOcr = new OcrHelper(message -> logD(message));
                        zizhiOcr.findTextPosition(bitmap, "资质证照", new OcrHelper.OcrCallback() {
                            @Override
                            public void onSuccess(OcrHelper.TextMatch match) {
                                zizhiFound[0] = true;
                                zizhiX[0] = match.center.x;
                                zizhiY[0] = match.center.y;
                                logD("✅ 找到'资质证照'坐标=(" + zizhiX[0] + "," + zizhiY[0] + ")");
                                zizhiOcrDone[0] = true;
                                zizhiOcr.release();
                                bitmap.recycle();
                            }
                            @Override
                            public void onFailure(String error) {
                                logD("⚠️ 未找到'资质证照': " + error);
                                zizhiOcrDone[0] = true;
                                zizhiOcr.release();
                                bitmap.recycle();
                            }
                        });
                    }
                    @Override
                    public void onFailure() { zizhiOcrDone[0] = true; }
                });
                long zizhiWait = System.currentTimeMillis();
                while (!zizhiOcrDone[0] && System.currentTimeMillis() - zizhiWait < 2000) {
                    Thread.sleep(100);
                }

                if (zizhiFound[0]) {
                    // 手势点击"资质证照"
                    logD("👆 点击'资质证照'坐标=(" + zizhiX[0] + "," + zizhiY[0] + ")...");
                    android.graphics.Path zizhiPath = new android.graphics.Path();
                    zizhiPath.moveTo(zizhiX[0], zizhiY[0]);
                    zizhiPath.lineTo(zizhiX[0], zizhiY[0]);
                    android.accessibilityservice.GestureDescription zizhiGesture =
                        new android.accessibilityservice.GestureDescription.Builder()
                            .addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(zizhiPath, 0, 100))
                            .build();
                    dispatchGesture(zizhiGesture, null, null);
                    Thread.sleep(500);

                    // OCR轮询等待资质页加载：识别到任意关键词即确认
                    logD("⏳ 等待资质页加载（OCR识别'商家资质'/'营业执照'/'企业类型'/'法人姓名'）...");
                    final String[] zizhiPageKeywords = {"商家资质", "营业执照", "企业类型", "法人姓名"};
                    boolean zizhiPageLoaded = false;
                    for (int zw = 0; zw < 6 && !zizhiPageLoaded; zw++) {
                        Thread.sleep(1000);
                        final boolean[] zwDone = {false};
                        final String[] zwMatched = {null};
                        takeScreenshot(new ScreenshotCallback() {
                            @Override
                            public void onSuccess(android.graphics.Bitmap bitmap) {
                                if (bitmap == null) { zwDone[0] = true; return; }
                                OcrHelper zwOcr = new OcrHelper(message -> logD(message));
                                zwOcr.findAnyTextPosition(bitmap, zizhiPageKeywords, new OcrHelper.OcrAnyCallback() {
                                    @Override
                                    public void onSuccess(String keyword) {
                                        zwMatched[0] = keyword;
                                        zwDone[0] = true;
                                        zwOcr.release();
                                        bitmap.recycle();
                                    }
                                    @Override
                                    public void onFailure(String error) {
                                        zwDone[0] = true;
                                        zwOcr.release();
                                        bitmap.recycle();
                                    }
                                });
                            }
                            @Override
                            public void onFailure() { zwDone[0] = true; }
                        });
                        long zwStart = System.currentTimeMillis();
                        while (!zwDone[0] && System.currentTimeMillis() - zwStart < 2000) {
                            Thread.sleep(100);
                        }
                        if (zwMatched[0] != null) {
                            logD("✅ 资质页已加载，检测到关键词[" + zwMatched[0] + "]（第" + (zw + 1) + "秒），准备截图");
                            zizhiPageLoaded = true;
                        } else {
                            logD("⌛ 第" + (zw + 1) + "秒：资质页未就绪，继续等待...");
                        }
                    }
                    if (!zizhiPageLoaded) {
                        logD("⚠️ 6秒后仍未检测到资质页内容，强制继续截图");
                    }

                    // 截图：资质证照页
                    logD("📸 截取资质证照页取证截图...");
                    takeScreenshotWithPrefix("购物车取证_资质", new ScreenshotCallback() {
                        @Override public void onSuccess() { logD("✅ 资质证照页截图保存成功"); }
                        @Override public void onFailure() { logE("❌ 资质证照页截图保存失败"); }
                    });
                    Thread.sleep(700);
                    logD("✅ 资质证照页截图完成");
                } else {
                    logD("⚠️ 未找到'资质证照'入口，跳过资质截图");
                }
            }

            // 智能返回视频页：持续按返回键，直到检测到视频播放页特征为止
            // 视频播放页特征：ID=qde（播放/暂停覆盖层）或 ID=k9m（作者头像）
            logD("🔙 开始智能返回视频页（最多返回6次）...");
            boolean backToVideoPage = false;
            for (int backCount = 0; backCount < 6 && !backToVideoPage; backCount++) {
                performGlobalAction(GLOBAL_ACTION_BACK);
                Thread.sleep(1100);

                // 检测是否已回到视频播放页
                android.view.accessibility.AccessibilityNodeInfo checkRoot = getRootInActiveWindow();
                if (checkRoot != null) {
                    // 特征1：查找视频播放/暂停覆盖层 qde
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> qdeNodes =
                        checkRoot.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/qde");
                    // 特征2：查找作者头像 k9m
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> k9mNodes =
                        checkRoot.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/k9m");
                    checkRoot.recycle();

                    boolean hasQde = qdeNodes != null && !qdeNodes.isEmpty();
                    boolean hasK9m = k9mNodes != null && !k9mNodes.isEmpty();
                    logD("🔍 返回检测第" + (backCount + 1) + "次: qde=" + hasQde + " k9m=" + hasK9m);

                    if (hasQde || hasK9m) {
                        backToVideoPage = true;
                        logD("✅ 已回到视频播放页（第" + (backCount + 1) + "次返回后检测到）");
                    }
                }
            }
            if (!backToVideoPage) {
                logE("⚠️ 返回6次后仍未检测到视频页特征，继续执行后续流程");
            }

        } catch (Exception e) {
            logE("❌ 购物车取证失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 向上遍历节点树，找到第一个可点击的父节点
     */
    private android.view.accessibility.AccessibilityNodeInfo findClickableParent(
            android.view.accessibility.AccessibilityNodeInfo node) {
        android.view.accessibility.AccessibilityNodeInfo parent = node.getParent();
        while (parent != null) {
            if (parent.isClickable()) {
                return parent;
            }
            android.view.accessibility.AccessibilityNodeInfo grandParent = parent.getParent();
            parent.recycle();
            parent = grandParent;
        }
        return null;
    }

    /**
     * 关闭评论区后，点击视频播放界面中的作者头像/昵称，进入侵权作者主页
     * 策略：依次尝试多个已知ID，兜底使用坐标点击（抖音作者名通常在屏幕左下角）
     */
    private void navigateToAuthorProfile() {
        try {
            logD("👤 准备进入侵权作者主页...");
            Thread.sleep(1500); // 等待评论区关闭动画完成，视频播放界面出现

            // ★ Step1: 点击作者头像（不再提前return，统一在下方等待+截图）
            boolean clicked = false;
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode != null) {
                // Dump确认：作者头像ID = user_avatar，位于右侧 [915,911]→[1059,1055]
                String[] authorIds = {
                    "com.ss.android.ugc.aweme:id/user_avatar",  // ✅ Dump确认的头像ID
                    "com.ss.android.ugc.aweme:id/iv_avatar",    // 备用
                    "com.ss.android.ugc.aweme:id/author_anim_icon",
                    "com.ss.android.ugc.aweme:id/expand_avatar",
                    "com.ss.android.ugc.aweme:id/avatar_cover"
                };

                for (String id : authorIds) {
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> nodes =
                        rootNode.findAccessibilityNodeInfosByViewId(id);
                    if (nodes != null && !nodes.isEmpty()) {
                        android.view.accessibility.AccessibilityNodeInfo node = nodes.get(0);
                        if (node.isVisibleToUser() &&
                            node.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK)) {
                            logD("✅ 点击作者头像成功 (ID: " + id + ")");
                            clicked = true;
                            for (android.view.accessibility.AccessibilityNodeInfo n : nodes) n.recycle();
                            break;
                        }
                        for (android.view.accessibility.AccessibilityNodeInfo n : nodes) n.recycle();
                    }
                    if (clicked) break;
                }

                if (!clicked) {
                    // 尝试通过desc包含"头像"的节点
                    android.view.accessibility.AccessibilityNodeInfo avatarNode = findNodeByDescContains(rootNode, "头像");
                    if (avatarNode != null) {
                        if (avatarNode.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK)) {
                            logD("✅ 点击作者头像成功 (Desc含'头像')");
                            clicked = true;
                        }
                        avatarNode.recycle();
                    }
                }
                rootNode.recycle();
            }

            if (!clicked) {
                // 兜底：坐标点击（Dump确认：user_avatar中心点约 x=987, y=983）
                logD("⚠️ 未找到作者头像节点，使用坐标兜底点击...");
                android.graphics.Path path = new android.graphics.Path();
                path.moveTo(987, 983);
                path.lineTo(987, 983);
                android.accessibilityservice.GestureDescription gesture =
                    new android.accessibilityservice.GestureDescription.Builder()
                        .addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(path, 0, 100))
                        .build();
                boolean dispatched = dispatchGesture(gesture, null, null);
                logD(dispatched ? "✅ 坐标点击作者头像成功" : "❌ 坐标点击作者头像失败");
            }

            // ★ Step2: 智能等待作者主页加载（最多轮询10次，每次500ms，共5秒）
            // Dump确认：主页特征节点 k9m(用户头像大图) 或 v17(关注按钮)
            logD("⏳ 等待作者主页加载...");
            boolean profileLoaded = false;
            for (int attempt = 0; attempt < 10; attempt++) {
                Thread.sleep(500);
                android.view.accessibility.AccessibilityNodeInfo checkRoot = getRootInActiveWindow();
                if (checkRoot != null) {
                    // 检测方法1：k9m（主页大头像，Dump确认 Desc="用户头像"）
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> markers =
                        checkRoot.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/k9m");
                    if (markers != null && !markers.isEmpty()) {
                        logD("✅ 检测到主页特征(k9m 用户头像大图)，主页已加载，耗时约 " + ((attempt + 1) * 500) + "ms");
                        profileLoaded = true;
                        for (android.view.accessibility.AccessibilityNodeInfo n : markers) n.recycle();
                        checkRoot.recycle();
                        break;
                    }
                    // 检测方法2：v17（主页"关注"按钮，Dump确认 Bounds=[48,1104]→[522,1248]）
                    markers = checkRoot.findAccessibilityNodeInfosByViewId("com.ss.android.ugc.aweme:id/v17");
                    if (markers != null && !markers.isEmpty()) {
                        logD("✅ 检测到主页特征(v17 关注按钮)，主页已加载，耗时约 " + ((attempt + 1) * 500) + "ms");
                        profileLoaded = true;
                        for (android.view.accessibility.AccessibilityNodeInfo n : markers) n.recycle();
                        checkRoot.recycle();
                        break;
                    }
                    checkRoot.recycle();
                }
            }

            if (!profileLoaded) {
                logD("⚠️ 等待5秒后仍未检测到主页特征，继续执行截图（兜底）");
            }

            // ★ Step3: 截图取证
            logD("📸 截取作者主页取证截图...");
            takeScreenshotWithPrefix("作者主页取证", new ScreenshotCallback() {
                @Override public void onSuccess() { logD("✅ 作者主页截图保存成功"); }
                @Override public void onFailure() { logE("❌ 作者主页截图保存失败"); }
            });
            Thread.sleep(500);

            // ★ Step4: 检查作者昵称下方是否有【店铺账号】标签，有则点击并截图
            // Dump确认：s+x(昵称)[408,376→738,464]，店铺账号(clickable)[408,464→1032,512]
            logD("🔍 检查作者主页是否有【店铺账号】标签...");
            android.view.accessibility.AccessibilityNodeInfo profileRoot = getRootInActiveWindow();
            if (profileRoot != null) {
                android.view.accessibility.AccessibilityNodeInfo shopAccountNode = null;

                // 优先通过Desc查找（"抖音组织认证：店铺账号"）
                shopAccountNode = findNodeByDescContains(profileRoot, "店铺账号");

                // 备用：通过文字查找（" 店铺账号"）
                if (shopAccountNode == null) {
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> textNodes =
                        profileRoot.findAccessibilityNodeInfosByText("店铺账号");
                    if (textNodes != null && !textNodes.isEmpty()) {
                        shopAccountNode = textNodes.get(0);
                    }
                }

                if (shopAccountNode != null) {
                    logD("✅ 检测到【店铺账号】标签，准备点击...");

                    // 先尝试无障碍API直接点击
                    boolean shopClicked = shopAccountNode.performAction(
                        android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);

                    if (!shopClicked) {
                        // 兜底：坐标点击（Dump确认中心约 (720, 488)）
                        android.graphics.Rect bounds = new android.graphics.Rect();
                        shopAccountNode.getBoundsInScreen(bounds);
                        int cx = bounds.isEmpty() ? 720 : (bounds.left + bounds.right) / 2;
                        int cy = bounds.isEmpty() ? 488 : (bounds.top + bounds.bottom) / 2;
                        android.graphics.Path tapPath = new android.graphics.Path();
                        tapPath.moveTo(cx, cy);
                        tapPath.lineTo(cx, cy);
                        android.accessibilityservice.GestureDescription tapGesture =
                            new android.accessibilityservice.GestureDescription.Builder()
                                .addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(
                                    tapPath, 0, 50))
                                .build();
                        dispatchGesture(tapGesture, null, null);
                        logD("✅ 坐标点击【店铺账号】坐标=(" + cx + "," + cy + ")");
                    } else {
                        logD("✅ 无障碍API点击【店铺账号】成功");
                    }
                    shopAccountNode.recycle();

                    // ── OCR智能等待店铺账号详情页加载（最多6秒）──
                    // Dump确认：纯WebView，用OCR检测"认证说明"/"企业认证详情"/"企业名称"/"资质证照"
                    logD("⏳ 等待店铺账号详情页加载（OCR识别）...");
                    String[] shopAccountKeywords = {"认证说明", "企业认证详情", "企业名称", "资质证照"};
                    boolean shopPageLoaded = false;
                    for (int sec = 1; sec <= 6; sec++) {
                        Thread.sleep(1000);
                        final boolean[] hit = {false};
                        final String[] hitWord = {""};
                        final boolean[] ocrDone = {false};
                        takeScreenshot(new ScreenshotCallback() {
                            @Override
                            public void onSuccess(android.graphics.Bitmap bitmap) {
                                if (bitmap == null) { ocrDone[0] = true; return; }
                                OcrHelper saOcr = new OcrHelper(message -> logD(message));
                                saOcr.findAnyTextPosition(bitmap, shopAccountKeywords, new OcrHelper.OcrAnyCallback() {
                                    @Override
                                    public void onSuccess(String keyword) {
                                        hit[0] = true;
                                        hitWord[0] = keyword;
                                        ocrDone[0] = true;
                                        saOcr.release();
                                        bitmap.recycle();
                                    }
                                    @Override
                                    public void onFailure(String error) {
                                        ocrDone[0] = true;
                                        saOcr.release();
                                        bitmap.recycle();
                                    }
                                });
                            }
                            @Override public void onFailure() { ocrDone[0] = true; }
                        });
                        // 等待OCR回调完成（最多2秒）
                        for (int w = 0; w < 20 && !ocrDone[0]; w++) Thread.sleep(100);
                        if (hit[0]) {
                            logD("✅ 店铺账号详情页已加载，检测到[" + hitWord[0] + "]（第" + sec + "秒），准备截图");
                            shopPageLoaded = true;
                            break;
                        } else {
                            logD("⌛ 第" + sec + "秒：店铺账号详情页未就绪，继续等待...");
                        }
                    }
                    if (!shopPageLoaded) {
                        logD("⚠️ 等待6秒后仍未检测到页面特征，强制截图");
                    }

                    // ── 截图取证 ──
                    logD("📸 截取店铺账号详情页取证截图...");
                    takeScreenshotWithPrefix("店铺账号取证", new ScreenshotCallback() {
                        @Override public void onSuccess() { logD("✅ 店铺账号详情页截图保存成功"); }
                        @Override public void onFailure() { logE("❌ 店铺账号详情页截图保存失败"); }
                    });
                    Thread.sleep(800);

                    // ── 点击资质证照图片 ──
                    // Dump确认：资质证照图片(clickable WebView节点) Bounds=[339,1071]→[633,1278]，中心=(486,1174)
                    logD("🖼️ 点击资质证照图片坐标=(486,1174)...");
                    android.graphics.Path imgTapPath = new android.graphics.Path();
                    imgTapPath.moveTo(486, 1174);
                    imgTapPath.lineTo(486, 1174);
                    android.accessibilityservice.GestureDescription imgTapGesture =
                        new android.accessibilityservice.GestureDescription.Builder()
                            .addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(
                                imgTapPath, 0, 50))
                            .build();
                    dispatchGesture(imgTapGesture, null, null);
                    logD("✅ 已点击资质证照图片");

                    // ── 智能等待图片查看器打开（检测"认证说明"消失）──
                    // 策略：店铺账号页有"认证说明"文字(16个文本块)，进入图片查看器后只剩1个(关闭按钮)
                    // 检测"认证说明"消失即确认已切换到图片查看器，再等2秒让图片内容渲染完成
                    logD("⏳ 等待图片查看器打开（检测页面切换）...");
                    final String[] shopPageFlag = {"认证说明"};
                    boolean imageViewerOpened = false;
                    for (int ivSec = 0; ivSec < 8 && !imageViewerOpened; ivSec++) {
                        Thread.sleep(1000);
                        final boolean[] ivDone = {false};
                        final boolean[] stillOnShopPage = {true}; // true=还在店铺页，false=已进入图片查看器
                        takeScreenshot(new ScreenshotCallback() {
                            @Override
                            public void onSuccess(android.graphics.Bitmap bitmap) {
                                if (bitmap == null) { ivDone[0] = true; return; }
                                OcrHelper ivOcr = new OcrHelper(message -> {});
                                ivOcr.findAnyTextPosition(bitmap, shopPageFlag, new OcrHelper.OcrAnyCallback() {
                                    @Override
                                    public void onSuccess(String keyword) {
                                        // 还能检测到"认证说明"，说明还在店铺账号页
                                        stillOnShopPage[0] = true;
                                        ivDone[0] = true;
                                        ivOcr.release();
                                        bitmap.recycle();
                                    }
                                    @Override
                                    public void onFailure(String error) {
                                        // 检测不到"认证说明"，说明已进入图片查看器
                                        stillOnShopPage[0] = false;
                                        ivDone[0] = true;
                                        ivOcr.release();
                                        bitmap.recycle();
                                    }
                                });
                            }
                            @Override
                            public void onFailure() { ivDone[0] = true; }
                        });
                        // 等待OCR回调完成（最多2秒）
                        long ivStart = System.currentTimeMillis();
                        while (!ivDone[0] && System.currentTimeMillis() - ivStart < 2000) {
                            Thread.sleep(100);
                        }
                        if (!stillOnShopPage[0]) {
                            logD("✅ 图片查看器已打开（第" + (ivSec + 1) + "秒检测到页面切换），等待2秒让图片渲染...");
                            imageViewerOpened = true;
                            Thread.sleep(2000); // 图片内容渲染缓冲
                        } else {
                            logD("⌛ 第" + (ivSec + 1) + "秒：还在店铺账号页，等待图片查看器打开...");
                        }
                    }
                    if (!imageViewerOpened) {
                        logD("⚠️ 等待8秒后页面未切换，强制执行（可能已打开但OCR未能判断）");
                    }

                    // ── 截图1：图片原始状态 ──
                    logD("📸 截取资质证照图片（原始状态）...");
                    takeScreenshotWithPrefix("资质证照原图", new ScreenshotCallback() {
                        @Override public void onSuccess() { logD("✅ 资质证照原图截图保存成功"); }
                        @Override public void onFailure() { logE("❌ 资质证照原图截图保存失败"); }
                    });
                    Thread.sleep(800);

                    // ── 双指捏合放大（Pinch-Out Zoom In）──
                    // 屏幕中心=(540,1200)，两指从内侧(±200px)向外侧(±400px)展开，持续600ms
                    logD("🔍 执行双指放大手势...");
                    android.graphics.Path zoomFinger1 = new android.graphics.Path();
                    zoomFinger1.moveTo(340, 1200);  // 左指起点（中心左200px）
                    zoomFinger1.lineTo(100, 1200);  // 左指终点（向左展开）
                    android.graphics.Path zoomFinger2 = new android.graphics.Path();
                    zoomFinger2.moveTo(740, 1200);  // 右指起点（中心右200px）
                    zoomFinger2.lineTo(980, 1200);  // 右指终点（向右展开）
                    android.accessibilityservice.GestureDescription zoomGesture =
                        new android.accessibilityservice.GestureDescription.Builder()
                            .addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(
                                zoomFinger1, 0, 600))
                            .addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(
                                zoomFinger2, 0, 600))
                            .build();
                    dispatchGesture(zoomGesture, null, null);
                    logD("✅ 双指放大手势执行完成");
                    Thread.sleep(1000); // 等待缩放动画完成

                    // ── 单指向右拖动（向右平移，显示图片左侧内容）──
                    // 从屏幕左侧(250,1200)拖到右侧(800,1200)，持续500ms
                    logD("👆 执行向右拖动手势，显示证照详细信息...");
                    android.graphics.Path dragPath = new android.graphics.Path();
                    dragPath.moveTo(250, 1200);  // 拖动起点
                    dragPath.lineTo(800, 1200);  // 拖动终点（向右）
                    android.accessibilityservice.GestureDescription dragGesture =
                        new android.accessibilityservice.GestureDescription.Builder()
                            .addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(
                                dragPath, 0, 500))
                            .build();
                    dispatchGesture(dragGesture, null, null);
                    logD("✅ 向右拖动手势执行完成");
                    Thread.sleep(800); // 等待拖动稳定

                    // ── 截图2：放大并平移后，完整展示证照信息 ──
                    logD("📸 截取资质证照详情（放大后）...");
                    takeScreenshotWithPrefix("资质证照详情", new ScreenshotCallback() {
                        @Override public void onSuccess() { logD("✅ 资质证照详情截图保存成功"); }
                        @Override public void onFailure() { logE("❌ 资质证照详情截图保存失败"); }
                    });
                    Thread.sleep(500);

                } else {
                    logD("ℹ️ 作者主页无【店铺账号】标签，跳过");
                }
                profileRoot.recycle();
            }

        } catch (Exception e) {
            logE("进入作者主页失败: " + e.getMessage());
        }
    }

    /**
     * 在节点树中递归查找 contentDescription 包含指定关键词的节点
     */
    private android.view.accessibility.AccessibilityNodeInfo findNodeByDescContains(
            android.view.accessibility.AccessibilityNodeInfo root, String keyword) {
        if (root == null) return null;
        CharSequence desc = root.getContentDescription();
        if (desc != null && desc.toString().contains(keyword)) {
            return root;
        }
        for (int i = 0; i < root.getChildCount(); i++) {
            android.view.accessibility.AccessibilityNodeInfo child = root.getChild(i);
            android.view.accessibility.AccessibilityNodeInfo result = findNodeByDescContains(child, keyword);
            if (result != null) {
                if (child != result) child.recycle();
                return result;
            }
            if (child != null) child.recycle();
        }
        return null;
    }

    /**
     * 🆕 保存Bitmap到相册
     * @param bitmap 要保存的图片
     * @param name 图片名称(不含扩展名)
     */
    private void saveBitmapToGallery(android.graphics.Bitmap bitmap, String name) {
        if (bitmap == null) {
            logE("❌ Bitmap为空,无法保存");
            return;
        }

        try {
            // 使用现有的保存方法
            saveScreenshotWithPrefix(bitmap, name);
            logD("✅ 图片已保存: " + name);
        } catch (Exception e) {
            logE("❌ 保存图片失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 🆕 递归查找所有节点
     */
    private void findAllNodes(android.view.accessibility.AccessibilityNodeInfo node, java.util.List<android.view.accessibility.AccessibilityNodeInfo> result) {
        if (node == null) {
            return;
        }

        result.add(node);

        int childCount = node.getChildCount();
        for (int i = 0; i < childCount; i++) {
            android.view.accessibility.AccessibilityNodeInfo child = node.getChild(i);
            if (child != null) {
                findAllNodes(child, result);
            }
        }
    }

    /**
     * 🆕 点击"我的订单"按钮
     */
    private void clickMyOrderButton() {
        try {
            logD("📱 准备点击'我的订单'按钮...");

            // 等待页面稳定
            Thread.sleep(1000);

            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                logE("❌ 无法获取根节点");
                return;
            }

            // 通过文本查找"我的订单"按钮
            logD("🔍 开始查找'我的订单'按钮...");

            java.util.List<android.view.accessibility.AccessibilityNodeInfo> textNodes =
                rootNode.findAccessibilityNodeInfosByText("我的订单");

            if (textNodes != null && !textNodes.isEmpty()) {
                logD("📋 找到 " + textNodes.size() + " 个包含'我的订单'文本的节点");

                for (android.view.accessibility.AccessibilityNodeInfo node : textNodes) {
                    // 获取节点文本,验证是否完全匹配
                    CharSequence nodeText = node.getText();
                    android.graphics.Rect bounds = new android.graphics.Rect();
                    node.getBoundsInScreen(bounds);

                    logD("  节点文本: " + (nodeText != null ? nodeText.toString() : "null"));
                    logD("  节点位置: [" + bounds.left + "," + bounds.top + "] → [" + bounds.right + "," + bounds.bottom + "]");

                    // 只处理文本完全匹配"我的订单"的节点
                    if (nodeText != null && "我的订单".equals(nodeText.toString())) {
                        logD("✅ 找到完全匹配的'我的订单'文本节点");

                        // 查找可点击的父节点
                        android.view.accessibility.AccessibilityNodeInfo clickableNode = node;
                        int parentLevel = 0;
                        while (clickableNode != null && !clickableNode.isClickable() && parentLevel < 5) {
                            clickableNode = clickableNode.getParent();
                            parentLevel++;
                        }

                        if (clickableNode != null && clickableNode.isClickable()) {
                            // 获取父节点的位置信息
                            android.graphics.Rect parentBounds = new android.graphics.Rect();
                            clickableNode.getBoundsInScreen(parentBounds);

                            String parentId = clickableNode.getViewIdResourceName();
                            logD("  可点击父节点ID: " + (parentId != null ? parentId : "null"));
                            logD("  父节点层级: " + parentLevel);
                            logD("  父节点位置: [" + parentBounds.left + "," + parentBounds.top + "] → [" + parentBounds.right + "," + parentBounds.bottom + "]");

                            boolean clicked = clickableNode.performAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);
                            if (clicked) {
                                logD("✅ 成功点击'我的订单'按钮(通过文本查找)");
                                rootNode.recycle();

                                // 等待页面加载
                                Thread.sleep(1000);

                                // 点击"更多"按钮并截图
                                clickMoreButtonAndScreenshot();

                                return;
                            } else {
                                logE("❌ 点击'我的订单'按钮失败");
                            }
                        } else {
                            logE("❌ 未找到可点击的父节点");
                        }
                    }
                }
            } else {
                logE("❌ 未找到'我的订单'文本节点");
            }

            rootNode.recycle();

        } catch (Exception e) {
            logE("点击'我的订单'失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 🆕 点击"更多"按钮并截图
     */
    private void clickMoreButtonAndScreenshot() {
        try {
            logD("📱 准备点击'更多'按钮...");

            // 等待1秒页面稳定
            Thread.sleep(1000);

            // 🆕 优先使用用户测试保存的坐标
            SharedPreferences prefs = getSharedPreferences("automation_config", MODE_PRIVATE);
            int savedX = prefs.getInt("more_button_x", -1);
            int savedY = prefs.getInt("more_button_y", -1);

            if (savedX != -1 && savedY != -1) {
                // 使用保存的坐标
                logD("✅ 使用保存的坐标: (" + savedX + ", " + savedY + ")");
                clickByCoordinates(savedX, savedY);
            } else {
                // 如果没有保存的坐标,使用测试得出的精确坐标
                logD("✅ 使用默认坐标: (957, 514)");

                // ✅ 通过坐标测试工具测试得出的精确坐标: (957, 514)
                // 这是"更多"按钮在1080x2400分辨率下的真实位置
                clickByCoordinates(957, 514);
            }

            // 等待1秒让弹窗完全显示
            Thread.sleep(1000);

            // 截图保存"更多工具与服务"弹窗,并使用同一个Bitmap进行OCR识别
            logD("📸 准备截屏保存订单更多页面...");
            takeScreenshotWithPrefix("订单更多", new ScreenshotCallback() {
                @Override
                public void onSuccess(android.graphics.Bitmap bitmap) {
                    logD("✅ 订单更多页面截屏成功");

                    // 使用同一个Bitmap进行OCR识别,避免弹窗关闭
                    clickQualificationRulesButtonWithOcr(bitmap);
                }

                @Override
                public void onFailure() {
                    logE("❌ 订单更多页面截屏失败");
                }
            });

        } catch (Exception e) {
            logE("点击'更多'按钮失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 🆕 使用OCR识别"资质规则"按钮并点击
     * @param bitmap 已截取的弹窗截图
     */
    private void clickQualificationRulesButtonWithOcr(android.graphics.Bitmap bitmap) {
        try {
            logD("🔍 准备使用OCR识别'资质规则'按钮...");

            if (bitmap == null) {
                logE("❌ Bitmap为空,无法进行OCR识别");
                return;
            }

            logD("✅ 使用已截取的弹窗图片,开始OCR识别...");

            // Copy一份Bitmap给OCR使用,避免异步处理时Bitmap被修改
            final android.graphics.Bitmap ocrBitmap = bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, false);

            // 使用OCR识别"资质规则" (传递日志回调)
            OcrHelper ocrHelper = new OcrHelper(message -> logD(message));
            ocrHelper.findTextPosition(ocrBitmap, "资质规则", new OcrHelper.OcrCallback() {
                        @Override
                        public void onSuccess(OcrHelper.TextMatch match) {
                            logD("🎯 OCR识别成功,找到'资质规则'按钮");
                            logD("📍 文本块: " + match.text);
                            logD("📍 位置: [" + match.bounds.left + "," + match.bounds.top + "][" + match.bounds.right + "," + match.bounds.bottom + "]");

                            try {
                                int clickX, clickY;

                                // 检查是否识别成了"我要开店 资质规则"(两个按钮连在一起)
                                if (match.text.contains("我要开店") && match.text.contains("资质规则")) {
                                    logD("⚠️ OCR把两个按钮识别成一个文本块,计算'资质规则'的位置...");
                                    // 点击文本块的右半部分(资质规则应该在右边)
                                    int width = match.bounds.right - match.bounds.left;
                                    clickX = match.bounds.left + (int)(width * 0.75); // 右侧3/4位置
                                    clickY = match.center.y;
                                    logD("📍 调整后的点击位置: (" + clickX + ", " + clickY + ")");
                                } else {
                                    // 正常情况,点击中心点
                                    clickX = match.center.x;
                                    clickY = match.center.y;
                                }

                                // 点击"资质规则"按钮
                                clickByCoordinates(clickX, clickY);
                                logD("✅ 已点击'资质规则'按钮");

                                // 等待页面开始加载
                                Thread.sleep(500);

                                // 使用OCR检测页面是否加载完成
                                logD("🔍 使用OCR检测资质规则页面是否加载完成...");
                                waitForQualificationPageLoaded(new PageLoadCallback() {
                                    @Override
                                    public void onLoaded() {
                                        logD("✅ 资质规则页面已加载完成!");

                                        // 截图保存"资质规则"页面
                                        logD("📸 准备截屏保存资质规则页面...");
                                        takeScreenshotWithPrefix("资质规则", new ScreenshotCallback() {
                                            @Override
                                            public void onSuccess() {
                                                logD("✅ 资质规则页面截屏成功");

                                                // 截图后下拉到最底部,然后返回到"我"的首页
                                                scrollToBottomAndReturnToMe();
                                            }

                                            @Override
                                            public void onFailure() {
                                                logE("❌ 资质规则页面截屏失败");
                                            }
                                        });
                                    }

                                    @Override
                                    public void onTimeout() {
                                        logE("❌ 资质规则页面加载超时,仍然尝试截图...");

                                        // 即使超时也尝试截图
                                        takeScreenshotWithPrefix("资质规则", new ScreenshotCallback() {
                                            @Override
                                            public void onSuccess() {
                                                logD("✅ 资质规则页面截屏成功(超时后)");

                                                // 截图后下拉到最底部,然后返回到"我"的首页
                                                scrollToBottomAndReturnToMe();
                                            }

                                            @Override
                                            public void onFailure() {
                                                logE("❌ 资质规则页面截屏失败");
                                            }
                                        });
                                    }
                                });

                            } catch (Exception e) {
                                logE("点击'资质规则'按钮失败: " + e.getMessage());
                                e.printStackTrace();
                            } finally {
                                // 在回调完成后释放资源
                                ocrHelper.release();
                                ocrBitmap.recycle();
                                bitmap.recycle();
                            }
                        }

                        @Override
                        public void onFailure(String error) {
                            logE("❌ OCR识别'资质规则'失败,尝试识别'我要开店'...");

                            // 方案2: 尝试识别"我要开店",然后点击它右边的位置
                            OcrHelper ocrHelper2 = new OcrHelper(message -> logD(message));
                            ocrHelper2.findTextPosition(ocrBitmap, "我要开店", new OcrHelper.OcrCallback() {
                                @Override
                                public void onSuccess(OcrHelper.TextMatch match) {
                                    logD("🎯 找到'我要开店'按钮,计算'资质规则'位置...");

                                    // "资质规则"在"我要开店"的右边,距离大约是按钮宽度
                                    int buttonWidth = match.bounds.right - match.bounds.left;
                                    int targetX = match.center.x + buttonWidth + 20; // 右边一个按钮的位置
                                    int targetY = match.center.y; // 同一行

                                    logD("📍 推测'资质规则'位置: (" + targetX + ", " + targetY + ")");

                                    try {
                                        // 点击推测的位置
                                        clickByCoordinates(targetX, targetY);
                                        logD("✅ 已点击推测的'资质规则'位置");

                                        // 等待页面加载
                                        Thread.sleep(1000);

                                        // 截图保存"资质规则"页面
                                        logD("📸 准备截屏保存资质规则页面...");
                                        takeScreenshotWithPrefix("资质规则", new ScreenshotCallback() {
                                            @Override
                                            public void onSuccess() {
                                                logD("✅ 资质规则页面截屏成功");
                                                logD("🎉 抖音自动化流程完成!");
                                            }

                                            @Override
                                            public void onFailure() {
                                                logE("❌ 资质规则页面截屏失败");
                                            }
                                        });

                                    } catch (Exception e) {
                                        logE("点击推测位置失败: " + e.getMessage());
                                        e.printStackTrace();
                                    } finally {
                                        ocrHelper2.release();
                                    }
                                }

                                @Override
                                public void onFailure(String error2) {
                                    logE("❌ OCR识别'我要开店'也失败,使用固定坐标点击...");

                                    // 方案3: 使用固定坐标点击 (从截图估算的位置)
                                    int fixedX = 850; // 右下角
                                    int fixedY = 1850;

                                    logD("📍 使用固定坐标: (" + fixedX + ", " + fixedY + ")");

                                    try {
                                        clickByCoordinates(fixedX, fixedY);
                                        logD("✅ 已点击固定坐标位置");

                                        Thread.sleep(1000);

                                        takeScreenshotWithPrefix("资质规则", new ScreenshotCallback() {
                                            @Override
                                            public void onSuccess() {
                                                logD("✅ 资质规则页面截屏成功");
                                                logD("🎉 抖音自动化流程完成!");
                                            }

                                            @Override
                                            public void onFailure() {
                                                logE("❌ 资质规则页面截屏失败");
                                            }
                                        });

                                    } catch (Exception e) {
                                        logE("点击固定坐标失败: " + e.getMessage());
                                        e.printStackTrace();
                                    } finally {
                                        ocrHelper2.release();
                                    }
                                }
                            });

                            // 释放资源
                            ocrHelper.release();
                            bitmap.recycle();
                        }
                    });

        } catch (Exception e) {
            logE("OCR识别'资质规则'失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 🆕 使用OCR等待营业执照图片加载完成,然后截屏
     */
    private void waitForBusinessLicenseAndScreenshot() {
        new Thread(() -> {
            try {
                logD("⏳ 使用OCR等待营业执照图片加载...");

                // 最多尝试10次,每次间隔1秒
                int maxAttempts = 10;
                boolean licenseLoaded = false;

                for (int i = 1; i <= maxAttempts; i++) {
                    Thread.sleep(1000); // 等待1秒

                    logD("🔍 第" + i + "次检测营业执照是否加载...");

                    // 截取当前屏幕
                    final boolean[] checkComplete = {false};
                    final boolean[] foundLicense = {false};

                    takeScreenshot(new ScreenshotCallback() {
                        @Override
                        public void onSuccess(android.graphics.Bitmap bitmap) {
                            if (bitmap == null) {
                                logE("❌ 截图失败");
                                checkComplete[0] = true;
                                return;
                            }

                            // 转换为可变的Bitmap (修复ML Kit错误)
                            final android.graphics.Bitmap mutableBitmap = bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, true);

                            // 使用OCR识别营业执照上的关键文字 (传递日志回调)
                            // 改用"MA"(统一社会信用代码特征)作为判断标准
                            // 从日志看到: 91110101MA0045NI7u - 包含"MA"
                            OcrHelper ocrHelper = new OcrHelper(message -> logD(message));
                            ocrHelper.findTextPosition(mutableBitmap, "MA", new OcrHelper.OcrCallback() {
                                @Override
                                public void onSuccess(OcrHelper.TextMatch match) {
                                    logD("✅ 检测到营业执照图片已加载!");
                                    logD("📍 找到统一社会信用代码特征: " + match.text);
                                    foundLicense[0] = true;
                                    checkComplete[0] = true;
                                    ocrHelper.release();
                                    mutableBitmap.recycle();
                                }

                                @Override
                                public void onFailure(String error) {
                                    logD("⏳ 营业执照图片尚未加载,继续等待...");
                                    checkComplete[0] = true;
                                    ocrHelper.release();
                                    mutableBitmap.recycle();
                                }
                            });
                        }

                        @Override
                        public void onFailure() {
                            logE("❌ 截图失败");
                            checkComplete[0] = true;
                        }
                    });

                    // 等待OCR检测完成
                    int waitCount = 0;
                    while (!checkComplete[0] && waitCount < 50) { // 最多等待5秒
                        Thread.sleep(100);
                        waitCount++;
                    }

                    if (foundLicense[0]) {
                        licenseLoaded = true;
                        break;
                    }
                }

                if (licenseLoaded) {
                    logD("🎉 营业执照图片已完全加载,准备截屏...");
                    Thread.sleep(500); // 再等待500ms确保完全加载
                } else {
                    logE("⚠️ 超时未检测到营业执照图片,仍然进行截屏...");
                }

                // 截屏保存营业执照页面
                logD("📸 准备截屏保存营业执照页面...");
                takeScreenshotWithPrefix("营业执照", new ScreenshotCallback() {
                    @Override
                    public void onSuccess() {
                        logD("✅ 营业执照页面截屏成功");

                        // 截图成功后,智能返回到"我"的首页
                        new Thread(() -> {
                            try {
                                Thread.sleep(1000); // 等待截图完成
                                smartReturnToMePage();
                            } catch (Exception e) {
                                logE("返回'我'页面失败: " + e.getMessage());
                            }
                        }).start();
                    }

                    @Override
                    public void onFailure() {
                        logE("❌ 营业执照页面截屏失败");
                    }
                });

            } catch (Exception e) {
                logE("等待营业执照加载失败: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 🆕 点击"营业执照"按钮
     */
    private void clickBusinessLicenseButton() {
        try {
            logD("🔍 准备点击'营业执照'按钮...");

            // 等待页面稳定
            Thread.sleep(1000);

            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                logE("❌ 无法获取根节点");
                return;
            }

            // 通过文本查找"营业执照"按钮
            logD("🔍 开始查找'营业执照'按钮...");

            // 尝试多种文本匹配方式
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> textNodes =
                rootNode.findAccessibilityNodeInfosByText("营业执照");

            // 如果没找到,尝试查找包含"营业"的节点
            if (textNodes == null || textNodes.isEmpty()) {
                logD("⚠️ 未找到'营业执照',尝试查找'营业'...");
                textNodes = rootNode.findAccessibilityNodeInfosByText("营业");
            }

            if (textNodes != null && !textNodes.isEmpty()) {
                logD("📋 找到 " + textNodes.size() + " 个包含相关文本的节点");

                for (android.view.accessibility.AccessibilityNodeInfo node : textNodes) {
                    // 获取节点文本,验证是否完全匹配
                    CharSequence nodeText = node.getText();
                    android.graphics.Rect bounds = new android.graphics.Rect();
                    node.getBoundsInScreen(bounds);

                    logD("  节点文本: " + (nodeText != null ? nodeText.toString() : "null"));
                    logD("  节点位置: [" + bounds.left + "," + bounds.top + "] → [" + bounds.right + "," + bounds.bottom + "]");

                    // 只处理文本完全匹配"营业执照"的节点
                    if (nodeText != null && "营业执照".equals(nodeText.toString())) {
                        logD("✅ 找到完全匹配的'营业执照'文本节点");

                        // 查找可点击的父节点
                        android.view.accessibility.AccessibilityNodeInfo clickableNode = node;
                        int parentLevel = 0;
                        while (clickableNode != null && !clickableNode.isClickable() && parentLevel < 5) {
                            clickableNode = clickableNode.getParent();
                            parentLevel++;
                        }

                        if (clickableNode != null && clickableNode.isClickable()) {
                            // 获取父节点的位置信息
                            android.graphics.Rect parentBounds = new android.graphics.Rect();
                            clickableNode.getBoundsInScreen(parentBounds);

                            String parentId = clickableNode.getViewIdResourceName();
                            logD("  可点击父节点ID: " + (parentId != null ? parentId : "null"));
                            logD("  父节点层级: " + parentLevel);
                            logD("  父节点位置: [" + parentBounds.left + "," + parentBounds.top + "] → [" + parentBounds.right + "," + parentBounds.bottom + "]");

                            boolean clicked = clickableNode.performAction(
                                android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                            );
                            if (clicked) {
                                logD("✅ 成功点击'营业执照'按钮(通过文本查找)");

                                // 使用OCR等待营业执照图片加载完成,然后截屏
                                waitForBusinessLicenseAndScreenshot();

                                rootNode.recycle();
                                return;
                            }
                        } else {
                            logE("❌ 未找到可点击的父节点");
                        }
                    }
                }
            } else {
                logE("❌ 未找到包含'营业执照'文本的节点,使用坐标点击...");
                rootNode.recycle();

                // 使用坐标点击作为备用方案
                // 根据截图,"营业执照"在页面上部
                // 点击整行中间位置,Y坐标参考"资质证照"的位置(1854-1915,中心约1885)
                // "营业执照"应该在更上方,大概Y=400左右
                clickByCoordinates(540, 400);

                // 使用OCR等待营业执照图片加载完成,然后截屏
                waitForBusinessLicenseAndScreenshot();

                return;
            }

            rootNode.recycle();

        } catch (Exception e) {
            logE("点击'营业执照'按钮失败: " + e.getMessage());
        }
    }

    /**
     * 页面加载回调接口
     */
    private interface PageLoadCallback {
        void onLoaded();
        void onTimeout();
    }

    /**
     * 截图后下拉到最底部,然后返回到"我"的首页
     */
    private void scrollToBottomAndReturnToMe() {
        new Thread(() -> {
            try {
                logD("📜 准备上拉屏幕到最底部...");

                // 等待截图完成
                Thread.sleep(500);

                // 执行向上滑动手势(从下往上滑,让页面滚动到底部)
                android.graphics.Path path = new android.graphics.Path();
                // 起始点: 屏幕中下部 (540, 2000)
                path.moveTo(540, 2000);
                // 结束点: 屏幕中上部 (540, 800) - 向上滑动1200像素
                path.lineTo(540, 800);

                // 创建手势描述
                android.accessibilityservice.GestureDescription.StrokeDescription strokeDescription =
                    new android.accessibilityservice.GestureDescription.StrokeDescription(
                        path,
                        0,      // 开始时间
                        500     // 持续时间500ms
                    );

                android.accessibilityservice.GestureDescription.Builder builder =
                    new android.accessibilityservice.GestureDescription.Builder();
                builder.addStroke(strokeDescription);
                android.accessibilityservice.GestureDescription gesture = builder.build();

                // 执行手势
                boolean dispatched = dispatchGesture(
                    gesture,
                    new android.accessibilityservice.AccessibilityService.GestureResultCallback() {
                        @Override
                        public void onCompleted(android.accessibilityservice.GestureDescription gestureDescription) {
                            super.onCompleted(gestureDescription);
                            logD("✅ 上拉手势执行成功,页面已滚动到底部");

                            // 下拉完成后,返回到"我"的首页
                            new Thread(() -> {
                                try {
                                    Thread.sleep(500); // 等待页面稳定

                                    logD("🔙 准备智能返回到'我'的首页...");

                                    // 智能返回:检测是否已经在"我"页面,如果是则停止返回
                                    int maxReturnTimes = 5; // 最多返回5次

                                    for (int i = 1; i <= maxReturnTimes; i++) {
                                        logD("🔙 第" + i + "次返回...");
                                        performGlobalAction(GLOBAL_ACTION_BACK);
                                        Thread.sleep(800); // 增加等待时间,让页面完全加载

                                        // 检测是否已经在"我"页面
                                        logD("🔍 检测是否到达'我'页面...");
                                        if (isOnMePage()) {
                                            logD("✅ 第" + i + "次返回后到达'我'页面,停止返回");

                                            // 返回到"我"页面后,点击"观看历史"
                                            clickViewHistory();
                                            return;
                                        } else {
                                            logD("⏳ 第" + i + "次返回后未到达'我'页面,继续返回...");
                                        }
                                    }

                                    // 如果循环结束还没到达"我"页面,再检测一次
                                    logD("🔍 最后检测是否到达'我'页面...");
                                    if (isOnMePage()) {
                                        logD("✅ 成功返回到'我'页面");

                                        // 返回到"我"页面后,点击"观看历史"
                                        clickViewHistory();
                                    } else {
                                        logE("❌ 未能返回到'我'页面,可能返回次数不够或页面识别失败");
                                        logD("🎉 抖音自动化流程完成(可能未完全返回)!");
                                    }

                                } catch (Exception e) {
                                    logE("返回到'我'的首页失败: " + e.getMessage());
                                    e.printStackTrace();
                                }
                            }).start();
                        }

                        @Override
                        public void onCancelled(android.accessibilityservice.GestureDescription gestureDescription) {
                            super.onCancelled(gestureDescription);
                            logE("❌ 上拉手势被取消");
                        }
                    },
                    null
                );

                if (!dispatched) {
                    logE("❌ 上拉手势分发失败");
                }

            } catch (Exception e) {
                logE("上拉屏幕失败: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 使用OCR等待资质规则页面加载完成
     * 检测"资质规则公示"文字
     */
    private void waitForQualificationPageLoaded(final PageLoadCallback callback) {
        new Thread(() -> {
            try {
                int maxAttempts = 10; // 最多检测10次
                int attemptInterval = 500; // 每次间隔500ms

                for (int i = 0; i < maxAttempts; i++) {
                    logD("🔍 第" + (i + 1) + "次检测资质规则页面是否加载...");

                    // 截图
                    final boolean[] screenshotSuccess = {false};
                    final android.graphics.Bitmap[] screenshotBitmap = {null};

                    takeScreenshot(new ScreenshotCallback() {
                        @Override
                        public void onSuccess(android.graphics.Bitmap bitmap) {
                            screenshotBitmap[0] = bitmap;
                            screenshotSuccess[0] = true;
                        }

                        @Override
                        public void onFailure() {
                            screenshotSuccess[0] = false;
                        }
                    });

                    // 等待截图完成
                    Thread.sleep(1000);

                    if (!screenshotSuccess[0] || screenshotBitmap[0] == null) {
                        logE("❌ 截图失败,继续等待...");
                        Thread.sleep(attemptInterval);
                        continue;
                    }

                    // 使用OCR识别"资质规则公示"
                    final boolean[] textFound = {false};
                    final boolean[] ocrCompleted = {false};
                    OcrHelper ocrHelper = new OcrHelper(message -> logD(message));

                    // 先尝试识别正确写法"资质规则公示"
                    ocrHelper.findTextPosition(screenshotBitmap[0], "资质规则公示", new OcrHelper.OcrCallback() {
                        @Override
                        public void onSuccess(OcrHelper.TextMatch match) {
                            logD("✅ 检测到'资质规则公示'文字,页面已加载!");
                            textFound[0] = true;
                            ocrCompleted[0] = true;
                        }

                        @Override
                        public void onFailure(String error) {
                            // OCR有时会把"规"误识别为"视",尝试模糊匹配
                            logD("⏳ 未检测到'资质规则公示',尝试OCR容错匹配(规→视)...");
                            OcrHelper fallbackOcr = new OcrHelper(message -> logD(message));
                            final android.graphics.Bitmap fallbackBitmap = screenshotBitmap[0].copy(android.graphics.Bitmap.Config.ARGB_8888, false);
                            fallbackOcr.findTextPosition(fallbackBitmap, "资质视则公示", new OcrHelper.OcrCallback() {
                                @Override
                                public void onSuccess(OcrHelper.TextMatch match2) {
                                    logD("✅ OCR容错匹配成功: 识别到'资质视则公示'(规被误读为视),页面已加载!");
                                    textFound[0] = true;
                                    ocrCompleted[0] = true;
                                    fallbackOcr.release();
                                    fallbackBitmap.recycle();
                                }

                                @Override
                                public void onFailure(String error2) {
                                    logD("⏳ 未检测到'资质规则公示'或'资质视则公示',页面可能还在加载...");
                                    textFound[0] = false;
                                    ocrCompleted[0] = true;
                                    fallbackOcr.release();
                                    fallbackBitmap.recycle();
                                }
                            });
                        }
                    });

                    // 等待OCR完成(最多等待3秒)
                    int waitCount = 0;
                    while (!ocrCompleted[0] && waitCount < 30) {
                        Thread.sleep(100);
                        waitCount++;
                    }

                    // 释放资源
                    ocrHelper.release();
                    screenshotBitmap[0].recycle();

                    if (textFound[0]) {
                        // 页面已加载
                        if (callback != null) {
                            callback.onLoaded();
                        }
                        return;
                    }

                    // 继续等待
                    Thread.sleep(attemptInterval);
                }

                // 超时
                logE("❌ 等待资质规则页面加载超时(已尝试" + maxAttempts + "次)");
                if (callback != null) {
                    callback.onTimeout();
                }

            } catch (Exception e) {
                logE("等待资质规则页面加载失败: " + e.getMessage());
                e.printStackTrace();
                if (callback != null) {
                    callback.onTimeout();
                }
            }
        }).start();
    }

}

