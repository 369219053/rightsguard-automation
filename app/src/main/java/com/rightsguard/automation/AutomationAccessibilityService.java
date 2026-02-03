package com.rightsguard.automation;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

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

    // 抖音应用相关
    private static final String DOUYIN_PACKAGE = "com.ss.android.ugc.aweme";
    private static final String DOUYIN_OPEN_BUTTON_ID = "com.ss.android.ugc.aweme:id/tnn"; // "打开看看"按钮
    private static final String DOUYIN_OPEN_BUTTON_TEXT = "打开看看";

    // 系统录屏权限弹窗相关
    private static final String SYSTEM_UI_PACKAGE = "com.android.systemui";
    private static final String SCREEN_SHARE_MODE_SPINNER_ID = "com.android.systemui:id/real_screen_share_mode_spinner";
    private static final String CONTINUE_BUTTON_ID = "android:id/button1";

    private static AutomationAccessibilityService instance;
    private boolean isRunning = false;
    private boolean hasClickedScreenRecord = false;
    private boolean hasSelectedDouyin = false; // 是否已勾选抖音
    private boolean hasClickedDouyinOpen = false; // 是否已点击抖音"打开看看"按钮
    private String remark = "";

    // 日志收集
    private static final StringBuilder logBuilder = new StringBuilder();
    private static final int MAX_LOG_LENGTH = 50000; // 最大日志长度
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

    // 随机延迟 (模拟真人操作,避免被检测)
    private static final Random random = new Random();
    private static final int MIN_DELAY_MS = 1500; // 最小延迟 1.5秒
    private static final int MAX_DELAY_MS = 3000; // 最大延迟 3秒

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        logD("无障碍服务已创建");
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

        // 处理抖音应用的口令打开页面
        if (DOUYIN_PACKAGE.equals(packageName)) {
            if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED ||
                eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
                handleDouyinOpenDialog();
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
        Log.d(TAG, "服务已销毁");
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
     * 启动自动化
     */
    public void startAutomation() {
        logD("🚀 启动自动化");
        isRunning = true;
        hasClickedScreenRecord = false;
        hasSelectedDouyin = false;
        hasClickedDouyinOpen = false; // 重置抖音"打开看看"按钮点击状态

        // 最小化当前应用(返回桌面)
        minimizeCurrentApp();

        // 延迟打开应用
        delayedOpenApp();
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
     * 处理抖音口令打开页面
     * 自动点击"打开看看"按钮
     */
    private void handleDouyinOpenDialog() {
        // 如果已经点击过,不再重复点击
        if (hasClickedDouyinOpen) {
            return;
        }

        try {
            android.view.accessibility.AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                return;
            }

            // 方法1: 通过ID查找"打开看看"按钮
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> buttonNodes =
                rootNode.findAccessibilityNodeInfosByViewId(DOUYIN_OPEN_BUTTON_ID);

            if (buttonNodes != null && !buttonNodes.isEmpty()) {
                android.view.accessibility.AccessibilityNodeInfo buttonNode = buttonNodes.get(0);

                logD("找到抖音'打开看看'按钮,准备点击");

                // 在新线程中执行随机延迟和点击操作
                new Thread(() -> {
                    try {
                        // 🎯 关键: 随机延迟 1.5s-3s (模拟真人操作)
                        randomDelay();

                        // 点击按钮
                        boolean clicked = buttonNode.performAction(
                            android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                        );

                        if (clicked) {
                            logD("✅ 成功点击'打开看看'按钮,等待进入视频页面...");
                            hasClickedDouyinOpen = true;
                        } else {
                            logE("❌ 点击'打开看看'按钮失败");
                        }

                        buttonNode.recycle();

                    } catch (Exception e) {
                        logE("点击'打开看看'按钮异常: " + e.getMessage());
                    }
                }).start();

                rootNode.recycle();
                return;
            }

            // 方法2: 通过文本查找"打开看看"按钮
            java.util.List<android.view.accessibility.AccessibilityNodeInfo> textNodes =
                rootNode.findAccessibilityNodeInfosByText(DOUYIN_OPEN_BUTTON_TEXT);

            if (textNodes != null && !textNodes.isEmpty()) {
                for (android.view.accessibility.AccessibilityNodeInfo node : textNodes) {
                    if (node.isClickable()) {
                        logD("通过文本找到'打开看看'按钮,准备点击");

                        // 在新线程中执行随机延迟和点击操作
                        new Thread(() -> {
                            try {
                                // 🎯 关键: 随机延迟 1.5s-3s (模拟真人操作)
                                randomDelay();

                                boolean clicked = node.performAction(
                                    android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
                                );

                                if (clicked) {
                                    logD("✅ 成功点击'打开看看'按钮,等待进入视频页面...");
                                    hasClickedDouyinOpen = true;
                                } else {
                                    logE("❌ 点击'打开看看'按钮失败");
                                }

                                node.recycle();

                            } catch (Exception e) {
                                logE("点击'打开看看'按钮异常: " + e.getMessage());
                            }
                        }).start();

                        rootNode.recycle();
                        return;
                    }
                }
            }

            rootNode.recycle();

        } catch (Exception e) {
            logE("处理抖音口令打开页面异常: " + e.getMessage());
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
                                    // 保存截图
                                    saveScreenshot(bitmap);
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
        try {
            // 使用备注作为文件名的一部分
            String fileName = "应用验真_" + remark.replace(":", "_") + "_" +
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
            sb.append("[").append(className != null ? className : "Unknown").append("]");
            sb.append(" (clickable=").append(node.isClickable()).append(")\n");

            // Resource ID
            String viewId = node.getViewIdResourceName();
            if (viewId != null && !viewId.isEmpty()) {
                for (int i = 0; i < depth; i++) sb.append("│   ");
                sb.append("  ID: ").append(viewId).append("\n");
            }

            // 文本内容
            CharSequence text = node.getText();
            if (text != null && text.length() > 0) {
                for (int i = 0; i < depth; i++) sb.append("│   ");
                sb.append("  Text: \"").append(text).append("\"\n");
            }

            // 内容描述
            CharSequence desc = node.getContentDescription();
            if (desc != null && desc.length() > 0) {
                for (int i = 0; i < depth; i++) sb.append("│   ");
                sb.append("  Desc: \"").append(desc).append("\"\n");
            }

            // 位置和大小
            android.graphics.Rect bounds = new android.graphics.Rect();
            node.getBoundsInScreen(bounds);
            for (int i = 0; i < depth; i++) sb.append("│   ");
            sb.append("  Bounds: [").append(bounds.left).append(",").append(bounds.top)
              .append("][").append(bounds.right).append(",").append(bounds.bottom).append("]\n");

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
            // 获取外部存储的Documents目录
            java.io.File documentsDir = android.os.Environment.getExternalStoragePublicDirectory(
                android.os.Environment.DIRECTORY_DOCUMENTS);

            // 创建RightsGuard目录
            java.io.File appDir = new java.io.File(documentsDir, "RightsGuard");
            if (!appDir.exists()) {
                appDir.mkdirs();
            }

            // 生成文件名(带时间戳)
            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss",
                java.util.Locale.CHINA).format(new java.util.Date());
            String fileName = "UI_Dump_" + timestamp + ".md";

            java.io.File file = new java.io.File(appDir, fileName);

            // 写入文件
            java.io.FileWriter writer = new java.io.FileWriter(file);
            writer.write("# UI结构 Dump\n\n");
            writer.write(dumpText);
            writer.close();

            logD("📄 文件已保存: " + file.getAbsolutePath());

            return file;

        } catch (Exception e) {
            logE("保存文件失败: " + e.getMessage());
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
     * 随机延迟 (模拟真人操作)
     * 用于录屏过程中的操作,避免操作过快被检测为机器人
     *
     * @param minMs 最小延迟时间(毫秒)
     * @param maxMs 最大延迟时间(毫秒)
     */
    private void randomDelay(int minMs, int maxMs) {
        try {
            int delayMs = minMs + random.nextInt(maxMs - minMs + 1);
            logD(String.format("⏱️ 随机延迟 %.2f 秒 (模拟真人操作)", delayMs / 1000.0));
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            logE("随机延迟被中断: " + e.getMessage());
        }
    }

    /**
     * 标准随机延迟 (1.5s - 3s)
     * 用于录屏过程中的所有操作
     */
    private void randomDelay() {
        randomDelay(MIN_DELAY_MS, MAX_DELAY_MS);
    }

}

