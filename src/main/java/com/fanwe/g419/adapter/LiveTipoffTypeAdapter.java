package com.fanwe.g419.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fanwe.g419.R;
import com.fanwe.g419.model.App_tipoff_typeModel;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2016-5-26 下午7:41:51 类说明
 */
public class LiveTipoffTypeAdapter extends BaseAdapter {
    List<App_tipoff_typeModel> list;
    Context mContext;

    public LiveTipoffTypeAdapter(Context c, List<App_tipoff_typeModel> list) {
        mContext = c;
        this.list = list;
    }

    // 获取图片的个数
    public int getCount() {
        return list.size();
    }

    // 获取图片在库中的位置
    public Object getItem(int position) {
        return position;
    }


    // 获取图片ID
    public long getItemId(int position) {
        return position;
    }

    int index=-1;
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_live_tipoff_type, null);
        TextView tv= (TextView) convertView.findViewById(R.id.tv);
        final App_tipoff_typeModel model=list.get(position);
        if(model.isSelected()){
            tv.setBackgroundResource(R.drawable.layer_tip_checked);
        }else{
            tv.setBackgroundResource(R.drawable.layer_tip_normal);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setSelected(!model.isSelected());
                if(index!=position&&index>-1){
                    list.get(index).setSelected(false);
                }
                if(index==position&&!model.isSelected()){
                    index=-1;
                }else{
                    index=position;
                }
                notifyDataSetChanged();
            }
        });
        tv.setText(list.get(position).getName());
        return convertView;
    }

    public long getSelectedId() {
        if(index==-1){
            return index;
        }
        return list.get(index).getId();
    }
}
