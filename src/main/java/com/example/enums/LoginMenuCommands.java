package com.example.enums;

import java.util.regex.*;

public enum LoginMenuCommands { // TODO - Sina - empty input fix
    // Register("^user create -u (?<username>.+) -p (?<password>.+) (?<passwordConfirmation>.+) -email (?<email>.+) -n (?<nickname>.+)$"),
    Register("^\\s*user\\s+create\\s+-u\\s*(?<username>.*)\\s+-p\\s*(?<password>[^\\s]*)\\s*(?<passwordConfirmation>.*)\\s+-email\\s*(?<email>.*)\\s+-n\\s*(?<nickname>.*)\\s*$"),
    RegisterRandom("^user create -u (?<username>.+) -p random -email (?<email>.+) -n (?<nickname>.+)$"),
    QuestionPick("^question pick -q (?<questionNumber>.+) -a (?<answer>.+) -c (?<answerConfirmation>.+)$"),
    Login("^user login -u (?<username>.+) -p (?<password>.+)$"),
    LoginAsAdmin("^admin login -p (?<password>.+)$"),
    ForgetPassword("^Forgot my password -u (?<username>.+)$"),
    Exit("^Exit$");

    private final String pattern;

    LoginMenuCommands(String pattern) {
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
