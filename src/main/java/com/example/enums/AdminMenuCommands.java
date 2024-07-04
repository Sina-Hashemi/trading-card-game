package com.example.enums;

import java.util.regex.*;

public enum AdminMenuCommands {
    back("^back$"),
    showCards("^show cards$"),
    showUsers("^show users$"),
    addCard("^add card -n (?<name>.+) -a (?<attack>.+) -d (?<damage>.+) -d (?<duration>.+) -bp (?<basePrice>.+) -ul (?<upgradeLevel>.+) -uc (?<upgradeCost>.+) -c (?<character>.+)$"),
    editCard("^edit card -n (?<name>.+)$"),
    removeCard("^remove card -n (?<name>.+)$");

    private final String pattern;

    AdminMenuCommands(String pattern) {
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
