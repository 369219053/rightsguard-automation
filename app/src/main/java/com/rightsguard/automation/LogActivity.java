package com.rightsguard.automation;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
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

public class LogActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private ImageView ivExport;
    private ScrollView scrollView;
    private TextView tvLogContent;
    private MaterialButton btnClearLog;
    private MaterialButton btnCopyLog;
    private MaterialButton btnExportLog;

    private Handler handler;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        handler = new Handler(Looper.getMainLooper());

        initViews();
        setupListeners();
        loadLogs();
        startAutoUpdate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAutoUpdate();
    }

    private void initViews() {
        ivBack = findViewById(R.id.iv_back);
        ivExport = findViewById(R.id.iv_export);
        scrollView = findViewById(R.id.scroll_view);
        tvLogContent = findViewById(R.id.tv_log_content);
        btnClearLog = findViewById(R.id.btn_clear_log);
        btnCopyLog = findViewById(R.id.btn_copy_log);
        btnExportLog = findViewById(R.id.btn_export_log);
    }

    private void setupListeners() {
        ivBack.setOnClickListener(this);
        ivExport.setOnClickListener(this);
        btnClearLog.setOnClickListener(this);
        btnCopyLog.setOnClickListener(this);
        btnExportLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.iv_export || id == R.id.btn_export_log) {
            exportLog();
        } else if (id == R.id.btn_clear_log) {
            showClearLogDialog();
        } else if (id == R.id.btn_copy_log) {
            copyLog();
        }
    }

    private void loadLogs() {
        updateLogDisplay();
    }

    private void startAutoUpdate() {
        isRunning = true;
        scheduleNextUpdate();
    }

    private void stopAutoUpdate() {
        isRunning = false;
        handler.removeCallbacksAndMessages(null);
    }

    private void scheduleNextUpdate() {
        if (!isRunning) return;
        handler.postDelayed(() -> {
            if (isRunning) {
                updateLogDisplay();
                scheduleNextUpdate();
            }
        }, 1000);
    }

    private void updateLogDisplay() {
        String logs = AutomationAccessibilityService.getLogs();

        if (logs == null || logs.isEmpty()) {
            tvLogContent.setText(R.string.log_empty);
            tvLogContent.setTextColor(getResources().getColor(R.color.text_hint, null));
        } else {
            tvLogContent.setText(logs);
            tvLogContent.setTextColor(getResources().getColor(R.color.text_secondary, null));
            scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
        }
    }

    private void showClearLogDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_clear_log_title);
        builder.setMessage(R.string.dialog_clear_log_message);
        builder.setPositiveButton(R.string.dialog_confirm, (dialog, which) -> clearLog());
        builder.setNegativeButton(R.string.dialog_cancel, null);
        builder.show();
    }

    private void clearLog() {
        AutomationAccessibilityService.clearLogs();
        updateLogDisplay();
        Toast.makeText(this, R.string.toast_log_cleared, Toast.LENGTH_SHORT).show();
    }

    private void copyLog() {
        String logs = AutomationAccessibilityService.getLogs();

        if (logs == null || logs.isEmpty()) {
            Toast.makeText(this, "没有日志可复制", Toast.LENGTH_SHORT).show();
            return;
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("自动化日志", logs);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this, "✅ 日志已复制到剪贴板", Toast.LENGTH_SHORT).show();
    }

    private void exportLog() {
        String logs = AutomationAccessibilityService.getLogs();

        if (logs == null || logs.isEmpty()) {
            Toast.makeText(this, "没有日志可导出", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // 1. 生成Markdown格式的日志内容
            StringBuilder markdown = new StringBuilder();
            markdown.append("# 权利卫士取证自动化 - 运行日志\n\n");
            markdown.append("**导出时间**: ").append(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    .format(new Date())
            ).append("\n\n");
            markdown.append("---\n\n");
            markdown.append("## 📋 日志内容\n\n");
            markdown.append("```\n");
            markdown.append(logs);
            markdown.append("\n```\n\n");
            markdown.append("---\n\n");
            markdown.append("*由权利卫士取证自动化系统自动生成*\n");

            // 2. 保存到Documents/RightsGuard/目录
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                    .format(new Date());
            String fileName = "Automation_Log_" + timestamp + ".md";

            File documentsDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS);
            File rightsGuardDir = new File(documentsDir, "RightsGuard");

            // 确保目录存在
            if (!rightsGuardDir.exists()) {
                rightsGuardDir.mkdirs();
            }

            File logFile = new File(rightsGuardDir, fileName);

            FileWriter writer = new FileWriter(logFile);
            writer.write(markdown.toString());
            writer.close();

            // 3. 创建分享Intent
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/markdown");
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            // 使用FileProvider获取URI
            android.net.Uri uri = androidx.core.content.FileProvider.getUriForFile(
                this,
                "com.rightsguard.automation.fileprovider",
                logFile
            );

            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "权利卫士取证自动化 - 运行日志");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "运行日志已导出,请查看附件");

            // 4. 弹出分享对话框
            startActivity(Intent.createChooser(shareIntent, "分享日志"));

            Toast.makeText(this, "✅ 日志已保存并准备分享", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "❌ 导出失败: " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

}

