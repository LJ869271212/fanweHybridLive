package com.fanwe.g419.adapter.viewholder;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.g419.R;
import com.fanwe.g419.model.custommsg.CustomMsg;
import com.fanwe.g419.model.custommsg.CustomMsgLiveMsg;

/**
 * 直播消息
 */
public class MsgLiveMsgViewHolder extends MsgViewHolder
{
    public MsgLiveMsgViewHolder(View itemView)
    {
        super(itemView);
    }

    @Override
    protected void bindCustomMsg(int position, CustomMsg customMsg)
    {
        CustomMsgLiveMsg msg = (CustomMsgLiveMsg) customMsg;

        WebView webViewIndexArticle = (WebView) find(R.id.webViewIndexArticle);
        //声明WebSettings子类
        WebSettings webSettings = webViewIndexArticle.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        webViewIndexArticle.setWebViewClient(new WebViewClient(){
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view,request);
            }

        });
        webViewIndexArticle.setBackgroundColor(0);
        webViewIndexArticle.loadData(msg.getDesc(),"text/html; charset=UTF-8","utf-8");

        //title
//        String title = SDResourcesUtil.getString(R.string.live_msg_title);
//        int titleColor = SDResourcesUtil.getColor(R.color.live_msg_title);
//        appendContent(title, titleColor);
//
//        // 内容
//        String text = msg.getDesc();
//        int textColor = SDResourcesUtil.getColor(R.color.live_msg_content);
//        appendContent(text, textColor);
    }
}
