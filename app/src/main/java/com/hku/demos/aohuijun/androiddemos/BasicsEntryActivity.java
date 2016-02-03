package com.hku.demos.aohuijun.androiddemos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class BasicsEntryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics_entry);

        Button launchModesButton = (Button) findViewById(R.id.button_entry_launch_modes);
        Button diffThemesButton = (Button) findViewById(R.id.button_entry_activity_themes);
        Button multiDrawablesButton = (Button) findViewById(R.id.button_entry_multi_drawables);

        launchModesButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.LAUNCH_MODES));
        diffThemesButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.DIFF_THEMES));
        multiDrawablesButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.MULTI_DRAWABLES));
    }
}
