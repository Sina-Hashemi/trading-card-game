package com.example.enums;

import java.util.Scanner;

import com.example.view.*;

public enum Menu {
    AdminMenu(new AdminMenu()),
    LoginMenu(new LoginMenu()),
    MainMenu(new MainMenu()),
    ProfileMenu(new ProfileMenu()),
    ShopMenu(new ShopMenu()),
    GameHistoryMenu(new GameHistoryMenu()),
    GameMenu(new GameMenu()),
    Exit(new ExitMenu());

    private final AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }

    public void checkCommand(Scanner scanner) {
        this.menu.check(scanner);
    }

}
