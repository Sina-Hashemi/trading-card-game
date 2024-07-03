package com.example.model;

import java.util.ArrayList;

import com.example.enums.Menu;

public class App {
    private static User loggedInUser = null;
    private static ArrayList<User> users = new ArrayList<>();
    private static Menu currentMenu = Menu.LoginMenu;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

}
