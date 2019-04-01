package com.fanwe.g419.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanwe.hybrid.activity.AppWebViewActivity;
import com.fanwe.hybrid.dao.InitActModelDao;
import com.fanwe.hybrid.fragment.BaseFragment;
import com.fanwe.hybrid.model.InitActModel;
import com.fanwe.library.adapter.SDPagerAdapter;
import com.fanwe.library.common.SDFragmentManager;
import com.fanwe.library.config.SDConfig;
import com.fanwe.library.customview.SDViewPager;
import com.fanwe.library.event.EOnClick;
import com.fanwe.library.listener.SDItemClickCallback;
import com.fanwe.library.utils.SDViewUtil;
import com.fanwe.library.view.SDViewPageIndicator;
import com.fanwe.g419.IMHelper;
import com.fanwe.g419.LiveConstant;
import com.fanwe.g419.R;
import com.fanwe.g419.activity.LiveChatC2CActivity;
import com.fanwe.g419.activity.LiveSearchUserActivity;
import com.fanwe.g419.adapter.LiveHomeTitleTabAdapter;
import com.fanwe.g419.appview.main.LiveTabBaseView;
import com.fanwe.g419.appview.main.LiveTabFollowView;
import com.fanwe.g419.appview.main.LiveTabHotView;
import com.fanwe.g419.appview.main.LiveTabNewView;
import com.fanwe.g419.appview.main.LiveTabUrlView;
import com.fanwe.g419.event.EIMLoginSuccess;
import com.fanwe.g419.event.EReSelectTabLiveBottom;
import com.fanwe.g419.event.ERefreshMsgUnReaded;
import com.fanwe.g419.event.ESelectLiveFinish;
import com.fanwe.g419.model.HomeTabTitleModel;
import com.fanwe.g419.model.TotalConversationUnreadMessageModel;
import com.fanwe.pay.activity.InviteRewardsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 直播列表
 *
 * @author Administrator
 * @date 2016-7-2 上午11:28:44
 */
public class LiveTabLiveFragment extends BaseFragment {

    private TextView tv_total_unreadnum;
    private SDViewPageIndicator vpg_indicator;
    private SDViewPager vpg_content;

    private List<HomeTabTitleModel> mListModel = new ArrayList<>();
    private LiveHomeTitleTabAdapter mAdapterTitleTab;
    private LiveTabLiveFragment context;
    private List<LiveTabBaseView> mListView = new ArrayList<>();
    SDFragmentManager sdFragmentManager;

    @Override
    protected int onCreateContentView() {
        return R.layout.frag_live_tab_live;
    }

    @Override
    protected void init() {
        super.init();
        context = this;
        tv_total_unreadnum = (TextView) findViewById(R.id.tv_total_unreadnum);
        vpg_indicator = (SDViewPageIndicator) findViewById(R.id.vpg_indicator);
        vpg_content = (SDViewPager) findViewById(R.id.vpg_content);

        ImageView imageInvite = (ImageView) findViewById(R.id.image_invite);
        final InitActModel model = InitActModelDao.query();
        if (model.getOpen_home_invite() == 1) {
            imageInvite.setVisibility(View.VISIBLE);
        } else {
            imageInvite.setVisibility(View.GONE);
        }
        Glide.with(getActivity()).load(model.getHome_invite_img()).asGif().into(imageInvite);
        imageInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), AppWebViewActivity.class);
