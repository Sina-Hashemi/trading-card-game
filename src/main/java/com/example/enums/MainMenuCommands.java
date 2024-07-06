package com.example.enums;

import java.util.regex.*;

public enum MainMenuCommands {
    enterProfileMenu("^profile menu$"),
    enterShopMenu("^shop menu$"),
    enterGameHistoryMenu("^game history menu$"),
    // showCards("^show user cards$"),
    // startNewGame("^start game turns username (?<username>.+)$"),
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
        // TODO - DONE - ehsan - make it prettier
        return RegexFormatter.regexToString(pattern) ;
    }
}
