package com.toothbrushclan.hangman.users;

/**
 * Created by ushaikh on 23/11/15.
 */
public class User {
    private int userId;
    private String userName;
    private String firstName;
    private String lastName;

    public User(String userName, String firstName, String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserFullName() {
        return this.firstName + this.lastName;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
