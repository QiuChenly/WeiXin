package com.qiuchenly.weixinplatform.weixin.UI;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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

        List<TextView> textViews = new ArrayList<>();
        textViews.add((TextView) $(R.id.myMap));
        textViews.add((TextView) $(R.id.myRoundPlace));
        textViews.add((TextView) $(R.id.mySelfText));

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(list, textViews);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(adapter);

    }

    @Override
    public void setListener() {
        ($(R.id.myMapLayout)).setOnClickListener(this);
        ($(R.id.myPlaceLayout)).setOnClickListener(this);
        ($(R.id.mySelfLayout)).setOnClickListener(this);
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
//        super.setDisableActionBar(true);
    }

    @Override
    public void ViewClick(View v) {
        switch (v.getId()) {
            case R.id.myMapLayout:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.myPlaceLayout:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.mySelfLayout:
                mViewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }
}
