package com.fanwe.g419.model.custommsg;

import com.fanwe.g419.LiveConstant.CustomMsgType;

public class CustomMsgPrivateText extends CustomMsg
{

    private String text = "";

    public CustomMsgPrivateText()
    {
        super();
        setType(CustomMsgType.MSG_PRIVATE_TEXT);
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        if (text != null)
        {
            this.text = text;
        }
    }

    @Override
    public String getConversationDesc()
    {
        return text;
    }

}
