package com.example.view;

import com.example.enums.Menu;
import com.example.model.App;

import java.util.Scanner;

public class AppView {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        // TODO - Sina - initialize from SQL

        System.out.println("Welcome! \nYou can see list of commands using \"show commands\"\n\tand see current menu using \"show current menu\"");
        while (App.getCurrentMenu() != Menu.Exit)
            App.getCurrentMenu().checkCommand(scanner);

    }
}
