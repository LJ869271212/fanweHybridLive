package com.fanwe.g419.adapter.viewholder;

import android.view.View;

import com.fanwe.g419.model.custommsg.CustomMsgPopMsg;

/**
 * 弹幕消息
 */
public class MsgPopViewHolder extends MsgTextViewHolder
{
    public MsgPopViewHolder(View itemView)
    {
        super(itemView);
    }

    @Override
    protected String getText()
    {
        CustomMsgPopMsg msg = (CustomMsgPopMsg) customMsg;
        return msg.getDesc();
    }
}
