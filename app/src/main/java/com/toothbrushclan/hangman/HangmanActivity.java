package com.toothbrushclan.hangman;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.toothbrushclan.hangman.categories.Category;
import com.toothbrushclan.hangman.utilities.AllDoneDialog;
import com.toothbrushclan.hangman.utilities.ConfirmationDialog;
import com.toothbrushclan.hangman.utilities.CongratulationsDialog;
import com.toothbrushclan.hangman.utilities.FailureDialog;
import com.toothbrushclan.hangman.utilities.HangmanSQLiteHelper;
import com.toothbrushclan.hangman.utilities.QuestionValidator;

import java.util.Map;

public class HangmanActivity extends Activity implements View.OnClickListener, FailureDialog.FailureDialogListener, CongratulationsDialog.CongratulationsDialogListener, AllDoneDialog.AllDoneDialogListener, ConfirmationDialog.ConfirmationDialogListener {

    TextView textViewCategory;
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
    ImageView hangmanImage;
    Category categoryObject;
    String category;
    String question;
    String hint;
    String maskedQuestion;
    String baseRegex = "[^\\s]";
    String regex = "";
    String categoryType;
    int maxTryCount = 8;
    int tryCount = 0;
    QuestionValidator questionValidator;
    int defaultTextColor;
    Drawable defaultButtonDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            categoryType = bundle.getString("categoryType");
        }
        init();
    }

    private void init() {
        questionValidator = new QuestionValidator();
        textViewCategory = (TextView) findViewById(R.id.category);
        textViewQuestion = (TextView) findViewById(R.id.question);
        textViewHint = (TextView) findViewById(R.id.hint);
        hangmanImage = (ImageView) findViewById(R.id.animation);
        defaultTextColor = textViewQuestion.getTextColors().getDefaultColor();
        setCategory(categoryType);
        findAndInitializeButtons();
        getNextQuestion();
    }

    private void setCategory(String categoryType) {
        String categoryCode;
        switch (categoryType) {
            case "Kids" :
                categoryCode = "kids";
                break;
            case "Cars" :
                categoryCode = "cars";
                break;
            case "Places" :
                categoryCode = "places";
                break;
            case "History" :
                categoryCode = "history";
                break;
            case "Space" :
                categoryCode = "space";
                break;
            case "Animals" :
                categoryCode = "animals";
                break;
            case "Politics" :
                categoryCode = "politics";
                break;
            case "Food" :
                categoryCode = "food";
                break;
            case "Sports" :
                categoryCode = "sports";
                break;
            case "Dictionary" :
                categoryCode = "dictionary";
                break;
            case "Custom 1" :
                categoryCode = "custom1";
                break;
            case "Custom 2" :
                categoryCode = "custom2";
                break;
            default :
                categoryCode = "random";
        }
        categoryObject = new Category(super.getApplicationContext(), categoryCode, 1);
    }

    private void getNextQuestion() {
        tryCount = 0;
        regex = baseRegex;
        if (categoryObject.getNextQuestionFromDb()){
            category = categoryObject.getCategory();
            question = categoryObject.getQuestion().toUpperCase();
            hint = categoryObject.getHint();
        } else {
            category = "All Done";
            question = "QUESTION";
            hint = "Nothing to see here";
            showAllDoneDialog();
        }
        hangmanImage.setImageResource(R.drawable.hangman1);
        maskedQuestion = questionValidator.getMaskedQuestion(question, regex);
        textViewCategory.setText(getResources().getString(R.string.category) + category);
        textViewQuestion.setText(maskedQuestion);
        textViewHint.setText(hint);
        enableAllKeys();
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
        defaultButtonDrawable = buttonA.getBackground();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                backKeyHandler();
                return true;
//            case R.id.action_settings :
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            default:
                Button buttonTemp = (Button) v;
                String selectedChar = (String) buttonTemp.getText();
                regex = questionValidator.updateRegex(selectedChar, regex);
                if (questionValidator.isCharacterPresent(selectedChar, question)) {
                    maskedQuestion = questionValidator.getMaskedQuestion(question, regex);
                    textViewQuestion.setText(maskedQuestion);
                    buttonTemp.setTextColor(Color.GREEN);
                    if (questionValidator.isQuestionComplete(maskedQuestion)) {
                        disableAllKeys();
                        showCongratulationsDialog();
                    }
                } else {
                    buttonTemp.setTextColor(Color.RED);
                    tryCount++;
                    switch (tryCount) {
                        case 0 :
                            hangmanImage.setImageResource(R.drawable.hangman1);
                            break;
                        case 1 :
                            hangmanImage.setImageResource(R.drawable.hangman2);
                            break;
                        case 2 :
                            hangmanImage.setImageResource(R.drawable.hangman3);
                            break;
                        case 3 :
                            hangmanImage.setImageResource(R.drawable.hangman4);
                            break;
                        case 4 :
                            hangmanImage.setImageResource(R.drawable.hangman5);
                            break;
                        case 5 :
                            hangmanImage.setImageResource(R.drawable.hangman6);
                            break;
                        case 6 :
                            hangmanImage.setImageResource(R.drawable.hangman7);
                            break;
                        case 7 :
                            hangmanImage.setImageResource(R.drawable.hangman8);
                            break;
                        case 8 :
                            hangmanImage.setImageResource(R.drawable.hangman9);
                            break;
                        default :
                            hangmanImage.setImageResource(R.drawable.hangman9);
                    }
                    if (tryCount == maxTryCount) {
                        textViewQuestion.setText(question);
                        disableAllKeys();
                        showFailureDialog();
                    }
                }

                buttonTemp.setBackgroundColor(Color.TRANSPARENT);
                buttonTemp.setEnabled(false);
        }
    }

    private void showFailureDialog() {
        DialogFragment dialog = new FailureDialog();
        dialog.show(getFragmentManager(),"failure");
    }

    private void showCongratulationsDialog() {
        DialogFragment dialog = new CongratulationsDialog();
        dialog.show(getFragmentManager(),"congratulations");
    }

    private void showAllDoneDialog() {
        DialogFragment dialog = new AllDoneDialog();
        dialog.show(getFragmentManager(),"allDone");
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
        buttonA.setBackground(defaultButtonDrawable);
        buttonB.setBackground(defaultButtonDrawable);
        buttonC.setBackground(defaultButtonDrawable);
        buttonD.setBackground(defaultButtonDrawable);
        buttonE.setBackground(defaultButtonDrawable);
        buttonF.setBackground(defaultButtonDrawable);
        buttonG.setBackground(defaultButtonDrawable);
        buttonH.setBackground(defaultButtonDrawable);
        buttonI.setBackground(defaultButtonDrawable);
        buttonJ.setBackground(defaultButtonDrawable);
        buttonK.setBackground(defaultButtonDrawable);
        buttonL.setBackground(defaultButtonDrawable);
        buttonM.setBackground(defaultButtonDrawable);
        buttonN.setBackground(defaultButtonDrawable);
        buttonO.setBackground(defaultButtonDrawable);
        buttonP.setBackground(defaultButtonDrawable);
        buttonQ.setBackground(defaultButtonDrawable);
        buttonR.setBackground(defaultButtonDrawable);
        buttonS.setBackground(defaultButtonDrawable);
        buttonT.setBackground(defaultButtonDrawable);
        buttonU.setBackground(defaultButtonDrawable);
        buttonV.setBackground(defaultButtonDrawable);
        buttonW.setBackground(defaultButtonDrawable);
        buttonX.setBackground(defaultButtonDrawable);
        buttonY.setBackground(defaultButtonDrawable);
        buttonZ.setBackground(defaultButtonDrawable);
        buttonA.setTextColor(defaultTextColor);
        buttonB.setTextColor(defaultTextColor);
        buttonC.setTextColor(defaultTextColor);
        buttonD.setTextColor(defaultTextColor);
        buttonE.setTextColor(defaultTextColor);
        buttonF.setTextColor(defaultTextColor);
        buttonG.setTextColor(defaultTextColor);
        buttonH.setTextColor(defaultTextColor);
        buttonI.setTextColor(defaultTextColor);
        buttonJ.setTextColor(defaultTextColor);
        buttonK.setTextColor(defaultTextColor);
        buttonL.setTextColor(defaultTextColor);
        buttonM.setTextColor(defaultTextColor);
        buttonN.setTextColor(defaultTextColor);
        buttonO.setTextColor(defaultTextColor);
        buttonP.setTextColor(defaultTextColor);
        buttonQ.setTextColor(defaultTextColor);
        buttonR.setTextColor(defaultTextColor);
        buttonS.setTextColor(defaultTextColor);
        buttonT.setTextColor(defaultTextColor);
        buttonU.setTextColor(defaultTextColor);
        buttonV.setTextColor(defaultTextColor);
        buttonW.setTextColor(defaultTextColor);
        buttonX.setTextColor(defaultTextColor);
        buttonY.setTextColor(defaultTextColor);
        buttonZ.setTextColor(defaultTextColor);
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

    @Override
    public void onDialogNextClick(DialogFragment dialog) {
        getNextQuestion();
    }

    @Override
    public void onDialogBackClick(DialogFragment dialog) {
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backKeyHandler();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backKeyHandler() {
        if ( ! regex.equals(baseRegex)) {
            showConfirmationDialog();
        } else {
            super.onBackPressed();
        }
    }

    private void showConfirmationDialog() {
        DialogFragment dialog = new ConfirmationDialog();
        dialog.show(getFragmentManager(),"confirmation");
    }

    @Override
    public void onDialogYesClick(DialogFragment dialog) {
        super.onBackPressed();
    }

    @Override
    public void onDialogNoClick(DialogFragment dialog) {
        // Do nothing
    }

}
