package com.qiuchenly.weixinplatform.weixin.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseActivity;
import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseRecyclerViewAdapter;
import com.qiuchenly.weixinplatform.weixin.BaseUtils.HttpUtils.FuncUtils;
import com.qiuchenly.weixinplatform.weixin.R;
import com.qiuchenly.weixinplatform.weixin.UI.UILogic.InterMsgID;

public class LoginView extends BaseActivity {

    EditText userName, passWord;
    Button loginBtn, registerBtn;

    RecyclerView mBack;

    FuncUtils funcUtils = new FuncUtils();

    //// TODO: 2017/08/10  Override LoginLogic，implement Login Function and JumpTo RegisterView or MainView

    @Override
    public void getSharedPreference(SharedPreferences sp, SharedPreferences.Editor Ueditors) {

    }

    @Override
    public void doBusiness(Context mContext, View v) {
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(this);
        mBack.setItemAnimator(new DefaultItemAnimator());
        mBack.setHasFixedSize(false);

        mBack.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        mBack.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 5;
                outRect.right = 5;
                outRect.top = 5;
                outRect.left = 5;
            }
        });
        mBack.setAdapter(adapter);

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
        mBack = $(R.id.mLoginBitmapRecycler);
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
//                    startActivity(RegisterView.class);

                    FrameLayout frameLayout = $(R.id.mLoginViewControl);
                    frameLayout.setVisibility(View.GONE);
                    frameLayout = $(R.id.mRegisterViewControl);
                    frameLayout.setVisibility(View.VISIBLE);

                    FrameLayout frameLayout1 = $(R.id.mRegisterBackControl);
                    frameLayout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FrameLayout frameLayout = $(R.id.mLoginViewControl);
                            frameLayout.setVisibility(View.VISIBLE);
                            frameLayout = $(R.id.mRegisterViewControl);
                            frameLayout.setVisibility(View.GONE);
                        }
                    });
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /*
         * 实现返回键退回登录页
         */
        FrameLayout frameLayout = $(R.id.mLoginViewControl);
        if (frameLayout.getVisibility() == View.GONE) {
            frameLayout.setVisibility(View.VISIBLE);
            frameLayout = $(R.id.mRegisterViewControl);
            frameLayout.setVisibility(View.GONE);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
