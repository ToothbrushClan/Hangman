package com.toothbrushclan.hangman.categories;

/**
 * Created by ushaikh on 07/10/15.
 */
public class Question {
    private int id;
    private int difficulty;
    private String category;
    private String question;
    private String hint;

    public Question () {

    }

    public Question (int id, int difficulty, String category, String question, String hint) {
        this.id = id;
        this.difficulty = difficulty;
        this.category = category;
        this.question = question;
        this.hint = hint;
    }

    public int getId () {
        return this.id;
    }

    public int getDifficulty () {
        return this.difficulty;
    }

    public String getCategory () {
        return this.category;
    }

    public String getQuestion () {
        return this.question;
    }

    public String getHint () {
        return this.hint;
    }

    public void setId (int id) {
        this.id = id;
    }

    public void setDifficulty (int difficulty) {
        this.difficulty = difficulty;
    }

    public void setCategory (String category) {
        this.category = category;
    }

    public void setQuestion (String question) {
        this.question = question;
    }

    public void setHint (String hint) {
        this.hint = hint;
    }

}
