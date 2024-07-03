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

}
