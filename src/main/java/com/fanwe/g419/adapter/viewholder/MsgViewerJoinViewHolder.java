package com.fanwe.g419.adapter.viewholder;

import android.view.View;

import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.g419.R;
import com.fanwe.g419.model.custommsg.CustomMsg;
import com.fanwe.g419.model.custommsg.CustomMsgViewerJoin;

/**
 * 普通用户加入提示
 */
public class MsgViewerJoinViewHolder extends MsgTextViewHolder
{
    public MsgViewerJoinViewHolder(View itemView)
    {
        super(itemView);
    }

    @Override
    protected void bindCustomMsg(int position, CustomMsg customMsg)
    {
        //title
        String title = SDResourcesUtil.getString(R.string.live_msg_title);
        int titleColor = SDResourcesUtil.getColor(R.color.live_msg_title);
        appendContent(title, titleColor);
        CustomMsgViewerJoin customMsgViewerJoin=(CustomMsgViewerJoin)customMsg;
        // 内容
        String text = customMsg.getSender().getNick_name() + " 来了";
        // 内容
        if(null!=customMsgViewerJoin.getMount()){
            if(null!=customMsgViewerJoin.getMount().getDesc()){
                text =customMsg.getSender().getNick_name()+ customMsgViewerJoin.getMount().getDesc();
            }
        }
        int textColor = SDResourcesUtil.getColor(R.color.live_msg_content);
        appendContent(text, textColor);
        setUserInfoClickListener(tv_content);
    }
}
