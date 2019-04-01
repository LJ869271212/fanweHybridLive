package com.fanwe.g419.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fanwe.hybrid.activity.BaseTitleActivity;
import com.fanwe.library.dialog.SDDialogCustom;
import com.fanwe.g419.R;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by shibx on 2016/7/18.
 */
public class LiveUserShareIncomeActivity extends BaseTitleActivity implements SDDialogCustom.SDDialogCustomListener {

    @ViewInject(R.id.tv_reward)
    private TextView tv_reward;//可提现金额
    @ViewInject(R.id.tv_take_reward_alipay)
    private TextView tv_take_reward_alipay;//支付宝提现

    private String jjr_money;//可提现金额

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_live_user_share_income);
        jjr_money = getIntent().getStringExtra("jjr_money");
        init();
    }

    private void init() {
        initTitle();
        tv_reward.setText(jjr_money);
        tv_take_reward_alipay.setOnClickListener(this);
    }

    private void initTitle() {
        mTitle.setMiddleTextTop("我的分享收益");
        mTitle.setLeftImageLeft(R.drawable.ic_arrow_left_white);
        mTitle.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_take_reward_alipay:
                doBinding();
                break;
            default:
                break;
        }
    }

    private void doBinding() {
            //绑定支付宝
            Intent intent = new Intent(this, LiveAlipayBindingActivity.class);
            startActivity(intent);
    }

    @Override
    public void onClickCancel(View v, SDDialogCustom dialog) {

    }

    @Override
    public void onClickConfirm(View v, SDDialogCustom dialog) {
        finish();
    }

    @Override
    public void onDismiss(SDDialogCustom dialog) {

    }

}
