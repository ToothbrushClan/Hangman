package com.toothbrushclan.hangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.toothbrushclan.hangman.utilities.HangmanSQLiteHelper;
import com.toothbrushclan.hangman.utilities.Settings;

/**
 * Created by ushaikh on 06/10/15.
 */
public class SettingsActivity extends Activity implements View.OnClickListener{

    Settings settings;
    Switch showHintsSwitch;
    Button addCustomWords;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    private void init() {
        settings = new Settings(this);
        showHintsSwitch = (Switch) findViewById(R.id.enableHints);
        addCustomWords = (Button) findViewById(R.id.addCustomWords);
        addCustomWords.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addCustomWords :
                Intent addCustomWordsIntent = new Intent(this, CustomWordsActivity.class);
                startActivity(addCustomWordsIntent);
                break;
        }
    }
}
