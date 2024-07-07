package com.example.view;

import com.example.enums.Menu;
import com.example.model.App;

import java.util.Scanner;

public class AppView {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\u001B[34mWelcome! \nYou can see list of commands using \u001B[0m\u001B[40m\u001B[47mshow commands\u001B[0m\n\t\u001B[34mand see current menu using \u001B[0m\u001B[40m\u001B[47mshow current menu\u001B[0m");
        while (App.getCurrentMenu() != Menu.Exit)
            App.getCurrentMenu().checkCommand(scanner);

    }
}
