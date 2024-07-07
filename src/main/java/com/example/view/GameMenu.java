package com.example.view;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.regex.Matcher;

import com.example.controller.GameMenuController;
import com.example.enums.GameMenuCommands;
import com.example.model.Card.GameCharacter;

public class GameMenu extends AppMenu {

    @Override
    public void check(Scanner scanner) {
        if(!GameMenuController.areCharactersOK()) {
            System.out.println("Please select characters from: " + GameCharacter.values());
        }
        else if(GameMenuController.isRoundFinished()) {
            System.out.println("Round ended!\n" + GameMenuController.moveTimeline());
        }
        else {
            System.out.println(GameMenuController.showGame());
            System.out.println(GameMenuController.showNowPlayer());
        }

        System.out.print("\u001B[33m");
        String input = scanner.nextLine();
        System.out.print("\u001B[0m");
        Matcher matcher;
        if(input.equals("show current menu")) System.out.println("Register/Login Menu");
        else if(input.equals("show commands"))
            for (GameMenuCommands command : EnumSet.allOf(GameMenuCommands.class))
                System.out.println(command);

        else if(!GameMenuController.areCharactersOK() && (matcher = GameMenuCommands.selectCharacter.getCommandMatcher(input)).find()) {
            System.out.println(GameMenuController.handleCharacter(matcher.group("character")));
        }
        else if(!GameMenuController.areCharactersOK()) return;

        else if((matcher = GameMenuCommands.selectCard.getCommandMatcher(input)).find()) {
            System.out.println(GameMenuController.selectCard(matcher.group("cardNum"), matcher.group("playerNum")));
        }
        else if((matcher = GameMenuCommands.placeCard.getCommandMatcher(input)).find()) {
            System.out.println(GameMenuController.placeCard(matcher.group("cardNum"), matcher.group("blockNum")));
        }

        else System.out.println("Invalid command!");
    }
}
