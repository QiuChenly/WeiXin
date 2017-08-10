package com.qiuchenly.weixinplatform.weixin.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseActivity;
import com.qiuchenly.weixinplatform.weixin.R;

public class LoginView extends BaseActivity {

    EditText userName, passWord;
    Button loginBtn, registerBtn;
    //// TODO: 2017/08/10  Override LoginLogic，implement Login Function and JumpTo RegisterView or MainView

    @Override
    public void getSharedPreference(SharedPreferences sp, SharedPreferences.Editor Ueditors) {

    }

    @Override
    public void doBusiness(Context mContext, View v) {

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


    /**
     * 初始化ID参数回调
     * @param view
     */
    @Override
    public void initView(View view) {
        userName = $(R.id.login_user);
        passWord = $(R.id.login_pass);

        loginBtn = $(R.id.login_loginbtn);
        registerBtn = $(R.id.login_registerBtn);
    }

    @Override
    public void initParams(Bundle bundle) {
        setAllowDoubleClickBackKey(true);
        //setAllowStatusBar(false);
        setDisableActionBar(true);
    }


    @Override
    public void ViewClick(View v) {

    }
}
