package com.qiuchenly.weixinplatform.weixin.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseActivity;
import com.qiuchenly.weixinplatform.weixin.R;

public class MainView extends BaseActivity {
    private ViewPager mViewPager;

    @Override
    public void doBusiness(Context mContext) {

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
        return R.layout.activity_main_view;
    }

    @Override
    public void initView(View view) {
        mViewPager = $(R.id.mMainViewPager);
    }

    @Override
    public void initParams(Bundle bundle) {
        super.setAllowDoubleClickBackKey(true);

    }

    @Override
    public void ViewClick(View v) {

    }
}
