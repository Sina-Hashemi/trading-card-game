package com.example.view;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.regex.Matcher;

import com.example.controller.ProfileMenuController;
import com.example.enums.ProfileMenuCommands;
import com.example.model.*;

public class ProfileMenu extends AppMenu {

    @Override
    public void check(Scanner scanner) {
        System.out.print("\u001B[33m");
        String input = scanner.nextLine();
        System.out.print("\u001B[0m");
        Matcher matcher;
        if(input.equals("show current menu")) System.out.println("Profile Menu");
        else if(input.equals("show commands"))
            for (ProfileMenuCommands command : EnumSet.allOf(ProfileMenuCommands.class))
                System.out.println(command);

        else if((matcher = ProfileMenuCommands.back.getCommandMatcher(input)).find()) {
            System.out.println(ProfileMenuController.back());
        }
        else if((matcher = ProfileMenuCommands.info.getCommandMatcher(input)).find()) {
            System.out.println(ProfileMenuController.printInfo());
        }
        else if ((matcher = ProfileMenuCommands.changeUsername.getCommandMatcher(input)).find()) {
            System.out.println(ProfileMenuController.changeUsername(matcher.group("username")));
        }
        else if ((matcher = ProfileMenuCommands.changeNickname.getCommandMatcher(input)).find()) {
            System.out.println(ProfileMenuController.changeNickname(matcher.group("nickname")));
        }
        else if ((matcher = ProfileMenuCommands.changePasswordRandom.getCommandMatcher(input)).find()) {
            String password = new RandomPasswordGenerator().getPassword();
            System.out.println("Your random password: \u001B[36m" + password + "\u001B[0m\n Please enter your password:");
            while(true) {
                System.out.print("\u001B[33m");
                input = scanner.nextLine();
                System.out.print("\u001B[0m");
                if(password.equals(input)) break;
                System.out.println("Please try again!");
            }

            Result result = ProfileMenuController.changePassword(matcher.group("oldPassword"), password, password);
            if(!result.isSuccessful()) {
                // shouldn't happen!
                System.out.println(result);
                return;
            }
            while(true) {
                Captcha captcha = new Captcha();
                System.out.println(captcha);
                System.out.print("\u001B[33m");
                input = scanner.nextLine();
                System.out.print("\u001B[0m");
                if(input.equals(captcha.getAns())) {
                    System.out.println(result);
                    break;
                }
                System.out.println("Captcha entered incorrectly. Try again!");
            }
        }
        else if ((matcher = ProfileMenuCommands.changePassword.getCommandMatcher(input)).find()) {
            Result result = ProfileMenuController.changePassword(matcher.group("oldPassword"), matcher.group("newPassword"), matcher.group("passwordConfirmation"));
            if(!result.isSuccessful()) {
                System.out.println(result);
                return;
            }
            while(true) {
                Captcha captcha = new Captcha();
                System.out.println(captcha);
                System.out.print("\u001B[33m");
                input = scanner.nextLine();
                System.out.print("\u001B[0m");
                if(input.equals(captcha.getAns())) {
                    System.out.println(result);
                    break;
                }
                System.out.println("Captcha entered incorrectly. Try again!");
            }
        }
        else if ((matcher = ProfileMenuCommands.changeEmail.getCommandMatcher(input)).find()) {
            System.out.println(ProfileMenuController.changeEmail(matcher.group("email")));
        }


        else System.out.println("Invalid command!");
    }
}
