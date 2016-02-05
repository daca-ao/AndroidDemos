package com.hku.demos.aohuijun.androiddemos.floatwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

import com.hku.demos.aohuijun.androiddemos.MyApp;

/**
 * Created by aohuijun on 16/2/4.
 */
public class FloatWindowManager {

    private Context mContext;
    private FloatWindowLayout mFloatWindowLayout;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    public void createFloatWindow(Context context) {
        mContext = context;
        WindowManager windowManager = getWindowManager(mContext);
        if (mFloatWindowLayout == null) {
            mFloatWindowLayout = new FloatWindowLayout(context);
            if (mLayoutParams == null)
                initLayoutParams();
            mFloatWindowLayout.setParams(mLayoutParams);
            windowManager.addView(mFloatWindowLayout, mLayoutParams);
        }
    }

    public void removeFloatWindow(Context context) {
        mContext = context;
        if (mFloatWindowLayout != null) {
            WindowManager windowManager = getWindowManager(mContext);
            windowManager.removeView(mFloatWindowLayout);
            mLayoutParams = null;
            mFloatWindowLayout = null;
        }
    }

    private void initLayoutParams() {
        mLayoutParams = new WindowManager.LayoutParams();
        //  Set window type
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;   //  NOTICE THE TYPE
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
    }

    private WindowManager getWindowManager(Context context) {
        mContext = context;
        if (mWindowManager == null)
            mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        return mWindowManager;
    }

    public FloatWindowLayout getFloatWindowInstance() {
        return mFloatWindowLayout;
    }
}
