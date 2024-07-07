package com.example.controller;

import com.example.model.*;
import com.example.enums.*;
import com.example.model.Card.GameCharacter;
import com.example.model.User.CardLevel;

import java.util.ArrayList;
import java.util.Random;

public class MainMenuController {

    public static Result logout() {
        Result result = new Result(true, "User " + App.getLoggedInUser().getUsername() + " logged out successfully!");
        App.setLoggedInUser(null);
        App.setCurrentMenu(Menu.LoginMenu);
        return result;
    }

    public static Result enterProfileMenu() {
        App.setCurrentMenu(Menu.ProfileMenu);
        return new Result(true, "Entered profile menu!");
    }

    public static Result enterShopMenu() {
        ShopMenuController.initialize();
        App.setCurrentMenu(Menu.ShopMenu);
        return new Result(true, "Entered shop menu!");
    }

    public static Result enterGameHistoryMenu() {
        App.setCurrentMenu(Menu.GameHistoryMenu);
        return new Result(true, "Entered game history menu!");
    }

    public static Result preparePlayerTwo(String username) {
        for (User user : App.getUsers()) {
            if(user.getUsername().equals(username)) {
                Game.startGame(App.getLoggedInUser(), user);
                return new Result(true, "Please enter password for " + username);
            }
        }
        return new Result(false, "Username not found!");
    }

    public static Result checkSecondUser(String password, String bet) {
        if(!Game.getGuestPlayer().getPlayer().getPassword().equals(password)) {
            return new Result(false, "Password is wrong!");
        }
        int x;
        try {
            x = Integer.parseInt(bet);
        } catch (Exception e) {
            return new Result(false, "Bet value is not a valid number!");
        }
        if(Game.getHostPlayer().getPlayer().getMoney() < x) return new Result(false, "Host player doesnt have enough money!");
        if(Game.getGuestPlayer().getPlayer().getMoney() < x) return new Result(false, "Guest player doesnt have enough money!");
        Game.setBet(x);
        App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "Game started!");
    }

    public static Result StarterPack() {
        if(App.getLoggedInUser().getCards().isEmpty()) {
            ArrayList<Integer> nums = new ArrayList<>();
            while (App.getLoggedInUser().getCards().size() < 20) {
                int x = new Random().nextInt(App.getCards().size());
                if(nums.contains(x)) continue;
                nums.add(x);
                if(App.getCards().get(x).getCharacter() == GameCharacter.None)
                    App.getLoggedInUser().getCards().add(new CardLevel(App.getCards().get(x), 0));
                    else
                    App.getLoggedInUser().getCards().add(new CardLevel(App.getCards().get(x), 1));
            }
            return new Result(true, "Starter pack opened!");
        }
        else return new Result(false, null);
    }
}
