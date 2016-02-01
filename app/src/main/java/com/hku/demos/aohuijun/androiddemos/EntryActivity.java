package com.hku.demos.aohuijun.androiddemos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class EntryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        Button basicsEntryButton = (Button) findViewById(R.id.button_entry_basics);
        Button demosEntryButton = (Button) findViewById(R.id.button_entry_demos);
        basicsEntryButton.setOnClickListener(new OnModuleSelectedListener(this, MyApp.ENTRY_BASICS));
        demosEntryButton.setOnClickListener(new OnModuleSelectedListener(this, MyApp.ENTRY_DEMOS));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
