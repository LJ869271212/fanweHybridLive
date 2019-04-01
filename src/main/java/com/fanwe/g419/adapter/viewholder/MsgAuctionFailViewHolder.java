package com.fanwe.g419.adapter.viewholder;

import android.view.View;

import com.fanwe.auction.model.custommsg.CustomMsgAuctionFail;
import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.g419.R;
import com.fanwe.g419.model.custommsg.CustomMsg;

/**
 * 流拍
 * Created by Administrator on 2016/9/6.
 */
public class MsgAuctionFailViewHolder extends MsgViewHolder{

    public MsgAuctionFailViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void bindCustomMsg(int position, CustomMsg customMsg) {
        CustomMsgAuctionFail msg = (CustomMsgAuctionFail) customMsg;
//        appendUserInfo(msg.getUser());

        //title
        String title = SDResourcesUtil.getString(R.string.live_msg_auction_title);
        int titleColor = SDResourcesUtil.getColor(R.color.live_msg_title);
        appendContent(title, titleColor);

        // 内容
        String text = msg.getDesc();
        int textColor = SDResourcesUtil.getColor(R.color.main_color);
        appendContent(text, textColor);

        setUserInfoClickListener(tv_content, msg.getUser());
    }

}
