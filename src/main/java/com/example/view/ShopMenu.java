package com.example.view;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.regex.Matcher;

import com.example.controller.ShopMenuController;
import com.example.enums.ShopMenuCommands;
import com.example.model.*;

public class ShopMenu extends AppMenu {

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        if(input.equals("show current menu")) System.out.println("Register/Login Menu");
        else if(input.equals("show commands"))
            for (ShopMenuCommands command : EnumSet.allOf(ShopMenuCommands.class))
                System.out.println(command);

        else if((matcher = ShopMenuCommands.back.getCommandMatcher(input)).find()) {
            System.out.println(ShopMenuController.back());
        }

        else System.out.println("Invalid command!");
    }
}
