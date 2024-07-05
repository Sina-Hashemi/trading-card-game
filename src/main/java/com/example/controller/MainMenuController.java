package com.example.controller;

import com.example.model.*;
import com.example.enums.*;

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
        if(!Game.getGuestPlayer().getPassword().equals(password)) {
            return new Result(false, "Password is wrong!");
        }
        int x;
        try {
            x = Integer.parseInt(bet);
        } catch (Exception e) {
            return new Result(false, "Bet value is not a valid number!");
        }
        Game.setBet(x);
        App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "Game started!");
    }
}
