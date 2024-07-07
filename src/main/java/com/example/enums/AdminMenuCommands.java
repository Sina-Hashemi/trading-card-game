package com.example.enums;

import java.util.regex.*;

public enum AdminMenuCommands {
    showCards("^show cards$"),
    showUsers("^show users$"),
    addCard("^add card -n (?<name>.+) -a (?<attack>.+) -d (?<damage>.+) -d (?<duration>.+) -bp (?<basePrice>.+) -ul (?<upgradeLevel>.+) -uc (?<upgradeCost>.+) -c (?<character>.+)$"),
    editCard("^edit card -id (?<cardNum>\\d+) (name|attack|playerDamage|duration|basePrice|upgradeLevel|upgradeCost|character) (.+)$"),
    removeCard("^remove card -id (?<cardNum>.+)$"),
    logout("^log out$");

    private final String pattern;

    AdminMenuCommands(String pattern) {
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
