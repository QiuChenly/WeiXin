package com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseAdapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: QiuChenluoye
 * Time: 2017/07/28,上午 09:42
 * Func: 基本ViewPagerAdapter
 * Using: 无解释
 */

public abstract class BaseViewPagerAdapter extends PagerAdapter
        implements ViewPager.OnPageChangeListener {

    List<View> mList;
    List<String> mTitle;

    public void InitBaseViewPagerAdapter() {
        mList = setViews();
        if (null == mList) {
            mList = new ArrayList<>();
        }

    }

    public abstract List<View> setViews();

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = mList.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ViewID(position);
    }

    public abstract void ViewID(int position);

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
