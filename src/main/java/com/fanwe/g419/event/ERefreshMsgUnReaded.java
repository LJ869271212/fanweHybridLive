package com.fanwe.g419.event;

import com.fanwe.g419.model.TotalConversationUnreadMessageModel;

/**
 * Created by Administrator on 2016/7/20.
 */
public class ERefreshMsgUnReaded
{
    public TotalConversationUnreadMessageModel model;

    public boolean isFromSetLocalReaded;//来自发送方发送消息为false，来自设置已读消息为true
}
