package com.example.testapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String currentTheme = sharedPref.getString(KEY_CURRENT_THEME, LIGHT_THEME);

        Switch darkTheme = findViewById(R.id.darkTheme);
        darkTheme.setChecked(currentTheme.equals(DARK_THEME));
        darkTheme.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            sharedPref.edit().putString(KEY_CURRENT_THEME, DARK_THEME).apply();
        } else {
            sharedPref.edit().putString(KEY_CURRENT_THEME, LIGHT_THEME).apply();
        }
        recreate();
    }
}