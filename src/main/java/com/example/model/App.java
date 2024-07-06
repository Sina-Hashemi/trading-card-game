package com.example.model;

import java.util.ArrayList;

import com.example.enums.Menu;

public class App {
    private static final String adminPass = "1234";

    private static User loggedInUser = null;
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Card> cards = new ArrayList<>();
    private static ArrayList<History> gameHistories = new ArrayList<>();
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

    public static ArrayList<Card> getCards() {
        return cards;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static String getAdminpass() {
        return adminPass;
    }

    public static ArrayList<History> getGameHistories() {
        return gameHistories;
    }
}
