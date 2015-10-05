package com.toothbrushclan.hangman.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HangmanDb";
    private static final String TABLE_QUESTIONS = "questions";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_HINT = "hint";
    private static final String COLUMN_DIFFICULTY = "difficulty";
    Context context;

    public HangmanSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE "
                + TABLE_QUESTIONS + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "category TEXT, " +
                "question TEXT, " +
                "hint TEXT, " +
                "difficulty INTEGER )";
        db.execSQL(CREATE_QUESTIONS_TABLE);
        loadData(db);
    }

    private void loadData(SQLiteDatabase db) {
        List<String> questionList = readFile("raw/sqldataquestions");
        for ( String questionLine : questionList ) {
            String[] questionArray = questionLine.split(";");
            ContentValues insertQuestionValues = new ContentValues();
            insertQuestionValues.put("category", questionArray[0]);
            insertQuestionValues.put("question", questionArray[1]);
            insertQuestionValues.put("hint", questionArray[2]);
            insertQuestionValues.put("difficulty", Integer.parseInt(questionArray[3]));
            db.insert(TABLE_QUESTIONS, null, insertQuestionValues);
        }
        System.out.println("hello");
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        this.onCreate(db);
    }

    public Map<String, Map<String, String>> getQuestionMap(String category, int difficulty){
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = COLUMN_CATEGORY + " = ? AND " + COLUMN_DIFFICULTY + " = ?";
        String[] whereArgs = {category,String.valueOf(difficulty)};
        if ( category.equals("random")) {
            whereClause = COLUMN_CATEGORY + " like ? AND " + COLUMN_DIFFICULTY + " = ?";
            whereArgs = new String[]{"%",String.valueOf(difficulty)};
        }
        Cursor cursor = db.query(TABLE_QUESTIONS,
                new String[]{COLUMN_CATEGORY, COLUMN_QUESTION, COLUMN_HINT},
                whereClause, whereArgs,
                null, null, COLUMN_CATEGORY);

        Map<String, Map<String, String>> categoryMap = new HashMap<>();
        Map<String, String> questionMap = new HashMap<>();

        String previousCategory = "";
        String currentCategory = "";

        if (cursor.moveToFirst()) {
            do {
                currentCategory = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY));
                String questionValue = cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION));
                String hintValue = cursor.getString(cursor.getColumnIndex(COLUMN_HINT));
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
}
