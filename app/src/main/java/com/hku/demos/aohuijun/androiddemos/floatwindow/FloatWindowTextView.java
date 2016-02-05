package com.hku.demos.aohuijun.androiddemos.floatwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by aohuijun on 16/2/5.
 */
public class FloatWindowTextView extends TextView {

    public FloatWindowTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
