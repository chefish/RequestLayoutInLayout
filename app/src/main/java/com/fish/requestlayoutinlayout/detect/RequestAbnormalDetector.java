package com.fish.requestlayoutinlayout.detect;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.fish.requestlayoutinlayout.view.ALinearLayout_V3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * It is used to detect requestLayout in Layout problem.It will lead to abnormal layout problem.
 * Created by fish on 2017/12/15.
 * yuxm_zju@aliyun.com
 */

public class RequestAbnormalDetector {

    private static final String TAG = "RequestAbnormalDetector";
    private static boolean on = false;
    private static Map<Activity, DetectorImpl> detectorMap = new HashMap<>();

    //Activity onCreate
    public static void start(Activity activity) {
        if (!on) {
            return;
        }
        detectorMap.put(activity, new DetectorImpl(activity));
    }

    //Activity onDestroy
    public static void stop(Activity activity) {
        if (!on) {
            return;
        }
        detectorMap.remove(activity);
    }

    //used in root view
    public static void afterForceLayout(View view) {
        Activity activity = getActivity(view);
        if (activity != null && detectorMap.keySet().contains(activity)) {
            detectorMap.get(activity).setStart();
        }
    }

    //used in abnormal View
    public static void preRequestLayout(View abnormalView) {
        if (!on) {
            return;
        }
//        Log.d(TAG, "@requestLayout observe " + view);

        ViewParent parent = abnormalView.getParent();
        Activity activity = getActivity(abnormalView);
        if (activity != null && parent != null && parent.isLayoutRequested() && detectorMap.keySet().contains(activity)) {
            //关键测试代码
            Log.w(TAG, "@preRequest parent isLayoutRequested " + abnormalView + " parent: " + parent + " activity: " + activity);

            detectorMap.get(activity).recordStack(abnormalView, Log.getStackTraceString(new RequestLayoutException()));

        }
    }

    public static boolean isOn() {
        return on;
    }

    //usually on when Debug off when release
    public static void setOn(boolean pOn) {
        on = pOn;
    }


    private static Activity getActivity(View view) {
        Context context = view.getContext();
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }
}
