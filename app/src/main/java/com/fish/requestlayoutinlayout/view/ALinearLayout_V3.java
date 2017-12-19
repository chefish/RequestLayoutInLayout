package com.fish.requestlayoutinlayout.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.fish.requestlayoutinlayout.detect.LayoutAbnormalDetector;

/**
 * Created by fish on 2017/12/15.
 * yuxm_zju@aliyun.com
 */

public class ALinearLayout_V3 extends LinearLayout {

    public ALinearLayout_V3(Context context) {
        super(context);
    }

    public ALinearLayout_V3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ALinearLayout_V3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
    }

    @Override
    public void forceLayout() {
        super.forceLayout();
        //tell RequestAbnormalDetector
        LayoutAbnormalDetector.afterForceLayout(this);
    }

}
