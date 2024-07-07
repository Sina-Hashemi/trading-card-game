package com.example.enums;

import java.util.regex.*;

public enum ShopMenuCommands {
    showShop("^show available cards$"),
    showUpgrades("^show available upgrades$"),
    buyCard("^buy card (?<cardName>.+)$"),
    upgradeCard("^upgrade card (?<cardName>.+)$"),
    back("^back$");

    private final String pattern;

    ShopMenuCommands(String pattern) {
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
