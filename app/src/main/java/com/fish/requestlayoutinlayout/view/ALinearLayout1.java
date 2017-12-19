package com.fish.requestlayoutinlayout.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by fish on 2017/12/15.
 * yuxm_zju@aliyun.com
 */

public class ALinearLayout1 extends LinearLayout {
    public ALinearLayout1(@NonNull Context context) {
        super(context);
    }

    public ALinearLayout1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ALinearLayout1(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
    }
}
