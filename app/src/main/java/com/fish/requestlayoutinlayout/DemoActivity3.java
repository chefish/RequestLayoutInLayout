package com.fish.requestlayoutinlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fish.requestlayoutinlayout.detect.LayoutAbnormalDetector;
import com.fish.requestlayoutinlayout.util.Switch;
import com.fish.requestlayoutinlayout.view.AFrameLayout2;
import com.fish.requestlayoutinlayout.view.ALinearLayout1;

/**
 * 打开app。点击上面的textview就会出发requestlayout in layout 问题 4.3以下必现
 */
public class DemoActivity3 extends AppCompatActivity {
    TextView textView, textViewSwitch;
    ALinearLayout1 aFrameLayout1;
    AFrameLayout2 aFrameLayout2;
    private static final String TAG = "DemoActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);
        Switch.init();
        textView = (TextView) findViewById(R.id.a0);
        textViewSwitch = (TextView) findViewById(R.id.switch1);
        aFrameLayout1 = (ALinearLayout1) findViewById(R.id.a1);
        aFrameLayout2 = (AFrameLayout2) findViewById(R.id.a2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("we are family");
            }
        });


        textViewSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch.change();
                textViewSwitch.setText("swtich " + Switch.on);
            }
        });
//        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Log.d(TAG, "@onGlobalLayout A1: " + aFrameLayout1.isLayoutRequested() + " A2: " + aFrameLayout2.isLayoutRequested());
//            }
//        });
        LayoutAbnormalDetector.start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LayoutAbnormalDetector.stop(this);
    }

}
