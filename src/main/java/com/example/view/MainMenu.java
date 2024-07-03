package com.example.view;

import java.util.Scanner;
import java.util.regex.Matcher;

import com.example.controller.MainMenuController;
import com.example.enums.MainMenuCommands;
import com.example.model.*;

public class MainMenu extends AppMenu {

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        if(input.equals("show current menu")) System.out.println("Main Menu");

        else if((matcher = MainMenuCommands.enterProfileMenu.getCommandMatcher(input)).find()) {
            System.out.println(MainMenuController.enterProfileMenu());
        }
        else if((matcher = MainMenuCommands.logout.getCommandMatcher(input)).find()) {
            System.out.println(MainMenuController.logout());
        }

        else System.out.println("Invalid command!");
    }

}
