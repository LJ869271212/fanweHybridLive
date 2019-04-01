package com.fanwe.g419.adapter.viewholder;

import android.graphics.Color;
import android.view.View;

import com.fanwe.g419.LiveConstant;
import com.fanwe.g419.model.custommsg.CustomMsg;
import com.fanwe.g419.model.custommsg.CustomMsgText;

/**
 * 文字消息
 */
public class MsgTextViewHolder extends MsgViewHolder {
    public MsgTextViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void bindCustomMsg(int position, CustomMsg customMsg) {
        appendUserInfo(customMsg.getSender());
        // 内容
        int textColor = 0;
//        if (customMsg.getSender().getUser_id().equals(LiveInformation.getInstance().getCreaterId())) {
//            // 主播
//            textColor = SDResourcesUtil.getColor(R.color.main_color);
//        } else if (customMsg.getSender().getUser_id().equals(AppRuntimeWorker.getLoginUserID())) {
//            //自己
            textColor = Color.parseColor(getTextColor());
//        } else {
//            textColor = SDResourcesUtil.getColor(R.color.main_color);
//        }
        appendContent(getText(), textColor);
        setUserInfoClickListener(tv_content);
    }

    protected String getText() {
        CustomMsgText msg = (CustomMsgText) customMsg;
        return msg.getText();
    }

    protected String getTextColor() {
        if (customMsg.getType() == LiveConstant.CustomMsgType.MSG_TEXT) {
            CustomMsgText msg = (CustomMsgText) customMsg;
            if(null!=msg.getV_identity_colour()){
                return msg.getV_identity_colour();
            }else
            return "#FFFFFF";
        } else
            return "#FFFFFF";
    }
}
