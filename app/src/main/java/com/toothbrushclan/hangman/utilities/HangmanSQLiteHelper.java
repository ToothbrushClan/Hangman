package com.toothbrushclan.hangman.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.toothbrushclan.hangman.R;
import com.toothbrushclan.hangman.categories.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ushaikh on 05/10/15.
 */
public class HangmanSQLiteHelper extends SQLiteOpenHelper {

    Context context;

    public HangmanSQLiteHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createQuestionsTable(db);
        createSettingsTable(db);
        loadData(db);
        loadSettings(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: Handle upgrade for questions. Should not wipe out custom questions
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_QUESTIONS);
        // db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_SETTINGS);
        createQuestionsTable(db);
        loadData(db);
    }

    public void createQuestionsTable (SQLiteDatabase db) {
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE "
                + Constants.TABLE_QUESTIONS + " ( " +
                Constants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.COLUMN_CATEGORY + " TEXT, " +
                Constants.COLUMN_QUESTION + " TEXT, " +
                Constants.COLUMN_HINT + " TEXT, " +
                Constants.COLUMN_DIFFICULTY + " INTEGER )";
        db.execSQL(CREATE_QUESTIONS_TABLE);
    }

    public void createSettingsTable (SQLiteDatabase db) {
        String CREATE_SETTINGS_TABLE = "CREATE TABLE "
                + Constants.TABLE_SETTINGS + " ( " +
                Constants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.COLUMN_NAME + " TEXT, " +
                Constants.COLUMN_VALUE + " TEXT )";
        db.execSQL(CREATE_SETTINGS_TABLE);
    }

    public void createStatisticsTable (SQLiteDatabase db) {
        // TODO: Define fields
        String CREATE_STATISTICS_TABLE = "CREATE TABLE "
                + Constants.TABLE_STATISTICS + " ( " +
                Constants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.COLUMN_NAME + " TEXT, " +
                Constants.COLUMN_VALUE + " TEXT )";
        db.execSQL(CREATE_STATISTICS_TABLE);
    }

    public void createUsersTable (SQLiteDatabase db) {
        // TODO: Define fields
        String CREATE_USERS_TABLE = "CREATE TABLE "
                + Constants.TABLE_USERS + " ( " +
                Constants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.COLUMN_NAME + " TEXT, " +
                Constants.COLUMN_VALUE + " TEXT )";
        db.execSQL(CREATE_USERS_TABLE);
    }

    private void loadData(SQLiteDatabase db) {
        List<String> questionList = readFile("raw/sqldataquestions");
        for ( String questionLine : questionList ) {
            String[] questionArray = questionLine.split(";");
            ContentValues insertQuestionValues = new ContentValues();
            insertQuestionValues.put(Constants.COLUMN_CATEGORY, questionArray[0]);
            insertQuestionValues.put(Constants.COLUMN_QUESTION, questionArray[1]);
            insertQuestionValues.put(Constants.COLUMN_HINT, questionArray[2]);
            insertQuestionValues.put(Constants.COLUMN_DIFFICULTY, Integer.parseInt(questionArray[3]));
            db.insert(Constants.TABLE_QUESTIONS, null, insertQuestionValues);
        }
    }

    private void loadSettings(SQLiteDatabase db) {
        ContentValues insertSettingsValues = new ContentValues();
        insertSettingsValues.put(Constants.COLUMN_NAME, context.getResources().getString(R.string.dbShowHints));
        insertSettingsValues.put(Constants.COLUMN_VALUE, context.getResources().getString(R.string.dbTrue));
        db.insert(Constants.TABLE_SETTINGS, null, insertSettingsValues);
    }

    protected List<String> readFile(String resourceName) {
        List<String> dataList = new ArrayList<>();
        String currentLine;
        BufferedReader br = null;
        try {
            int resId = context.getResources().getIdentifier(resourceName, "raw", context.getPackageName());
            InputStream ins =context.getResources().openRawResource(resId);
            br = new BufferedReader (new InputStreamReader(ins));
            while ((currentLine = br.readLine()) != null) {
                dataList.add(currentLine);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return dataList;
    }

    public Map<String, Map<String, String>> getQuestionMap(String category, int difficulty){
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = Constants.COLUMN_CATEGORY + " = ? AND " + Constants.COLUMN_DIFFICULTY + " = " + difficulty;
        String[] whereArgs = {category};
        if ( category.equals("random")) {
            whereClause = Constants.COLUMN_CATEGORY + " like ? AND " + Constants.COLUMN_DIFFICULTY + " = " + difficulty;
            whereArgs = new String[]{"%",String.valueOf(difficulty)};
        }
        Cursor cursor = db.query(Constants.TABLE_QUESTIONS,
                new String[]{Constants.COLUMN_CATEGORY, Constants.COLUMN_QUESTION, Constants.COLUMN_HINT},
                whereClause, whereArgs,
                null, null, Constants.COLUMN_CATEGORY);

        Map<String, Map<String, String>> categoryMap = new HashMap<>();
        Map<String, String> questionMap = new HashMap<>();

        String previousCategory = "";
        String currentCategory = "";

        if (cursor.moveToFirst()) {
            do {
                currentCategory = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_CATEGORY));
                String questionValue = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_QUESTION));
                String hintValue = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_HINT));
                if (category.equals("random")) {
                    if (!currentCategory.equals(previousCategory)) {
                        if (!previousCategory.equals("")) {
                            Map<String, String> tempQuestionMap = new HashMap<>(questionMap);
                            categoryMap.put(previousCategory, tempQuestionMap);
                            questionMap.clear();
                        }
                        previousCategory = currentCategory;
                    }
                }
                questionMap.put(questionValue, hintValue);
            }while ( cursor.moveToNext() );
            categoryMap.put(currentCategory, questionMap);
        }
        return categoryMap;
    }

    public Question[] getQuestions(String queryCategory){
        Question[] questions;
        int questionCount = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = Constants.COLUMN_CATEGORY + " = ?";
        String[] whereArgs = {queryCategory};
        if ( queryCategory.equals("random")) {
            whereClause = Constants.COLUMN_CATEGORY + " like ?";
            whereArgs = new String[]{"%"};
        }
        Cursor cursor = db.query(Constants.TABLE_QUESTIONS,
                new String[]{Constants.COLUMN_ID, Constants.COLUMN_CATEGORY, Constants.COLUMN_QUESTION, Constants.COLUMN_HINT, Constants.COLUMN_DIFFICULTY},
                whereClause, whereArgs,
                null, null, Constants.COLUMN_CATEGORY);

        if (cursor.getCount() > 0) {
            questions = new Question[cursor.getCount()];
        } else {
            return null;
        }

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_ID));
                int difficulty = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_DIFFICULTY));
                String category = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_CATEGORY));
                String question = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_QUESTION));
                String hint = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_HINT));

                questions[questionCount] = new Question(id, difficulty, category, question, hint);
                questionCount++;
            }while ( cursor.moveToNext() );
        }
        return questions;
    }

    public void addQuestion(Question question){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues insertQuestionValues = new ContentValues();
        insertQuestionValues.put(Constants.COLUMN_CATEGORY, question.getCategory());
        insertQuestionValues.put(Constants.COLUMN_QUESTION, question.getQuestion());
        insertQuestionValues.put(Constants.COLUMN_HINT, question.getHint());
        insertQuestionValues.put(Constants.COLUMN_DIFFICULTY, question.getDifficulty());
        db.insert(Constants.TABLE_QUESTIONS, null, insertQuestionValues);
    }

    public void addCustomQuestion(Question question){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues insertQuestionValues = new ContentValues();
        insertQuestionValues.put(Constants.COLUMN_CATEGORY, Constants.CUSTOM_CATEGORY);
        insertQuestionValues.put(Constants.COLUMN_QUESTION, question.getQuestion());
        insertQuestionValues.put(Constants.COLUMN_HINT, question.getHint());
        insertQuestionValues.put(Constants.COLUMN_DIFFICULTY, question.getDifficulty());
        db.insert(Constants.TABLE_QUESTIONS, null, insertQuestionValues);
    }

    public void editQuestion(Question question){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = Constants.COLUMN_CATEGORY + " = ? AND " + Constants.COLUMN_ID + " = ?";
        String[] whereArgs = {question.getCategory(), String.valueOf(question.getId())};
        ContentValues updateQuestionValues = new ContentValues();
        updateQuestionValues.put(Constants.COLUMN_QUESTION, question.getQuestion());
        updateQuestionValues.put(Constants.COLUMN_HINT, question.getHint());
        updateQuestionValues.put(Constants.COLUMN_DIFFICULTY, question.getDifficulty());
        db.update(Constants.TABLE_QUESTIONS, updateQuestionValues, whereClause, whereArgs);
    }

    public void deleteQuestion(Question question){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = Constants.COLUMN_CATEGORY + " = ? AND " + Constants.COLUMN_QUESTION + " = ? AND " + Constants.COLUMN_ID + " = ?";
        String[] whereArgs = {question.getCategory(),question.getQuestion(),String.valueOf(question.getId())};
        db.delete(Constants.TABLE_QUESTIONS, whereClause, whereArgs);
    }

    public String getSetting (String settingName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = Constants.COLUMN_NAME + " = ?";
        String[] whereArgs = {settingName};
        Cursor cursor = db.query(Constants.TABLE_SETTINGS,
                new String[]{Constants.COLUMN_NAME, Constants.COLUMN_VALUE},
                whereClause, whereArgs,
                null, null, null);
        if (cursor.moveToFirst()) {
            String value = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_VALUE));
            return value;
        }
        return null;
    }

    public void setSetting (String settingName, String settingValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = Constants.COLUMN_NAME + " = ?";
        String[] whereArgs = {settingName};
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_VALUE, settingValue);
        db.update(Constants.TABLE_SETTINGS, values, whereClause, whereArgs);
    }

}
