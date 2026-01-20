package com.rightsguard.automation;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * UI Dump结果显示界面
 * ⚠️ 此功能仅用于开发调试,正式发布版本将移除!
 */
public class DumpResultActivity extends AppCompatActivity {
    
    private TextView tvDumpText;
    private ScrollView scrollView;
    private Button btnCopy;
    private Button btnSave;
    private Button btnShare;
    private Button btnClose;
    
    private String dumpText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dump_result);
        
        // 获取dump文本
        dumpText = getIntent().getStringExtra("dump_text");
        if (dumpText == null || dumpText.isEmpty()) {
            dumpText = "无法获取UI结构";
        }
        
        // 初始化视图
        initViews();
        
        // 设置监听器
        setupListeners();
        
        // 显示dump文本
        tvDumpText.setText(dumpText);
    }
    
    private void initViews() {
        tvDumpText = findViewById(R.id.tv_dump_text);
        scrollView = findViewById(R.id.scroll_view);
        btnCopy = findViewById(R.id.btn_copy);
        btnSave = findViewById(R.id.btn_save);
        btnShare = findViewById(R.id.btn_share);
        btnClose = findViewById(R.id.btn_close);
    }
    
    private void setupListeners() {
        btnCopy.setOnClickListener(v -> copyToClipboard());
        btnSave.setOnClickListener(v -> saveToFile());
        btnShare.setOnClickListener(v -> shareText());
        btnClose.setOnClickListener(v -> finish());
    }
    
    /**
     * 复制到剪贴板
     */
    private void copyToClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            ClipData clip = ClipData.newPlainText("UI Dump", dumpText);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "✅ 已复制到剪贴板", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "❌ 复制失败", Toast.LENGTH_SHORT).show();
        }
    }
    
    /**
     * 保存到文件
     */
    private void saveToFile() {
        try {
            // 创建文件名
            String fileName = "dump_" + System.currentTimeMillis() + ".txt";
            
            // 创建目录
            File dir = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "权利卫士取证");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            // 创建文件
            File file = new File(dir, fileName);
            
            // 写入内容
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(dumpText);
            }
            
            Toast.makeText(this, "✅ 已保存: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
            
        } catch (IOException e) {
            Toast.makeText(this, "❌ 保存失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
    /**
     * 分享文本
     */
    private void shareText() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "UI结构Dump");
        shareIntent.putExtra(Intent.EXTRA_TEXT, dumpText);
        startActivity(Intent.createChooser(shareIntent, "分享UI Dump"));
    }
}

