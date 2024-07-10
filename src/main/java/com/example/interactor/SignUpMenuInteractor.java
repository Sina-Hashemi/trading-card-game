package com.example.interactor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.Main;
import com.example.controller.LoginMenuController;
import com.example.model.Captcha;
import com.example.model.RandomPasswordGenerator;
import com.example.model.Result;

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
    // TODO sina fill questions
    private String [] questions =  {"a", "b" , "c "};

    private static Captcha captcha;

    @FXML
    protected void onRandomButtonClick() {
        String randPassword = new RandomPasswordGenerator().getPassword();
        password.setText(randPassword);
    }

    @FXML
    protected void onSignUpButtonClick() throws IOException {
        // TODO sina - fix it line 57
//        Result result = LoginMenuController.register(userName.getText(), password.getText(), passwordConfirmation.getText(), email.getText(), nickName.getText());
//        if(result.isSuccessful()) {
//            result = LoginMenuController.pickQuestion("1", securityQuestion.getText(), securityConfirmation.getText());
//            if(result.isSuccessful()) {
//                if(captchaAnswer.getText().equals(captcha.getAns())) {
//                    Main.setRoot("LoginMenu");
//                }
//                else {
//                    SignUpPrompt.setText("Captcha entered incorrectly. Try again!");
//                }
//            }
//            else {
//                SignUpPrompt.setText(result.toString());
//            }
//        }
//        else {
//            SignUpPrompt.setText(result.toString());
//        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll(questions) ;
        captcha = new Captcha();
        CaptchaQ.setText(captcha.toString());

        // TODO show questions
    }
}