package com.example.enums;

import java.util.regex.*;

public enum ProfileMenuCommands {
    back("^back$"),
    info("^Show information$"),
    changeUsername("^Profile change -u (?<username>.+)$"),
    changeNickname("^Profile change -n (?<nickname>.+)$"),
    changePassword("^Profile change password -o (?<oldPassword>.+) -n (?<newPassword>.+) (?<passwordConfirmation>.+)$"),
    changePasswordRandom("^Profile change password -o (?<oldPassword>.+) -n random$"),
    changeEmail("^Profile change -e (?<email>.+)$");

    private final String pattern;

    ProfileMenuCommands(String pattern) {
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
