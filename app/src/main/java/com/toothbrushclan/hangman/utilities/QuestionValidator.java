package com.toothbrushclan.hangman.utilities;

/**
 * Created by ushaikh on 03/10/15.
 */
public class QuestionValidator {
    public boolean isCharacterPresent (String character, String string) {
        return string.contains(character);
    }

    public String updateRegex (String character, String regex) {
        return regex.replace("\\s]", character.toLowerCase() + character.toUpperCase() + "\\s]");
    }

    public String getMaskedQuestion (String question, String regex) {
        return question.replaceAll(regex, "_ ");
    }

    public boolean isQuestionComplete(String maskedQuestion) {
        if (maskedQuestion.contains("_") ) {
            return false;
        } else {
            return true;
        }
    }
}
