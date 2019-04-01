package com.fanwe.g419.fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.fanwe.g419.activity.LiveChatC2CActivity;
import com.fanwe.g419.activity.LiveSearchUserActivity;
import com.fanwe.hybrid.dao.InitActModelDao;
import com.fanwe.hybrid.fragment.BaseFragment;
import com.fanwe.g419.R;
import com.fanwe.hybrid.model.InitActModel;

public class LiveHomeFragment extends BaseFragment {

    @Override
    protected int onCreateContentView() {
        return R.layout.live_home_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();
        Fragment fragment = new LiveTabLiveFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.content_container, fragment).commit();

        findViewById(R.id.linear_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                InitActModel model = InitActModelDao.query();
                Uri contentUrl = Uri.parse(model.getCustomer_service());
                intent.setData(contentUrl);
                startActivity(intent);
            }
        });

        findViewById(R.id.ll_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSearch();
            }
        });
        findViewById(R.id.ll_private_chat_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickChatList();
            }
        });
    }

    /**
     * 私聊列表
     */
    private void clickChatList() {
        Intent intent = new Intent(getActivity(), LiveChatC2CActivity.class);
        startActivity(intent);
    }

    /**
     * 搜索
     */
    private void clickSearch() {
        Intent intent = new Intent(getActivity(), LiveSearchUserActivity.class);
        startActivity(intent);
    }
}
