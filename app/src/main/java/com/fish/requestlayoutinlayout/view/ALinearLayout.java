package com.fish.requestlayoutinlayout.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by fish on 2017/12/15.
 * yuxm_zju@aliyun.com
 */

public class ALinearLayout extends LinearLayout {

    public ALinearLayout(Context context) {
        super(context);
    }

    public ALinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ALinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
    }

}
