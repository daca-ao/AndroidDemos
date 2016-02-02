package com.hku.demos.aohuijun.androiddemos.textsharing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hku.demos.aohuijun.androiddemos.R;

public class TextSharingActivity extends Activity {

    private TextView mSharedTextView;
    private EditText mInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_sharing);

        //  Set shared text
        mSharedTextView = (TextView) findViewById(R.id.shared_text);
        String action = getIntent().getAction();
        String type = getIntent().getType();
        if (type != null && Intent.ACTION_SEND.equals(action)) {
            if (type.startsWith("text/")) {
                String mShowText = getIntent().getStringExtra(Intent.EXTRA_TEXT);
                mSharedTextView.setText(mShowText);
            }
        }

        //  Share text to other apps
        mInputText = (EditText) findViewById(R.id.shared_input);
        Button mButton = (Button) findViewById(R.id.shared_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInputText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.notification_empty_input),
                            Toast.LENGTH_SHORT).show();
                } else {
                    mSharedTextView.setText(mInputText.getText());
                    mInputText.setText(null);
                }
            }
        });
        mSharedTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mSharedTextView.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.notification_empty_sharing),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent sharedIntent = new Intent(Intent.ACTION_SEND);
                    sharedIntent.putExtra(Intent.EXTRA_TEXT, mSharedTextView.getText().toString());
                    sharedIntent.setType("text/plain");
                    Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_SHORT).show();
                    if (sharedIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(Intent.createChooser(sharedIntent,
                                getResources().getString(R.string.title_shared_target)));
                    }
                }
                return false;
            }
        });

    }
}
