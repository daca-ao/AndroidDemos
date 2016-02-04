package com.hku.demos.aohuijun.androiddemos.floatwindow;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;

public class FloatWindowService extends Service implements View.OnTouchListener, View.OnClickListener{

    @Override
    public void onCreate() {
        super.onCreate();
        createView();
    }

    private void createView() {

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

    }
}
