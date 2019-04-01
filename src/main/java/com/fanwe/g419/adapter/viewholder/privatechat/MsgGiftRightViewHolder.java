package com.fanwe.g419.adapter.viewholder.privatechat;

import android.view.View;
import android.widget.TextView;

import com.fanwe.library.utils.SDViewBinder;
import com.fanwe.g419.R;
import com.fanwe.g419.model.custommsg.CustomMsg;
import com.fanwe.g419.model.custommsg.CustomMsgPrivateGift;
import com.fanwe.g419.utils.GlideUtil;

/**
 * Created by Administrator on 2016/8/30.
 */
public class MsgGiftRightViewHolder extends MsgGiftLeftViewHolder
{
    public TextView tv_score;

    public MsgGiftRightViewHolder(View itemView)
    {
        super(itemView);
        tv_score = find(R.id.tv_score);
    }

    @Override
    protected void bindCustomMsg(int position, CustomMsg customMsg)
    {
        CustomMsgPrivateGift msg = (CustomMsgPrivateGift) customMsg;

        // 图片
        GlideUtil.load(msg.getProp_icon()).into(iv_gift);
        SDViewBinder.setTextView(tv_msg, msg.getFrom_msg());
        SDViewBinder.setTextView(tv_score, msg.getFrom_score());
    }
}
