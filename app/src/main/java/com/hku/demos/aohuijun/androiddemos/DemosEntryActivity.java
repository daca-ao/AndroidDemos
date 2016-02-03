package com.hku.demos.aohuijun.androiddemos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class DemosEntryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demos_entry);

        Button textShareButton = (Button) findViewById(R.id.button_entry_text_sharing);
        Button appsCenterButton = (Button) findViewById(R.id.button_entry_apps_center);
        Button selectableGridViewButton = (Button) findViewById(R.id.button_entry_selectable_grid_view);
        Button loadableListViewButton = (Button) findViewById(R.id.button_entry_loadable_list_view);
        Button floatWindowButton = (Button) findViewById(R.id.button_entry_float_window);
        Button memosButton = (Button) findViewById(R.id.button_entry_memos);

        textShareButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.TEXT_SHARE));
        appsCenterButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.APPS_CENTER));
        selectableGridViewButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.SELECTABLE_GRID_VIEW));
        loadableListViewButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.LOADABLE_LIST_VIEW));
        floatWindowButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.FLOAT_WINDOW));
        memosButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.MEMOS));
    }
}
