package com.example.enums;

import java.util.regex.*;

public enum ProfileMenuCommands { // TODO - Sina - empty input fix
    back("^back$"),
    info("^Show information$"),
    changeUsername("^Profile change -u (?<username>.+)$"),
    changeNickname("^Profile change -n (?<nickname>.+)$"),
    changePassword("^profile change password -o (?<oldPassword>.+) -n (?<newPassword>.+) (?<passwordConfirmation>.+)$"),
    changePasswordRandom("^profile change password -o (?<oldPassword>.+) random)$"),
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
        // TODO - DONE - ehsan - make it prettier
        return RegexFormatter.regexToString(pattern) ;
    }
}
