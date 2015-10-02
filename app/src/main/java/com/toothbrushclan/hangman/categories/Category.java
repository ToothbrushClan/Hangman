package com.toothbrushclan.hangman.categories;

import android.content.Context;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ushaikh on 02/10/15.
 */
public abstract class Category {
    Context context;
    ArrayList<String> questionList;
    public Category(Context context){
        this.context = context;
    }
    protected abstract void readFile();
    public abstract String getNextQuestion();
    //public abstract String getHint();
}
