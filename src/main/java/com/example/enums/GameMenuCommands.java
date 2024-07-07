package com.example.enums;

import java.util.regex.*;

public enum GameMenuCommands {
    selectCharacter("^-select character (?<character>.+)$"),
    selectCard("^-select card (?<cardNum>.+) player (?<playerNum>.+)$"),
    placeCard("^-place card (?<cardNum>.+) in block (?<blockNum>.+)$");

    private final String pattern;

    GameMenuCommands(String pattern) {
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
