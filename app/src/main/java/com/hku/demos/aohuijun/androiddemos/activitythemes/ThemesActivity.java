package com.hku.demos.aohuijun.androiddemos.activitythemes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hku.demos.aohuijun.androiddemos.R;

public class ThemesActivity extends Activity {

    private static final String THEME = "theme";
    private static final int THEME_LIGHT = 0;
    private static final int THEME_DARK = 1;
    private static final int THEME_FULL = 2;
    private static final int THEME_NO_ACTION_BAR = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(getIntent().getIntExtra(THEME, -1));
        setContentView(R.layout.activity_themes);
        Button lightActionBarButton = (Button) findViewById(R.id.button_light_action_bar);
        Button darkActionBarButton = (Button) findViewById(R.id.button_dark_action_bar);
        Button fullScreenButton = (Button) findViewById(R.id.button_full_screen);
        Button noActionBarButton = (Button) findViewById(R.id.button_no_action_bar);
        lightActionBarButton.setOnClickListener(new OnThemeSelectListener(THEME_LIGHT));
        darkActionBarButton.setOnClickListener(new OnThemeSelectListener(THEME_DARK));
        fullScreenButton.setOnClickListener(new OnThemeSelectListener(THEME_FULL));
        noActionBarButton.setOnClickListener(new OnThemeSelectListener(THEME_NO_ACTION_BAR));
    }

    private class OnThemeSelectListener implements View.OnClickListener {
        private int themeType;

        public OnThemeSelectListener(int type) {
            this.themeType = type;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (themeType) {
                case THEME_LIGHT:
                    intent.putExtra(THEME, R.style.AppsCenterTheme);
                    break;
                case THEME_DARK:
                    intent.putExtra(THEME, R.style.DarkActionBarTheme);
                    break;
                case THEME_FULL:
                    intent.putExtra(THEME, R.style.FullScreenNoActionBarTheme);
                    break;
                case THEME_NO_ACTION_BAR:
                    intent.putExtra(THEME, R.style.NoActionBarTheme);
                    break;
                default:
                    break;
            }
            intent.setClass(ThemesActivity.this, ThemesActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
