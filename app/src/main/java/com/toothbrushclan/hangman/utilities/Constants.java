package com.toothbrushclan.hangman.utilities;

/**
 * Created by ushaikh on 23/11/15.
 */
public class Constants {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "HangmanDb";
    public static final String TABLE_QUESTIONS = "questions";
    public static final String TABLE_SETTINGS = "settings";
    public static final String TABLE_STATISTICS = "statistics";
    public static final String TABLE_USERS = "users";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_HINT = "hint";
    public static final String COLUMN_DIFFICULTY = "difficulty";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_VALUE = "value";
    public static final String COLUMN_USER_ID = "userid";
    public static final String COLUMN_USER_NAME = "username";
    public static final String COLUMN_TOTAL_QUESTIONS = "totalquestions";
    public static final String COLUMN_TOTAL_CORRECT = "totalcorrect";
    public static final String COLUMN_TOTAL_WRONG = "totalwrong";
    public static final String COLUMN_TOTAL_SKIPPED = "totalskipped";
    public static final String COLUMN_TOTAL_POINTS = "totalpoints";
    public static final String COLUMN_TOTAL_PERFECT_ANSWERS = "totalperfectanswers";
    public static final String COLUMN_TOTAL_SHOW_HINTS = "totalshowhints";
    public static final String COLUMN_TOTAL_JUST_SAVED = "totaljustsaved";
    public static final String COLUMN_TOTAL_EASY = "totaleasy";
    public static final String COLUMN_TOTAL_MEDIUM = "totalmedium";
    public static final String COLUMN_TOTAL_HARD = "totalhard";
    public static final String COLUMN_TOTAL_EXTREME = "totalextreme";

    public static final String CUSTOM_CATEGORY = "custom";

    public static enum difficulty {
        EASY, MEDIUM, HARD, EXTREME;
    }


}
