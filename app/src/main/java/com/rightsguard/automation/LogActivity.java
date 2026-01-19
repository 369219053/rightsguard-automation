package com.rightsguard.automation;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日志界面Activity
 * 显示和管理运行日志
 */
public class LogActivity extends AppCompatActivity {

    private ImageView ivBack;
    private ImageView ivExport;
    private TextView tvLogContent;
    private MaterialButton btnClearLog;
    private MaterialButton btnExportLog;

    private StringBuilder logBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        initViews();
        setupListeners();
        loadLogs();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        ivBack = findViewById(R.id.iv_back);
        ivExport = findViewById(R.id.iv_export);
        tvLogContent = findViewById(R.id.tv_log_content);
        btnClearLog = findViewById(R.id.btn_clear_log);
        btnExportLog = findViewById(R.id.btn_export_log);
    }

    /**
     * 设置监听器
     */
    private void setupListeners() {
        // 返回按钮
        ivBack.setOnClickListener(v -> finish());

        // 导出按钮(标题栏)
        ivExport.setOnClickListener(v -> exportLog());

        // 清除日志按钮
        btnClearLog.setOnClickListener(v -> showClearLogDialog());

        // 导出日志按钮
        btnExportLog.setOnClickListener(v -> exportLog());
    }

    /**
     * 加载日志
     */
    private void loadLogs() {
        // TODO: 从文件或数据库加载日志
        // 这里使用示例日志
        logBuilder.append("[2024-01-19 10:30:15] 系统启动\n");
        logBuilder.append("[2024-01-19 10:30:16] 检查权限...\n");
        logBuilder.append("[2024-01-19 10:30:17] 无障碍服务已启用\n");
        logBuilder.append("[2024-01-19 10:30:18] 悬浮窗权限已授予\n");
        logBuilder.append("[2024-01-19 10:30:20] 开始自动化任务\n");
        logBuilder.append("[2024-01-19 10:30:25] 正在查找权利卫士应用...\n");
        logBuilder.append("[2024-01-19 10:30:30] 应用已找到,准备启动\n");
        logBuilder.append("[2024-01-19 10:30:35] 等待界面加载...\n");
        logBuilder.append("[2024-01-19 10:30:40] 界面加载完成\n");
        logBuilder.append("[2024-01-19 10:30:45] 开始执行自动化操作\n");

        updateLogDisplay();
    }

    /**
     * 更新日志显示
     */
    private void updateLogDisplay() {
        if (logBuilder.length() == 0) {
            tvLogContent.setText(R.string.log_empty);
            tvLogContent.setTextColor(getResources().getColor(R.color.text_hint, null));
        } else {
            tvLogContent.setText(logBuilder.toString());
            tvLogContent.setTextColor(getResources().getColor(R.color.text_secondary, null));
        }
    }

    /**
     * 显示清除日志确认对话框
     */
    private void showClearLogDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_clear_log_title)
                .setMessage(R.string.dialog_clear_log_message)
                .setPositiveButton(R.string.dialog_confirm, (dialog, which) -> clearLog())
                .setNegativeButton(R.string.dialog_cancel, null)
                .show();
    }

    /**
     * 清除日志
     */
    private void clearLog() {
        logBuilder.setLength(0);
        updateLogDisplay();
        Toast.makeText(this, R.string.toast_log_cleared, Toast.LENGTH_SHORT).show();
    }

    /**
     * 导出日志
     */
    private void exportLog() {
        if (logBuilder.length() == 0) {
            Toast.makeText(this, "没有日志可导出", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // 生成文件名
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                    .format(new Date());
            String fileName = "automation_log_" + timestamp + ".txt";

            // 保存到Downloads目录
            File downloadsDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS);
            File logFile = new File(downloadsDir, fileName);

            // 写入文件
            FileWriter writer = new FileWriter(logFile);
            writer.write(logBuilder.toString());
            writer.close();

            Toast.makeText(this, "日志已导出到: " + logFile.getAbsolutePath(), 
                    Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "导出失败: " + e.getMessage(), 
                    Toast.LENGTH_SHORT).show();
        }
    }
}

