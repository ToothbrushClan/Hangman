package com.toothbrushclan.hangman.categories;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by ushaikh on 02/10/15.
 */
public class Category {
    Context context;
    ArrayList<String> questionList;
    protected String resourceName = "";
    public Category(Context context, String resourceFileName){
        this.resourceName = resourceFileName;
        this.context = context;
    }

    protected void readFile() {
        String sCurrentLine;
        BufferedReader br = null;
        try {
            int resId = context.getResources().getIdentifier(resourceName, "raw", context.getPackageName());
            InputStream ins =context.getResources().openRawResource(resId);
            br = new BufferedReader (new InputStreamReader(ins));
            questionList = new ArrayList<>();
            while ((sCurrentLine = br.readLine()) != null) {
                questionList.add(sCurrentLine);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public String getNextQuestion() {
        String questionLine = null;
        if (questionList == null ) {
            readFile();
            if (questionList == null) {
                System.out.println("ERROR!!!!");
            }
        }
        Collections.shuffle(questionList);
        ListIterator<String> iterator = questionList.listIterator();
        if (iterator.hasNext()) {
            questionLine = iterator.next();
            iterator.remove();
        } else {
            return null;
        }
        return questionLine;
    }

    //public abstract String getHint();
}
