package com.toothbrushclan.hangman.statistics;

import com.toothbrushclan.hangman.users.User;
import com.toothbrushclan.hangman.utilities.Constants;

/**
 * Created by ushaikh on 23/11/15.
 */
public class Statistics {
    private int totalQuestions;
    private int totalCorrect;
    private int totalWrong;
    private int totalSkipped;
    private int totalPoints;
    private int totalPerfectAnswers;
    private int totalShowHints;
    private int totalJustSaved;
    private int easyQuestions;
    private int easyCorrect;
    private int easyWrong;
    private int easySkipped;
    private int easyPoints;
    private int easyPerfectAnswers;
    private int easyShowHints;
    private int easyJustSaved;
    private int mediumQuestions;
    private int mediumCorrect;
    private int mediumWrong;
    private int mediumSkipped;
    private int mediumPoints;
    private int mediumPerfectAnswers;
    private int mediumShowHints;
    private int mediumJustSaved;
    private int hardQuestions;
    private int hardCorrect;
    private int hardWrong;
    private int hardSkipped;
    private int hardPoints;
    private int hardPerfectAnswers;
    private int hardShowHints;
    private int hardJustSaved;
    private int extremeQuestions;
    private int extremeCorrect;
    private int extremeWrong;
    private int extremeSkipped;
    private int extremePoints;
    private int extremePerfectAnswers;
    private int extremeShowHints;
    private int extremeJustSaved;
    private int userId;
    private String userName;
    private String userFullName;

    public Statistics (User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userFullName = user.getUserFullName();
        this.totalQuestions = 0;
        this.totalCorrect = 0;
        this.totalWrong = 0;
        this.totalSkipped = 0;
        this.totalPoints = 0;
        this.totalPerfectAnswers = 0;
        this.totalShowHints = 0;
        this.totalJustSaved = 0;
        this.easyQuestions = 0;
        this.easyCorrect = 0;
        this.easyWrong = 0;
        this.easySkipped = 0;
        this.easyPoints = 0;
        this.easyPerfectAnswers = 0;
        this.easyShowHints = 0;
        this.easyJustSaved = 0;
        this.mediumQuestions = 0;
        this.mediumCorrect = 0;
        this.mediumWrong = 0;
        this.mediumSkipped = 0;
        this.mediumPoints = 0;
        this.mediumPerfectAnswers = 0;
        this.mediumShowHints = 0;
        this.mediumJustSaved = 0;
        this.hardQuestions = 0;
        this.hardCorrect = 0;
        this.hardWrong = 0;
        this.hardSkipped = 0;
        this.hardPoints = 0;
        this.hardPerfectAnswers = 0;
        this.hardShowHints = 0;
        this.hardJustSaved = 0;
        this.extremeQuestions = 0;
        this.extremeCorrect = 0;
        this.extremeWrong = 0;
        this.extremeSkipped = 0;
        this.extremePoints = 0;
        this.extremePerfectAnswers = 0;
        this.extremeShowHints = 0;
        this.extremeJustSaved = 0;
    }

    public void questionAnsweredIncorrectly (Constants.difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                break;
            case MEDIUM:
                break;
            case HARD:
                break;
            case EXTREME:
                break;
        }
        this.totalQuestions++;

    }

    public void questionSkipped (Constants.difficulty difficulty) {
        this.totalSkipped++;
    }

    public void questionAnsweredCorrectly (Constants.difficulty difficulty, int score, boolean isPerfect, boolean isShowHints, boolean isJustSaved) {

    }

}
