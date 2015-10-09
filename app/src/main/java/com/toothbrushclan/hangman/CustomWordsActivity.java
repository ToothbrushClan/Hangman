package com.toothbrushclan.hangman;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.toothbrushclan.hangman.categories.Question;
import com.toothbrushclan.hangman.utilities.CustomWordsBaseAdapter;
import com.toothbrushclan.hangman.utilities.HangmanSQLiteHelper;

/**
 * Created by ushaikh on 07/10/15.
 */
public class CustomWordsActivity extends Activity implements View.OnClickListener, CustomWordsBaseAdapter.CustomButtonListener {

    Button addWord;
    Button addWordDialog;
    EditText question;
    EditText hint;
    Spinner difficulty;
    ListView customWordsList;
    CustomWordsBaseAdapter customWordsBaseAdapter;
    HangmanSQLiteHelper db;
    Dialog dialog;
    Question questionArg;
    int maxCustomWords = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_custom_words);
        init();
    }

    private void init() {
        db = new HangmanSQLiteHelper(this);
        Question[] questions = db.getQuestions(getResources().getString(R.string.custom));
        customWordsBaseAdapter = new CustomWordsBaseAdapter(this, getLayoutInflater(), questions);
        customWordsBaseAdapter.setCustomButtonListener(this);
        customWordsList = (ListView) findViewById(R.id.customWordsList);
        customWordsList.setAdapter(customWordsBaseAdapter);
        customWordsList.setEmptyView(findViewById(R.id.empty));
        addWord = (Button) findViewById(R.id.addCustomWords);
        if ( customWordsBaseAdapter.getCount() >= maxCustomWords ) {
            addWord.setEnabled(false);
        } else {
            addWord.setEnabled(true);
        }
        addWord.setOnClickListener(this);
    }

    private void updateAdapter() {
        Question[] questions = db.getQuestions(getResources().getString(R.string.custom));
        customWordsBaseAdapter.updateDate(questions);
        if ( customWordsBaseAdapter.getCount() >= maxCustomWords ) {
            addWord.setEnabled(false);
        } else {
            addWord.setEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addCustomWords :
                showCustomDialog(null);
                break;
            case R.id.addWordButton :
                dialogButtonClick();
                break;
        }
    }

    private void showCustomDialog(Question questionArg) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_word_dialog);
        dialog.setCancelable(false);
        this.questionArg = questionArg;

        addWordDialog = (Button) dialog.findViewById(R.id.addWordButton);
        addWordDialog.setBackgroundColor(Color.TRANSPARENT);
        question = (EditText) dialog.findViewById(R.id.questionEditText);
        hint = (EditText) dialog.findViewById(R.id.hintEditText);
        difficulty = (Spinner) dialog.findViewById(R.id.spinnerDifficultyLevel);
        String[] items = new String[]{"Easy", "Medium", "Hard", "Extreme"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        difficulty.setAdapter(adapter);
        dialog.setTitle("Add Word");

        if ( questionArg != null ) {
            question.setText(questionArg.getQuestion());
            hint.setText(questionArg.getHint());
            difficulty.setSelection(questionArg.getDifficulty() - 1);
            addWordDialog.setText("Edit Word");
            dialog.setTitle("Edit Word");
        }

        addWordDialog.setOnClickListener(this);
        dialog.show();
    }

    public void dialogButtonClick() {
        Question questionObject = new Question();
        questionObject.setQuestion(question.getText().toString().toLowerCase());
        questionObject.setHint(hint.getText().toString());
        questionObject.setDifficulty(difficulty.getSelectedItemPosition() + 1);
        questionObject.setCategory(getResources().getString(R.string.custom));
        if ( questionArg != null ) {
            questionObject.setId(questionArg.getId());
            questionObject.setCategory(questionArg.getCategory());
            db.editQuestion(questionObject);
        } else {
            db.addQuestion(questionObject);
        }
        updateAdapter();
        customWordsBaseAdapter.notifyDataSetChanged();
        dialog.dismiss();
    }

    @Override
    public void onButtonClickListener(View view, int position, Question question) {
        switch (view.getId()) {
            case R.id.customEdit :
                showCustomDialog(question);
                break;
            case R.id.customDelete :
                db.deleteQuestion(question);
                updateAdapter();
                customWordsBaseAdapter.notifyDataSetChanged();
                break;
        }
    }
}
