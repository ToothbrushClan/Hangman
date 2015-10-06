package com.toothbrushclan.hangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.toothbrushclan.hangman.utilities.HangmanSQLiteHelper;

import java.util.Map;

/**
 * Created by ushaikh on 04/10/15.
 */
public class StartGameActivity extends Activity implements View.OnClickListener {

    Button buttonPlayGame;
    Button buttonSettings;
    Button buttonAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        init();
    }

    private void init() {
        buttonPlayGame = (Button) findViewById(R.id.playGame);
        buttonPlayGame.setOnClickListener(this);
        buttonSettings = (Button) findViewById(R.id.settings);
        buttonSettings.setOnClickListener(this);
        buttonAboutUs = (Button) findViewById(R.id.aboutUs);
        buttonAboutUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playGame :
                Intent playGameIntent = new Intent(this, SelectCategoryActivity.class);
                startActivity(playGameIntent);
                break;
            case R.id.settings :
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            case R.id.aboutUs :
                Intent aboutUsIntent = new Intent(this, AboutUsActivity.class);
                startActivity(aboutUsIntent);
                break;
            default :

        }
    }
}
