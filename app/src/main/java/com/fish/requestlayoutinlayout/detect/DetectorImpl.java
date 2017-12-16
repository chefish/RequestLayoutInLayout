package com.fish.requestlayoutinlayout.detect;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by fish on 2017/12/16.
 * yuxm_zju@aliyun.com
 */

class DetectorImpl {
    private static final String TAG = "DetectorImpl";
    private Activity activity;
    private View contentView;
    private Map<View, String> stackMap = new HashMap<>();

    private boolean isStart;

    //在MsgResizedReport之后才记录

    DetectorImpl(Activity activity) {
        this.activity = activity;
        ViewGroup contentFrameLayout = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        contentView = contentFrameLayout.getChildAt(0);
        observe();
    }


    private void observe() {
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!isStart()) {
                    return;
                }

                LayoutFlagChecker layoutFlagChecker = new LayoutFlagChecker();
                LayoutFlagChecker.LayoutFlagCheckerResult result = layoutFlagChecker.checkLayoutFlag(contentView, 0);
                if (result.isEmpty()) {
                    Log.d(TAG, "@onGlobalLayout " + " well ");
                    stackMap.clear();
                } else {
                    Log.d(TAG, "@onGlobalLayout abnormal view " + "start------> ");
                    //打印异常的view的层级
                    printAbnormalDepth(result);
                    printProblemView(result.lastTrueView);
                    handleStack(result);

                    Log.d(TAG, "@onGlobalLayout abnormal view " + "end<------ ");
                }

            }
        });
    }

    private boolean isStart() {
        return isStart;
    }

    public void recordStack(View v, String stackTraceString) {
        if (!isStart()) {
            return;
        }
        stackMap.put(v, stackTraceString);
    }

    private void filterStackMap(List<View> views, Map<View, String> map) {
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            View v = (View) entry.getKey();
            if (!views.contains(v)) {
                iter.remove();
            }
        }
    }

    private void printAbnormalDepth(LayoutFlagChecker.LayoutFlagCheckerResult result) {
        View view = result.deepElement.view;
        int deep = result.deepElement.depth;
        while (deep >= 0) {
            printAbnormal(view, deep);
            view = (View) view.getParent();
            deep--;
        }

        //print stackFrame for lastTrueView

    }

    private void printAbnormal(View view, int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append("depth: ").append(depth)
                .append(" PFLAG_FORCE_LAYOUT:").append(view.isLayoutRequested())
                .append(" view: ").append(view)
                .append(extra(view));
        Log.w(TAG, sb.toString());
    }


    private String extra(View view) {
        if (view instanceof TextView) {
            return ((TextView) view).getText().toString();
        }
        return "";
    }


    private void printProblemView(View lastTrueView) {
        Log.e(TAG, "please preRequest this: " + lastTrueView + " in activity: " + activity);
    }

    private void handleStack(LayoutFlagChecker.LayoutFlagCheckerResult result) {
        if (activity != null) {
            filterStackMap(result.views, stackMap);
            String currentStack = stackMap.get(result.lastTrueView);
            if (!TextUtils.isEmpty(currentStack)) {
                Log.e(TAG, currentStack);
            }
        }
    }


    public void setStart() {
        isStart = true;
    }
}
