package com.fanwe.pay;

import com.fanwe.g419.LiveConstant;
import com.fanwe.g419.activity.room.ILiveActivity;
import com.fanwe.g419.business.LiveBaseBusiness;
import com.fanwe.g419.model.custommsg.MsgModel;
import com.fanwe.pay.model.custommsg.CustomMsgStartPayMode;
import com.fanwe.pay.model.custommsg.CustomMsgStartScenePayMode;

/**
 * Created by Administrator on 2016/12/1.
 */

public abstract class LivePayBusiness extends LiveBaseBusiness
{
    public LivePayBusiness(ILiveActivity liveActivity)
    {
        super(liveActivity);
    }

    public void onMsgPayMode(MsgModel msg)
    {
        if (msg.getCustomMsgType() == LiveConstant.CustomMsgType.MSG_START_PAY_MODE)
        {
            onMsgPayWillStart(msg.getCustomMsgStartPayMode());
        } else if (msg.getCustomMsgType() == LiveConstant.CustomMsgType.MSG_START_SCENE_PAY_MODE)
        {
            onMsgScenePayWillStart(msg.getCustomMsgStartScenePayMode());
        }
    }

    protected void onMsgPayWillStart(CustomMsgStartPayMode customMsg)
    {

    }

    protected void onMsgScenePayWillStart(CustomMsgStartScenePayMode customMsg)
    {

    }
}
