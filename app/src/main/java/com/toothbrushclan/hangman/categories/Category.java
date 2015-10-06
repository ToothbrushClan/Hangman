package com.toothbrushclan.hangman.categories;

import android.content.Context;

import com.toothbrushclan.hangman.utilities.HangmanSQLiteHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by ushaikh on 02/10/15.
 */
public class Category {
    Context context;
    ArrayList<String> questionList;
    String resourceName = "";
    String categoryName = "";
    int difficulty;
    String question = "QUESTION";
    String hint = "Nothing to see here";
    String category = "";
    Map<String, Map<String, String>> categoryMap;
    Map<String, String> questionMap;
    Map<String, String> randomQuestionMap;
    List questionKeys;
    List randomQuestionKeys;
    HangmanSQLiteHelper db;

    public Category(Context context, String resourceFileName){
        this.resourceName = resourceFileName;
        this.context = context;
    }

    public Category(Context context, String categoryName, int difficulty){
        this.categoryName = categoryName;
        this.difficulty = difficulty;
        this.context = context;
        initializeDatabase();
    }

    private void initializeDatabase () {
        db = new HangmanSQLiteHelper(this.context);
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

    public boolean getNextQuestionFromDb() {
        if (categoryMap == null ) {
            categoryMap = db.getQuestionMap(categoryName,difficulty);
            if (categoryMap.isEmpty()) {
                return false;
            }
            if (categoryName.equals("random")) {
                randomQuestionMap = new HashMap<>();
                for (String categoryKey : categoryMap.keySet()) {
                    Map<String, String> tempQuestionMap = categoryMap.get(categoryKey);
                    for (String questionKey : tempQuestionMap.keySet()) {
                        randomQuestionMap.put(categoryKey + ";" + questionKey, tempQuestionMap.get(questionKey));
                    }
                }
                randomQuestionKeys = new ArrayList(randomQuestionMap.keySet());
            } else {
                setCategory(categoryName);
                questionMap = categoryMap.get(categoryName);
                if (questionMap.size() == 0) {
                    return false;
                }
                questionKeys = new ArrayList(questionMap.keySet());
            }
        }

        if (categoryName.equals("random")) {
            Collections.shuffle(randomQuestionKeys);
            ListIterator<String> iterator = randomQuestionKeys.listIterator();
            if (iterator.hasNext()) {
                String categoryQuestion = iterator.next();
                String[] categoryQuestionArray = categoryQuestion.split(";");
                setCategory(categoryQuestionArray[0]);
                setQuestion(categoryQuestionArray[1]);
                setHint(randomQuestionMap.get(categoryQuestion));
                iterator.remove();
            } else {
                return false;
            }
        } else {
            Collections.shuffle(questionKeys);
            ListIterator<String> iterator = questionKeys.listIterator();
            if (iterator.hasNext()) {
                setQuestion(iterator.next());
                setHint(questionMap.get(question));
                iterator.remove();
            } else {
                return false;
            }
        }
        return true;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getHint() {
        return this.hint;
    }

    public String getCategory() {
        return this.category;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
