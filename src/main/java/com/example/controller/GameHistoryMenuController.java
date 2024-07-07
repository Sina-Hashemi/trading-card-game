package com.example.controller;

import com.example.model.*;
import com.example.enums.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameHistoryMenuController {

    public static ArrayList<History> records = new ArrayList<>();
    public static int pageNum;

    public static Result back() {
        records.clear();
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Entered main menu!");
    }

    public static Result showBoard() {
        if(records.isEmpty()) {
            fillRecords();
        }

        return new Result(true, printRecords());
    }

    private static String printRecords() {
        String output = "Page " + (pageNum + 1) + ":\n";
        for (int i = 0 + 10 * pageNum; i < records.size() && i < 10 * (pageNum + 1); i++) {
            output += records.get(i).toString() + "\n";
        }
        return output;
    }

    private static void fillRecords() {
        for (Integer i : App.getLoggedInUser().getRecords()) {
            for (History history : App.getGameHistories()) {
                if(history.getID() == i) {
                    records.add(history);
                    break;
                }
            }
        }
        pageNum = 0;

        records.sort(Comparator.comparing(History::getGameTime));
    }

    public static Result sort(String typeOfSort, String ascDesc) {
        switch (typeOfSort) {
            case "Date":
                records.sort(Comparator.comparing(History::getGameTime));
                break;
            case "winLose":
                records.sort(Comparator.comparing(History::getGameResult));
                break;
            case "opponentName":
                records.sort(Comparator.comparing(record -> record.getRival().getUsername()));
                break;
            case "opponentLevel":
                records.sort(Comparator.comparing(record -> record.getRival().getLevel()));
                break;
        }
        if(ascDesc.equals("descending")) Collections.reverse(records);

        return new Result(true, "Done!");
    }

    public static Result changePage(String group) {
        if (group.equals("next")) {
            pageNum += 1;
            return new Result(true, "Page is now " + (pageNum + 1));
        }
        else if (group.equals("previous")) {
            if(pageNum > 0) {
                pageNum -= 1;
                return new Result(true, "Page is now " + (pageNum + 1));
            }
            return new Result(false, "We dont have page zero!");
        }
        return new Result(false, "Not a valid input");
    }

    public static Result changePageByNum(String group) {
        int x;
        try {
            x = Integer.parseInt(group);
        } catch (Exception e) {
            return new Result(false, "Page num is not a number!");
        }
        if(x > 10 || x <= 0) {
            return new Result(false, "Not a valid number");
        }
        pageNum = x - 1;
        return new Result(true, "Page is now " + (pageNum + 1));
    }

}
