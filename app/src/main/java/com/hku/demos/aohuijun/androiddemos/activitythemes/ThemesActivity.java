package com.hku.demos.aohuijun.androiddemos.activitythemes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.hku.demos.aohuijun.androiddemos.R;

public class ThemesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);
        Button lightActionBarButton;
        Button darkActionBarButton;
        Button fullScreenButton;
        Button noActionBarButton;
    }
}
