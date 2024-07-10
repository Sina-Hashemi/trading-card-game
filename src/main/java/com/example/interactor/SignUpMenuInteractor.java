package com.example.interactor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.Main;
import com.example.controller.LoginMenuController;
import com.example.model.Captcha;
import com.example.model.RandomPasswordGenerator;
import com.example.model.Result;
import com.example.model.SecurityQuestion;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpMenuInteractor implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBox ;
    @FXML
    private Label CaptchaQ;
    @FXML
    private Label SignUpPrompt ;
    @FXML
    private TextField userName ;
    @FXML
    private TextField password;
    @FXML
    private TextField passwordConfirmation;
    @FXML
    private TextField nickName;

    @FXML
    private TextField securityConfirmation;
    @FXML
    private TextField email;
    @FXML
    private TextField captchaAnswer ;

    private String [] questions = new String[3];

    private static Captcha captcha;

    @FXML
    protected void onRandomButtonClick() {
        String randPassword = new RandomPasswordGenerator().getPassword();
        password.setText(randPassword);
    }

    @FXML
    protected void onSignUpButtonClick() throws IOException {
        String num = "";
        for (int i = 0; i < questions.length; i++) {
            if(questions[i].equals(choiceBox.getValue())) {
                num = Integer.toString(i + 1);
                break;
            }
        }
       Result result = LoginMenuController.register(userName.getText(), password.getText(), passwordConfirmation.getText(), email.getText(), nickName.getText());
       if(result.isSuccessful()) {
           result = LoginMenuController.pickQuestion(num, securityConfirmation.getText(), securityConfirmation.getText());
           if(result.isSuccessful()) {
               if(captchaAnswer.getText().equals(captcha.getAns())) {
                   Main.setRoot("LoginMenu");
               }
               else {
                   SignUpPrompt.setText("Captcha entered incorrectly. Try again!");
               }
           }
           else {
               SignUpPrompt.setText(result.toString());
           }
       }
       else {
           SignUpPrompt.setText(result.toString());
       }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < questions.length; i++) {
            questions[i] = SecurityQuestion.questions.get(i + 1);
        }
        choiceBox.getItems().addAll(questions) ;
        captcha = new Captcha();
        CaptchaQ.setText(captcha.toString());
    }
}