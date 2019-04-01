package com.fanwe.g419.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fanwe.hybrid.activity.BaseActivity;
import com.fanwe.hybrid.http.AppRequestCallback;
import com.fanwe.hybrid.model.BaseActModel;
import com.fanwe.library.adapter.http.model.SDResponse;
import com.fanwe.library.dialog.SDDialogMenu;
import com.fanwe.library.dialog.SDDialogMenu.SDDialogMenuListener;
import com.fanwe.library.utils.SDToast;
import com.fanwe.g419.LiveSongDownloadManager;
import com.fanwe.g419.R;
import com.fanwe.g419.adapter.LiveSongListAdapter;
import com.fanwe.g419.common.AppRuntimeWorker;
import com.fanwe.g419.common.CommonInterface;
import com.fanwe.g419.event.ELiveSongDownload;
import com.fanwe.g419.event.EPlayMusic;
import com.fanwe.g419.model.LiveSongModel;
import com.fanwe.g419.model.Music_downurlActModel;
import com.fanwe.g419.model.Music_user_musicActModel;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

import de.greenrobot.event.EventBus;


public class LiveSongChooseActivity extends BaseActivity implements LiveSongListAdapter.OnXItemClickListener{

	private PullToRefreshListView mListView;
	private LiveSongListAdapter mAdapter;
	private Music_user_musicActModel mActModel;
	private int mPage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_live_song_choose);
		findViewById(R.id.rl_back).setOnClickListener(this);
		findViewById(R.id.tv_search).setOnClickListener(this);
		
		initList();
		
	}
	
	protected void initList() {
		mListView = (PullToRefreshListView)findViewById(R.id.lv_list);
		mListView.setEmptyView(findViewById(R.id.rl_empty));
		mAdapter = new LiveSongListAdapter(this);
		mListView.setAdapter(mAdapter);
		
		mListView.setMode(Mode.BOTH);
		mListView.setOnRefreshListener(new OnRefreshListener2<ListView>()
		{

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
			{
				reloadData();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
			{
				if (mActModel != null && mActModel.hasNext()) {
					loadData();
				}else {
					mListView.onRefreshComplete();
					SDToast.showToast("没有更多");
				}
				
			}
		});

		mListView.setRefreshing();
		
		mAdapter.setXItemClickListener(this);
	}
	
	protected void reloadData() {
		mPage = 1;
		loadData();
	}
	protected void loadData() {

		CommonInterface.requestMusic_user_music(mPage, new AppRequestCallback<Music_user_musicActModel>() {
			
			@Override
			protected void onSuccess(SDResponse resp) {
				if (actModel.isOk()) {
					if (mPage == 1) {
						mAdapter.updateData(actModel.getList());
//						LiveUserSongManager.getInstance().setList(actModel.getList());
					}else {
						mAdapter.appendData(actModel.getList());
//						LiveUserSongManager.getInstance().addList(actModel.getList());
					}
					mPage += 1;
					mActModel = actModel;
				}else {
					if (!TextUtils.isEmpty(actModel.getError())) {
						SDToast.showToast(actModel.getError());
					}
					
				}
			}
			
			@Override
			protected void onError(SDResponse resp) {
				super.onError(resp);
				SDToast.showToast(resp.getResult());
			}
			
			@Override
			protected void onFinish(SDResponse resp) {
				super.onFinish(resp);
				mListView.onRefreshComplete();
			}
		});
	}
	
	@Override
	public void onClick(View v)
	{
		switch(v.getId()) {
		case R.id.rl_back:
			finish();
			break;
		case R.id.tv_search:
			Intent intent = new Intent(LiveSongChooseActivity.this, LiveSongSearchActivity.class);
			startActivity(intent);
			break;
		}
	}
	
	
	public void onEventMainThread(ELiveSongDownload event)
	{
		mAdapter.updateSongItem(event);
	}

	protected void delSong(final LiveSongModel model) {
		showProgressDialog("");
		CommonInterface.requestMusic_del_music(model.getAudio_id(), new AppRequestCallback<BaseActModel>() {
			
			@Override
			protected void onSuccess(SDResponse resp) {
				if (actModel.isOk()) {
					mAdapter.removeData(model);
				}else {
					if (!TextUtils.isEmpty(actModel.getError())) {
						SDToast.showToast(actModel.getError());
					}
				}
			}
			
			@Override
			protected void onError(SDResponse resp) {
				super.onError(resp);
				SDToast.showToast(resp.getResult());
			}
			
			@Override
			protected void onFinish(SDResponse resp) {
				super.onFinish(resp);
				dismissProgressDialog();
			}
		});
	}
	@Override
	public void onXItemClick(int position, View convertView, ViewGroup parent,
			LiveSongModel model) {

		if (model.getStatus() == 1) {
			// 下载中
			return;
		}
		
		if (model.isMusicExist()) {
			// 跳转到直接UI			
			EventBus.getDefault().post(new EPlayMusic(model));
			AppRuntimeWorker.openLiveCreaterActivity(LiveSongChooseActivity.this);
		}else {
			loadAudioUrl(model);
		}
		
	}
	@Override
	public void onXItemLongClick(int position, View convertView,
			ViewGroup parent, final LiveSongModel model) {
		SDDialogMenu dialog = new SDDialogMenu(this);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setItems(new String[] { "删除" });
		dialog.setmListener(new SDDialogMenuListener()
		{
			@Override
			public void onItemClick(View v, int index, SDDialogMenu dialog)
			{
				switch (index)
				{
				case 0:
					delSong(model);
					break;

				default:
					break;
				}
			}

			@Override
			public void onDismiss(SDDialogMenu dialog)
			{
			}

			@Override
			public void onCancelClick(View v, SDDialogMenu dialog)
			{
			}
		});
		dialog.showBottom();
		
	}
	protected void loadAudioUrl(final LiveSongModel model) {
		showProgressDialog("");
		CommonInterface.requestMusic_downurl(model.getAudio_id(), new AppRequestCallback<Music_downurlActModel>() {

			@Override
			protected void onSuccess(SDResponse resp) {
				if (actModel.isOk()) {
					model.setAudio_link(actModel.getAudio().getAudio_link());
					model.setTime_len(actModel.getAudio().getTime_len());
					model.setLrc_content(actModel.getAudio().getLrc_content());
					LiveSongDownloadManager.getInstance().addTask(model);
				}else {
					if (!TextUtils.isEmpty(actModel.getError())) {
						SDToast.showToast(actModel.getError());
					}
					
				}
			}
			@Override
			protected void onError(SDResponse resp) {
				super.onError(resp);
				SDToast.showToast(resp.getResult());
				
			}
			
			@Override
			protected void onFinish(SDResponse resp) {
				super.onFinish(resp);
				dismissProgressDialog();
			}
		});
	}
}
