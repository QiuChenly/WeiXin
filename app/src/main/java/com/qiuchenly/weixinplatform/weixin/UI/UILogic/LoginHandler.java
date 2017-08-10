package com.qiuchenly.weixinplatform.weixin.UI.UILogic;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * Author: QiuChenluoye
 * Time: 2017/08/10,下午 03:53
 * Func: 功能名称
 * Using: 无解释
 */

public class LoginHandler extends Handler {
    private Context context;

    public LoginHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        
    }
}
