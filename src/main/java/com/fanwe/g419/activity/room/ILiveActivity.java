package com.fanwe.g419.activity.room;

import com.fanwe.g419.ILiveInfo;

public interface ILiveActivity extends ILiveInfo
{

    /**
     * 打开直播间输入框
     *
     * @param content
     */
    void openSendMsg(String content);

}
