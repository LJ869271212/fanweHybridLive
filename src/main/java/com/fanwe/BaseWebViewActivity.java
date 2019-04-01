package com.fanwe;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fanwe.hybrid.activity.BaseTitleActivity;
import com.fanwe.g419.R;

public class BaseWebViewActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_webview_layout);
        mTitle.setMiddleTextTop("邀请奖励");
        mTitle.setLeftImageLeft(R.drawable.ic_arrow_left_white);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(getIntent().getStringExtra("url"));
    }
}
