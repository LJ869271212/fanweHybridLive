package com.fanwe.g419.adapter.viewholder;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.g419.R;
import com.fanwe.g419.model.custommsg.CustomMsg;
import com.fanwe.g419.model.custommsg.CustomMsgGift;
import com.fanwe.g419.span.LiveMsgGiftSpan;

/**
 * 主播礼物提示
 */
public class MsgGiftCreaterViewHolder extends MsgTextViewHolder
{
    public MsgGiftCreaterViewHolder(View itemView)
    {
        super(itemView);
    }

    @Override
    protected void bindCustomMsg(int position, CustomMsg customMsg)
    {
        CustomMsgGift msg = (CustomMsgGift) customMsg;

        appendUserInfo(msg.getSender());

        String text = msg.getDesc2();
        int textColor = SDResourcesUtil.getColor(R.color.live_msg_send_gift);
        String color = msg.getFonts_color();
        if (!TextUtils.isEmpty(color))
        {
            textColor = Color.parseColor(color);
        }
        appendContent(text, textColor);

        // 礼物
        String url = msg.getIcon();
        LiveMsgGiftSpan giftSpan = new LiveMsgGiftSpan(tv_content);
        giftSpan.setImage(url);
        sb.appendSpan(giftSpan, "gift");

        setUserInfoClickListener(tv_content);
    }
}
