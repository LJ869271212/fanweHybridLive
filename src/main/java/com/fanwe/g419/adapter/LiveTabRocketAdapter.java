package com.fanwe.g419.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fanwe.library.adapter.SDSimpleRecyclerAdapter;
import com.fanwe.library.adapter.viewholder.SDRecyclerViewHolder;
import com.fanwe.library.utils.SDViewBinder;
import com.fanwe.g419.R;
import com.fanwe.g419.activity.LiveUserHomeActivity;
import com.fanwe.g419.model.RocketModel;
import com.fanwe.g419.utils.GlideUtil;

public class LiveTabRocketAdapter extends SDSimpleRecyclerAdapter<RocketModel> {

    public LiveTabRocketAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public int getLayoutId(ViewGroup parent, int viewType) {
        return R.layout.item_hot_rocket;
    }

    @Override
    public void onBindData(SDRecyclerViewHolder<RocketModel> holder, int position, final RocketModel model) {
        ImageView iv_pic = holder.get(R.id.iv_head);
        TextView tv_name = holder.get(R.id.tv_name);
        if(null!=model.getHead_image()){
            GlideUtil.loadHeadImage(model.getHead_image()).into(iv_pic);
        }else{
            SDViewBinder.setImageViewVisibleOrGone(iv_pic,R.drawable.ic_me_qiuzhan);
        }
        SDViewBinder.setTextView(tv_name,model.getNick_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=model.getUser_id()){
                    Intent intent = new Intent(getActivity(), LiveUserHomeActivity.class);
                    intent.putExtra(LiveUserHomeActivity.EXTRA_USER_ID, model.getUser_id()+"");
                    intent.putExtra(LiveUserHomeActivity.EXTRA_USER_IMG_URL, model.getHead_image());
                    getActivity().startActivity(intent);
                }
            }
        });
    }
}
