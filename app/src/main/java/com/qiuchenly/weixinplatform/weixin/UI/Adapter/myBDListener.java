package com.qiuchenly.weixinplatform.weixin.UI.Adapter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;

import java.util.List;

/**
 * Author: QiuChenluoye
 * Time: 2017/07/31,下午 02:24
 * Func: 功能名称
 * Using: 无解释
 */

public abstract class myBDListener implements BDLocationListener {

    Context conn;

    public myBDListener(Context conn) {
        this.conn = conn;
    }

    public abstract void ResolveData(String data,BDLocation location);

    @Override
    public void onReceiveLocation(BDLocation location) {
        int type = location.getLocType();
        switch (type) {
            case 161:
                break;
            case 61:
                break;
            default:
                Toast.makeText(conn, "定位失败！错误代码：" + type, Toast.LENGTH_SHORT).show();
                break;
        }
        //获取定位结果
        StringBuffer sb = new StringBuffer(256);

        sb.append("定位时间 : ");
        sb.append(location.getTime());    //获取定位时间

        sb.append("\n服务器返回代码 : ");
        sb.append(location.getLocType());    //获取类型类型

        sb.append("\n纬度 : ");
        sb.append(location.getLatitude());    //获取纬度信息

        sb.append("\n经度 : ");
        sb.append(location.getLongitude());    //获取经度信息

        sb.append("\n定位精准度 : ");
        sb.append(location.getRadius());    //获取定位精准度

        if (location.getLocType() == BDLocation.TypeGpsLocation) {

            // GPS定位结果
            sb.append("\n通过GPS定位成功。");

            sb.append("\n公里每小时 : ");
            sb.append(location.getSpeed());    // 单位：公里每小时

            sb.append("\n卫星数 : ");
            sb.append(location.getSatelliteNumber());    //获取卫星数

            sb.append("\n海拔高度 : ");
            sb.append(location.getAltitude());    //获取海拔高度信息，单位米

            sb.append("\n方向 : ");
            sb.append(location.getDirection());    //获取方向信息，单位度

            sb.append("\n地址 : ");
            sb.append(location.getAddrStr());    //获取地址信息

            sb.append("\ndescribe : ");
            sb.append("GPS定位成功");

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

            // 网络定位结果
            sb.append("\n地址 : ");
            sb.append(location.getAddrStr());    //获取地址信息

            sb.append("\n运营商 : ");
            sb.append(location.getOperators());    //获取运营商信息

            sb.append("\ndescribe : ");
            sb.append("网络定位成功");

        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

            // 离线定位结果
            sb.append("\ndescribe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");

        } else if (location.getLocType() == BDLocation.TypeServerError) {

            sb.append("\ndescribe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

            sb.append("\ndescribe : ");
            sb.append("网络不同导致定位失败，请检查网络是否通畅");

        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

            sb.append("\ndescribe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");

        }

        sb.append("\n最近的位置 : ");
        sb.append(location.getLocationDescribe());    //位置语义化信息

        List<Poi> list = location.getPoiList();    // POI数据
        if (list != null) {
            sb.append("\n周边地点数 : ");
            sb.append(list.size());
            for (Poi p : list) {
                sb.append("\n周边地点 : ");
                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            }
        }
        ResolveData(sb.toString(),location);
//        time : 2017-07-31 14:46:47
//        error code : 161
//        latitude : 32.042194
//        lontitude : 118.64888
//        radius : 393.0
//        addr : 中国江苏省南京市浦口区团结路
//        operationers : 0
//        describe : 网络定位成功
//        locationdescribe : 在南京软件园孵鹰大厦A座附近
//        poilist size = : 5
//        poi= : 3571054212937767835 南京软件园孵鹰大厦A座 0.99
//        poi= : 232902452275494860 江北新区研创园孵鹰大厦 0.99
//        poi= : 16015751985088393805 南京软件园孵鹰大厦-D座 0.99
//        poi= : 18003711806215815167 江浦农场二队 0.99
//        poi= : 9034431519753591653 江北新区研创园儒商科技大厦 0.99
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }
}
