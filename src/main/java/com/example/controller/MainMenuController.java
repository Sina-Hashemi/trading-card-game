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

}
