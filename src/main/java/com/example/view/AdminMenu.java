package com.example.view;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.regex.Matcher;

import com.example.controller.AdminMenuController;
import com.example.enums.AdminMenuCommands;
// import com.example.model.*;

public class AdminMenu extends AppMenu {

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        @SuppressWarnings("unused")
        Matcher matcher;
        if(input.equals("show current menu")) System.out.println("Register/Login Menu");
        else if(input.equals("show commands"))
            for (AdminMenuCommands command : EnumSet.allOf(AdminMenuCommands.class))
                System.out.println(command);

        else if((matcher = AdminMenuCommands.back.getCommandMatcher(input)).find()) {
            System.out.println(AdminMenuController.back());
        }
        else if((matcher = AdminMenuCommands.showCards.getCommandMatcher(input)).find()) {
            System.out.println(AdminMenuController.showCards());
        }
        else if((matcher = AdminMenuCommands.showUsers.getCommandMatcher(input)).find()) {
            System.out.println(AdminMenuController.showUsers());
        }
        else if((matcher = AdminMenuCommands.addCard.getCommandMatcher(input)).find()) {
            System.out.println(AdminMenuController.addCard(matcher.group("name"), matcher.group("attack"), matcher.group("damage"), matcher.group("duration"), matcher.group("basePrice"), matcher.group("upgradeLevel"), matcher.group("upgradeCost"), matcher.group("character")));
        }
        else if((matcher = AdminMenuCommands.editCard.getCommandMatcher(input)).find()) {
            System.out.println(AdminMenuController.editCard(matcher.group("name")));
        }
        else if((matcher = AdminMenuCommands.removeCard.getCommandMatcher(input)).find()) {
            System.out.println("are you sure you want to delete this card?(Y/n)");
            input = scanner.nextLine();
            if (input.equals("Y") || input.equals("y")) {
                System.out.println(AdminMenuController.removeCard(matcher.group("name")));
            }
        }

        else System.out.println("Invalid command!");
    }
}
