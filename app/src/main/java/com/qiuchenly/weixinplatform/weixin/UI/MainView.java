package com.qiuchenly.weixinplatform.weixin.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseActivity;
import com.qiuchenly.weixinplatform.weixin.R;
import com.qiuchenly.weixinplatform.weixin.UI.Adapter.MainViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainView extends BaseActivity {
    private ViewPager mViewPager;

    @Override
    public void doBusiness(Context mContext) {
        LayoutInflater inflater = getLayoutInflater();
        List<View> list = new ArrayList<>();
        list.add(inflater.inflate(R.layout.mlayout_main_map, null));
        list.add(inflater.inflate(R.layout.mlayout_inquiryview, null));
        list.add(inflater.inflate(R.layout.mlayout_myinfo, null));


        //testUp
        List<String> listTitle = new ArrayList<>();
        listTitle.add("首页");
        listTitle.add("查找地图");
        listTitle.add("关于我");
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(list, listTitle);
        mViewPager.setAdapter(adapter);

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
