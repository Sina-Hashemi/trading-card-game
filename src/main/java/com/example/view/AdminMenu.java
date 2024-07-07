package com.example.view;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.regex.Matcher;

import com.example.controller.AdminMenuController;
import com.example.enums.AdminMenuCommands;
import com.example.model.*;

public class AdminMenu extends AppMenu {

    @Override
    public void check(Scanner scanner) {
        System.out.print("\u001B[33m");
        String input = scanner.nextLine();
        System.out.print("\u001B[0m");
        Matcher matcher;
        if(input.equals("show current menu")) System.out.println("Register/Login Menu");
        else if(input.equals("show commands"))
            for (AdminMenuCommands command : EnumSet.allOf(AdminMenuCommands.class))
                System.out.println(command);

        else if((matcher = AdminMenuCommands.logout.getCommandMatcher(input)).find()) {
            System.out.println(AdminMenuController.logout());
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
            Result result = AdminMenuController.checkCard(matcher.group("cardNum"));
            System.out.println(result);
            if (result.isSuccessful()) {
                if (input.equals("Y") || input.equals("y")) {
                    System.out.println(AdminMenuController.editCard(Integer.parseInt(matcher.group("cardNum")), matcher.group(2), matcher.group(3)));
                }
            }
        }
        else if((matcher = AdminMenuCommands.removeCard.getCommandMatcher(input)).find()) {
            Result result = AdminMenuController.checkCard(matcher.group("cardNum"));
            System.out.println(result);
            if (result.isSuccessful()) {
                System.out.print("\u001B[33m");
                input = scanner.nextLine();
                System.out.print("\u001B[0m");
                if (input.equals("Y") || input.equals("y")) {
                    System.out.println(AdminMenuController.removeCard(Integer.parseInt(matcher.group("cardNum"))));
                }
            }
        }

        else System.out.println("Invalid command!");
    }
}
