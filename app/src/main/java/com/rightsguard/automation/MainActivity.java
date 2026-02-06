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

            // 设置侵权链接
            if (parseResult.infringementUrl != null && !parseResult.infringementUrl.isEmpty()) {
                service.setInfringementUrl(parseResult.infringementUrl);
                Log.d("MainActivity", "✅ 已设置侵权链接: " + parseResult.infringementUrl);
            } else {
                Log.d("MainActivity", "⚠️ 未解析到侵权链接");
            }

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

            // 智能提取侵权链接 (最后一个URL)
            int lastHttpIndex = info.lastIndexOf("http://");
            int lastHttpsIndex = info.lastIndexOf("https://");
            int lastUrlStart = Math.max(lastHttpIndex, lastHttpsIndex);

            if (lastUrlStart >= 0) {
                String urlPart = info.substring(lastUrlStart);
                int spaceIndex = urlPart.indexOf(" ");
                if (spaceIndex > 0) {
                    result.infringementUrl = urlPart.substring(0, spaceIndex).trim();
                } else {
                    result.infringementUrl = urlPart.trim();
                }
                Log.d("MainActivity", "✅ 侵权链接: " + result.infringementUrl);
            }

            // 🆕 提取备注 (第一个URL之前的内容)
            int firstHttpIndex = info.indexOf("http://");
            int firstHttpsIndex = info.indexOf("https://");
            int firstUrlStart = -1;

            if (firstHttpIndex >= 0 && firstHttpsIndex >= 0) {
                firstUrlStart = Math.min(firstHttpIndex, firstHttpsIndex);
            } else if (firstHttpIndex >= 0) {
                firstUrlStart = firstHttpIndex;
            } else if (firstHttpsIndex >= 0) {
                firstUrlStart = firstHttpsIndex;
            }

            if (firstUrlStart > 0) {
                result.remark = info.substring(0, firstUrlStart).trim();
                Log.d("MainActivity", "✅ 备注: " + result.remark);
            } else {
                result.remark = info;
                Log.d("MainActivity", "⚠️ 未找到URL,使用完整内容作为备注");
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

