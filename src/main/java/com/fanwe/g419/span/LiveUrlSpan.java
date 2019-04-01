package com.fanwe.g419.span;

import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.g419.R;

import android.os.Parcel;
import android.text.style.ForegroundColorSpan;

public class LiveUrlSpan extends ForegroundColorSpan
{

	public LiveUrlSpan(int color)
	{
		super(color);
	}

	public LiveUrlSpan(Parcel src)
	{
		super(src);
	}

	public LiveUrlSpan()
	{
		this(SDResourcesUtil.getColor(R.color.live_msg_url));
	}

}
