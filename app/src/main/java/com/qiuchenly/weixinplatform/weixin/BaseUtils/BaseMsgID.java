package com.qiuchenly.weixinplatform.weixin.BaseUtils;

/**
 * Author: QiuChenluoye
 * Time: 2017/08/07,下午 02:27
 * Func: 功能名称
 * Using: 无解释
 */

public interface BaseMsgID {
    interface Base {
        int Base = 0x1000000;
    }

    interface MsgID {
        int Handle_BaiduMapInit = Base.Base + 1;
    }
}