package com.toothbrushclan.hangman;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.toothbrushclan.hangman.categories.Category;
import com.toothbrushclan.hangman.categories.Kids;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView question;
    TextView hint;
    Button buttonQ;
    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = (TextView) findViewById(R.id.question);
        hint = (TextView) findViewById(R.id.hint);
        buttonQ = (Button) findViewById(R.id.keyQ);
        buttonQ.setOnClickListener(this);
        category = new Kids(super.getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String questionLine = category.getNextQuestion();
        if (questionLine == null ) {
            question.setText("question");
            hint.setText("hint");
        } else {
            String[] strQuestion =  questionLine.split(";");
            String guess = strQuestion[0];
            String displayQuestion = guess.replaceAll("[^\\s]","_ ");
            question.setText(displayQuestion);
            hint.setText(strQuestion[1]);
        }
    }
}
