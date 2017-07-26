package com.qiuchenly.weixinplatform.weixin.UI;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseActivity;
import com.qiuchenly.weixinplatform.weixin.R;

public class LoginView extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void doBusiness(Context mContext) {
        showToast("WelCome to LoginView!");
    }

    @Override
    public void setListener() {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login_view;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initParams(Bundle bundle) {
        setAllowDoubleClickBackKey(true);
//        setAllowStatusBar(true);
        setDisableActionBar(true);
    }

    @Override
    public void ViewClick(View v) {

    }
}
