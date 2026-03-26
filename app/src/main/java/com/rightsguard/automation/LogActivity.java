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

    /** 用户是否手动向上滚动了（true=不自动跟随底部；false=自动跟随最新日志） */
    private boolean userScrolledUp = false;

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

        // 监听用户手动滚动：向上翻时停止自动跟随，滑回底部附近时恢复自动跟随
        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            int maxScroll = scrollView.getChildAt(0).getHeight() - scrollView.getHeight();
            if (maxScroll <= 0) {
                userScrolledUp = false;
                return;
            }
            // 距离底部 120px 以内视为"在底部"，恢复自动跟随
            if (scrollY >= maxScroll - 120) {
                userScrolledUp = false;
            } else if (scrollY < oldScrollY) {
                // 用户主动往上滑，停止自动跟随
                userScrolledUp = true;
            }
        });
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
        try {
            String logs = AutomationAccessibilityService.getLogs();

            if (logs == null || logs.isEmpty()) {
                tvLogContent.setText(R.string.log_empty);
                tvLogContent.setTextColor(getResources().getColor(R.color.text_hint, null));
            } else {
                tvLogContent.setText(logs);
                tvLogContent.setTextColor(getResources().getColor(R.color.text_secondary, null));
                // 只有用户没有手动向上翻时才自动滚到底部
                if (!userScrolledUp) {
                    scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            tvLogContent.setText("❌ 日志加载失败: " + e.getMessage());
            tvLogContent.setTextColor(getResources().getColor(android.R.color.holo_red_dark, null));
            Toast.makeText(this, "日志加载失败: " + e.getMessage(), Toast.LENGTH_LONG).show();
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
        userScrolledUp = false; // 清空日志后恢复自动跟随底部
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

            // 2. 保存到应用私有外部存储目录(不需要权限,Android 11+兼容)
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                    .format(new Date());
            String fileName = "Automation_Log_" + timestamp + ".md";

            // 使用应用私有目录,避免权限问题
            File logsDir = new File(getExternalFilesDir(null), "Logs");

            // 确保目录存在
            if (!logsDir.exists()) {
                boolean created = logsDir.mkdirs();
                if (!created) {
                    Toast.makeText(this, "❌ 创建日志目录失败", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            File logFile = new File(logsDir, fileName);

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

            Toast.makeText(this, "✅ 日志已保存: " + logFile.getAbsolutePath(),
                Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "❌ 导出失败: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "❌ 发生异常: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

}

