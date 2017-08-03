package com.yifeng.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yifeng.view.R;
import com.yifeng.view.view.WaterRippleView;

/**
 * Created by lxf on 2017/7/23.
 *
 */

public class WaterRippleActivity extends BaseActivity {

    private WaterRippleView mWaterWrv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_ripple);

        mWaterWrv = (WaterRippleView) findViewById(R.id.wrv_water);
    }

    public void onClickStart(View v) {
        mWaterWrv.start();
    }

    public void onClickStop(View v) {
        mWaterWrv.stop();
    }

}
