package com.toothbrushclan.hangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by ushaikh on 03/10/15.
 */
public class SelectCategoryActivity extends Activity implements View.OnClickListener {

    Button buttonKids;
    Button buttonCars;
    Button buttonPlaces;
    Button buttonHistory;
    Button buttonSpace;
    Button buttonAnimals;
    Button buttonPolitics;
    Button buttonFood;
    Button buttonSports;
    Button buttonDictionary;
    Button buttonCustom1;
    Button buttonCustom2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout is right
        setContentView(R.layout.activity_select_category);

        buttonKids = (Button) findViewById(R.id.categoryKids);
        buttonCars = (Button) findViewById(R.id.categoryCars);
        buttonPlaces = (Button) findViewById(R.id.categoryPlaces);
        buttonHistory = (Button) findViewById(R.id.categoryHistory);
        buttonSpace = (Button) findViewById(R.id.categorySpace);
        buttonAnimals = (Button) findViewById(R.id.categoryAnimals);
        buttonPolitics = (Button) findViewById(R.id.categoryPolitics);
        buttonFood = (Button) findViewById(R.id.categoryFood);
        buttonSports = (Button) findViewById(R.id.categorySports);
        buttonDictionary = (Button) findViewById(R.id.categoryDictionary);
        buttonCustom1 = (Button) findViewById(R.id.categoryCustom1);
        buttonCustom2 = (Button) findViewById(R.id.categoryCustom2);
        buttonKids.setOnClickListener(this);
        buttonCars.setOnClickListener(this);
        buttonPlaces.setOnClickListener(this);
        buttonHistory.setOnClickListener(this);
        buttonSpace.setOnClickListener(this);
        buttonAnimals.setOnClickListener(this);
        buttonPolitics.setOnClickListener(this);
        buttonFood.setOnClickListener(this);
        buttonSports.setOnClickListener(this);
        buttonDictionary.setOnClickListener(this);
        buttonCustom1.setOnClickListener(this);
        buttonCustom2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button buttonTemp = (Button) v;
        String category = (String) buttonTemp.getText();
        Intent categoryIntent = new Intent(this, HangmanActivity.class);
        categoryIntent.putExtra("categoryType", category);
        startActivity(categoryIntent);
    }
}
