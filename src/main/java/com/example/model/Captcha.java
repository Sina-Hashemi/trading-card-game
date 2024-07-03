package com.example.model;

public class Captcha {
    private final String question, ans;
    // TODO - ehsan - config it

    public Captcha() {
        this.question = "salam";
        this.ans = "salam";
    }

    public String getAns() {
        return ans;
    }

    @Override
    public String toString() {
        return question.toString();
    }
}
