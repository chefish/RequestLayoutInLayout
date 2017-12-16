package com.fish.requestlayoutinlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.fish.requestlayoutinlayout.detect.RequestAbnormalDetector;
import com.fish.requestlayoutinlayout.base.ScreenUtil;
import com.fish.requestlayoutinlayout.util.Switch;
import com.fish.requestlayoutinlayout.view.ALinearLayout1;
import com.fish.requestlayoutinlayout.view.AFrameLayout2;

/**
 * 打开app。点击上面的textview就会出发requestlayout in layout 问题 4.3以下必现
 */
public class DemoActivity1 extends AppCompatActivity {
    TextView textView;
    ALinearLayout1 aFrameLayout1;
    AFrameLayout2 aFrameLayout2;
    private static final String TAG = "DemoActivity1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

        textView = (TextView) findViewById(R.id.a0);
        aFrameLayout1 = (ALinearLayout1) findViewById(R.id.a1);
        aFrameLayout2 = (AFrameLayout2) findViewById(R.id.a2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("我们都是共产党");
            }
        });
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d(TAG, "@onGlobalLayout A1: " + aFrameLayout1.isLayoutRequested() + " A2: " + aFrameLayout2.isLayoutRequested());
            }
        });

        //
        RequestAbnormalDetector.start(this);
    }

}
