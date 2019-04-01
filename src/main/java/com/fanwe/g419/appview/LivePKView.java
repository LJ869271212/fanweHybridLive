package com.fanwe.g419.appview;

import android.content.Context;
import android.util.AttributeSet;

import com.fanwe.library.utils.LogUtil;
import com.fanwe.library.utils.SDViewUtil;
import com.fanwe.g419.R;
import com.fanwe.g419.model.custommsg.data.LinkMicLayoutParams;
import com.tencent.rtmp.TXLivePlayer;

/**
 * 多人连麦view
 */
public class LivePKView extends BaseAppView {
    public LivePKView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LivePKView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LivePKView(Context context) {
        super(context);
        init();
    }


    private LiveLinkMicView view_link_mic_0;

    public void stopPlay(){
        view_link_mic_0.stop();
    }
    protected void init() {
        setContentView(R.layout.view_pk_group);
        view_link_mic_0 = find(R.id.view_link_mic_0);
    }

    public void setLinkMicInfo(String url) {
        view_link_mic_0.setVisibility(VISIBLE);
        view_link_mic_0.setPlayer(url, TXLivePlayer.PLAY_TYPE_LIVE_RTMP_ACC);
        view_link_mic_0.getPusher().setConfigLinkMicSub();
        view_link_mic_0.start();
    }

    private void resetView(LiveLinkMicView view) {
        LogUtil.i("resetView:" + view.getUserId());
        view.resetView();
        SDViewUtil.setGone(view);
    }


    /**
     * 根据布局参数，重新设置view的位置和大小
     *
     * @param view
     * @param params
     */
    private void layoutView(LiveLinkMicView view, LinkMicLayoutParams params) {
        if (params == null) {
            return;
        }

        SDViewUtil.setVisible(view);

        int x = SDViewUtil.getScreenWidthPercent(params.getLocation_x());
        int y = SDViewUtil.getScreenHeightPercent(params.getLocation_y());
        int width = SDViewUtil.getScreenWidthPercent(params.getImage_width());
        int height = SDViewUtil.getScreenHeightPercent(params.getImage_height());

        SDViewUtil.setMarginLeft(view, x);
        SDViewUtil.setMarginTop(view, y);
        SDViewUtil.setSize(view, width, height);
        LogUtil.i("layoutView:" + view.getUserId());
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onDestroy() {
        view_link_mic_0.onDestroy();
    }

}
