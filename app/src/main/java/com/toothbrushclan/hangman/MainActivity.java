package com.toothbrushclan.hangman;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.toothbrushclan.hangman.categories.Category;
import com.toothbrushclan.hangman.categories.Kids;
import com.toothbrushclan.hangman.utilities.QuestionValidator;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView textViewQuestion;
    TextView textViewHint;
    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    Button buttonE;
    Button buttonF;
    Button buttonG;
    Button buttonH;
    Button buttonI;
    Button buttonJ;
    Button buttonK;
    Button buttonL;
    Button buttonM;
    Button buttonN;
    Button buttonO;
    Button buttonP;
    Button buttonQ;
    Button buttonR;
    Button buttonS;
    Button buttonT;
    Button buttonU;
    Button buttonV;
    Button buttonW;
    Button buttonX;
    Button buttonY;
    Button buttonZ;
    Category category;
    String question;
    String hint;
    String maskedQuestion;
    String baseRegex = "[^\\s]";
    String regex = "";
    int maxTryCount = 8;
    int tryCount = 0;
    QuestionValidator questionValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewQuestion = (TextView) findViewById(R.id.question);
        textViewHint = (TextView) findViewById(R.id.hint);
        findAndInitializeButtons();
        category = new Kids(super.getApplicationContext());
        questionValidator = new QuestionValidator();
        regex = baseRegex;
        String questionLine = category.getNextQuestion();
        if (questionLine == null ) {
            textViewQuestion.setText("question");
            textViewHint.setText("hint");
        } else {
            String[] strQuestion =  questionLine.split(";");
            question = strQuestion[0];
            hint = strQuestion[1];
            maskedQuestion = questionValidator.getMaskedQuestion(question, regex);
            textViewQuestion.setText(maskedQuestion);
            textViewHint.setText(hint);
        }
    }

    private void findAndInitializeButtons() {
        buttonA = (Button) findViewById(R.id.keyA);
        buttonB = (Button) findViewById(R.id.keyB);
        buttonC = (Button) findViewById(R.id.keyC);
        buttonD = (Button) findViewById(R.id.keyD);
        buttonE = (Button) findViewById(R.id.keyE);
        buttonF = (Button) findViewById(R.id.keyF);
        buttonG = (Button) findViewById(R.id.keyG);
        buttonH = (Button) findViewById(R.id.keyH);
        buttonI = (Button) findViewById(R.id.keyI);
        buttonJ = (Button) findViewById(R.id.keyJ);
        buttonK = (Button) findViewById(R.id.keyK);
        buttonL = (Button) findViewById(R.id.keyL);
        buttonM = (Button) findViewById(R.id.keyM);
        buttonN = (Button) findViewById(R.id.keyN);
        buttonO = (Button) findViewById(R.id.keyO);
        buttonP = (Button) findViewById(R.id.keyP);
        buttonQ = (Button) findViewById(R.id.keyQ);
        buttonR = (Button) findViewById(R.id.keyR);
        buttonS = (Button) findViewById(R.id.keyS);
        buttonT = (Button) findViewById(R.id.keyT);
        buttonU = (Button) findViewById(R.id.keyU);
        buttonV = (Button) findViewById(R.id.keyV);
        buttonW = (Button) findViewById(R.id.keyW);
        buttonX = (Button) findViewById(R.id.keyX);
        buttonY = (Button) findViewById(R.id.keyY);
        buttonZ = (Button) findViewById(R.id.keyZ);
        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        buttonE.setOnClickListener(this);
        buttonF.setOnClickListener(this);
        buttonG.setOnClickListener(this);
        buttonH.setOnClickListener(this);
        buttonI.setOnClickListener(this);
        buttonJ.setOnClickListener(this);
        buttonK.setOnClickListener(this);
        buttonL.setOnClickListener(this);
        buttonM.setOnClickListener(this);
        buttonN.setOnClickListener(this);
        buttonO.setOnClickListener(this);
        buttonP.setOnClickListener(this);
        buttonQ.setOnClickListener(this);
        buttonR.setOnClickListener(this);
        buttonS.setOnClickListener(this);
        buttonT.setOnClickListener(this);
        buttonU.setOnClickListener(this);
        buttonV.setOnClickListener(this);
        buttonW.setOnClickListener(this);
        buttonX.setOnClickListener(this);
        buttonY.setOnClickListener(this);
        buttonZ.setOnClickListener(this);
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
        // Log.i("View toString: ", v.toString());
        Button buttonTemp = (Button) v;
        String selectedChar = (String) buttonTemp.getText();
        if (questionValidator.isCharacterPresent(selectedChar, question)) {
            regex = questionValidator.updateRegex(selectedChar, regex);
            maskedQuestion = questionValidator.getMaskedQuestion(question, regex);
            textViewQuestion.setText(maskedQuestion);
            buttonTemp.setTextColor(Color.GREEN);
            if (questionValidator.isQuestionComplete(maskedQuestion)) {
                textViewHint.setText("CONGRATULATIONS!!!");
                disableAllKeys();
            }
        } else {
            buttonTemp.setTextColor(Color.RED);
            tryCount++;
            if (tryCount == maxTryCount) {
                textViewHint.setText("FAIL!!! :(");
                disableAllKeys();
            }
        }

        buttonTemp.setBackgroundColor(Color.TRANSPARENT);
        buttonTemp.setEnabled(false);

        // Log.i("View Text: ", (String) getText(v.getId()));

        // Log.i("View Text: ",v.get);
//        if (v.getAccessibilityClassName().equals("android.widget.Button")) {
//
//        }

    }

    private void enableAllKeys() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
        buttonE.setEnabled(true);
        buttonF.setEnabled(true);
        buttonG.setEnabled(true);
        buttonH.setEnabled(true);
        buttonI.setEnabled(true);
        buttonJ.setEnabled(true);
        buttonK.setEnabled(true);
        buttonL.setEnabled(true);
        buttonM.setEnabled(true);
        buttonN.setEnabled(true);
        buttonO.setEnabled(true);
        buttonP.setEnabled(true);
        buttonQ.setEnabled(true);
        buttonR.setEnabled(true);
        buttonS.setEnabled(true);
        buttonT.setEnabled(true);
        buttonU.setEnabled(true);
        buttonV.setEnabled(true);
        buttonW.setEnabled(true);
        buttonX.setEnabled(true);
        buttonY.setEnabled(true);
        buttonZ.setEnabled(true);
    }
    private void disableAllKeys() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        buttonE.setEnabled(false);
        buttonF.setEnabled(false);
        buttonG.setEnabled(false);
        buttonH.setEnabled(false);
        buttonI.setEnabled(false);
        buttonJ.setEnabled(false);
        buttonK.setEnabled(false);
        buttonL.setEnabled(false);
        buttonM.setEnabled(false);
        buttonN.setEnabled(false);
        buttonO.setEnabled(false);
        buttonP.setEnabled(false);
        buttonQ.setEnabled(false);
        buttonR.setEnabled(false);
        buttonS.setEnabled(false);
        buttonT.setEnabled(false);
        buttonU.setEnabled(false);
        buttonV.setEnabled(false);
        buttonW.setEnabled(false);
        buttonX.setEnabled(false);
        buttonY.setEnabled(false);
        buttonZ.setEnabled(false);
        buttonA.setBackgroundColor(Color.TRANSPARENT);
        buttonB.setBackgroundColor(Color.TRANSPARENT);
        buttonC.setBackgroundColor(Color.TRANSPARENT);
        buttonD.setBackgroundColor(Color.TRANSPARENT);
        buttonE.setBackgroundColor(Color.TRANSPARENT);
        buttonF.setBackgroundColor(Color.TRANSPARENT);
        buttonG.setBackgroundColor(Color.TRANSPARENT);
        buttonH.setBackgroundColor(Color.TRANSPARENT);
        buttonI.setBackgroundColor(Color.TRANSPARENT);
        buttonJ.setBackgroundColor(Color.TRANSPARENT);
        buttonK.setBackgroundColor(Color.TRANSPARENT);
        buttonL.setBackgroundColor(Color.TRANSPARENT);
        buttonM.setBackgroundColor(Color.TRANSPARENT);
        buttonN.setBackgroundColor(Color.TRANSPARENT);
        buttonO.setBackgroundColor(Color.TRANSPARENT);
        buttonP.setBackgroundColor(Color.TRANSPARENT);
        buttonQ.setBackgroundColor(Color.TRANSPARENT);
        buttonR.setBackgroundColor(Color.TRANSPARENT);
        buttonS.setBackgroundColor(Color.TRANSPARENT);
        buttonT.setBackgroundColor(Color.TRANSPARENT);
        buttonU.setBackgroundColor(Color.TRANSPARENT);
        buttonV.setBackgroundColor(Color.TRANSPARENT);
        buttonW.setBackgroundColor(Color.TRANSPARENT);
        buttonX.setBackgroundColor(Color.TRANSPARENT);
        buttonY.setBackgroundColor(Color.TRANSPARENT);
        buttonZ.setBackgroundColor(Color.TRANSPARENT);
    }
}
