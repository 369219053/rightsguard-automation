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

        // 获取备注内容
        String remark = "";
        if (etRemark != null && etRemark.getText() != null) {
            remark = etRemark.getText().toString().trim();
        }

        // 启动自动化
        AutomationAccessibilityService service = AutomationAccessibilityService.getInstance();
        if (service != null) {
            service.setRemark(remark);
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
}

