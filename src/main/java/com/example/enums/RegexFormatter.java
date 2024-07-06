package com.example.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFormatter {
    public static String regexToString (String pattern) {

        Pattern patternDash = Pattern.compile("-\\S+");
        Pattern patternName = Pattern.compile("(\\(\\?<(?<name>[^>]+)>\\.\\+\\))");
        String patternCopy = pattern;
        Matcher matcherDash = patternDash.matcher(pattern);
        int dashCount = 0;
        while (matcherDash.find()) {
            dashCount++;
        }
        matcherDash = patternDash.matcher(pattern);
        for (int i = 0; i < dashCount; i++) {
            matcherDash.find();
            if (i == 0) {
                patternCopy = patternCopy.replace(matcherDash.group(0), ": Enter");
            } else if (i == dashCount - 1) {
                patternCopy = patternCopy.replace(matcherDash.group(0), "then");
            } else {
                patternCopy = patternCopy.replace(matcherDash.group(0), "and");
            }
        }
        Matcher matcherName = patternName.matcher(pattern);
        int nameCount = 0;
        while (matcherName.find()) {
            nameCount++;
        }
        matcherName = patternName.matcher(pattern);
        for (int i = 0; i < nameCount; i++) {
            matcherName.find();
            patternCopy = patternCopy.replace(matcherName.group(0), matcherName.group("name"));
        }

        // command ha
        return patternCopy.substring(1, patternCopy.length() - 1);
    }
}
