package com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseAdapter;

import android.support.v4.view.PagerAdapter;
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

public abstract class BaseViewPagerAdapter extends PagerAdapter {

    List<View> mList;
    List<String> mTitle;

    public void InitBaseViewPagerAdapter() {
        mList = setViews();
        if (null == mList) {
            mList = new ArrayList<>();
        }
        mTitle = setViewTitle();
        if (null == mTitle) {
            mTitle = new ArrayList<>();
            if (mList != null) {
                for (int a = 1; a <= mList.size(); a++) {
                    mTitle.add(String.valueOf(a));
                }
            }
        }
    }

    public abstract List<View> setViews();

    public abstract List<String> setViewTitle();

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
        return mTitle.get(position);
    }
}
