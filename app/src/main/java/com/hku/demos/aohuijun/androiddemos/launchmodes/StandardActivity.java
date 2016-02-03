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

public class StandardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        Button singleTopButton = (Button) findViewById(R.id.navigation_ac1_to_ac2);
        Button singleTaskButton = (Button) findViewById(R.id.navigation_ac1_to_ac3);
        Button singleInstanceButton = (Button) findViewById(R.id.navigation_ac1_to_ac4);
        Button standardButton = (Button) findViewById(R.id.navigation_ac1_to_ac1);
        singleTopButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.SINGLE_TOP));
        singleTaskButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.SINGLE_TASK));
        singleInstanceButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.SINGLE_INSTANCE));
        standardButton.setOnClickListener(new OnModuleSelectListener(this, MyApp.STANDARD));

        Button showIDButton = (Button) findViewById(R.id.button_show_id_1);
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
