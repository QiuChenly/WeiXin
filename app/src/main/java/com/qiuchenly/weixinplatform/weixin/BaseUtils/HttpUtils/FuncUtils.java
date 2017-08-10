package com.qiuchenly.weixinplatform.weixin.BaseUtils.HttpUtils;

import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseData;
import com.qiuchenly.weixinplatform.weixin.BaseUtils.httpClient.GsonUtil;
import com.qiuchenly.weixinplatform.weixin.BaseUtils.httpClient.ResponseData;
import com.qiuchenly.weixinplatform.weixin.BaseUtils.httpClient.httpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * Author: QiuChenluoye
 * Time: 2017/08/09,上午 11:09
 * Func: Http基础请求工具类
 * Using: 无解释
 */

public class FuncUtils implements AbsLogin {
    public static void mWeatherInquiry() throws IOException {
        String u = "https://www.sogou.com/web?query=%E5%A4%A9%E6%B0%94" +
                "&_asf=www.sogou.com&_ast=&w=01019900&p=40040100&ie=utf8" +
                "&from=index-nologin&s_from=index&sut=19144&sst0=1502262294475&lkt=6%2C1502262282877%2C1502262283493&sugsuv=1502262034716239&sugtime=1502262294475";
        ResponseData res = httpClient.Request(u);
        if (res.responseCode == 302) {
            u = res.RedrictUrl;
            res = httpClient.Request(u, httpClient.cookies);
            String html = res.responseText;
            String regex = "{high: '(.*?)',\n" +
                    "                low: '(.*?)',\n" +
                    "                month: '(.*?)',\n" +
                    "                days: '(.*?)',\n" +
                    "                week: '(.*?)',\n" +
                    "                date: '(.*?)',\n" +
                    "                daydescription: '(.*?)',\n" +
                    "                nightdescription: '(.*?)',\n" +
                    "                description: '(.*?)',\n" +
                    "                wind: '(.*?)',\n" +
                    "                wind1: '(.*?)',\n" +
                    "                forbidden: '(.*?)'\n" +
                    "                }";

            String regex1 = "sunrise :  '(.*?)',\n" +
                    "            sunset:  '(.*?)',\n" +
                    "            city: '(.*?)',\n" +
                    "            xianzhen: '(.*?)',\n" +
                    "            jingdian: '(.*?)',\n" +
                    "            province: '(.*?)',\n" +
                    "            location: '(.*?)',\n" +
                    "            voiceTpl:\"(.*?)\",";

        }

    }

    /**
     * 登录实现接口
     *
     * @param userPhoneNum 手机号码
     * @param pass         明文密码
     * @return 返回true 成功,返回false失败
     */
    @Override
    public boolean login(String userPhoneNum, String pass) {
        /*
         * 简单加密算法签名接口校验
         */
        long tt = System.currentTimeMillis();
        String passWord = md5(pass + tt + "QiuChenly");
        String Sign = md5(tt + passWord + "QiuChenly");
        StringBuilder url = new StringBuilder();
        url.append(BaseData.HostName + BaseData.Func_Login + "userName=").
                append(userPhoneNum).append("&password=").
                append(passWord).append("&tt=").
                append(tt).append("&sign=").
                append(Sign);
        Sign = md5(Sign + passWord + url);
        url.append("&Signs=").append(Sign);
        LoginResult result;
        String s = null;
        try {
            s = httpClient.Request_Str(url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = GsonUtil.ResolveJsonA(s, LoginResult.class);
        if (Objects.equals(result.getErrNo(), "0")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void register(String userNick, String PhoneNum, String passWord) {

    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
