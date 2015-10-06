package com.toothbrushclan.hangman.utilities;

import android.content.Context;

/**
 * Created by ushaikh on 06/10/15.
 */
public class Settings {

    Context context;
    HangmanSQLiteHelper db;

    public Settings(Context context){
        this.context = context;
        initializeDatabase();
    }

    private void initializeDatabase () {
        db = new HangmanSQLiteHelper(context);
    }

    public String getSetting(String settingName) {
        return db.getSetting(settingName);
    }

    public void setSetting(String settingName, String settingValue) {
        db.setSetting(settingName, settingValue);
    }
}
