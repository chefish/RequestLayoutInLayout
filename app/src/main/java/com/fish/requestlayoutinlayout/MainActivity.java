package com.fish.requestlayoutinlayout;

import android.os.Bundle;
import android.widget.TextView;

import com.fish.requestlayoutinlayout.base.HomeActivity;
import com.fish.requestlayoutinlayout.base.ScreenUtil;
import com.fish.requestlayoutinlayout.view.ALinearLayout1;
import com.fish.requestlayoutinlayout.view.AFrameLayout2;


public class MainActivity extends HomeActivity {
    TextView textView, textViewSwitch;
    ALinearLayout1 aFrameLayout1;
    AFrameLayout2 aFrameLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addClick(R.id.demo1, DemoActivity1.class);
        addClick(R.id.demo2, DemoActivity2.class);
        addClick(R.id.demo3, DemoActivity3.class);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
