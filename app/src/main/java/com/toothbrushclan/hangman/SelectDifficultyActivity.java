package com.toothbrushclan.hangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by ushaikh on 03/10/15.
 */
public class SelectDifficultyActivity extends Activity implements View.OnClickListener {

    Button buttonEasy;
    Button buttonMedium;
    Button buttonHard;
    Button buttonExtreme;
    String categoryType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout is right
        setContentView(R.layout.activity_select_difficulty);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            categoryType = bundle.getString("categoryType");
        }

        buttonEasy = (Button) findViewById(R.id.difficultyEasy);
        buttonMedium = (Button) findViewById(R.id.difficultyMedium);
        buttonHard = (Button) findViewById(R.id.difficultyHard);
        buttonExtreme = (Button) findViewById(R.id.difficultyExtreme);
        buttonEasy.setOnClickListener(this);
        buttonMedium.setOnClickListener(this);
        buttonHard.setOnClickListener(this);
        buttonExtreme.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button buttonTemp = (Button) v;
        String difficulty = (String) buttonTemp.getText();
        int difficultyId = 1;
        switch (difficulty) {
            case "Easy":
                difficultyId = 1;
                break;
            case "Medium":
                difficultyId = 2;
                break;
            case "Hard":
                difficultyId = 3;
                break;
            case "Extreme":
                difficultyId = 4;
                break;
            default:
                difficultyId = 1;
        }
        Intent difficultyIntent = new Intent(this, HangmanActivity.class);
        difficultyIntent.putExtra("categoryType", categoryType);
        difficultyIntent.putExtra("difficultyId", difficultyId);
        startActivity(difficultyIntent);
    }
}
