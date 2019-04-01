package com.fanwe.baimei.fragment;

import android.content.Intent;
import android.widget.LinearLayout;

import com.fanwe.g419.R;
import com.fanwe.g419.activity.LivePrivateChatActivity;
import com.fanwe.g419.appview.LiveChatC2CNewView;
import com.fanwe.g419.model.LiveConversationListModel;

/**
 * 包名 com.fanwe.baimei.fragment
 * 描述 游戏首页
 * 作者 Su
 * 创建时间 2017/5/15 15:46
 **/

public class BMMessageFragment extends BMBaseFragment
{
    private LinearLayout ll_content;
    public void init()
    {
        ll_content= (LinearLayout) findViewById(R.id.ll_content);
        LiveChatC2CNewView view = new LiveChatC2CNewView(getActivity());
        view.showBackView(false);
        view.setOnChatItemClickListener(new LiveChatC2CNewView.OnChatItemClickListener()
        {
            @Override
            public void onChatItemClickListener(LiveConversationListModel itemLiveChatListModel)
            {
                Intent intent = new Intent(getActivity(), LivePrivateChatActivity.class);
                intent.putExtra(LivePrivateChatActivity.EXTRA_USER_ID, itemLiveChatListModel.getPeer());
                startActivity(intent);
            }
        });
        ll_content.addView(view);
        //传入数据
        view.requestData();
    }

    public static BMMessageFragment newInstance()
    {
        return new BMMessageFragment();
    }

    @Override
    protected int getContentLayoutRes()
    {
        return R.layout.act_live_chat_c2c;
    }

    @Override
    protected void onViewFirstTimeCreated() {
        init();
    }


}