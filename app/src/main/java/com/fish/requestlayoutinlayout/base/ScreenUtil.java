package com.fish.requestlayoutinlayout.base;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by fish on 16/8/6.
 */
public class ScreenUtil {
    public static float density;
    public static int screenWidth;
    public static int screenHeight;

    public static int dip2px(float dipValue) {
        final float scale = ScreenUtil.getDisplayDensity();
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = ScreenUtil.getDisplayDensity();
        return (int) (pxValue / scale + 0.5f);
    }

    private static float getDisplayDensity() {

        return density;
    }

    public static void init(Context context) {
        if (null == context) {
            return;
        }
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        density = dm.density;
    }
}