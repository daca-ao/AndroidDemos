package com.hku.demos.aohuijun.androiddemos.floatwindow;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hku.demos.aohuijun.androiddemos.MyApp;

public class FloatWindowService extends Service implements View.OnTouchListener, View.OnClickListener {

    //  Layout of the float window
    private LinearLayout mLayout;
    //  Manage the window's layout parameters
    private WindowManager mWindowManager;
    //  Window's layout parameters
    private WindowManager.LayoutParams mLayoutParams;

    @Override
    public void onCreate() {
        super.onCreate();
        createView();
    }

    private void createView() {
        mWindowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        //  Set window type
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;   //  NOTICE
        //  Set background as transparent
        mLayoutParams.format = PixelFormat.RGBA_8888;
        //  Set not focusable (Visible parent Activity can have operations)
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //  Set initial gravity of the window
        mLayoutParams.gravity = Gravity.START | Gravity.TOP;
        //  Set initial position of the window
        mLayoutParams.x = MyApp.FLOAT_WINDOW_INIT_X;
        mLayoutParams.y = MyApp.FLOAT_WINDOW_INIT_Y;
        //  Set the size of window layout
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        //  Next: INFLATE THE LAYOUT INTO THIS WINDOW INSTANCE
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
