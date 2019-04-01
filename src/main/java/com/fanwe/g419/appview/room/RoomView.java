package com.fanwe.g419.appview.room;

import android.content.Context;
import android.util.AttributeSet;

import com.fanwe.baimei.model.custommsg.BMCustomMsgPushStatus;
import com.fanwe.games.model.custommsg.CustomMsgGameBanker;
import com.fanwe.g419.activity.room.ILiveActivity;
import com.fanwe.g419.appview.BaseAppView;
import com.fanwe.g419.business.LiveMsgBusiness;
import com.fanwe.g419.event.EImOnNewMessages;
import com.fanwe.g419.model.App_viewerActModel;
import com.fanwe.g419.model.custommsg.CustomMsgAcceptLinkMic;
import com.fanwe.g419.model.custommsg.CustomMsgApplyLinkMic;
import com.fanwe.g419.model.custommsg.CustomMsgCreaterComeback;
import com.fanwe.g419.model.custommsg.CustomMsgCreaterLeave;
import com.fanwe.g419.model.custommsg.CustomMsgData;
import com.fanwe.g419.model.custommsg.CustomMsgEndVideo;
import com.fanwe.g419.model.custommsg.CustomMsgGift;
import com.fanwe.g419.model.custommsg.CustomMsgGreedLinkMic;
import com.fanwe.g419.model.custommsg.CustomMsgHeatRank;
import com.fanwe.g419.model.custommsg.CustomMsgLargeGift;
import com.fanwe.g419.model.custommsg.CustomMsgLight;
import com.fanwe.g419.model.custommsg.CustomMsgOpenShouhu;
import com.fanwe.g419.model.custommsg.CustomMsgPopMsg;
import com.fanwe.g419.model.custommsg.CustomMsgPrize;
import com.fanwe.g419.model.custommsg.CustomMsgRedEnvelope;
import com.fanwe.g419.model.custommsg.CustomMsgRejectLinkMic;
import com.fanwe.g419.model.custommsg.CustomMsgStopLinkMic;
import com.fanwe.g419.model.custommsg.CustomMsgStopLive;
import com.fanwe.g419.model.custommsg.CustomMsgViewerJoin;
import com.fanwe.g419.model.custommsg.CustomMsgViewerQuit;
import com.fanwe.g419.model.custommsg.CustomMsgWarning;
import com.fanwe.g419.model.custommsg.MsgModel;
import com.fanwe.g419.model.custommsg.data.DataLinkMicInfoModel;

/**
 * Created by Administrator on 2016/8/4.
 */
public class RoomView extends BaseAppView implements LiveMsgBusiness.LiveMsgBusinessCallback
{
    private LiveMsgBusiness mMsgBusiness;

    public RoomView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public RoomView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public RoomView(Context context)
    {
        super(context);
    }

    public ILiveActivity getLiveActivity()
    {
        if (getActivity() instanceof ILiveActivity)
        {
            return (ILiveActivity) getActivity();
        } else
        {
            return null;
        }
    }

    public LiveMsgBusiness getMsgBusiness()
    {
        if (mMsgBusiness == null)
        {
            mMsgBusiness = new LiveMsgBusiness();
            mMsgBusiness.setBusinessCallback(this);
        }
        return mMsgBusiness;
    }

    public final void onEventMainThread(EImOnNewMessages event)
    {
        getMsgBusiness().parseMsg(event.msg, getLiveActivity().getGroupId());
    }

    @Override
    public void onMsgRedEnvelope(CustomMsgRedEnvelope msg)
    {

    }

    @Override
    public void onMsgApplyLinkMic(CustomMsgApplyLinkMic msg)
    {

    }

    @Override
    public void onMsgAcceptLinkMic(CustomMsgAcceptLinkMic msg)
    {

    }

    @Override
    public void onMsgGreedLinkMic(CustomMsgGreedLinkMic msg) {

    }

    @Override
    public void onMsgRejectLinkMic(CustomMsgRejectLinkMic msg)
    {

    }

    @Override
    public void onMsgStopLinkMic(CustomMsgStopLinkMic msg)
    {

    }

    @Override
    public void onMsgEndVideo(CustomMsgEndVideo msg)
    {

    }

    @Override
    public void onMsgStopLive(CustomMsgStopLive msg)
    {

    }

    @Override
    public void onMsgPrivate(MsgModel msg)
    {

    }

    @Override
    public void onMsgAuction(MsgModel msg)
    {

    }

    @Override
    public void onMsgShop(MsgModel msg)
    {

    }

    @Override
    public void onMsgPayMode(MsgModel msg)
    {

    }

    @Override
    public void onMsgGame(MsgModel msg)
    {

    }

    @Override
    public void onMsgGameBanker(CustomMsgGameBanker msg)
    {

    }

    @Override
    public void onMsgGift(CustomMsgGift msg)
    {

    }

    @Override
    public void onMsgPopMsg(CustomMsgPopMsg msg)
    {

    }

    @Override
    public void onMsgViewerJoin(CustomMsgViewerJoin msg)
    {

    }

    @Override
    public void onMsgViewerQuit(CustomMsgViewerQuit msg)
    {

    }

    @Override
    public void onMsgCreaterLeave(CustomMsgCreaterLeave msg)
    {

    }

    @Override
    public void onMsgCreaterComeback(CustomMsgCreaterComeback msg)
    {

    }

    @Override
    public void onMsgLight(CustomMsgLight msg)
    {

    }

    @Override
    public void onMsgLiveChat(MsgModel msg)
    {

    }

    @Override
    public void onMsgWarning(CustomMsgWarning msgWarning)
    {

    }

    @Override
    public void onMsgData(CustomMsgData msg)
    {

    }

    @Override
    public void onMsgHeatRank(CustomMsgHeatRank msg) {

    }



    @Override
    public void onMsgPrize(CustomMsgPrize msg) {

    }

    @Override
    public void onMsgLiveOpenShouhu(CustomMsgOpenShouhu msg) {

    }

    @Override
    public void onMsgDataViewerList(App_viewerActModel model)
    {

    }

    @Override
    public void onMsgDataLinkMicInfo(DataLinkMicInfoModel model)
    {

    }

    @Override
    public void onMsgLargeGift(CustomMsgLargeGift customMsgLargeGift)
    {

    }

    @Override
    public void onMsgPushStatus(BMCustomMsgPushStatus bmCustomMsgPushStatus)
    {

    }
}
