package com.weibo.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fanwe.library.adapter.SDSimpleRecyclerAdapter;
import com.fanwe.library.adapter.viewholder.SDRecyclerViewHolder;
import com.fanwe.library.model.ImageModel;
import com.fanwe.g419.R;
import com.fanwe.g419.utils.GlideUtil;

import java.util.List;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2016-6-7 上午11:58:35 类说明
 */
public class LivePickPhotosAdapter extends SDSimpleRecyclerAdapter<ImageModel> {

    public LivePickPhotosAdapter(List<ImageModel> listModel, Activity activity) {
        super(listModel, activity);
    }

    @Override
    public void onBindData(SDRecyclerViewHolder<ImageModel> holder, final int position, final ImageModel model) {
        ImageView iv = holder.get(R.id.ic_thumb_xr_view_holder_photo_list_user_dynamic);
        ImageView iv_clear = holder.get(R.id.ic_clear);
        if(position>8){
            return;
        }
        if (null != model.getUri()) {
            GlideUtil.load(model.getUri()).into(iv);
            iv_clear.setVisibility(View.VISIBLE);
        } else{
            iv.setImageResource(R.drawable.ic_add_image);
            iv_clear.setVisibility(View.GONE);
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.addClick(position);
            }
        });
        iv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.clearClick(position);
            }
        });
    }

    @Override
    public int getLayoutId(ViewGroup parent, int viewType) {
        return R.layout.xr_view_holder_photo_list_user_dynamic;
    }
    public ClickListener clickListener;

    public ClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void clearClick(int position);
        void addClick(int position);
    }
}
