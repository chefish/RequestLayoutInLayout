package com.fish.requestlayoutinlayout.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.fish.requestlayoutinlayout.R;
import com.fish.requestlayoutinlayout.base.LogUtil;
import com.fish.requestlayoutinlayout.base.ScreenUtil;
import com.fish.requestlayoutinlayout.util.RequestDetectV1;
import com.fish.requestlayoutinlayout.util.Switch;

/**
 * Created by fish on 2017/12/15.
 * yuxm_zju@aliyun.com
 */

public class AFrameLayout3_V2 extends FrameLayout {
    public AFrameLayout3_V2(@NonNull Context context) {
        super(context);
    }

    public AFrameLayout3_V2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AFrameLayout3_V2(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void requestLayout() {
        RequestDetectV1.preRequest(this);
        super.requestLayout();
    }

    @Override
    public void forceLayout() {
        super.forceLayout();
        LogUtil.d("AFrameLayout3@forceLayout");
    }

    ALinearLayout1 aFrameLayout1;


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        aFrameLayout1 = (ALinearLayout1) findViewById(R.id.a1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = aFrameLayout1.getWidth();
        if (w == ScreenUtil.screenWidth && Switch.on) {
            //call A1 requestlayout
            aFrameLayout1.setLayoutParams(new LayoutParams(400, -1));
        }
    }
}
