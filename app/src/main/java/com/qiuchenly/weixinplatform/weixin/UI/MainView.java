package com.qiuchenly.weixinplatform.weixin.UI;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.qiuchenly.weixinplatform.weixin.BaseUtils.BaseActivity;
import com.qiuchenly.weixinplatform.weixin.R;
import com.qiuchenly.weixinplatform.weixin.UI.Adapter.MainViewPagerAdapter;
import com.qiuchenly.weixinplatform.weixin.UI.Adapter.QuiryViewPagerAdapter;
import com.qiuchenly.weixinplatform.weixin.UI.Adapter.myBDListener;

import java.util.ArrayList;
import java.util.List;

public class MainView extends BaseActivity {
    private String permissionInfo;

    TextView testShow;

    MapView mMapView = null;

    private ViewPager mViewPager;
    private LocationClient mBDLoactionClient;
    BDLocationListener mBDLocationListener;

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
      /*
       * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 127);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void initLocation() {
        getPersimmions();

        mBDLocationListener = new myBDListener(this) {
            @Override
            public void ResolveData(String data, BDLocation location) {
                if (mMapView == null) {
                    return;
                }
                BaiduMap mBaiduMap = mMapView.getMap();
                // 开启定位图层
                mBaiduMap.setMyLocationEnabled(true);
// 构造定位数据
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(100).latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();
// 设置定位数据
                mBaiduMap.setMyLocationData(locData);
// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
                BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_person);
                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS, true, mCurrentMarker);
                mBaiduMap.setMyLocationConfiguration(config);
                //Toast.makeText(conn, data, Toast.LENGTH_SHORT).show();
            }
        };

        mBDLoactionClient = new LocationClient(getApplicationContext());
        mBDLoactionClient.registerLocationListener(mBDLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mBDLoactionClient.setLocOption(option);
    }

    @Override
    public void doBusiness(Context mContext, View v) {
        LayoutInflater inflater = getLayoutInflater();
        List<View> list = new ArrayList<>();
        list.add(inflater.inflate(R.layout.mlayout_main_map, null));
        list.add(inflater.inflate(R.layout.mlayout_inquiryview, null));
        list.add(inflater.inflate(R.layout.mlayout_myinfo, null));

        List<TextView> textViews = new ArrayList<>();
        textViews.add((TextView) $(R.id.myMap));
        textViews.add((TextView) $(R.id.myRoundPlace));
        textViews.add((TextView) $(R.id.mySelfText));

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(list, textViews) {
            @Override
            public void SwitchView(int position) {
                showToast(String.valueOf(position));
            }
        };
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(adapter);
        //设置三级页面缓存
        mViewPager.setOffscreenPageLimit(3);
        initLocation();
        mBDLoactionClient.start();
    }

    //FIXME:2017/07/31
    //设计Handler通信。
    //设计解耦
    //将切换View的时间结合
    //ViewClick和ViewSwitch

    @Override
    public void setListener() {
        ($(R.id.myMapLayout)).setOnClickListener(this);
        ($(R.id.myPlaceLayout)).setOnClickListener(this);
        ($(R.id.mySelfLayout)).setOnClickListener(this);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main_view;
    }

    @Override
    public void initView(View view) {
        mViewPager = $(R.id.mMainViewPager);
//        testShow = $(R.id.TestShowXY);
    }

    @Override
    public void initParams(Bundle bundle) {
        super.setAllowDoubleClickBackKey(true);
//        super.setDisableActionBar(true);
    }

    Handler MasterHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {

            return false;
        }
    });

    @Override
    public void ViewClick(View v) {
        mMapView = $(R.id.bmapView);
        switch (v.getId()) {
            case R.id.myMapLayout:
                mViewPager.setCurrentItem(0);
                mMapView = $(R.id.bmapView);
                BaiduMap mBaiduMap = mMapView.getMap();
                mBaiduMap.setTrafficEnabled(true);
                //普通地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

//卫星地图
                // mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);

//空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
                //                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
                //                mBaiduMap.setBaiduHeatMapEnabled(false);

                // 当不需要定位图层时关闭定位图层
                //mBaiduMap.setMyLocationEnabled(false);

                mMapView.onResume();
                break;
            case R.id.myPlaceLayout:
                mMapView.onPause();
                mViewPager.setCurrentItem(1);
                ViewPager viewPager = $(R.id.mQuiryViewPager);

                List<View> list = new ArrayList<>();
                LayoutInflater inflater = getLayoutInflater();
                list.add(inflater.inflate(R.layout.mlayout_main_map, null));
                list.add(inflater.inflate(R.layout.mlayout_main_map, null));
                list.add(inflater.inflate(R.layout.mlayout_main_map, null));

                List<String> lists = new ArrayList<>();
                lists.add("Page1");
                lists.add("Page2");
                lists.add("Page3");
                QuiryViewPagerAdapter adapter = new QuiryViewPagerAdapter(list, lists);
                viewPager.setOffscreenPageLimit(3);
                viewPager.setAdapter(adapter);
                break;
            case R.id.mySelfLayout:
                mMapView.onPause();
                mViewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
