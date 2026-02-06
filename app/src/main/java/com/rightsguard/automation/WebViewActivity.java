package com.rightsguard.automation;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.util.Log;

/**
 * WebView Activity - 用于在APP内部打开抖音链接
 * 避免跳转到外部浏览器导致无障碍服务被杀掉
 */
public class WebViewActivity extends Activity {
    private static final String TAG = "WebViewActivity";
    private WebView webView;
    private BroadcastReceiver closeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 创建WebView
        webView = new WebView(this);
        setContentView(webView);

        // 配置WebView
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setUserAgentString("Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.120 Mobile Safari/537.36");

        // 设置WebViewClient
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "拦截URL跳转: " + url);

                // 检测抖音URL Scheme (snssdk开头的是抖音的URL Scheme)
                if (url != null && url.startsWith("snssdk")) {
                    Log.d(TAG, "✅ 检测到抖音URL Scheme,准备打开抖音APP");

                    // 通知无障碍服务:抖音URL Scheme已检测到
                    AutomationAccessibilityService service = AutomationAccessibilityService.getInstance();
                    if (service != null) {
                        service.onDouyinSchemeDetected(url);
                    }

                    return true; // 阻止WebView加载这个URL
                }

                return false; // 其他URL正常加载
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG, "页面加载完成: " + url);
            }
        });

        // 注册关闭广播接收器
        closeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "收到关闭广播,关闭WebView Activity");
                finish();
            }
        };
        IntentFilter filter = new IntentFilter("com.rightsguard.automation.CLOSE_WEBVIEW");

        // Android 13+ 需要指定 RECEIVER_NOT_EXPORTED
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(closeReceiver, filter, Context.RECEIVER_NOT_EXPORTED);
        } else {
            registerReceiver(closeReceiver, filter);
        }

        // 获取URL并加载
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        if (url != null && !url.isEmpty()) {
            Log.d(TAG, "加载URL: " + url);
            webView.loadUrl(url);
        } else {
            Log.e(TAG, "URL为空!");
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (closeReceiver != null) {
            unregisterReceiver(closeReceiver);
        }
        if (webView != null) {
            webView.destroy();
        }
    }
}

