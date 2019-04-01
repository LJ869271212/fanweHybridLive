package com.fanwe.g419.appview.main;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.fanwe.g419.R;
import com.fanwe.g419.model.HomeTabTitleModel;

public class LiveTabUrlView extends LiveTabBaseView {

    private HomeTabTitleModel mTabTitleModel;

    public LiveTabUrlView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    int type;

    public LiveTabUrlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LiveTabUrlView(Context context, HomeTabTitleModel mTabTitleModel) {
        super(context);
        this.mTabTitleModel = mTabTitleModel;
        init();
    }

    protected void init() {
        setContentView(R.layout.frag_live_tab_url);
        WebView mWebView = (WebView) findViewById(R.id.webView);
        if (!TextUtils.isEmpty(mTabTitleModel.getClassified_url())) {
            mWebView.loadUrl(mTabTitleModel.getClassified_url());
        }
        mWebView.getSettings().setJavaScriptEnabled(true);
    }
}
