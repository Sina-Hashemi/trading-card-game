package com.example.enums;

import java.util.regex.*;

public enum GameMenuCommands {
    back("^back$");

    private final String pattern;

    GameMenuCommands(String pattern) {
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
