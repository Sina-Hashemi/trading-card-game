package com.example.view;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.regex.Matcher;

import com.example.controller.GameMenuController;
import com.example.enums.GameMenuCommands;
import com.example.model.*;

public class GameMenu extends AppMenu {

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        if(input.equals("show current menu")) System.out.println("Register/Login Menu");
        else if(input.equals("show commands"))
            for (GameMenuCommands command : EnumSet.allOf(GameMenuCommands.class))
                System.out.println(command);

        else if((matcher = GameMenuCommands.back.getCommandMatcher(input)).find()) {
            System.out.println(GameMenuController.back());
        }

        else System.out.println("Invalid command!");
    }
}
