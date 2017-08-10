package com.qiuchenly.weixinplatform.weixin.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseActivity;
import com.qiuchenly.weixinplatform.weixin.BaseUtils.HttpUtils.FuncUtils;
import com.qiuchenly.weixinplatform.weixin.R;
import com.qiuchenly.weixinplatform.weixin.UI.UILogic.InterMsgID;

public class LoginView extends BaseActivity {

    EditText userName, passWord;
    Button loginBtn, registerBtn;

    FuncUtils funcUtils = new FuncUtils();

    //// TODO: 2017/08/10  Override LoginLogic，implement Login Function and JumpTo RegisterView or MainView

    @Override
    public void getSharedPreference(SharedPreferences sp, SharedPreferences.Editor Ueditors) {

    }

    @Override
    public void doBusiness(Context mContext, View v) {

    }

    @Override
    public void setListener() {
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
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
     *
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

    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case InterMsgID.LoginMsg:
                    final String userN = userName.getText().toString();
                    final String passW = passWord.getText().toString();
                    new Thread() {
                        @Override
                        public void run() {
                            final boolean result = funcUtils.login(userN, passW);
                            LoginView.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (result) {
                                        startActivity(MainView.class);
                                        finish();
                                    } else {
                                        showToast("账号或者密码错误,请重试");
                                    }
                                }
                            });
                        }
                    }.start();
                    break;
                case InterMsgID.registerMsg:
                    startActivity(RegisterView.class);
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * 逻辑实现类
     *
     * @param v 响应的具体View
     */
    //FIXME 2017-8-10 QiuChenly
    @Override
    public void ViewClick(View v) {
        switch (v.getId()) {
            case R.id.login_loginbtn:
                hand.sendEmptyMessage(InterMsgID.LoginMsg);
                break;
            case R.id.login_registerBtn:
                hand.sendEmptyMessage(InterMsgID.registerMsg);
                break;
            default:
                break;
        }
    }
}
