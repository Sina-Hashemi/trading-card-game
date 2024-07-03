package com.example.view;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.regex.Matcher;

import com.example.controller.LoginMenuController;
import com.example.enums.LoginMenuCommands;
import com.example.model.*;

public class LoginMenu extends AppMenu {

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        if(input.equals("show current menu")) System.out.println("Register/Login Menu");
        else if(input.equals("show commands"))
            for (LoginMenuCommands command : EnumSet.allOf(LoginMenuCommands.class))
                System.out.println(command);

        else if((matcher = LoginMenuCommands.Register.getCommandMatcher(input)).find()) {
            Result result = LoginMenuController.register(matcher.group("username"), matcher.group("password"), matcher.group("passwordConfirmation"), matcher.group("email"), matcher.group("nickname"));
            System.out.println(result);
            if(result.isSuccessful()) {
                handleQuestionAndCaptcha(scanner);
            }
        }
        else if((matcher = LoginMenuCommands.RegisterRandom.getCommandMatcher(input)).find()) {
            String password = new RandomPasswordGenerator().getPassword();
            System.out.println("Your random password: " + password + "\n Please enter your password:");
            while(true) {
                input = scanner.nextLine();
                if(password.equals(input)) break;
                System.out.println("Please try again!");
            }
            Result result = LoginMenuController.register(matcher.group("username"), password, password, matcher.group("email"), matcher.group("nickname"));
            System.out.println(result);
            if(result.isSuccessful()) {
                handleQuestionAndCaptcha(scanner);
            }
        }
        else if((matcher = LoginMenuCommands.Login.getCommandMatcher(input)).find()) {
            System.out.println(LoginMenuController.login(matcher.group("username"), matcher.group("password")));
        }
        else if((matcher = LoginMenuCommands.ForgetPassword.getCommandMatcher(input)).find()) {
            Result result = LoginMenuController.forgetPassword(matcher.group("username"));
            String username = matcher.group("username");
            if(result.isSuccessful()) {
                while (true) {
                    System.out.println(result.getMessage().split("%")[0]);
                    input = scanner.nextLine();
                    if(result.getMessage().split("%")[1].equals(input)) {
                        while(true) {
                            System.out.println("Enter new password:");
                            input = scanner.nextLine();
                            result = LoginMenuController.resetPassword(username, input);
                            System.out.println(result);
                            if(result.isSuccessful()) break;
                        }
                        break;
                    }
                }
            }
            else {
                System.out.println(result);
            }
        }
        else if((matcher = LoginMenuCommands.Exit.getCommandMatcher(input)).find()) {
            LoginMenuController.exit();
        }

        else System.out.println("Invalid command!");
    }

    private void handleQuestionAndCaptcha(Scanner scanner) {
        String input;
        Matcher matcher;
        Result result;
        SecurityQuestion.printQustions();
        while(true) {
            input = scanner.nextLine();
            if((matcher = LoginMenuCommands.QuestionPick.getCommandMatcher(input)).find()) {
                result = LoginMenuController.pickQuestion(matcher.group("questionNumber"), matcher.group("answer"), matcher.group("answerCnfirmation"));
                System.out.println(result);
                if(result.isSuccessful()) break;
                continue;
            }
            System.out.println("Invalid command!");
        }
        while(true) {
            Captcha captcha = new Captcha();
            System.out.println(captcha);
            input = scanner.nextLine();
            if(input.equals(captcha.getAns())) {
                System.out.println("Everything is alright. Let's go!");
                break;
            }
            System.out.println("Captcha entered incorrectly. Try again!");
        }
    }
}
