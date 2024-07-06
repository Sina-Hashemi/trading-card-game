package com.example;

import com.example.model.SQLConnector;
import com.example.view.AppView;

public class Main {
    public static void main(String[] args) {
        SQLConnector.initialize();
        (new AppView()).run();
        // 
    }
}