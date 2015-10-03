package com.toothbrushclan.hangman.categories;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by ushaikh on 02/10/15.
 */
public class Kids extends Category {
    BufferedReader br = null;

    public Kids (Context context) {
        super(context);
    }


    public String getNextQuestion() {
        String questionLine = null;
        if (questionList == null ) {
            readFile();
            if (questionList == null) {
                System.out.println("ERROR!!!!");
            }
        }
        ListIterator<String> iterator = questionList.listIterator();
        if (iterator.hasNext()) {
            questionLine = iterator.next();
            iterator.remove();
        } else {
            return null;
        }
        return questionLine;
    }

    protected void readFile() {
        String sCurrentLine;
        BufferedReader br = null;
        try {
            int resId = context.getResources().getIdentifier("raw/kids", "raw", context.getPackageName());
            InputStream ins =context.getResources().openRawResource(resId);
            br = new BufferedReader (new InputStreamReader(ins));
            questionList = new ArrayList<>();
            while ((sCurrentLine = br.readLine()) != null) {
                questionList.add(sCurrentLine);
                // System.out.println(sCurrentLine);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
