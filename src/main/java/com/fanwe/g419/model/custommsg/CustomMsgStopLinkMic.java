package com.fanwe.g419.model.custommsg;

import com.fanwe.g419.LiveConstant.CustomMsgType;

public class CustomMsgStopLinkMic extends CustomMsg
{

    public CustomMsgStopLinkMic()
    {
        super();
        setType(CustomMsgType.MSG_STOP_LINK_MIC);
    }
    private boolean is_pk;
    public boolean is_pk() {
        return is_pk;
    }

    public void setIs_pk(boolean is_pk) {
        this.is_pk = is_pk;
    }
}
