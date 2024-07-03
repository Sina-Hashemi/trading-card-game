package com.example.view;

import com.example.enums.Menu;
import com.example.model.App;

import java.util.Scanner;

public class AppView {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        // TODO - Sina - initialize from SQL

        while (App.getCurrentMenu() != Menu.Exit)
            App.getCurrentMenu().checkCommand(scanner);

    }
}
