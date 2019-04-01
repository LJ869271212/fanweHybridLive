package com.fanwe.g419.dialog;

import android.app.Activity;

import com.fanwe.library.dialog.SDDialogBase;
import com.fanwe.g419.R;
import com.fanwe.g419.activity.room.ILiveActivity;

public class LiveBaseDialog extends SDDialogBase
{
    public LiveBaseDialog(Activity activity)
    {
        super(activity, R.style.dialogBase);
    }

    public ILiveActivity getLiveActivity()
    {
        if (getOwnerActivity() instanceof ILiveActivity)
        {
            return (ILiveActivity) getOwnerActivity();
        } else
        {
            return null;
        }
    }
}
