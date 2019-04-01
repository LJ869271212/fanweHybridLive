package com.fanwe.g419.appview.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fanwe.baimei.fragment.BMDayPopularityFragment;
import com.fanwe.baimei.fragment.BMDayTyrantFragment;
import com.fanwe.hybrid.fragment.BaseFragment;
import com.fanwe.library.common.SDSelectManager;
import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.library.utils.SDViewUtil;
import com.fanwe.library.view.SDTabText;
import com.fanwe.library.view.select.SDSelectViewManager;
import com.fanwe.g419.R;
import com.fanwe.g419.event.EFinishAdImg;
import com.fanwe.g419.view.ViewPagerNoScroll;
import com.sunday.eventbus.SDEventManager;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 榜单列表
 * Created by Administrator on 2017-7-29.
 */
public class LiveTabDayRankView extends BaseFragment {
    @ViewInject(R.id.tab_popularity)
    private SDTabText tab_popularity;
    @ViewInject(R.id.tab_tyrant)
    private SDTabText tab_tyrant;
    @ViewInject(R.id.fl_content_anchor)
    private ViewPagerNoScroll fl_content_anchor;
    //    @ViewInject(R.id.fragment_bmp)
//    private BMPopularityFragment fragment_bmp;
//    @ViewInject(R.id.fragment_bmt)
//    private BMTyrantFragment fragment_bmt;
    @ViewInject(R.id.lv_bmp)
    private LinearLayout lv_bmp;
    @ViewInject(R.id.lv_bmt)
    private LinearLayout lv_bmt;
    private Context context;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private SDSelectViewManager<SDTabText> selectViewManager = new SDSelectViewManager<>();
    private PagerAdapter adapter;

    @Override
    protected int onCreateContentView() {
        return R.layout.bm_act_anchor_day_ranklist;
    }

    @Override
    protected void init() {
        super.init();
        initTitle();
        initTabs();
        List<Fragment> list = new ArrayList<>();
        list.add(new BMDayPopularityFragment());
        list.add(new BMDayTyrantFragment());
        adapter = new PagerAdapter(getChildFragmentManager(), list);
        fl_content_anchor.setAdapter(adapter);
    }

    private void initTitle() {
        //    mTitle.setMiddleTextTop("主播榜");
    }

    private void initTabs() {
        tab_popularity.setTextTitle("播主榜");
        tab_popularity.mTv_title.setPadding(SDViewUtil.dp2px(20), SDViewUtil.dp2px(2), SDViewUtil.dp2px(20), SDViewUtil.dp2px(2));
        tab_popularity.getViewConfig(tab_popularity.mTv_title).setBackgroundNormal(SDResourcesUtil.getDrawable(R.drawable.bm_bg_no_color_corner_5dp))
                .setBackgroundSelected(SDResourcesUtil.getDrawable(R.drawable.bm_bg_main_color_corner_5dp));
        tab_popularity.getViewConfig(tab_popularity.mTv_title).setTextColorNormal(SDResourcesUtil.getColor(R.color.text_title_bar)).setTextColorSelected(SDResourcesUtil.getColor(R.color.white))
                .setTextSizeNormal(SDViewUtil.dp2px(16)).setTextSizeSelected(SDViewUtil.dp2px(16));

        tab_tyrant.mTv_title.setPadding(SDViewUtil.dp2px(20), SDViewUtil.dp2px(2), SDViewUtil.dp2px(20), SDViewUtil.dp2px(2));
        tab_tyrant.setTextTitle("土豪榜");
        tab_tyrant.getViewConfig(tab_tyrant.mTv_title).setBackgroundNormal(SDResourcesUtil.getDrawable(R.drawable.bm_bg_no_color_corner_5dp))
                .setBackgroundSelected(SDResourcesUtil.getDrawable(R.drawable.bm_bg_main_color_corner_5dp));
        tab_tyrant.getViewConfig(tab_tyrant.mTv_title).setTextColorNormal(SDResourcesUtil.getColor(R.color.text_title_bar)).setTextColorSelected(SDResourcesUtil.getColor(R.color.white))
                .setTextSizeNormal(SDViewUtil.dp2px(16)).setTextSizeSelected(SDViewUtil.dp2px(16));

        SDTabText[] items = new SDTabText[]{tab_popularity, tab_tyrant};

        selectViewManager.addSelectCallback(new SDSelectManager.SelectCallback<SDTabText>() {

            @Override
            public void onNormal(int index, SDTabText item) {
            }

            @Override
            public void onSelected(int index, SDTabText item) {
                switch (index) {
                    case 0:
                        clickTabMerits();
                        break;
                    case 1:
                        clickTabContribution();
                        break;
                    default:
                        break;
                }
            }
        });
        selectViewManager.setItems(items);
        selectViewManager.setSelected(0, true);
    }

    protected void clickTabMerits() {
        //fragment_bmp.getSDFragmentManager().findFragmentById(R.id.fl_content_ptbase).getView().setVisibility(VISIBLE);
        //  fragment_bmt.getSDFragmentManager().findFragmentById(R.id.fl_content_ptbase).getView().setVisibility(GONE);
//        lv_bmp.setVisibility(VISIBLE);
//        lv_bmt.setVisibility(GONE);
        fl_content_anchor.setCurrentItem(0);
        //getSDFragmentManager().toggle(R.id.fl_content_anchor, null, BMPopularityFragment.class);
        /*fragment_bmp.getSDFragmentManager().show(s).setVisibility(VISIBLE);
        fragment_bmt.getView().setVisibility(GONE);*/
    }

    protected void clickTabContribution() {
        // fragment_bmt.getSDFragmentManager().findFragmentById(R.id.fl_content_ptbase).getView().setVisibility(VISIBLE);
        // fragment_bmp.getSDFragmentManager().findFragmentById(R.id.fl_content_ptbase).getView().setVisibility(GONE);
//        lv_bmt.setVisibility(VISIBLE);
//        lv_bmp.setVisibility(GONE);
        fl_content_anchor.setCurrentItem(1);
        //  getSDFragmentManager().toggle(R.id.fl_content_anchor, null, BMTyrantFragment.class);
       /* fragment_bmp.getView().setVisibility(GONE);
        fragment_bmt.getView().setVisibility(VISIBLE);*/
        // fragment_bmt.hideFragmentView();

    }

   /* @Override
    public void onDestroy()
    {
     super.onDestroy();
        onSendEvent();
    }*/

    /**
     * 发送关闭事件
     */
    private void onSendEvent() {
        EFinishAdImg event = new EFinishAdImg();
        SDEventManager.post(event);
    }

    /**
     * 自定义一个PagerAdapter
     */
    class PagerAdapter extends FragmentPagerAdapter {
        List<Fragment> list;

        public PagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
