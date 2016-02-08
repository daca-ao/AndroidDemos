package com.hku.demos.aohuijun.androiddemos.floatwindow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hku.demos.aohuijun.androiddemos.R;

import java.lang.reflect.Field;

/**
 * Created by aohuijun on 16/2/5.
 */
public class FloatWindowLayout extends LinearLayout implements View.OnClickListener {

    //  Delta values between MotionEvents and window position
    private float deltaX, deltaY;
    //  Manage the window's layout parameters
    private WindowManager mWindowManager;
    //  Window's layout parameters
    private WindowManager.LayoutParams mLayoutParams;
    private OnFloatWindowCallback mCallback;
    private static int mStatusBarHeight;

    public FloatWindowLayout(Context context) {
        super(context);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.item_float_window, this);
        Button mButton = (Button) findViewById(R.id.float_window_button);
        mButton.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("TOUCH", "DOWN");
                deltaX = event.getX();
                deltaY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TOUCH", "MOVE");
                mLayoutParams.x = (int) (event.getRawX() - deltaX);
                mLayoutParams.y = (int) (event.getRawY() - deltaY) - getStatusBarHeight();
                mWindowManager.updateViewLayout(this, mLayoutParams);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TOUCH", "UP");
                Toast.makeText(getContext().getApplicationContext(),
                        getResources().getString(R.string.float_window_hint),
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
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
        Toast.makeText(getContext().getApplicationContext(),
                getResources().getString(R.string.float_window_closed),
                Toast.LENGTH_SHORT).show();
        mCallback.onRemove();
    }

    public void setParams(WindowManager.LayoutParams params) {
        this.mLayoutParams = params;
    }

    public void setOnFloatWindowCallback(OnFloatWindowCallback callback) {
        this.mCallback = callback;
    }

    public interface OnFloatWindowCallback {
        void onRemove();
    }
}
