package com.fanwe.g419.adapter.viewholder;

import android.view.View;

import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.g419.R;
import com.fanwe.g419.model.custommsg.CustomMsg;
import com.fanwe.g419.model.custommsg.CustomMsgViewerJoin;

/**
 * 高级用户加入提示
 */
public class MsgShouhuViewerJoinViewHolder extends MsgTextViewHolder
{
    public MsgShouhuViewerJoinViewHolder(View itemView)
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

        // 内容
        CustomMsgViewerJoin customMsgViewerJoin=(CustomMsgViewerJoin)customMsg;
        String text= customMsg.getSender().getNick_name() + "加入了...";
        if(null!=customMsgViewerJoin.getGuard().getGuard_animated().getContent()){
            text = customMsgViewerJoin.getGuard().getGuard_animated().getName()+ customMsg.getSender().getNick_name() + customMsgViewerJoin.getGuard().getGuard_animated().getContent();
        }
        int textColor = SDResourcesUtil.getColor(R.color.live_msg_content);
        appendContent(text, textColor);
        setUserInfoClickListener(tv_content);
    }
}
