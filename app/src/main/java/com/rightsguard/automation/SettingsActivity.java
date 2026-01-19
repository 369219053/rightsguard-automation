package com.rightsguard.automation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

/**
 * 设置界面Activity
 * 配置权限和自动化参数
 */
public class SettingsActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvAccessibilityStatus;
    private TextView tvOverlayStatus;
    private MaterialButton btnAccessibility;
    private MaterialButton btnOverlay;
    private TextInputEditText etRetryCount;
    private TextInputEditText etWaitTime;
    private SwitchMaterial switchAutoRetry;
    private SwitchMaterial switchShowFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
        setupListeners();
        checkPermissions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        ivBack = findViewById(R.id.iv_back);
        tvAccessibilityStatus = findViewById(R.id.tv_accessibility_status);
        tvOverlayStatus = findViewById(R.id.tv_overlay_status);
        btnAccessibility = findViewById(R.id.btn_accessibility);
        btnOverlay = findViewById(R.id.btn_overlay);
        etRetryCount = findViewById(R.id.et_retry_count);
        etWaitTime = findViewById(R.id.et_wait_time);
        switchAutoRetry = findViewById(R.id.switch_auto_retry);
        switchShowFloat = findViewById(R.id.switch_show_float);
    }

    /**
     * 设置监听器
     */
    private void setupListeners() {
        // 返回按钮
        ivBack.setOnClickListener(v -> finish());

        // 无障碍服务设置
        btnAccessibility.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        });

        // 悬浮窗权限设置
        btnOverlay.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        });

        // 自动重试开关
        switchAutoRetry.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // TODO: 保存设置
            Toast.makeText(this, "自动重试: " + (isChecked ? "开启" : "关闭"), 
                    Toast.LENGTH_SHORT).show();
        });

        // 显示悬浮窗开关
        switchShowFloat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // TODO: 保存设置并控制悬浮窗显示
            Toast.makeText(this, "悬浮窗: " + (isChecked ? "显示" : "隐藏"), 
                    Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * 检查权限状态
     */
    private void checkPermissions() {
        // 检查无障碍服务
        boolean accessibilityEnabled = isAccessibilityServiceEnabled();
        updatePermissionStatus(tvAccessibilityStatus, accessibilityEnabled);

        // 检查悬浮窗权限
        boolean overlayEnabled = Settings.canDrawOverlays(this);
        updatePermissionStatus(tvOverlayStatus, overlayEnabled);
    }

    /**
     * 更新权限状态显示
     */
    private void updatePermissionStatus(TextView textView, boolean granted) {
        if (granted) {
            textView.setText(R.string.permission_granted);
            textView.setTextColor(getResources().getColor(R.color.status_success, null));
        } else {
            textView.setText(R.string.permission_denied);
            textView.setTextColor(getResources().getColor(R.color.status_error, null));
        }
    }

    /**
     * 检查无障碍服务是否已启用
     */
    private boolean isAccessibilityServiceEnabled() {
        // TODO: 实现无障碍服务检查逻辑
        return false;
    }
}

