package com.yifeng.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yifeng.view.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickWaterRipple(View v) {
        startActivity(new Intent(this, WaterRippleActivity.class));
    }

    public void onClickCircleLoading(View v) {
        startActivity(new Intent(this, CircleLoadingActivity.class));
    }

}
