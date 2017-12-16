package com.fish.requestlayoutinlayout.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by fish on 2017/12/16.
 * yuxm_zju@aliyun.com
 */

public class HomeActivity extends AppCompatActivity {
    protected void addClick(int id, final Class activityClass) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, activityClass);
                startActivity(intent);
            }
        });
    }
}
