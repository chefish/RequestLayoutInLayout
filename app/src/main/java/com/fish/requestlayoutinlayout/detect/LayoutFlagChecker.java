package com.fish.requestlayoutinlayout.detect;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fish on 2017/12/15.
 * yuxm_zju@aliyun.com
 */

class LayoutFlagChecker {

    private Element deepElement;
    private List<View> views = new ArrayList<>();
    //最后一个标志位true的view
    private View lastTrueView;


    public static class Result {
        public Element deepElement;
        public List<View> views = new ArrayList<>();
        //last PFLAG_FORCE_LAYOUT true的view
        public View lastTrueView;

        public Result(Element deepElement, List<View> views, View lastTrueView) {
            this.deepElement = deepElement;
            this.views = views;
            this.lastTrueView = lastTrueView;
        }

        public boolean isEmpty() {
            return deepElement == null;
        }

        public View getDepthView() {
            if (deepElement == null) {
                return null;
            }
            return deepElement.view;
        }
    }


    public Result checkLayoutFlag(View view, int depth) {
        views.add(view);
        //self
        if (depth > getMaxDepth()) {
            checkViewSelf(view, depth);
        }

        //children
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            depth++;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View view1 = vg.getChildAt(i);
                //只检测可见的
                if (view1.getVisibility() == View.VISIBLE) {
                    checkLayoutFlag(view1, depth);
                }
            }
        }

        findLastRequestedView();
        return new Result(deepElement, views, lastTrueView);
    }

    private void findLastRequestedView() {
        if (deepElement == null) {
            return;
        }
        View view = deepElement.view;
        int deep = deepElement.depth;
        while (deep >= 0) {
            view = (View) view.getParent();
            deep--;
            if (view.isLayoutRequested()) {
                lastTrueView = view;
            }
        }
    }

    int getMaxDepth() {
        if (deepElement == null) {
            return -1;
        } else {
            return deepElement.depth;
        }
    }


    private void checkViewSelf(View view, int depth) {
        if (view.isLayoutRequested()) {
//            LogUtil.e("@onGlobalLayout " + );
            updateElement(view, depth);
        }
    }


    private void updateElement(View view, int depth) {
        if (deepElement == null) {
            deepElement = new Element(depth, view);
        } else if (depth > deepElement.depth) {
            deepElement.view = view;
            deepElement.depth = depth;
        }


    }
}
