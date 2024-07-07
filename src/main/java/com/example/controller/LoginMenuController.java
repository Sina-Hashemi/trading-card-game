package com.example.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.model.*;
import com.example.enums.*;

public class LoginMenuController {

    public static Matcher getCommandMatcher(String input, String pattern) {
        return Pattern.compile(pattern).matcher(input);
    }

    public static void exit() {
        App.setCurrentMenu(Menu.Exit);
    }

    public static Result LoginAsAdmin(String password) {
        if(!password.equals(App.getAdminpass())) return new Result(false, "password is wrong");
        App.setCurrentMenu(Menu.AdminMenu);
        return new Result(true, "Logged in as Admin!");
    }

    public static Result register(String username, String password, String passwordConfirmation, String email, String nickname) {
        if(username.equals("")) return new Result(false, "Username field is empty!");
        if(password.equals("")) return new Result(false, "Password field is empty!");
        if(passwordConfirmation.equals("")) return new Result(false, "PasswordConfiguration field is empty!");
        if(email.equals("")) return new Result(false, "Email field is empty!");
        if(nickname.equals("")) return new Result(false, "Nickname field is empty!");

        if(!getCommandMatcher(username, "^[a-zA-Z_0-9]+$").find()) {
            return new Result(false, "Incorrect format for username!");
        }
        User tempUser = searchUserByUsername(username);
        if(tempUser != null) return new Result(false, "Username already exists!");

        if(!checkPassword(password).isSuccessful()) return checkPassword(password);

        if(!password.equals(passwordConfirmation)) return new Result(false, "Password and password configuration are not the same!");

        if(!getCommandMatcher(email, "^[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+$").find()) return new Result(false, "Not a valid email address!");

        tempUser = new User(username, password, email, nickname);
        App.getUsers().add(tempUser);
        return new Result(true, "User created successfully. Please choose a security question :");
    }

    public static Result pickQuestion(String questionNumber, String answer, String answerConfirmation) {
        try {
            int questionNumberInteger = Integer.parseInt(questionNumber);
            if (questionNumberInteger > 3 || questionNumberInteger < 1){
                return new Result(false,"Please choose an Integer between 1 and 3 for security question");
            }
            if(!answer.equals(answerConfirmation)) return new Result(false, "answer and answerConfirmation doesnt match");
            App.getUsers().get(App.getUsers().size() - 1).setPasswordRecoveryQuestion(new SecurityQuestion(questionNumberInteger, answer));
            return new Result(true, "Done! Now please answer the captcha.");
        }
        catch (Exception e){
            return new Result(false,"Please choose an Integer between 1 and 3 for security question");
        }
    }

    public static Result login(String username, String password) {
        if(BlockingTime.remainingTime() > 0) return new Result(false, "Try again in "+ BlockingTime.remainingTime() + " seconds");

        if(username.equals("")) return new Result(false, "Username field is empty!");
        if(password.equals("")) return new Result(false, "Password field is empty!");

        User user = searchUserByUsername(username);
        if(user == null) return new Result(false, "Username doesn't exist!");
        if(user.getPassword().equals(password)) {
            App.setLoggedInUser(user);
            App.setCurrentMenu(Menu.MainMenu);
            BlockingTime.reset();
            return new Result(true, "user logged in successfully!");
        }
        BlockingTime.increase();
        return new Result(false, "Password and Username don't match!");
    }

    public static Result forgetPassword(String username) {
        if(username.equals("")) return new Result(false, "Username field is empty!");

        User user = searchUserByUsername(username);
        if(user == null) return new Result(false, "Username doesn't exist!");

        return new Result(true, SecurityQuestion.questions.get(user.getPasswordRecoveryQuestion().getKey()) + "%" + user.getPasswordRecoveryQuestion().getAns());
    }

    public static Result resetPassword(String username, String password) {
        if(password.equals("")) return new Result(false, "Username field is empty!");

        if(!checkPassword(password).isSuccessful()) return checkPassword(password);

        User user = searchUserByUsername(username);
        user.setPassword(password);
        return new Result(true, "Password reseted succesfully");
    }

    public static User searchUserByUsername(String username) {
        for (User user : App.getUsers()) {
            if(user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public static Result checkPassword(String password) {
        if(!getCommandMatcher(password, "^.{8,20}$").find()) return new Result(false, "The password must have at least 8 characters!");
        if(!getCommandMatcher(password, "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*:;\\<\\=\\>\\?\\[\\]\\\\\\_`])[\\w!@#$%^&*:;\\<\\=\\>\\?\\[\\]\\\\\\_`]{8,20}$").find()) return new Result(false, "The password must have at least one lowercase English letter and one uppercase English letter and one number and one non-alphanumeric character!"); // (?![0-9])
        return new Result(true, "");
    }
}
