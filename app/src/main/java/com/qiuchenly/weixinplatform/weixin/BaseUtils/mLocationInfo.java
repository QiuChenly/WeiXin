package com.qiuchenly.weixinplatform.weixin.BaseUtils;

/**
 * Author: QiuChenluoye
 * Time: 2017/08/09,下午 04:53
 * Func: 功能名称
 * Using: 无解释
 */

public class mLocationInfo {
    private String sunIn,sunOut,province,location,voiceTpl;

    public mLocationInfo(String sunIn, String sunOut, String province, String location, String voiceTpl) {
        this.sunIn = sunIn;
        this.sunOut = sunOut;
        this.province = province;
        this.location = location;
        this.voiceTpl = voiceTpl;
    }

    public String getSunIn() {
        return sunIn;
    }

    public void setSunIn(String sunIn) {
        this.sunIn = sunIn;
    }

    public String getSunOut() {
        return sunOut;
    }

    public void setSunOut(String sunOut) {
        this.sunOut = sunOut;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVoiceTpl() {
        return voiceTpl;
    }

    public void setVoiceTpl(String voiceTpl) {
        this.voiceTpl = voiceTpl;
    }
}
