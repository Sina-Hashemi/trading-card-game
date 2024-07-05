package com.example.view;

import java.util.EnumSet;
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
        else if(input.equals("show commands"))
            for (MainMenuCommands command : EnumSet.allOf(MainMenuCommands.class))
                System.out.println(command);

        else if((matcher = MainMenuCommands.startNewGame.getCommandMatcher(input)).find()) {
            Result result = MainMenuController.preparePlayerTwo(matcher.group("username"));
            System.out.println(result);
            if(result.isSuccessful()) {
                input = scanner.nextLine();
                System.out.println(MainMenuController.checkSecondUser(input, "0"));
            }
        }
        else if((matcher = MainMenuCommands.startNewBetGame.getCommandMatcher(input)).find()) {
            Result result = MainMenuController.preparePlayerTwo(matcher.group("username"));
            System.out.println(result);
            if(result.isSuccessful()) {
                input = scanner.nextLine();
                System.out.println(MainMenuController.checkSecondUser(input, matcher.group("bet")));
            }
        }
        else if((matcher = MainMenuCommands.enterProfileMenu.getCommandMatcher(input)).find()) {
            System.out.println(MainMenuController.enterProfileMenu());
        }
        else if((matcher = MainMenuCommands.enterShopMenu.getCommandMatcher(input)).find()) {
            System.out.println(MainMenuController.enterShopMenu());
        }
        else if((matcher = MainMenuCommands.enterGameHistoryMenu.getCommandMatcher(input)).find()) {
            System.out.println(MainMenuController.enterGameHistoryMenu());
        }
        else if((matcher = MainMenuCommands.logout.getCommandMatcher(input)).find()) {
            System.out.println(MainMenuController.logout());
        }

        else System.out.println("Invalid command!");
    }

}
