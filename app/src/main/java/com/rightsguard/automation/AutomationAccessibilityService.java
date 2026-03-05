package com.rightsguard.automation;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
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
            } else {
                logD("⚠️ 未找到'单个应用'文本,可能已经是'整个屏幕'模式");
                // 检查是否已经是"整个屏幕"模式
                java.util.List<android.view.accessibility.AccessibilityNodeInfo> wholeScreenNodes =
                    rootNode.findAccessibilityNodeInfosByText("整个屏幕");

                if (wholeScreenNodes != null && !wholeScreenNodes.isEmpty()) {
                    logD("✅ 已经是'整个屏幕'模式,查找'立即开始'按钮");
                    // 已经是整个屏幕模式,查找并点击"立即开始"按钮
                    scanSystemDialogButtons(rootNode);
                    findAndClickStartButton(rootNode);
                } else {
                    logD("⚠️ 既不是'单个应用'也不是'整个屏幕',可能是其他状态");
                }
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
        void onSuccess();
        void onFailure();
    }

    /**
     * 截屏保存应用验真页面
     */
    private void takeScreenshotBeforeVerify(final ScreenshotCallback callback) {
        takeScreenshotWithPrefix("应用验真", callback);
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
                                    // 保存截图(使用自定义前缀)
                                    saveScreenshotWithPrefix(bitmap, prefix);
                                    bitmap.recycle();

                                    if (callback != null) {
                                        callback.onSuccess();
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

            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                logE("❌ 无法获取UI结构: rootNode为null");
                return;
            }

            // 构建dump文本
            StringBuilder sb = new StringBuilder();
            sb.append("=== UI结构 Dump ===\n");
            sb.append("包名: ").append(rootNode.getPackageName()).append("\n");
            sb.append("时间: ").append(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                                      java.util.Locale.CHINA).format(new java.util.Date())).append("\n\n");

            // 递归遍历UI树
            dumpNode(rootNode, sb, 0);

            // 释放资源
            rootNode.recycle();

            // 显示dump结果
            showDumpResult(sb.toString());

            logD("✅ Dump完成");

        } catch (Exception e) {
            logE("❌ Dump UI结构失败: " + e.getMessage());
            e.printStackTrace();
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
        try {
            android.content.Intent intent = getPackageManager().getLaunchIntentForPackage(DOUYIN_PACKAGE);
            if (intent != null) {
                intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK |
                              android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                logD("✅ 已切换到抖音APP");
            } else {
                logE("❌ 无法获取抖音启动Intent");
            }
        } catch (Exception e) {
            logE("切换到抖音失败: " + e.getMessage());
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
                logD("⏱️ 等待'我'页面加载完成(2秒)...");
                Thread.sleep(2000);

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
                randomDelay();
                clickQualificationButton();

                logD("✅ 抖音自动化流程完成");

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
            Thread.sleep(2000); // 增加等待时间,确保页面完全稳定

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

                                // 等待资质证照页面加载,然后截屏
                                new Thread(() -> {
                                    try {
                                        Thread.sleep(2000); // 等待页面加载
                                        logD("📸 准备截屏保存资质证照页面...");
                                        takeScreenshotWithPrefix("资质证照", new ScreenshotCallback() {
                                            @Override
                                            public void onSuccess() {
                                                logD("✅ 资质证照页面截屏成功");
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

}

