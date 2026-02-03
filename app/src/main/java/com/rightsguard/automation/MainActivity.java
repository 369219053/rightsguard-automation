package com.rightsguard.automation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
    private MaterialButton btnStop;
    private MaterialButton btnViewLog;
    private ImageView ivSettings;
    private TextInputEditText etRemark;

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
        btnStop = findViewById(R.id.btn_stop);
        btnViewLog = findViewById(R.id.btn_view_log);
        ivSettings = findViewById(R.id.iv_settings);
        etRemark = findViewById(R.id.et_remark);
    }

    /**
     * 设置监听器
     */
    private void setupListeners() {
        // 开始按钮
        btnStart.setOnClickListener(v -> startAutomation());

        // 停止按钮
        btnStop.setOnClickListener(v -> stopAutomation());

        // 查看日志按钮 - 暂时禁用,等待修复D8编译器bug
        btnViewLog.setOnClickListener(v -> {
            Toast.makeText(this, "日志功能暂时不可用,请使用logcat查看日志", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(MainActivity.this, LogActivity.class);
            // startActivity(intent);
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

        // 如果输入为空,直接返回
        if (evidenceInfo.isEmpty()) {
            Toast.makeText(this, "请输入取证信息", Toast.LENGTH_SHORT).show();
            return;
        }

        // 解析取证信息
        EvidenceData data = parseEvidenceInfo(evidenceInfo);
        if (data == null) {
            Toast.makeText(this, "取证信息格式错误\n正确格式: 原创名称-抖音:侵权人-原创链接+侵权链接", Toast.LENGTH_LONG).show();
            return;
        }

        // 显示解析结果
        String message = String.format("✅ 解析成功!\n原创: %s\n侵权人: %s\n原创链接: %s\n侵权链接: %s",
                data.originalName, data.infringerName,
                data.originalUrl.length() > 30 ? data.originalUrl.substring(0, 30) + "..." : data.originalUrl,
                data.infringementUrl.length() > 30 ? data.infringementUrl.substring(0, 30) + "..." : data.infringementUrl);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        // 启动自动化
        AutomationAccessibilityService service = AutomationAccessibilityService.getInstance();
        if (service != null) {
            // 只传递 "原创名称-抖音:侵权人名称",不包含链接
            String remark = data.originalName + "-抖音:" + data.infringerName;
            service.setRemark(remark);

            // TODO: 将原创链接和侵权链接传递给服务,供智能截图使用
            // service.setOriginalUrl(data.originalUrl);
            // service.setInfringementUrl(data.infringementUrl);

            service.startAutomation();
            isRunning = true;
            updateStatus(STATUS_RUNNING);
            Toast.makeText(this, R.string.toast_started, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "无障碍服务未启动", Toast.LENGTH_SHORT).show();
        }
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

    /**
     * 解析取证信息
     * 格式: 原创名称-抖音:侵权人账号名称-原创分享链接+侵权人分享链接
     * 示例: 张三-抖音:李四-https://www.douyin.com/xxx+https://www.douyin.com/yyy
     *
     * 智能解析: 能够自动提取URL,忽略分享链接中的描述文字
     */
    private EvidenceData parseEvidenceInfo(String info) {
        if (info == null || info.isEmpty()) {
            return null;
        }

        try {
            // 1. 找到 "-抖音:" 的位置
            int douyinIndex = info.indexOf("-抖音:");
            if (douyinIndex == -1) {
                return null;
            }

            // 2. 提取原创名称
            String originalName = info.substring(0, douyinIndex).trim();

            // 3. 从 "-抖音:" 后面开始查找下一个 "-"
            int nextDashIndex = info.indexOf("-", douyinIndex + 4);
            if (nextDashIndex == -1) {
                return null;
            }

            // 4. 提取侵权人账号名称
            String infringerName = info.substring(douyinIndex + 4, nextDashIndex).trim();

            // 5. 剩余部分包含两个链接,用 "+" 分割
            String urlsPart = info.substring(nextDashIndex + 1);
            int plusIndex = urlsPart.indexOf("+");
            if (plusIndex == -1) {
                return null;
            }

            // 6. 智能提取第一个URL (原创链接)
            String firstPart = urlsPart.substring(0, plusIndex);
            String originalUrl = extractUrl(firstPart);
            if (originalUrl == null) {
                return null;
            }

            // 7. 智能提取第二个URL (侵权链接)
            String secondPart = urlsPart.substring(plusIndex + 1);
            String infringementUrl = extractUrl(secondPart);
            if (infringementUrl == null) {
                return null;
            }

            return new EvidenceData(originalName, infringerName, originalUrl, infringementUrl);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从文本中智能提取URL
     * 支持提取 http:// 或 https:// 开头的URL
     * 会自动忽略URL前后的描述文字
     */
    private String extractUrl(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }

        try {
            // 查找 http:// 或 https:// 的位置
            int httpIndex = text.indexOf("http://");
            int httpsIndex = text.indexOf("https://");

            int startIndex = -1;
            if (httpIndex != -1 && httpsIndex != -1) {
                // 两个都存在,取最前面的
                startIndex = Math.min(httpIndex, httpsIndex);
            } else if (httpIndex != -1) {
                startIndex = httpIndex;
            } else if (httpsIndex != -1) {
                startIndex = httpsIndex;
            }

            if (startIndex == -1) {
                return null;
            }

            // 从 http 开始,找到URL的结束位置
            // URL结束的标志: 空格、换行、制表符等空白字符
            String urlPart = text.substring(startIndex);
            int endIndex = urlPart.length();

            // 查找第一个空白字符
            for (int i = 0; i < urlPart.length(); i++) {
                char c = urlPart.charAt(i);
                if (Character.isWhitespace(c)) {
                    endIndex = i;
                    break;
                }
            }

            String url = urlPart.substring(0, endIndex).trim();

            // 验证URL格式
            if (url.startsWith("http://") || url.startsWith("https://")) {
                return url;
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 取证数据类
     */
    private static class EvidenceData {
        String originalName;      // 原创名称
        String infringerName;     // 侵权人账号名称
        String originalUrl;       // 原创分享链接
        String infringementUrl;   // 侵权人分享链接

        EvidenceData(String originalName, String infringerName, String originalUrl, String infringementUrl) {
            this.originalName = originalName;
            this.infringerName = infringerName;
            this.originalUrl = originalUrl;
            this.infringementUrl = infringementUrl;
        }
    }
}

