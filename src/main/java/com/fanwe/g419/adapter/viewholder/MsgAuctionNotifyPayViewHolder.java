package com.fanwe.g419.adapter.viewholder;

import android.view.View;

import com.fanwe.auction.model.custommsg.CustomMsgAuctionNotifyPay;
import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.g419.R;
import com.fanwe.g419.model.custommsg.CustomMsg;

/**
 * 竞拍通知付款，比如第一名超时未付款，通知下一名付款
 * Created by Administrator on 2016/9/6.
 */
public class MsgAuctionNotifyPayViewHolder extends MsgViewHolder{

    public MsgAuctionNotifyPayViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void bindCustomMsg(int position, CustomMsg customMsg) {
        CustomMsgAuctionNotifyPay msg = (CustomMsgAuctionNotifyPay) customMsg;
        appendUserInfo(msg.getUser());
        String text = msg.getDesc();
        int textColor = SDResourcesUtil.getColor(R.color.main_color);
        appendContent(text, textColor);
        setUserInfoClickListener(tv_content, msg.getUser());
    }
}
