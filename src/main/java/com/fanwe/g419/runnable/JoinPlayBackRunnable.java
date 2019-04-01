package com.fanwe.g419.runnable;

import android.app.Activity;

import com.fanwe.library.common.SDActivityManager;
import com.fanwe.library.looper.impl.SDWaitRunner;
import com.fanwe.g419.LiveInformation;
import com.fanwe.g419.activity.room.LivePlaybackActivity;
import com.fanwe.g419.common.AppRuntimeWorker;
import com.fanwe.g419.model.PlayBackData;

/**
 * Created by Administrator on 2017/7/11.
 */

public class JoinPlayBackRunnable implements Runnable
{
    private PlayBackData mData;

    public JoinPlayBackRunnable(PlayBackData data)
    {
        mData = data;
    }

    @Override
    public void run()
    {
        final Activity activity = SDActivityManager.getInstance().getLastActivity();
        if (activity == null)
        {
            return;
        }

        new SDWaitRunner().run(new Runnable()
        {
            @Override
            public void run()
            {
                AppRuntimeWorker.startPlayback(mData, activity);
            }
        }).condition(new SDWaitRunner.Condition()
        {
            @Override
            public boolean canRun()
            {
                if (SDActivityManager.getInstance().containActivity(LivePlaybackActivity.class)
                        || LiveInformation.getInstance().getRoomId() > 0)
                {
                    return false;
                } else
                {
                    return true;
                }
            }
        }).setTimeout(10 * 1000).startWait(100);
    }
}
