package com.example.controller;

import com.example.model.*;
import com.example.model.Card.GameCharacter;
import com.example.enums.*;

public class AdminMenuController {

    public static Result back() {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Entered main menu!");
    }

    public static Result showCards() {
        String output = "";
        for (Card card : App.getCards()) {
            output += card.toString();
        }
        return new Result(true, output);
    }

    public static Result showUsers() {
        String output = "";
        for (User user : App.getUsers()) {
            output += user.toString();
        }
        return new Result(true, output);
    }

    public static Result addCard(String name, String attack, String damage, String duration, String basePrice,
            String upgradeLevel, String upgradeCost, String character) {
        try {
            switch (character) {
                case "electricity":
                    App.getCards().add(new Card(name, Integer.parseInt(attack), Integer.parseInt(damage), Integer.parseInt(duration), Integer.parseInt(basePrice), Integer.parseInt(upgradeLevel), Integer.parseInt(upgradeCost), GameCharacter.Electricity));
                    break;
                case "posion":
                    App.getCards().add(new Card(name, Integer.parseInt(attack), Integer.parseInt(damage), Integer.parseInt(duration), Integer.parseInt(basePrice), Integer.parseInt(upgradeLevel), Integer.parseInt(upgradeCost), GameCharacter.Posion));
                    break;
                case "fire":
                    App.getCards().add(new Card(name, Integer.parseInt(attack), Integer.parseInt(damage), Integer.parseInt(duration), Integer.parseInt(basePrice), Integer.parseInt(upgradeLevel), Integer.parseInt(upgradeCost), GameCharacter.Fire));
                    break;
                case "ice":
                    App.getCards().add(new Card(name, Integer.parseInt(attack), Integer.parseInt(damage), Integer.parseInt(duration), Integer.parseInt(basePrice), Integer.parseInt(upgradeLevel), Integer.parseInt(upgradeCost), GameCharacter.Ice));
                    break;

                default:
                    throw new UnsupportedOperationException("wrong character");
            }
            return new Result(true, "card added successfully");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    public static Result editCard(String name) {
        // TODO - sina
        return new Result(false, "UnsupportedOperationException");
    }

    public static Result removeCard(String name) {
        for (Card card : App.getCards()) {
            if(card.getName().equals(name)) {
                App.getCards().remove(card);
                break;
            }
        }
        start: for (User user : App.getUsers()) {
            for (Card card : user.getCards()) {
                if(card.getName().equals(name)) {
                    user.getCards().remove(card);
                    break start;
                }
            }
        }
        return new Result(true, "Card removed!");
    }
}
