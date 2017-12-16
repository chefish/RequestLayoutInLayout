package com.fish.requestlayoutinlayout;

import android.app.Application;

import com.fish.requestlayoutinlayout.base.ScreenUtil;
import com.fish.requestlayoutinlayout.detect.RequestAbnormalDetector;

/**
 * Created by fish on 2017/12/16.
 * yuxm_zju@aliyun.com
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ScreenUtil.init(this);
        RequestAbnormalDetector.setOn(true);
    }
}
