package com.qiuchenly.weixinplatform.weixin.UI.Adapter;

import android.view.View;

import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseAdapter.BaseViewPagerAdapter;

import java.util.List;

/**
 * Author: QiuChenluoye
 * Time: 2017/07/28,上午 10:33
 * Func: 具体实现类
 * Using: 无解释
 */

public class MainViewPagerAdapter extends BaseViewPagerAdapter {

    List<View> listView;
    List<String> listStringTitle;

    public MainViewPagerAdapter(List<View> listView, List<String> listStringTitle) {
        this.listView = listView;
        this.listStringTitle = listStringTitle;
        super.InitBaseViewPagerAdapter();
    }

    @Override
    public List<View> setViews() {
        return listView;
    }

    @Override
    public List<String> setViewTitle() {
        return listStringTitle;
    }
}
