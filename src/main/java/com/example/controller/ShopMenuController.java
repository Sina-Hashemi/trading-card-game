package com.example.controller;

import com.example.model.*;
import com.example.model.Card.GameCharacter;
import com.example.enums.*;
import com.example.model.User.CardLevel;

import java.util.ArrayList;
public class ShopMenuController {

    private static ArrayList<Card> purchaseableCards;
    private static ArrayList<CardLevel> UpgradableCards;

    public static void initialize() {
        purchaseableCards = new ArrayList<>();
        UpgradableCards = new ArrayList<>();

        start: for (Card card : App.getCards()) {
            for (CardLevel playerCard : App.getLoggedInUser().getCards()) {
                if(card.getID() == playerCard.getCard().getID()) continue start;
            }
            purchaseableCards.add(card);
        }

        for (CardLevel playerCard : App.getLoggedInUser().getCards()) {
            if(playerCard.getLevel() == 0) continue;
            UpgradableCards.add(playerCard);
        }

    }

    public static Result back() {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Entered main menu!");
    }

    public static Result showShop() {
        String output = "";

        for (Card card : purchaseableCards) {
            String color = "\u001B[32m";
            if(App.getLoggedInUser().getMoney() < card.getBasePrice()) color = "\u001B[31m";
            output += card.getName() + " A/D point:" + card.getAttack() + " Damage: " + card.getPlayerDamage() + " Duration: " + card.getDuration() + " Character: " + card.getCharacter() + " Price: " + color + card.getBasePrice() + "\u001B[0m\n";
        }

        return new Result(true, output);
    }

    public static Result showUpgrades() {
        String output = "";

        for (CardLevel card : UpgradableCards) {
            String levelColor = "\u001B[32m", moneyColor = "\u001B[32m";
            if(App.getLoggedInUser().getLevel() < card.getCard().getUpgradeLevel()) levelColor = "\u001B[31m";
            if(App.getLoggedInUser().getMoney() < card.getCard().getUpgradeCost() * card.getLevel()) moneyColor = "\u001B[31m";
            output += card.getCard().getName() + " A/D point:" + card.getCard().getAttack() + " -> " + (card.getCard().getAttack() + 5) + " Damage: " + card.getCard().getPlayerDamage() + " -> " + ((int) card.getCard().getPlayerDamage() * 1.25) + " Duration: " + card.getCard().getDuration() + " Character: " + card.getCard().getCharacter() + " Upgrade Price: " + moneyColor + (card.getCard().getUpgradeCost() * card.getLevel()) + "\u001B[0m min Upgrade Level needed" + levelColor + card.getCard().getUpgradeLevel() + "\u001B[0m\n";
        }

        return new Result(true, output);
    }

    public static Result buyCard(String cardName) {
        for (Card card : purchaseableCards) {
            if(card.getName().equals(cardName)) {
                if(App.getLoggedInUser().getMoney() < card.getBasePrice()) return new Result(false, "you dont have enough moeny");

                App.getLoggedInUser().setMoney(App.getLoggedInUser().getMoney() - card.getBasePrice());
                if(card.getCharacter() == GameCharacter.None) App.getLoggedInUser().getCards().add(new CardLevel(card, 0));
                else App.getLoggedInUser().getCards().add(new CardLevel(card, 1));
                purchaseableCards.remove(card);

                return new Result(true, "card bought successfully!");
            }
        }
        return new Result(false, "Wrong card name!");
    }

    public static Result upgradeCard(String cardName) {
        for (CardLevel card : UpgradableCards) {
            if(card.getCard().getName().equals(cardName)) {
                if(App.getLoggedInUser().getLevel() < card.getCard().getUpgradeLevel()) return new Result(false, "your level is low!");
                if(App.getLoggedInUser().getMoney() < card.getCard().getUpgradeCost() * card.getLevel()) return new Result(false, "you dont have enough moeny");

                App.getLoggedInUser().setMoney(App.getLoggedInUser().getMoney() - card.getCard().getUpgradeCost() * card.getLevel());
                card.setLevel(card.getLevel() + 1);

                return new Result(true, "card bought successfully!");
            }
        }
        return new Result(false, "Wrong card name!");
    }

    public static Result status() {
        return new Result(true, App.getLoggedInUser().getUsername() + " Money: " + App.getLoggedInUser().getMoney() + " Level: " + App.getLoggedInUser().getLevel());
    }
}
