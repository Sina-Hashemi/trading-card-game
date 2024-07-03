package com.example.enums;

import java.util.regex.*;

public enum ShopMenuCommands {
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
        // TODO - ehsan - make it prettier
        return pattern;
    }
}
