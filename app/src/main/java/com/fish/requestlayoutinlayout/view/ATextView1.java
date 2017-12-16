package com.fish.requestlayoutinlayout.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.fish.requestlayoutinlayout.util.RequestDetectV1;

/**
 * Created by fish on 2017/12/15.
 * yuxm_zju@aliyun.com
 */

public class ATextView1 extends TextView {
    public ATextView1(Context context) {
        super(context);
    }

    public ATextView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ATextView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void requestLayout() {
        RequestDetectV1.preRequest(this);
        super.requestLayout();
    }
}
