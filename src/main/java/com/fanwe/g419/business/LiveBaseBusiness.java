package com.fanwe.g419.business;

import com.fanwe.g419.activity.room.ILiveActivity;

/**
 * Created by Administrator on 2017/6/3.
 */

public abstract class LiveBaseBusiness extends BaseBusiness
{
    private ILiveActivity mLiveActivity;

    public LiveBaseBusiness(ILiveActivity liveActivity)
    {
        mLiveActivity = liveActivity;
    }

    public ILiveActivity getLiveActivity()
    {
        return mLiveActivity;
    }

    @Override
    public void onDestroy()
    {
        mLiveActivity = null;
        super.onDestroy();
    }
}
