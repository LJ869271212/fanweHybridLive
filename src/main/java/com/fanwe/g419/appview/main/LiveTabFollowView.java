package com.fanwe.g419.appview.main;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

import com.fanwe.hybrid.http.AppRequestCallback;
import com.fanwe.library.adapter.http.model.SDResponse;
import com.fanwe.library.common.SDHandlerManager;
import com.fanwe.library.model.SDTaskRunnable;
import com.fanwe.library.utils.SDCollectionUtil;
import com.fanwe.library.view.HeaderGridView;
import com.fanwe.g419.R;
import com.fanwe.g419.adapter.LiveTabFollowAdapter;
import com.fanwe.g419.common.CommonInterface;
import com.fanwe.g419.model.Index_focus_videoActModel;
import com.fanwe.g419.model.LivePlaybackModel;
import com.fanwe.g419.model.LiveRoomModel;
import com.fanwe.g419.view.SDProgressPullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 关注直播列表
 *
 * @author Administrator
 * @date 2016-7-2 上午11:28:13
 */
public class LiveTabFollowView extends LiveTabBaseView
{
    public LiveTabFollowView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }
    public LiveTabFollowView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public LiveTabFollowView(Context context)
    {
        super(context);
        init();
    }

    protected SDProgressPullToRefreshGridView lv_content;

    protected List<Object> listModel = new ArrayList<>();
    protected LiveTabFollowAdapter adapter;

    protected List<LiveRoomModel> listRoom;
    protected List<LivePlaybackModel> listPlayback;

    protected void init()
    {
        setContentView(R.layout.frag_live_tab_follow);
        lv_content = find(R.id.lv_content);

        setAdapter();
        initPullToRefresh();
    }

    protected void setAdapter()
    {
        adapter = new LiveTabFollowAdapter(listModel, getActivity());
        lv_content.setAdapter(adapter);
    }

    protected void initPullToRefresh()
    {
        lv_content.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        lv_content.setOnRefreshListener(new SDProgressPullToRefreshGridView.OnRefreshListener2<HeaderGridView>() {

                @Override
                public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                    requestData();
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                }
            });
        requestData();
    }

    @Override
    public void onActivityResumed(Activity activity)
    {
        requestData();
        super.onActivityResumed(activity);
    }

    protected void requestData()
    {
        CommonInterface.requestFocusVideo(new AppRequestCallback<Index_focus_videoActModel>()
        {
            @Override
            protected void onSuccess(SDResponse resp)
            {
                if (actModel.isOk())
                {
                    synchronized (LiveTabFollowView.this)
                    {
                        listRoom = actModel.getList();
                        listPlayback = actModel.getPlayback();
                        orderData();

                        adapter.updateData(listModel);
                    }
                }
            }

            @Override
            protected void onFinish(SDResponse resp)
            {
                lv_content.onRefreshComplete();
                super.onFinish(resp);
            }
        });
    }

    protected void orderData()
    {
        listModel.clear();
        if (SDCollectionUtil.isEmpty(listRoom))
        {
            listModel.add(new LiveTabFollowAdapter.TypeNoLiveRoomModel());
        } else
        {
            for (LiveRoomModel room : listRoom)
            {
                listModel.add(room);
            }
        }
        if (!SDCollectionUtil.isEmpty(listPlayback))
        {
            for (LivePlaybackModel playback : listPlayback)
            {
                listModel.add(playback);
            }
        }
    }

    @Override
    public void scrollToTop()
    {
        lv_content.getRefreshableView().setSelection(1);
    }

    @Override
    protected void onRoomClosed(final int roomId)
    {
        super.onRoomClosed(roomId);
        SDHandlerManager.getBackgroundHandler().post(new SDTaskRunnable<LiveRoomModel>()
        {
            @Override
            public LiveRoomModel onBackground()
            {
                synchronized (LiveTabFollowView.this)
                {
                    if (SDCollectionUtil.isEmpty(listRoom))
                    {
                        return null;
                    }
                    Iterator<LiveRoomModel> it = listRoom.iterator();
                    while (it.hasNext())
                    {
                        LiveRoomModel item = it.next();
                        if (roomId == item.getRoom_id())
                        {
                            return item;
                        }
                    }
                }
                return null;
            }

            @Override
            public void onMainThread(LiveRoomModel result)
            {
                if (result != null)
                {
                    synchronized (LiveTabFollowView.this)
                    {
                        adapter.removeData(result);
                    }
                }
            }
        });
    }
}
