package com.fish.requestlayoutinlayout.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.fish.requestlayoutinlayout.base.LogUtil;
import com.fish.requestlayoutinlayout.detect.LayoutAbnormalDetector;

/**
 * Created by fish on 2017/12/15.
 * yuxm_zju@aliyun.com
 */

public class AFrameLayout2 extends FrameLayout {
    public AFrameLayout2(@NonNull Context context) {
        super(context);
    }

    public AFrameLayout2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AFrameLayout2(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void requestLayout() {
        LayoutAbnormalDetector.preRequestLayout(this);
        super.requestLayout();
        LogUtil.d("AFrameLayout2@requestLayout");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtil.d("AFrameLayout2@onLayout");

    }
}
