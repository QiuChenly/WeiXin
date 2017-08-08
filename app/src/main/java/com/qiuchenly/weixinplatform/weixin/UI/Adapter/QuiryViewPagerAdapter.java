package com.qiuchenly.weixinplatform.weixin.UI.Adapter;

import android.view.View;

import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseAdapter.BaseViewPagerAdapter;

import java.util.List;

/**
 * Author: QiuChenluoye
 * Time  : 2017/07/28,上午 10:33
 * Func  : 具体ViewPager实现类
 * Using : 实现Viewpager适配器
 */

public class QuiryViewPagerAdapter extends BaseViewPagerAdapter {

    List<View> listView;
    List<String> StringList;

    public QuiryViewPagerAdapter(List<View> listView, List<String> StringList) {
        this.listView = listView;
        this.StringList = StringList;
        super.InitBaseViewPagerAdapter();
    }

    @Override
    public List<View> setViews() {
        return listView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return StringList.get(position);
    }

    @Override
    public void ViewID(int position) {

    }
}
