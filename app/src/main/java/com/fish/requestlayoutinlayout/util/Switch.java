package com.fish.requestlayoutinlayout.util;

/**
 * Created by fish on 2017/12/15.
 * yuxm_zju@aliyun.com
 */

public class Switch {
    public static boolean on = false;

    public static void change() {
        on = !on;
    }

    public static void init() {
        on = false;
    }

}
