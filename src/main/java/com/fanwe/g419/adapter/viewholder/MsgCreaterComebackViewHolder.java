package com.fanwe.g419.adapter.viewholder;

import android.view.View;

import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.g419.R;
import com.fanwe.g419.model.custommsg.CustomMsg;
import com.fanwe.g419.model.custommsg.CustomMsgCreaterComeback;

/**
 * 主播回来
 */
public class MsgCreaterComebackViewHolder extends MsgViewHolder
{

    public MsgCreaterComebackViewHolder(View itemView)
    {
        super(itemView);
    }

    @Override
    protected void bindCustomMsg(int position, CustomMsg customMsg)
    {
        CustomMsgCreaterComeback msg = (CustomMsgCreaterComeback) customMsg;

        //title
        String title = SDResourcesUtil.getString(R.string.live_msg_title);
        int titleColor = SDResourcesUtil.getColor(R.color.live_msg_title);
        appendContent(title, titleColor);

        // 内容
        String text = msg.getText();
        int textColor = SDResourcesUtil.getColor(R.color.live_msg_content);
        appendContent(text, textColor);
    }
}