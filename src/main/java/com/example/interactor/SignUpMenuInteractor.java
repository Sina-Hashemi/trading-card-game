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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SignUpMenuInteractor implements Initializable {
    @FXML
    private Label CaptchaQ;
    @FXML
    private Label SignUpPrompt ;
    @FXML
    private TextArea userName ;
    @FXML
    private TextArea password;
    @FXML
    private TextArea passwordConfirmation;
    @FXML
    private TextArea nickName;
    @FXML
    private TextArea securityQuestion;
    @FXML
    private TextArea securityConfirmation;
    @FXML
    private TextArea email;
    @FXML
    private TextArea captchaAnswer ;

    private static Captcha captcha;

    @FXML
    protected void onRandomButtonClick() {
        String randPassword = new RandomPasswordGenerator().getPassword();
        password.setText(randPassword);
    }

    @FXML
    protected void onSignUpButtonClick() throws IOException {
        Result result = LoginMenuController.register(userName.getText(), password.getText(), passwordConfirmation.getText(), email.getText(), nickName.getText());
        if(result.isSuccessful()) {
            result = LoginMenuController.pickQuestion("1", securityQuestion.getText(), securityConfirmation.getText());
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
        captcha = new Captcha();
        CaptchaQ.setText(captcha.toString());

        // TODO show questions
    }
}