package com.example.enums;

import java.util.regex.*;

public enum GameHistoryMenuCommands {
    back("^back$");

    private final String pattern;

    GameHistoryMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getCommandMatcher(String input) {
        return Pattern.compile(this.pattern).matcher(input);
    }

    @Override
    public String toString() {
        // TODO - DONE - ehsan - make it prettier
        return RegexFormatter.regexToString(pattern);
    }
}
