package com.example.enums;

import java.util.regex.*;

public enum MainMenuCommands {
    enterProfileMenu("^profile menu$"),
    enterShopMenu("^shop menu$"),
    enterGameHistoryMenu("^game history menu$"),
    startNewGame("^start normal game with -u (?<username>.+)$"),
    startNewBetGame("^start bet game with -u (?<username>.+) -b (?<bet>.+)$"),
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
        return RegexFormatter.regexToString(pattern);
    }
}
