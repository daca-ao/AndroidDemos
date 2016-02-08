package com.hku.demos.aohuijun.androiddemos.floatwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hku.demos.aohuijun.androiddemos.R;

public class FloatWindowActivity extends Activity implements View.OnClickListener, FloatWindowLayout.OnFloatWindowCallback {

    private FloatWindowManager mFloatWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_window);

        mFloatWindowManager = new FloatWindowManager();
        Button startFlowWindowButton = (Button) findViewById(R.id.float_window_start_button);
        Button closeFloatWindowButton = (Button) findViewById(R.id.float_window_close_button);
        startFlowWindowButton.setOnClickListener(this);
        closeFloatWindowButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.float_window_start_button:
                /**
                 * Method 1: Start a new Service with a layout.
                 */
//                Intent startIntent = new Intent();
//                startIntent.setClass(FloatWindowActivity.this, FloatWindowService.class);
//                startService(startIntent);
                /**
                 * Method 2: Create & inflate a simple self-defined LinearLayout.
                 */
                mFloatWindowManager.createFloatWindow(this);
                mFloatWindowManager.getFloatWindowInstance().setOnFloatWindowCallback(this);
                break;
            case R.id.float_window_close_button:
                /**
                 * Method 1: Directly stop the service.
//                 */
//                Intent closeIntent = new Intent();
//                closeIntent.setClass(FloatWindowActivity.this, FloatWindowService.class);
//                stopService(closeIntent);
                /**
                 * Method 2: Remove the LinearLayout.
                 */
                mFloatWindowManager.removeFloatWindow(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRemove() {
        mFloatWindowManager.removeFloatWindow(this);
    }
}
