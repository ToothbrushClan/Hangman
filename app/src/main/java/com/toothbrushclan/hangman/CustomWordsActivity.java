package com.toothbrushclan.hangman;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
    EditText difficulty;
    ListView customWordsList;
    CustomWordsBaseAdapter customWordsBaseAdapter;
    HangmanSQLiteHelper db;

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
        addWord.setOnClickListener(this);
    }

    private void updateAdapter() {
        Question[] questions = db.getQuestions(getResources().getString(R.string.custom));
        customWordsBaseAdapter.updateDate(questions);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addCustomWords :
                showCustomDialog(null);
                break;
        }
    }

    private void showCustomDialog(final Question questionArg) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_word_dialog);
        dialog.setCancelable(false);

        addWordDialog = (Button) dialog.findViewById(R.id.addWordButton);
        addWordDialog.setBackgroundColor(Color.TRANSPARENT);
        question = (EditText) dialog.findViewById(R.id.questionEditText);
        hint = (EditText) dialog.findViewById(R.id.hintEditText);
        difficulty = (EditText) dialog.findViewById(R.id.difficultyEditText);
        dialog.setTitle("Add Word");

        if ( questionArg != null ) {
            question.setText(questionArg.getQuestion());
            hint.setText(questionArg.getHint());
            difficulty.setText(String.valueOf(questionArg.getDifficulty()));
            addWordDialog.setText("Edit Word");
            dialog.setTitle("Edit Word");
        }

        addWordDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question questionObject = new Question();
                questionObject.setQuestion(question.getText().toString().toLowerCase());
                questionObject.setHint(hint.getText().toString());
                questionObject.setDifficulty(Integer.parseInt(difficulty.getText().toString()));
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
        });
        dialog.show();
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
