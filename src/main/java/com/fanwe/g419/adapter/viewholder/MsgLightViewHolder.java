package com.fanwe.g419.adapter.viewholder;

import android.view.View;

import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.g419.R;
import com.fanwe.g419.model.custommsg.CustomMsg;
import com.fanwe.g419.model.custommsg.CustomMsgLight;
import com.fanwe.g419.span.LiveHeartSpan;

/**
 * 点亮
 */
public class MsgLightViewHolder extends MsgTextViewHolder
{
    public MsgLightViewHolder(View itemView)
    {
        super(itemView);
    }

    @Override
    protected void bindCustomMsg(int position, CustomMsg customMsg)
    {
        CustomMsgLight msg = (CustomMsgLight) customMsg;

        appendUserInfo(msg.getSender());

        // 内容
        String text = SDResourcesUtil.getString(R.string.live_light);
        int textColor = 0;
        textColor = SDResourcesUtil.getColor(R.color.live_msg_text_creater);

//        if (customMsg.getSender().getUser_id().equals(LiveInformation.getInstance().getCreaterId()))
//        {
//            // 主播
//            textColor = SDResourcesUtil.getColor(R.color.live_msg_text_creater);
//        }
//        else if(customMsg.getSender().getUser_id().equals(AppRuntimeWorker.getLoginUserID())){
//            //自己
//            textColor = SDResourcesUtil.getColor(R.color.main_color);
//        }
//        else{
//            textColor = SDResourcesUtil.getColor(R.color.live_msg_text_creater);
//        }


        appendContent(text, textColor);

        // 心
        int id = SDResourcesUtil.getIdentifierDrawable(msg.getImageName());
        if (id == 0)
        {
            id = R.drawable.heart0;
        }
        LiveHeartSpan giftSpan = new LiveHeartSpan(getAdapter().getActivity(), id);
        sb.appendSpan(giftSpan, "heart");

        setUserInfoClickListener(tv_content);
    }
}