package com.example.view;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.regex.Matcher;

import com.example.controller.GameHistoryMenuController;
import com.example.controller.MainMenuController;
import com.example.enums.GameHistoryMenuCommands;
import com.example.model.*;

public class GameHistoryMenu extends AppMenu {

    @Override
    public void check(Scanner scanner) {

        System.out.println(GameHistoryMenuController.showBoard());

        System.out.print("\u001B[33m");
        String input = scanner.nextLine();
        System.out.print("\u001B[0m");
        Matcher matcher;
        if(input.equals("show current menu")) System.out.println("Game History Menu");
        else if(input.equals("show commands"))
            for (GameHistoryMenuCommands command : EnumSet.allOf(GameHistoryMenuCommands.class))
                System.out.println(command);

        else if((matcher = GameHistoryMenuCommands.back.getCommandMatcher(input)).find()) {
            System.out.println(GameHistoryMenuController.back());
        }
        else if((matcher = GameHistoryMenuCommands.sort.getCommandMatcher(input)).find()) {
            System.out.println(GameHistoryMenuController.sort(matcher.group(1), matcher.group(2)));
        }
        else if((matcher = GameHistoryMenuCommands.changePage.getCommandMatcher(input)).find()) {
            System.out.println(GameHistoryMenuController.changePage(matcher.group(1)));
        }
        else if((matcher = GameHistoryMenuCommands.changePageByNum.getCommandMatcher(input)).find()) {
            System.out.println(GameHistoryMenuController.changePageByNum(matcher.group("pageNum")));
        }
        else if((matcher = GameHistoryMenuCommands.challengeAgain.getCommandMatcher(input)).find()) {
            Result result = MainMenuController.preparePlayerTwo(matcher.group("username"));
            System.out.println(result);
            if(result.isSuccessful()) {
                System.out.print("\u001B[33m");
                input = scanner.nextLine();
                System.out.print("\u001B[0m");
                System.out.println(MainMenuController.checkSecondUser(input, "0"));
            }
        }

        else System.out.println("Invalid command!");
    }
}
