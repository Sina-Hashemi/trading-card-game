package com.example.controller;

import com.example.model.*;
import com.example.enums.*;

public class GameHistoryMenuController {

    public static Result back() {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Entered main menu!");
    }

        // ArrayList<User> users = new ArrayList<>(App.getUsers());
        // users.sort(Comparator.comparing(User::getLevel).thenComparing(User::getExperience).reversed().thenComparing(User::getUsername));
        // for (int i = 1; i <= users.size(); i++) {
        //     if(users.get(i-1).getUsername().equals(App.getLoggedInUser().getUsername())) {
        //         System.out.println("rank: " + i);
        //     }
        // }
}
