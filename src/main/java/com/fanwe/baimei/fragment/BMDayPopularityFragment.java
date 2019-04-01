package com.fanwe.baimei.fragment;

import com.fanwe.g419.R;

/**
 * Created by yhz on 2017/5/24. 人气榜
 */

public class BMDayPopularityFragment extends BMPTDayBaseFragment
{
    @Override
    void clickTabDay()
    {
        getSDFragmentManager().toggle(R.id.fl_content_ptbase, null, BMPopularityDayFragment.class);
    }


    @Override
    void clickTabWeek() {
        getSDFragmentManager().toggle(R.id.fl_content_ptbase, null, BMPopularityWeekFragment.class);
    }

    @Override
    void clickTabMonth()
    {
        getSDFragmentManager().toggle(R.id.fl_content_ptbase, null, BMPopularityMonthFragment.class);
    }

    @Override
    void clickTabTotal()
    {
        getSDFragmentManager().toggle(R.id.fl_content_ptbase, null, BMPopularityAllFragment.class);
    }
}
