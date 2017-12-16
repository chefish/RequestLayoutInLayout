package com.fish.requestlayoutinlayout.util;

import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;

import com.fish.requestlayoutinlayout.base.LogUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by fish on 2017/12/15.
 * yuxm_zju@aliyun.com
 */

public class RequestDetectV1 {
    private static Set<View> getViews() {
        return views;
    }

    private static Set<View> views = new HashSet<>();

    public static void start(View any) {
        any.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                boolean eliminateAlarm = false;
                Iterator iterator = views.iterator();
                while (iterator.hasNext()) {
                    View v = (View) iterator.next();
                    if (v.isLayoutRequested()) {
                        LogUtil.e("@onGlobalLayout " + v);
                    } else {
                        eliminateAlarm = true;
                        iterator.remove();
                    }
                }
                //这是因为页面首次的时候会触发一次全员forceLayout
                if (eliminateAlarm && views.isEmpty()) {
                    LogUtil.w("@onGlobalLayout everything is well");
                }
            }
        });
    }

    public static void end(){
        views.clear();
    }

    public static void add(View view) {
        views.add(view);
    }


    public static void preRequest(View view) {
        LogUtil.d("@requestLayout start " + view);
        ViewParent parent = view.getParent();
        if (parent != null && parent.isLayoutRequested()) {
            //关键测试代码
            LogUtil.w("@requestLayout parent isLayoutRequested " + view + " parent: " + parent);
            RequestDetectV1.add(view);
        }
    }

}
