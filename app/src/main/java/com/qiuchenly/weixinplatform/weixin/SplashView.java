package com.qiuchenly.weixinplatform.weixin;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseActivity;
import com.qiuchenly.weixinplatform.weixin.UI.LoginView;

public class SplashView extends BaseActivity {


    /**
     * 设置全局统一OnClickListener
     */
    @Override
    public void setListener() {

    }


    /**
     * 设置视图
     *
     * @return
     */
    @Override
    public View bindView() {
        return null;
    }

    /**
     * 设置此Activity绑定的布局
     *
     * @return
     */
    @Override
    public int bindLayout() {
        return R.layout.activity_splash_view;
    }

    /**
     * 初始化控件的findViewById
     *
     * @param view
     */
    @Override
    public void initView(View view) {

    }

    /**
     * 解析跳转携带的Bundle数据或者设置默认显示数据
     *
     * @param bundle
     */
    @Override
    public void initParams(Bundle bundle) {
        super.setAllowStatusBar(true);
        super.setAllowDoubleClickBackKey(false);
        super.setDisableActionBar(true);
    }

    /**
     * Activity点击事件
     *
     * @param v 响应的具体View
     */
    @Override
    public void ViewClick(View v) {

    }

    /**
     * 业务操作逻辑
     *
     * @param mContext
     */

    @Override
    public void doBusiness(Context mContext) {
        showToast("WelCome to SplashView");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(LoginView.class);
            }
        }, 3000);
    }

}
