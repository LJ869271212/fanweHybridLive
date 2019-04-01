package com.fanwe.g419.adapter.viewholder;

import android.view.View;

import com.fanwe.auction.model.custommsg.CustomMsgAuctionSuccess;
import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.g419.R;
import com.fanwe.g419.model.custommsg.CustomMsg;

/**
 * 竞拍成功
 * Created by Administrator on 2016/9/6.
 */
public class MsgAuctionSucViewHolder extends MsgViewHolder
{

    public MsgAuctionSucViewHolder(View itemView)
    {
        super(itemView);
    }

    @Override
    protected void bindCustomMsg(int position, CustomMsg customMsg)
    {
        CustomMsgAuctionSuccess msg = (CustomMsgAuctionSuccess) customMsg;
        appendUserInfo(msg.getUser());
        String text = msg.getDesc();
        int textColor = SDResourcesUtil.getColor(R.color.main_color);
        appendContent(text, textColor);
        setUserInfoClickListener(tv_content, msg.getUser());
    }

}
