package com.hku.demos.aohuijun.androiddemos.floatwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.hku.demos.aohuijun.androiddemos.R;

public class FloatWindowActivity extends Activity implements View.OnClickListener {

    private FloatWindowManager mFloatWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_window);

        mFloatWindowManager = new FloatWindowManager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
