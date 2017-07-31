package com.qiuchenly.weixinplatform.weixin.UI.Adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseAdapter.BaseViewPagerAdapter;

import java.util.List;

/**
 * Author: QiuChenluoye
 * Time  : 2017/07/28,上午 10:33
 * Func  : 具体ViewPager实现类
 * Using : 实现Viewpager适配器
 */

public abstract class MainViewPagerAdapter extends BaseViewPagerAdapter {

    List<View> listView;
    List<TextView> textViewList;

    public MainViewPagerAdapter(List<View> listView, List<TextView> textViewList) {
        this.listView = listView;
        this.textViewList = textViewList;
        super.InitBaseViewPagerAdapter();
    }

    @Override
    public List<View> setViews() {
        return listView;
    }

    @Override
    public void ViewID(int position) {
        for (TextView t : textViewList) {
            t.setTextColor(Color.parseColor("#888888"));
        }
        (textViewList.get(position)).setTextColor(Color.parseColor("#333333"));

        SwitchView(position);
    }

    public abstract void SwitchView(int position);

}
