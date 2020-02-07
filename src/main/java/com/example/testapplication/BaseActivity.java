package com.example.testapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private String currentTheme;
    private SharedPreferences sharedPref;

    String KEY_CURRENT_THEME = "current_theme";
    String LIGHT_THEME = "light";
    String DARK_THEME = "dark";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        currentTheme = sharedPref.getString(KEY_CURRENT_THEME, LIGHT_THEME);
        setAppTheme(currentTheme);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String selectedTheme = sharedPref.getString(KEY_CURRENT_THEME, LIGHT_THEME);
        if (!currentTheme.equals(selectedTheme)) {
            recreate();
        }
    }

    private void setAppTheme(String currentTheme) {
        if (currentTheme.equals(DARK_THEME)) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
    }
}