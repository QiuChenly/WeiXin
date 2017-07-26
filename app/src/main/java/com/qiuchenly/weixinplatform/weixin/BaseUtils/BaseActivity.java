package com.qiuchenly.weixinplatform.weixin.BaseUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Author: QiuChenluoye
 * Time: 2017/07/26,上午 09:58
 * Func: 基础Activity类,所有Activity都继承此类
 * Using: 基础继承 抽象方法
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener
{

    //日志输出标记
    protected final String TAG = this.getClass().getSimpleName();

    //是否透明状态栏
    private boolean isAllowStatusBar = false;

    //当前Activity渲染的视图View
    private View mContextView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseActivity----is OnCreate.");

    }

    @Override
    public void onClick(View view) {
        ViewClick(view);
    }

    /**
     * 抽象方法,响应点击事件
     * @param v 响应的具体View
     */
   public  abstract void ViewClick(View v);

    //是否允许沉浸式状态栏
    public void setAllowStatusBar(boolean allowStatusBar) {
        isAllowStatusBar = allowStatusBar;
    }

    /**
     * 简化Toast显示
     * @param msg 消息内容
     */
    protected void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 跳转到新的Activity
     * @param newView 新的Activity
     */
    public void startActivity(Class<?> newView){
        startActivity(new Intent(BaseActivity.this,newView));
    }


}
