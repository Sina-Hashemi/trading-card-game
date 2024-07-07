package com.example.view;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.regex.Matcher;

import com.example.controller.GameHistoryMenuController;
import com.example.enums.GameHistoryMenuCommands;
import com.example.model.*;

public class GameHistoryMenu extends AppMenu {

    @Override
    public void check(Scanner scanner) {

        System.out.println(GameHistoryMenuController.showBoard());

        String input = scanner.nextLine();
        Matcher matcher;
        if(input.equals("show current menu")) System.out.println("Register/Login Menu");
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

        else System.out.println("Invalid command!");
    }
}
