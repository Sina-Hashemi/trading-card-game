package com.example.enums;

import java.util.regex.*;

public enum MainMenuCommands {
    enterProfileMenu("^profile menu$"),
    // listOfUsers("^list of users$"),
    // scoreboard("^scoreboard$"),
    // enterShopMenu("^shop menu$"),
    // startNewGame("^start game turns count (?<turns>\\d+) username (?<username>.+)$"),
    logout("^log out$");

    private final String pattern;

    MainMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getCommandMatcher(String input) {
        return Pattern.compile(this.pattern).matcher(input);
    }

    @Override
    public String toString() {
        // TODO - ehsan - make it prettier
        return pattern;
    }
}
