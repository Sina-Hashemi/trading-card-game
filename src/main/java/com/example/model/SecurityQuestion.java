package com.example.model;

import java.util.HashMap;

public class SecurityQuestion {
    public static HashMap<Integer, String> questions = new HashMap<>(){{put(1, "What is your father's name?");put(2, "What is your favourite color?");put(3, "What was the name of your first pet?");}};
    private final int key;
    private final String ans;

    public SecurityQuestion(int key, String ans) {
        this.key = key;
        this.ans = ans;
    }

    public static String printQustions() {
        String output = "";
        for( HashMap.Entry<Integer, String> entry : questions.entrySet()) {
            output += entry.getKey() + "-" + entry.getValue() + "\n";
        }
        return output;
    }

    public int getKey() {
        return key;
    }

    public String getAns() {
        return ans;
    }
}
