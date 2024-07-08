package com.example.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameHistoryMenuCommands {
    sort("^sort (Date|winLose|opponentName|opponentLevel) (ascending|descending)$"),
    changePage("^(next|previous) page$"),
    changePageByNum("^go to page (?<pageNum>\\d+)$"),
    challengeAgain("^challenge again -u (?<username>.+)$"),
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
        return RegexFormatter.regexToString(pattern);
    }
}
