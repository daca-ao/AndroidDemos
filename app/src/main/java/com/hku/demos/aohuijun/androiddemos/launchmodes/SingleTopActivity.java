package com.hku.demos.aohuijun.androiddemos.launchmodes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hku.demos.aohuijun.androiddemos.MyApp;
import com.hku.demos.aohuijun.androiddemos.OnModuleSelectedListener;

import com.hku.demos.aohuijun.androiddemos.R;

public class SingleTopActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);

        Button standardButton = (Button) findViewById(R.id.navigation_ac2_to_ac1);
        Button singleTaskButton = (Button) findViewById(R.id.navigation_ac2_to_ac3);
        Button singleInstanceButton = (Button) findViewById(R.id.navigation_ac2_to_ac4);
        Button singleTopButton = (Button) findViewById(R.id.navigation_ac2_to_ac2);
        standardButton.setOnClickListener(new OnModuleSelectedListener(this, MyApp.STANDARD));
        singleTaskButton.setOnClickListener(new OnModuleSelectedListener(this, MyApp.SINGLE_TASK));
        singleInstanceButton.setOnClickListener(new OnModuleSelectedListener(this, MyApp.SINGLE_INSTANCE));
        singleTopButton.setOnClickListener(new OnModuleSelectedListener(this, MyApp.SINGLE_TOP));

        Button showIDButton = (Button) findViewById(R.id.button_show_id_2);
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
