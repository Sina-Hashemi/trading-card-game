package com.example.controller;

import com.example.model.*;
import com.example.enums.*;

public class ProfileMenuController {

    public static Result back() {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Entered main menu!");
    }

    public static Result printInfo() {
        String output = "";
        output += "Username: " + App.getLoggedInUser().getUsername();
        output += "\nPassword: " + App.getLoggedInUser().getPassword();
        output += "\nEmail: " + App.getLoggedInUser().getEmail();
        output += "\nNickname: " + App.getLoggedInUser().getNickname();
        output += "\nSecurity Question: " + SecurityQuestion.questions.get(App.getLoggedInUser().getPasswordRecoveryQuestion().getKey()) + " " + App.getLoggedInUser().getPasswordRecoveryQuestion().getKey();
        output += "\nLevel: " + App.getLoggedInUser().getLevel();
        output += "\nMax HP: " + App.getLoggedInUser().getMaxHP();
        output += "\nXP: " + App.getLoggedInUser().getXP();
        output += "\nMoney: " + App.getLoggedInUser().getMoney();

        // ArrayList<User> users = new ArrayList<>(App.getUsers());
        // users.sort(Comparator.comparing(User::getLevel).thenComparing(User::getExperience).reversed().thenComparing(User::getUsername));
        // for (int i = 1; i <= users.size(); i++) {
        //     if(users.get(i-1).getUsername().equals(App.getLoggedInUser().getUsername())) {
        //         System.out.println("rank: " + i);
        //     }
        // }
        return new Result(true, output);
    }

    public static Result changeUsername(String username) {
        if(username.equals("")) return new Result(false, "Username field is empty!");

        if(!LoginMenuController.getCommandMatcher(username, "^[a-zA-Z_0-9]+$").find()) {
            return new Result(false, "Incorrect format for username!");
        }
        User tempUser = LoginMenuController.searchUserByUsername(username);
        if(tempUser != null) return new Result(false, "Username already exists!");

        App.getLoggedInUser().setUsername(username);
        return new Result(true, "Username changed successfully!");
    }

    public static Result changeNickname(String nickname) {
        if(nickname.equals("")) return new Result(false, "Nickname field is empty!");

        App.getLoggedInUser().setNickname(nickname);
        return new Result(true, "Nickname changed successfully!");
    }

    public static Result changePassword(String oldPassword, String newPassword, String passwordConfirmation) {
        if(oldPassword.equals("")) return new Result(false, "Old password field is empty!");
        if(newPassword.equals("")) return new Result(false, "New password field is empty!");

        if(!App.getLoggedInUser().getPassword().equals(oldPassword)) return new Result(false, "Current password is incorrect!");
        if(newPassword.equals(oldPassword)) return new Result(false, "Please enter a new password!");
        if(!newPassword.equals(passwordConfirmation)) return new Result(false, "Please enter your new password again!");

        if(!LoginMenuController.checkPassword(newPassword).isSuccessful()) return LoginMenuController.checkPassword(newPassword);

        App.getLoggedInUser().setPassword(newPassword);
        return new Result(true, "Password changed succesfully");

    }

    public static Result changeEmail(String email) {
        if(email.equals("")) return new Result(false, "Email field is empty!");

        if(!LoginMenuController.getCommandMatcher(email, "^[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+$").find()) return new Result(false, "Not a valid email address!");

        App.getLoggedInUser().setEmail(email);
        return new Result(true, "Email changed successfully!");
    }
}
