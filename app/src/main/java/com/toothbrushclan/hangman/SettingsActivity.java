package com.toothbrushclan.hangman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.toothbrushclan.hangman.utilities.HangmanSQLiteHelper;
import com.toothbrushclan.hangman.utilities.Settings;

/**
 * Created by ushaikh on 06/10/15.
 */
public class SettingsActivity extends Activity {

    Settings settings;
    Switch showHintsSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    private void init() {
        settings = new Settings(this);
        showHintsSwitch = (Switch) findViewById(R.id.enableHints);
        if (settings.getSetting(getResources().getString(R.string.dbShowHints)).equals(getResources().getString(R.string.dbTrue))) {
            showHintsSwitch.setChecked(true);
        } else {
            showHintsSwitch.setChecked(false);
        }
        showHintsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    settings.setSetting(getResources().getString(R.string.dbShowHints), getResources().getString(R.string.dbTrue));
                }else{
                    settings.setSetting(getResources().getString(R.string.dbShowHints), getResources().getString(R.string.dbFalse));
                }
            }
        });
    }

}
