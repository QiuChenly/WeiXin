package com.qiuchenly.weixinplatform.weixin.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseActivity;
import com.qiuchenly.weixinplatform.weixin.R;

public class RegisterView extends BaseActivity {

    @Override
    public void getSharedPreference(SharedPreferences sp, SharedPreferences.Editor Ueditors) {

    }

    @Override
    public void doBusiness(Context mContext, View v) {

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
        return R.layout.activity_register_view;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initParams(Bundle bundle) {
        setDisableActionBar(true);
        setOpenDoubleClickBackKeyReturnUpView(true);
    }

    @Override
    public void ViewClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

}
