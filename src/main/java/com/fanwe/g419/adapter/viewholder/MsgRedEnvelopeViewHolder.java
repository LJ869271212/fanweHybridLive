package com.fanwe.g419.adapter.viewholder;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.g419.R;
import com.fanwe.g419.dialog.LiveRedEnvelopeNewDialog;
import com.fanwe.g419.model.custommsg.CustomMsg;
import com.fanwe.g419.model.custommsg.CustomMsgRedEnvelope;
import com.fanwe.g419.span.LiveMsgRedEnvelopeSpan;

/**
 * 红包消息
 */
public class MsgRedEnvelopeViewHolder extends MsgTextViewHolder
{
    public MsgRedEnvelopeViewHolder(View itemView)
    {
        super(itemView);
    }

    @Override
    protected void bindCustomMsg(int position, CustomMsg customMsg)
    {
        final CustomMsgRedEnvelope msg = (CustomMsgRedEnvelope) customMsg;

        appendUserInfo(msg.getSender());

        // 内容
        String text = msg.getDesc();
        int textColor = SDResourcesUtil.getColor(R.color.live_msg_send_gift);
        String color = msg.getFonts_color();
        if (!TextUtils.isEmpty(color))
        {
            textColor = Color.parseColor(color);
        }
        appendContent(text, textColor);

        // 红包
        LiveMsgRedEnvelopeSpan redEnvelopeSpan = new LiveMsgRedEnvelopeSpan(tv_content);
        redEnvelopeSpan.setImage(msg.getIcon());
        sb.appendSpan(redEnvelopeSpan, "red");

        tv_content.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LiveRedEnvelopeNewDialog dialog = new LiveRedEnvelopeNewDialog(getAdapter().getActivity(), msg);
                dialog.show();
            }
        });
    }
}
