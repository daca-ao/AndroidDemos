package com.hku.demos.aohuijun.androiddemos.multidrawables;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.hku.demos.aohuijun.androiddemos.R;

public class MultiDrawablesActivity extends Activity {

    private GradientDrawable mDrawable;
    private LinearLayout mLayout;
    private int[] mColorsList = {Color.BLACK, Color.CYAN, Color.GRAY, Color.GREEN, Color.LTGRAY,
                                Color.MAGENTA, Color.RED, Color.YELLOW};
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_drawables);
        mDrawable = new GradientDrawable();
        mDrawable.setCornerRadius(20);
        mDrawable.setColor(Color.YELLOW);
        mLayout = (LinearLayout) findViewById(R.id.multi_drawables_shape_code);
        mLayout.setBackground(mDrawable);
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawable.setColor(mColorsList[count % mColorsList.length]);
                mLayout.setBackground(mDrawable);
                count++;
            }
        });
    }
}
