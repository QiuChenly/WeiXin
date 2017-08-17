package com.qiuchenly.weixinplatform.weixin.BaseUtils.HttpUtils;

/**
 * Author: QiuChenluoye
 * Time: 2017/08/10,上午 09:47
 * Func: 功能名称
 * Using: 无解释
 */

public interface AbsLogin {
    boolean login(String userPhoneNum, String pass);

    boolean register(String userNick, String PhoneNum, String passWord);
}