//                intent.putExtra(AppWebViewActivity.EXTRA_URL, model.getInvite_url());
//                getActivity().startActivity(intent);
                getActivity().startActivity(new Intent(getContext(),InviteRewardsActivity.class));
            }
        });

        initTabsData();
        initViewPager();
        initViewPagerIndicator();

    }

    private void initTabsData() {
        HomeTabTitleModel tabNearby = new HomeTabTitleModel();
        tabNearby.setTitle("关注");
        HomeTabTitleModel tabHot = new HomeTabTitleModel();
        tabHot.setTitle("热门");
        HomeTabTitleModel tabFollow = new HomeTabTitleModel();
        tabFollow.setTitle("附近");
        HomeTabTitleModel tabRank = new HomeTabTitleModel();
        tabRank.setTitle("榜单");
        HomeTabTitleModel tabCommend = new HomeTabTitleModel();
        tabCommend.setTitle("推荐");
        mListModel.add(tabNearby);
        mListModel.add(tabHot);
        mListModel.add(tabFollow);
//        mListModel.add(tabRank);
//        mListModel.add(tabCommend);
        InitActModel initActModel = InitActModelDao.query();
        if (initActModel != null)
        {
            List<HomeTabTitleModel> listTab = initActModel.getVideo_classified();
            if (listTab != null && !listTab.isEmpty())
            {
                mListModel.addAll(listTab);
                for (HomeTabTitleModel model: listTab) {
                    Log.i(LiveTabLiveFragment.class.getSimpleName(), TextUtils.isEmpty(model.getClassified_url()) ? "url is null" : model.getClassified_url());
                }
            }
        }
    }

    private void initViewPager() {
        vpg_indicator.setViewPager(vpg_content);
        vpg_content.setOffscreenPageLimit(3);
        for(int i=0;i<mListModel.size();i++){
            LiveTabBaseView view;
            switch (i) {
                case 0:
                    view = new LiveTabFollowView(getActivity());
                    break;
                case 1:
                    view = new LiveTabHotView(getActivity());
                    break;
                case 2:
                    view = new LiveTabNewView(getActivity());
                    break;
//                case 3:
//                    view = new LiveTabRankView(getActivity());
//                    ((LiveTabRankView)view).setFragmentManager(getChildFragmentManager());
//                    break;
//                case 4:
//                    view = new LiveTabTypeView(getActivity(),mListModel.get(i),1);
//                    break;
                default:
//                    view = new LiveTabTypeView(getActivity(),mListModel.get(i),0);
                    view = new LiveTabUrlView(getActivity(), mListModel.get(i));
                    break;
            }
            mListView.add(view);
        }
        vpg_content.setAdapter(new SDPagerAdapter<LiveTabBaseView>(mListView, getActivity()) {
            @Override
            public View getView(ViewGroup container, int position) {
                return mListView.get(position);
            }
        });
    }
    private void initViewPagerIndicator() {
        mAdapterTitleTab = new LiveHomeTitleTabAdapter(mListModel, getActivity());
        mAdapterTitleTab.setItemClickCallback(new SDItemClickCallback<HomeTabTitleModel>() {
            @Override
            public void onItemClick(int position, HomeTabTitleModel item, View view) {
                vpg_indicator.setCurrentItem(position);
            }
        });
        vpg_indicator.setAdapter(mAdapterTitleTab);
        vpg_indicator.setCurrentItem(1);
    }

    public void onEventMainThread(ESelectLiveFinish event) {
        String text = SDConfig.getInstance().getString(R.string.config_live_select_city, "");
        if (TextUtils.isEmpty(text)) {
            text = LiveConstant.LIVE_HOT_CITY;
        }
        mAdapterTitleTab.getData(1).setTitle(text);
        mAdapterTitleTab.updateData(1);
    }

    public void onEventMainThread(EReSelectTabLiveBottom event) {
        if (event.index == 0) {
            int index = vpg_content.getCurrentItem();
            LiveTabBaseView view = mListView.get(index);
            if (view != null) {
                view.scrollToTop();
            }
        }
    }

    public void onEventMainThread(EOnClick event) {
        if (R.id.tv_tab_live_follow_goto_live == event.view.getId()) {
            vpg_indicator.setCurrentItem(0);
        }
    }

    @Override
    public void onResume() {
        initUnReadNum();
        super.onResume();
    }

    private void initUnReadNum() {
        TotalConversationUnreadMessageModel model = IMHelper.getC2CTotalUnreadMessageModel();
        setUnReadNumModel(model);
    }

    public void onEventMainThread(ERefreshMsgUnReaded event) {
        TotalConversationUnreadMessageModel model = event.model;
        setUnReadNumModel(model);
    }

    //SDK启动成功接收事件获取未读数量
    public void onEventMainThread(EIMLoginSuccess event) {
        initUnReadNum();
    }

    private void setUnReadNumModel(TotalConversationUnreadMessageModel model) {
        SDViewUtil.setGone(tv_total_unreadnum);
        if (model != null && model.getTotalUnreadNum() > 0) {
            SDViewUtil.setVisible(tv_total_unreadnum);
            tv_total_unreadnum.setText(model.getStr_totalUnreadNum());
        }
    }
}
