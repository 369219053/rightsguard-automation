package com.rightsguard.automation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

/**
 * 主界面Activity
 * 显示自动化控制按钮和当前状态
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private TextView tvStatusDetail;
    private View viewStatusIndicator;
    private MaterialButton btnStart;
    private MaterialButton btnCaptchaTest;
    private MaterialButton btnCargoTest;
    private MaterialButton btnSalesTest; // 🆕 销量测试
    private MaterialButton btnStop;
    private MaterialButton btnViewLog;
    private ImageView ivSettings;
    private TextInputEditText etRemark;
    private TextInputEditText etSalesThreshold; // 🆕 销量筛选阈值输入框

    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
        updateStatus(STATUS_IDLE);
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        tvStatus = findViewById(R.id.tv_status);
        tvStatusDetail = findViewById(R.id.tv_status_detail);
        viewStatusIndicator = findViewById(R.id.view_status_indicator);
        btnStart = findViewById(R.id.btn_start);
        btnCaptchaTest = findViewById(R.id.btn_captcha_test);
        btnCargoTest = findViewById(R.id.btn_cargo_test);
        btnSalesTest = findViewById(R.id.btn_sales_test); // 🆕
        btnStop = findViewById(R.id.btn_stop);
        btnViewLog = findViewById(R.id.btn_view_log);
        ivSettings = findViewById(R.id.iv_settings);
        etRemark = findViewById(R.id.et_remark);
        etSalesThreshold = findViewById(R.id.et_sales_threshold); // 🆕
    }

    /**
     * 设置监听器
     */
    private void setupListeners() {
        // 开始按钮
        btnStart.setOnClickListener(v -> startAutomation());

        // 验证测试按钮
        btnCaptchaTest.setOnClickListener(v -> startCaptchaTestMode());

        // 带货测试按钮
        btnCargoTest.setOnClickListener(v -> startCargoTestMode());

        // 销量测试按钮
        btnSalesTest.setOnClickListener(v -> startSalesTestMode());

        // 停止按钮
        btnStop.setOnClickListener(v -> stopAutomation());

        // 查看日志按钮
        btnViewLog.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LogActivity.class);
            startActivity(intent);
        });

        // 设置按钮
        ivSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 开始自动化
     */
    private void startAutomation() {
        // 检查无障碍服务是否可用
        if (!AutomationAccessibilityService.isServiceAvailable()) {
            Toast.makeText(this, R.string.toast_accessibility_required, Toast.LENGTH_LONG).show();
            return;
        }

        // 获取取证信息
        String evidenceInfo = "";
        if (etRemark != null && etRemark.getText() != null) {
            evidenceInfo = etRemark.getText().toString().trim();
        }

        // 检查是否为空
        if (evidenceInfo.isEmpty()) {
            Toast.makeText(this, "请输入取证信息", Toast.LENGTH_SHORT).show();
            return;
        }

        // 解析取证信息
        ParseResult parseResult = parseEvidenceInfo(evidenceInfo);

        // 显示解析结果Toast
        if (parseResult.infringementUrl != null && !parseResult.infringementUrl.isEmpty()) {
            Toast.makeText(this, "✅ 侵权链接: " + parseResult.infringementUrl,
                Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "⚠️ 未解析到侵权链接,请检查输入格式",
                Toast.LENGTH_LONG).show();
        }

        // 启动自动化
        AutomationAccessibilityService service = AutomationAccessibilityService.getInstance();
        if (service != null) {
            // 设置备注
            service.setRemark(parseResult.remark);
            // 🔧 直接设置侵权人名称和原创名称，不依赖 setRemark 二次解析
            service.setInfringerName(parseResult.infringerName);
            service.setOriginalName(parseResult.originalName);
            Log.d("MainActivity", "✅ [startAutomation] 侵权人: '" + parseResult.infringerName + "' 原创: '" + parseResult.originalName + "'");

            // 设置侵权链接
            if (parseResult.infringementUrl != null && !parseResult.infringementUrl.isEmpty()) {
                service.setInfringementUrl(parseResult.infringementUrl);
                Log.d("MainActivity", "✅ 已设置侵权链接: " + parseResult.infringementUrl);
            } else {
                Log.d("MainActivity", "⚠️ 未解析到侵权链接");
            }

            // 🆕 设置视频关键词
            if (parseResult.videoKeywords != null && !parseResult.videoKeywords.isEmpty()) {
                service.setVideoKeywords(parseResult.videoKeywords);
                Log.d("MainActivity", "✅ 已设置视频关键词: " + parseResult.videoKeywords);
            } else {
                Log.d("MainActivity", "⚠️ 未解析到视频关键词");
            }

            // 🆕 设置视频时长
            service.setVideoDurationSeconds(parseResult.videoDurationSeconds);
            Log.d("MainActivity", "✅ 已设置视频时长: " + parseResult.videoDurationSeconds + "秒");

            // 🆕 设置封面URL（用于创作灵感MD5封面对比）
            if (parseResult.coverImageUrl != null && !parseResult.coverImageUrl.isEmpty()) {
                service.setCoverImageUrl(parseResult.coverImageUrl);
                Log.d("MainActivity", "✅ 已设置封面URL: " + parseResult.coverImageUrl);
            } else {
                Log.d("MainActivity", "⚠️ 未提供封面URL，跳过创作灵感封面对比");
            }

            // 🆕 设置销量筛选阈值
            int salesThresh = parseSalesThresholdInput();
            service.setSalesThreshold(salesThresh);
            Log.d("MainActivity", "✅ 已设置销量筛选阈值: " + (salesThresh > 0 ? salesThresh : "不筛选"));

            service.startAutomation();
            isRunning = true;
            updateStatus(STATUS_RUNNING);
        } else {
            Toast.makeText(this, "无障碍服务未启动", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 解析取证信息
     */
    private ParseResult parseEvidenceInfo(String info) {
        ParseResult result = new ParseResult();

        try {
            Log.d("MainActivity", "🔍 开始解析: " + info);

            // 🔧 兼容中文键盘全角冒号：将全角冒号「：」统一替换为半角冒号「:」，防止 indexOf("-抖音:") 失败
            info = info.replace("：", ":");

            // 格式: 原创名称-抖音:侵权人账号名称-原创分享链接+侵权人分享链接+侵权视频标题+侵权视频时长[+封面URL]
            // 示例(含封面): 花开富贵-抖音:文文工艺品-https://v.douyin.com/xxx/+https://v.douyin.com/iFLNKJNj/+花开富贵檀香，燃起来就会开花+106+https://p3-aio.ecombdimg.com/jpeg_m_xxx

            // 🆕 先检查最后一段是否为封面URL（以http开头则为新格式，剥离后再解析其余字段）
            String effectiveInfo = info;
            {
                int lastIdx = info.lastIndexOf("+");
                if (lastIdx > 0) {
                    String lastSeg = info.substring(lastIdx + 1).trim();
                    if (lastSeg.startsWith("http")) {
                        result.coverImageUrl = lastSeg;
                        Log.d("MainActivity", "✅ 封面URL: " + lastSeg);
                        effectiveInfo = info.substring(0, lastIdx); // 剥离封面URL，后续解析用短字符串
                    }
                }
            }

            // 1. 提取视频时长 (effectiveInfo中最后一个+号之后的内容)
            int lastPlusIndex = effectiveInfo.lastIndexOf("+");
            if (lastPlusIndex > 0 && lastPlusIndex < effectiveInfo.length() - 1) {
                String durationStr = effectiveInfo.substring(lastPlusIndex + 1).trim();
                try {
                    result.videoDurationSeconds = Integer.parseInt(durationStr);
                    Log.d("MainActivity", "✅ 视频时长: " + result.videoDurationSeconds + "秒");
                } catch (NumberFormatException e) {
                    Log.d("MainActivity", "⚠️ 视频时长解析失败,使用默认值60秒");
                    result.videoDurationSeconds = 60;
                }
            } else {
                Log.d("MainActivity", "⚠️ 未找到视频时长,使用默认值60秒");
                result.videoDurationSeconds = 60;
            }

            // 2. 提取视频标题 (倒数第二个+号和最后一个+号之间的内容)
            if (lastPlusIndex > 0) {
                String beforeLastPlus = effectiveInfo.substring(0, lastPlusIndex);
                int secondLastPlusIndex = beforeLastPlus.lastIndexOf("+");

                if (secondLastPlusIndex > 0) {
                    result.videoKeywords = beforeLastPlus.substring(secondLastPlusIndex + 1).trim();
                    Log.d("MainActivity", "✅ 视频关键词: " + result.videoKeywords);
                } else {
                    Log.d("MainActivity", "⚠️ 未找到视频关键词");
                }
            }

            // 3. 提取侵权链接 (倒数第三个+号和倒数第二个+号之间的内容)
            if (lastPlusIndex > 0) {
                String beforeLastPlus = effectiveInfo.substring(0, lastPlusIndex);
                int secondLastPlusIndex = beforeLastPlus.lastIndexOf("+");

                if (secondLastPlusIndex > 0) {
                    String beforeSecondLastPlus = beforeLastPlus.substring(0, secondLastPlusIndex);
                    int thirdLastPlusIndex = beforeSecondLastPlus.lastIndexOf("+");

                    if (thirdLastPlusIndex > 0) {
                        result.infringementUrl = beforeSecondLastPlus.substring(thirdLastPlusIndex + 1).trim();
                        Log.d("MainActivity", "✅ 侵权链接: " + result.infringementUrl);
                    } else {
                        Log.d("MainActivity", "⚠️ 未找到侵权链接");
                    }
                }
            }

            // 3. 提取备注 (格式: 原创名称-抖音:侵权人账号名称)
            // 新格式: 原创名称-抖音:侵权人账号名称-原创分享链接+侵权人分享链接+侵权视频标题
            int douyinIndex = info.indexOf("-抖音:");

            if (douyinIndex > 0) {
                // 找到了"-抖音:",提取原创名称
                String originalName = info.substring(0, douyinIndex).trim();

                // 从"-抖音:"之后开始查找侵权人账号名称
                String afterDouyin = info.substring(douyinIndex + 4); // 跳过"-抖音:"

                // 侵权人账号名称到下一个"-"之前 (新格式中,账号名称后面是"-原创分享链接")
                int nextDash = afterDouyin.indexOf("-");
                String infringerName;
                if (nextDash > 0) {
                    infringerName = afterDouyin.substring(0, nextDash).trim();
                } else {
                    // 如果没有"-",就使用全部内容
                    infringerName = afterDouyin.trim();
                }

                // 生成备注: 原创名称-抖音:侵权人账号名称
                result.remark = originalName + "-抖音:" + infringerName;
                // 🔧 直接存储解析结果，让 MainActivity 通过独立 setter 传给 service，避免 setRemark 二次解析失败
                result.infringerName = infringerName;
                result.originalName = originalName;
                Log.d("MainActivity", "✅ 备注: " + result.remark);
                Log.d("MainActivity", "  - 原创名称: " + originalName);
                Log.d("MainActivity", "  - 侵权人账号名称: " + infringerName);
            } else {
                // 没有找到"-抖音:",使用完整内容作为备注
                result.remark = info;
                Log.d("MainActivity", "⚠️ 未找到'-抖音:',使用完整内容作为备注");
            }

        } catch (Exception e) {
            Log.e("MainActivity", "解析失败: " + e.getMessage());
            result.remark = info;
        }

        return result;
    }

    /**
     * 解析结果类
     */
    private static class ParseResult {
        String infringementUrl;
        String remark;
        String videoKeywords; // 🆕 视频文案关键词
        int videoDurationSeconds; // 🆕 视频时长(秒)
        String coverImageUrl; // 🆕 侵权视频封面URL（用于创作灵感MD5对比）
        String infringerName = ""; // 🔧 直接存储已解析的侵权人名称，避免setRemark二次解析失败
        String originalName = "";  // 🔧 直接存储已解析的原创名称
    }

    /**
     * 🆕 从 et_sales_threshold 输入框解析销量阈值，留空则返回 -1（不筛选）
     */
    private int parseSalesThresholdInput() {
        if (etSalesThreshold == null || etSalesThreshold.getText() == null) return -1;
        String s = etSalesThreshold.getText().toString().trim();
        if (s.isEmpty()) return -1;
        try {
            int val = Integer.parseInt(s);
            return val > 0 ? val : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * 🔐 验证测试模式：切换到抖音，点击"资质证照"，测试超级鹰验证码能否通过
     * 使用前：手动把抖音停在"店铺详情"页面，然后回到本APK点击按钮
     */
    private void startCaptchaTestMode() {
        if (!AutomationAccessibilityService.isServiceAvailable()) {
            Toast.makeText(this, R.string.toast_accessibility_required, Toast.LENGTH_LONG).show();
            return;
        }

        AutomationAccessibilityService service = AutomationAccessibilityService.getInstance();
        if (service == null) {
            Toast.makeText(this, "无障碍服务未启动", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "🔐 验证测试启动，请确保抖音已在店铺详情页", Toast.LENGTH_LONG).show();
        service.startCaptchaTestMode();
        isRunning = true;
        updateStatus(STATUS_RUNNING);
    }

    /**
     * 🛒 带货测试模式：切换到抖音（创作灵感界面）→ 点击"达人" → 切换近90日 → 查找侵权账号
     * 使用前：手动把抖音停在"创作灵感"页面，输入框填写取证信息（用于提取侵权人账号名称）
     */
    private void startCargoTestMode() {
        if (!AutomationAccessibilityService.isServiceAvailable()) {
            Toast.makeText(this, R.string.toast_accessibility_required, Toast.LENGTH_LONG).show();
            return;
        }

        AutomationAccessibilityService service = AutomationAccessibilityService.getInstance();
        if (service == null) {
            Toast.makeText(this, "无障碍服务未启动", Toast.LENGTH_SHORT).show();
            return;
        }

        // 从输入框提取侵权人名称（可选，不填则只截图不查人）
        String evidenceInfo = "";
        if (etRemark != null && etRemark.getText() != null) {
            evidenceInfo = etRemark.getText().toString().trim();
        }
        if (!evidenceInfo.isEmpty()) {
            ParseResult parseResult = parseEvidenceInfo(evidenceInfo);
            service.setInfringerName(parseResult.infringerName);
            service.setOriginalName(parseResult.originalName);
            service.setRemark(parseResult.remark);
            Log.d("MainActivity", "✅ [带货测试] 侵权人: '" + parseResult.infringerName + "'");
        } else {
            // 未填写，清空侵权人名称，仅走截图流程
            service.setInfringerName("");
            service.setOriginalName("");
            Log.d("MainActivity", "⚠️ [带货测试] 未填写取证信息，仅执行截图流程");
        }

        Toast.makeText(this, "🛒 带货测试启动，请确保抖音已在创作灵感页", Toast.LENGTH_LONG).show();
        service.startCargoTestMode();
        isRunning = true;
        updateStatus(STATUS_RUNNING);
    }

    /**
     * 💰 销量测试模式：切换到抖音（创作灵感界面）→ 封面Key对比 + 销量阈值筛选 → 取证
     * 使用前：手动把抖音停在"创作灵感"页面，填写取证信息和销量阈值
     */
    private void startSalesTestMode() {
        if (!AutomationAccessibilityService.isServiceAvailable()) {
            Toast.makeText(this, R.string.toast_accessibility_required, Toast.LENGTH_LONG).show();
            return;
        }
        AutomationAccessibilityService service = AutomationAccessibilityService.getInstance();
        if (service == null) {
            Toast.makeText(this, "无障碍服务未启动", Toast.LENGTH_SHORT).show();
            return;
        }

        // 解析取证信息（用于封面Key和侵权人名称）
        String evidenceInfo = "";
        if (etRemark != null && etRemark.getText() != null) {
            evidenceInfo = etRemark.getText().toString().trim();
        }
        if (!evidenceInfo.isEmpty()) {
            ParseResult parseResult = parseEvidenceInfo(evidenceInfo);
            service.setInfringerName(parseResult.infringerName);
            service.setOriginalName(parseResult.originalName);
            service.setRemark(parseResult.remark);
            if (parseResult.coverImageUrl != null && !parseResult.coverImageUrl.isEmpty()) {
                service.setCoverImageUrl(parseResult.coverImageUrl);
                Log.d("MainActivity", "✅ [销量测试] 封面URL: " + parseResult.coverImageUrl);
            }
            Log.d("MainActivity", "✅ [销量测试] 侵权人: '" + parseResult.infringerName + "'");
        } else {
            service.setInfringerName("");
            service.setOriginalName("");
            Log.d("MainActivity", "⚠️ [销量测试] 未填写取证信息");
        }

        // 设置销量阈值
        int salesThresh = parseSalesThresholdInput();
        service.setSalesThreshold(salesThresh);
        Log.d("MainActivity", "✅ [销量测试] 销量阈值: " + (salesThresh > 0 ? salesThresh : "不筛选"));

        Toast.makeText(this, "💰 销量测试启动，请确保抖音已在创作灵感页", Toast.LENGTH_LONG).show();
        service.startSalesTestMode();
        isRunning = true;
        updateStatus(STATUS_RUNNING);
    }

    /**
     * 停止自动化
     */
    private void stopAutomation() {
        // 停止自动化
        AutomationAccessibilityService service = AutomationAccessibilityService.getInstance();
        if (service != null) {
            service.stopAutomation();
        }

        isRunning = false;
        updateStatus(STATUS_IDLE);
        Toast.makeText(this, R.string.toast_stopped, Toast.LENGTH_SHORT).show();
    }

    /**
     * 更新状态显示
     */
    private void updateStatus(int status) {
        if (status == STATUS_IDLE) {
            tvStatus.setText(R.string.status_idle);
            tvStatus.setTextColor(getResources().getColor(R.color.text_secondary, null));
            tvStatusDetail.setText("等待开始自动化任务");
            viewStatusIndicator.setBackgroundColor(getResources().getColor(R.color.text_secondary, null));
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
        } else if (status == STATUS_RUNNING) {
            tvStatus.setText(R.string.status_running);
            tvStatus.setTextColor(getResources().getColor(R.color.status_info, null));
            tvStatusDetail.setText("自动化任务正在执行中...");
            viewStatusIndicator.setBackgroundColor(getResources().getColor(R.color.status_info, null));
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
        } else if (status == STATUS_RECORDING) {
            tvStatus.setText(R.string.status_recording);
            tvStatus.setTextColor(getResources().getColor(R.color.status_success, null));
            tvStatusDetail.setText("正在录制屏幕...");
            viewStatusIndicator.setBackgroundColor(getResources().getColor(R.color.status_success, null));
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
        } else if (status == STATUS_ERROR) {
            tvStatus.setText(R.string.status_error);
            tvStatus.setTextColor(getResources().getColor(R.color.status_error, null));
            tvStatusDetail.setText("自动化执行出错,请查看日志");
            viewStatusIndicator.setBackgroundColor(getResources().getColor(R.color.status_error, null));
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
        }
    }

    // 状态常量
    private static final int STATUS_IDLE = 0;
    private static final int STATUS_RUNNING = 1;
    private static final int STATUS_RECORDING = 2;
    private static final int STATUS_ERROR = 3;
}

