package com.fanwe.g419.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.fanwe.hybrid.activity.BaseActivity;
import com.fanwe.hybrid.http.AppRequestCallback;
import com.fanwe.library.adapter.http.model.SDResponse;
import com.fanwe.g419.R;
import com.fanwe.g419.common.CommonInterface;
import com.fanwe.g419.dialog.LiveRechargeShouhuDialog;
import com.fanwe.g419.model.PayShouhuModel;

/**
 * Created by Administrator on 2016/7/18.
 */
public class LiveOpenShouhuActivity extends BaseActivity {
    private ImageView iv_back;
    private Button btn_buy;
    private LiveRechargeShouhuDialog dialog;
    private PayShouhuModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_shouhu);
        init();
    }

    /**
     * 加载聊天fragment
     */
    private void init() {
        requestPayShouhu();
        iv_back = find(R.id.iv_back);
        btn_buy = find(R.id.btn_buy);
        if (getIntent().getBooleanExtra("is_shouhu", false)) {
            btn_buy.setText("续费守护");
        }
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=dialog){
                    dialog.showBottom();
                }else{
                    requestPayShouhu();
                    dialog.showBottom();
                }
            }
        });
    }

    private void requestPayShouhu() {
        CommonInterface.requestShouhuPay(getIntent().getStringExtra("create_id"), new AppRequestCallback<PayShouhuModel>() {
            @Override
            protected void onSuccess(SDResponse sdResponse) {
                model = actModel;
                model.getGuard_rule_list().get(0).setChecked(true);
                dialog = new LiveRechargeShouhuDialog(getActivity(), model, getIntent().getStringExtra("create_id"), new LiveRechargeShouhuDialog.buyShuohu() {
                    @Override
                    public void buySuccess() {
                        btn_buy.setText("续费守护");
                    }
                });
            }

            @Override
            protected void onError(SDResponse resp) {
                super.onError(resp);
            }
        });
    }
}
