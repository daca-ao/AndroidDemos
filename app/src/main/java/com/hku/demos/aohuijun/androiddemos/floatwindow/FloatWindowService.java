package com.hku.demos.aohuijun.androiddemos.floatwindow;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hku.demos.aohuijun.androiddemos.MyApp;
import com.hku.demos.aohuijun.androiddemos.R;

import java.lang.reflect.Field;

public class FloatWindowService extends Service implements View.OnTouchListener, View.OnClickListener {

    //  Layout of the float window
    private LinearLayout mFloatWindowLayout;
    //  Manage the window's layout parameters
    private WindowManager mWindowManager;
    //  Window's layout parameters
    private WindowManager.LayoutParams mLayoutParams;
    //  Delta values between MotionEvents and window position
    private float deltaX, deltaY;
    private static int mStatusBarHeight;

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
        LayoutInflater mInflater = LayoutInflater.from(getApplicationContext());
        mFloatWindowLayout = (LinearLayout) mInflater.inflate(R.layout.item_float_window, null);
        Button mButton = (Button) mFloatWindowLayout.findViewById(R.id.float_window_button);
        FloatWindowTextView mTextView = (FloatWindowTextView) mFloatWindowLayout.findViewById(R.id.float_window_text);
        mTextView.setOnTouchListener(this);
        mTextView.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mWindowManager.addView(mFloatWindowLayout, mLayoutParams);
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

    private int getStatusBarHeight() {
        if (mStatusBarHeight == 0) {
            try {
                /**
                 * A new way to get height of StatusBar.
                 * Use OLD method:
                 *      getWindow().getDecorView().getWindowVisibleDisplayFrame(frame)
                 * may return 0.
                 */
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (int) field.get(o);
                mStatusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mStatusBarHeight;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
