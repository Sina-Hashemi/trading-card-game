package com.example.model;

import java.util.ArrayList;

public class Captcha {
    private final String question, ans;

    public Captcha() {
        ArrayList<String> A =  ReturnCaptcha.captchaMaker() ;
        this.question = A.get(0) ;
        this.ans = A.get(1) ;
    }

    public String getAns() {
        return ans;
    }

    @Override
    public String toString() {
        return question.toString();
    }
}
