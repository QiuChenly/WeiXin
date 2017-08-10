package com.qiuchenly.weixinplatform.weixin.BaseUtils;

/**
 * Author: QiuChenluoye
 * Time: 2017/08/09,下午 03:54
 * Func: 功能名称
 * Using: 无解释
 */

public class mWeatherInfo {
    private String high,low,month,days,weekday,data,wind,wind1;

    public mWeatherInfo(String high, String low, String month, String days, String weekday, String data, String wind, String wind1) {
        this.high = high;
        this.low = low;
        this.month = month;
        this.days = days;
        this.weekday = weekday;
        this.data = data;
        this.wind = wind;
        this.wind1 = wind1;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWind1() {
        return wind1;
    }

    public void setWind1(String wind1) {
        this.wind1 = wind1;
    }
}