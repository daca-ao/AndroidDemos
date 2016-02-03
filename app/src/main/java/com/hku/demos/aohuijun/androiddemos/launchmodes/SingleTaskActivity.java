package com.hku.demos.aohuijun.androiddemos.launchmodes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hku.demos.aohuijun.androiddemos.MyApp;
import com.hku.demos.aohuijun.androiddemos.OnModuleSelectListener;

import com.hku.demos.aohuijun.androiddemos.R;

public class SingleTaskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

        Button standardButton = (Button) findViewById(R.id.navigation_ac3_to_ac1);
        Button singleTopButton = (Button) findViewById(R.id.navigation_ac3_to_ac2);
        Button singleInstanceButton = (Button) findViewById(R.id.navigation_ac3_to_ac4);
        Button singleTaskButton = (Button) findViewById(R.id.navigation_ac3_to_ac3);
        standardButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.STANDARD));
        singleTopButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.SINGLE_TOP));
        singleInstanceButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.SINGLE_INSTANCE));
        singleTaskButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.SINGLE_TASK));

        Button showIDButton = (Button) findViewById(R.id.button_show_id_3);
        showIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        getSystemService(Context.ACTIVITY_SERVICE).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
