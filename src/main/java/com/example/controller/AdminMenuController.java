package com.example.controller;

import com.example.model.*;
import com.example.model.Card.GameCharacter;
import com.example.model.User.CardLevel;
import com.example.enums.*;

public class AdminMenuController {

    public static Result logout() {
        App.setCurrentMenu(Menu.LoginMenu);
        return new Result(true, "Entered login menu!");
    }

    public static Result showCards() {
        String output = "";
        for (Card card : App.getCards()) {
            output += card.toString() + "\n";
        }
        return new Result(true, output);
    }

    public static Result showUsers() {
        String output = "";
        for (User user : App.getUsers()) {
            output += user.toString() + "\n";
        }
        return new Result(true, output);
    }

    public static Result addCard(String name, String attack, String damage, String duration, String basePrice,
            String upgradeLevel, String upgradeCost, String character) {

        for (Card card : App.getCards()) {
            if(card.getName().equals(name)) return new Result(false, "Name should be unique!");
        }

        int x;
        try {
            x = Integer.parseInt(attack);
            if(x > 100 || x < 10) return new Result(false, "Attack/Defence should be from 10 to 100");
        } catch (Exception e) {
            return new Result(false, "Attack/Defence point is not a valid number!");
        }
        try {
            x = Integer.parseInt(duration);
            if(x > 5 || x < 1) return new Result(false, "Duration should be from 1 to 5");
        } catch (Exception e) {
            return new Result(false, "Duration is not a valid number!");
        }
        try {
            x = Integer.parseInt(damage);
            if(x > 50 || x < 10) return new Result(false, "Damage should be from 10 to 50");
        } catch (Exception e) {
            return new Result(false, "Damage is not a valid number!");
        }
        try {
            x = Integer.parseInt(basePrice);
            if(x < 0) return new Result(false, "Price should be positive");
        } catch (Exception e) {
            return new Result(false, "Price is not a valid number!");
        }
        try {
            x = Integer.parseInt(upgradeLevel);
            if(x < 1) return new Result(false, "upgradeLevel should be positive");
        } catch (Exception e) {
            return new Result(false, "upgradeLevel is not a valid number!");
        }
        try {
            x = Integer.parseInt(upgradeCost);
            if(x < 0) return new Result(false, "upgradeCost should be positive");
        } catch (Exception e) {
            return new Result(false, "upgradeCost is not a valid number!");
        }

        try {
            App.getCards().add(new Card(name, Integer.parseInt(attack), Integer.parseInt(damage), Integer.parseInt(duration), Integer.parseInt(basePrice), Integer.parseInt(upgradeLevel), Integer.parseInt(upgradeCost), GameCharacter.valueOf(character)));
            return new Result(true, "card added successfully");
        } catch (Exception e) {
            return new Result(false, "Not a valid character!");
        }
    }

    public static Result editCard(int ID, String what, String todo) {
        Card card = null;
        for (Card tempCard : App.getCards()) {
            if(tempCard.getID() == ID) {
                card = tempCard;
                break;
            }
        }

        int x;
        if(what.equals("name")) {
            for (Card tempCard : App.getCards()) {
                if(tempCard.getName().equals(todo)) return new Result(false, "Name should be unique!");
            }
            card.setName(todo);
            return new Result(true, "name changed successfully!");
        }
        else if(what.equals("attack")) {
            try {
                x = Integer.parseInt(todo);
                if(x > 100 || x < 10) return new Result(false, "Attack/Defence should be from 10 to 100");
            } catch (Exception e) {
                return new Result(false, "Attack/Defence point is not a valid number!");
            }
            card.setAttack(x);
            return new Result(true, "Attack/Defence point changed successfully!");
        }
        else if(what.equals("playerDamage")) {
            try {
                x = Integer.parseInt(todo);
                if(x > 50 || x < 10) return new Result(false, "Damage should be from 10 to 50");
            } catch (Exception e) {
                return new Result(false, "Damage is not a valid number!");
            }
            card.setPlayerDamage(x);
            return new Result(true, "Damage changed successfully!");
        }
        else if(what.equals("duration")) {
            try {
                x = Integer.parseInt(todo);
                if(x > 5 || x < 1) return new Result(false, "Duration should be from 1 to 5");
            } catch (Exception e) {
                return new Result(false, "Duration is not a valid number!");
            }
            card.setDuration(x);
            return new Result(true, "Duration changed successfully!");
        }
        else if(what.equals("basePrice")) {
            try {
                x = Integer.parseInt(todo);
                if(x < 0) return new Result(false, "Price should be positive");
            } catch (Exception e) {
                return new Result(false, "Price is not a valid number!");
            }
            card.setBasePrice(x);
            return new Result(true, "Price changed successfully!");
        }
        else if(what.equals("upgradeLevel")) {
            try {
                x = Integer.parseInt(todo);
                if(x < 1) return new Result(false, "upgradeLevel should be positive");
            } catch (Exception e) {
                return new Result(false, "upgradeLevel is not a valid number!");
            }
            card.setUpgradeLevel(x);
            return new Result(true, "upgradeLevel changed successfully!");
        }
        else if(what.equals("upgradeCost")) {
            try {
                x = Integer.parseInt(todo);
                if(x < 0) return new Result(false, "upgradeCost should be positive");
            } catch (Exception e) {
                return new Result(false, "upgradeCost is not a valid number!");
            }
            card.setUpgradeCost(x);
            return new Result(true, "upgradeCost changed successfully!");
        }
        else if(what.equals("character")) {
            try {
                card.setCharacter(GameCharacter.valueOf(todo));
                return new Result(true, "Character changed successfully");
            } catch (Exception e) {
                return new Result(false, "Not a valid character!");
            }
        }
        else {
            return new Result(false, "Wrong input!");
        }

    }

    public static Result checkCard(String cardNum) {
        int ID;
        try {
            ID = Integer.parseInt(cardNum);
        } catch (Exception e) {
            return new Result(false, "Not a valid number!");
        }

        boolean found = false;
        for (Card card : App.getCards()) {
            if(card.getID() == ID) {
                found = true;
                break;
            }
        }
        if(!found) return new Result(false, "wrong card number!");

        return new Result(true, "Are you sure you want to do this?(Y/n)");
    }

    public static Result removeCard(int ID) {
        for (User user : App.getUsers()) {
            for (CardLevel card : user.getCards()) {
                if(card.getCard().getID() == ID) {
                    user.getCards().remove(card);
                    break;
                }
            }
        }
        for (Card card : App.getCards()) {
            if(card.getID() == ID) {
                App.getCards().remove(card);
                break;
            }
        }
        return new Result(true, "Card removed!");
    }
}
