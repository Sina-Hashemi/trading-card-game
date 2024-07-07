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
        System.out.println(ShopMenuController.status());

        String input = scanner.nextLine();
        Matcher matcher;
        if(input.equals("show current menu")) System.out.println("Register/Login Menu");
        else if(input.equals("show commands"))
            for (ShopMenuCommands command : EnumSet.allOf(ShopMenuCommands.class))
                System.out.println(command);

        else if((matcher = ShopMenuCommands.back.getCommandMatcher(input)).find()) {
            System.out.println(ShopMenuController.back());
        }
        else if((matcher = ShopMenuCommands.showShop.getCommandMatcher(input)).find()) {
            System.out.println(ShopMenuController.showShop());
        }
        else if((matcher = ShopMenuCommands.showUpgrades.getCommandMatcher(input)).find()) {
            System.out.println(ShopMenuController.showUpgrades());
        }
        else if((matcher = ShopMenuCommands.buyCard.getCommandMatcher(input)).find()) {
            System.out.println(ShopMenuController.buyCard(matcher.group("cardName")));
        }
        else if((matcher = ShopMenuCommands.upgradeCard.getCommandMatcher(input)).find()) {
            System.out.println(ShopMenuController.upgradeCard(matcher.group("cardName")));
        }

        else System.out.println("Invalid command!");
    }
}
