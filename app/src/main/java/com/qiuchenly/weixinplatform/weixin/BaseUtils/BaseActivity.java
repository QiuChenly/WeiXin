package com.qiuchenly.weixinplatform.weixin.BaseUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Author: QiuChenluoye
 * Time: 2017/07/26,上午 09:58
 * Func: 基础Activity类,所有Activity都继承此类
 * Using: 基础继承 抽象方法
 */

public abstract class BaseActivity extends AppCompatActivity
        implements View.OnClickListener {

    //上一次按键时间
    long lastTime;

    //日志输出标记
    protected final String TAG = this.getClass().getSimpleName();

    //是否关闭ActionBar改由自定义Toolbar
    private boolean isDisableActionBar = false;

    //是否透明状态栏
    private boolean isAllowStatusBar = true;

    //当前Activity渲染的视图View
    private View mContextView = null;

    //是否开启双击返回键退出
    private boolean isAllowDoubleClickBackKey = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseActivity----is OnCreate.");

        Bundle bundle = getIntent().getExtras();
        initParams(bundle);

        View setView = bindView();
        if (isNull(setView)) {
            mContextView = LayoutInflater.from(this).inflate(
                    bindLayout(), null);
        } else {
            mContextView = setView;
        }

        if (isAllowStatusBar) {
            setLayoutFullScreen();
        }

        if (isDisableActionBar) {
            if (getSupportActionBar().isShowing()) {
                getSupportActionBar().hide();
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
        setContentView(mContextView);
        initView(mContextView);
        setListener();
        doBusiness(mContextView.getContext());
    }

    public <T> boolean isNull(T obj) {
        return (null == obj);
    }

    public abstract void doBusiness(Context mContext);

    public abstract void setListener();

    public abstract View bindView();

    public abstract int bindLayout();

    public abstract void initView(final View view);

    protected <T extends View> T $(int resID) {
        return (T) super.findViewById(resID);
    }

    public abstract void initParams(Bundle bundle);

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isAllowDoubleClickBackKey) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                long now = System.currentTimeMillis();
                if (now - lastTime > 2000) {
                    showToast("再按一次关闭程序~");
                    lastTime = now;
                } else {
                    moveTaskToBack(true);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        ViewClick(view);
    }

    /**
     * 抽象方法,响应点击事件
     *
     * @param v 响应的具体View
     */
    public abstract void ViewClick(View v);


    public void setDisableActionBar(boolean disableActionBar) {
        isDisableActionBar = disableActionBar;
    }

    public void setAllowDoubleClickBackKey(boolean allowDoubleClickBackKey) {
        isAllowDoubleClickBackKey = allowDoubleClickBackKey;
    }

    //是否允许沉浸式状态栏
    public void setAllowStatusBar(boolean allowStatusBar) {
        isAllowStatusBar = allowStatusBar;
    }

    /**
     * 简化Toast显示
     *
     * @param msg 消息内容
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 跳转到新的Activity
     *
     * @param newView 新的Activity
     */
    public void startActivity(Class<?> newView) {
        startActivity(new Intent(BaseActivity.this, newView));
    }

    /**
     * 携带数据跳转到新的Activity
     *
     * @param newView 新Activity
     * @param data    要携带的数据
     */
    public void startActivity(Class<?> newView, Bundle data) {
        Intent i = new Intent(this, newView);
        i.putExtras(data);
        startActivity(i);
    }

    private void setLayoutFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

            );
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "BaseActivity----onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "BaseActivity----onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "BaseActivity----onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "BaseActivity----onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "BaseActivity----onStart");
    }
}
