package com.example.interactor;

import java.io.IOException;

import com.example.Main;
import com.example.controller.LoginMenuController;
import com.example.model.RandomPasswordGenerator;
import com.example.model.Result;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginMenuInteractor {
    @FXML
    private Label LoginCheck ;
    @FXML
    private Label securityCheckText ;
    @FXML
    private Label changePasswordText ;
    @FXML
    private TextField userName ;
    @FXML
    private TextField  password;
    @FXML
    private TextField  newPassword;
    @FXML
    private TextField newPasswordConfirmation;
    @FXML
    private CheckBox forgotPassword;
    @FXML
    private Label securityQuestion;
    @FXML
    private TextField securityAns;
    @FXML
    private Button checkSecurity;
    @FXML
    private Button changePassword;
    @FXML
    private Button randomPassword;


    Result result;


    @FXML
    protected void onLoginButtonClick() throws IOException {
        result = LoginMenuController.login(userName.getText(), password.getText());
        if(result.isSuccessful()) {
            Main.setRoot("MainMenu");
        }
        else {
            LoginCheck.setText(result.toString());
        }
    }

    @FXML
    protected void onForgotPasswordCheck() {
        boolean isSelected = forgotPassword.isSelected();

        result = LoginMenuController.forgetPassword(userName.getText());
        if(result.isSuccessful()) {
            securityQuestion.setText(result.getMessage().split("%")[0]);
        }
        else {
            forgotPassword.setSelected(false);
            isSelected = false;
            LoginCheck.setText(result.toString());
        }

        securityQuestion.setVisible(isSelected);
        securityAns.setVisible(isSelected);
        checkSecurity.setVisible(isSelected);
    }

    @FXML
    protected void onCheckButtonCheck() {
        if(result.getMessage().split("%")[1].equals(securityAns.getText())) {
            newPassword.setVisible(true);
            newPasswordConfirmation.setVisible(true);
            changePassword.setVisible(true);
            randomPassword.setVisible(true);
            changePasswordText.setVisible(true);
        }
    }

    @FXML
    protected void onChangePasswordCheck() {
        if(!newPassword.getText().equals(newPasswordConfirmation.getText())) {
            changePasswordText.setText("Password doesnt match passwordConfirmation");
            return;
        }

        result = LoginMenuController.resetPassword(userName.getText(), newPassword.getText());
        changePasswordText.setText(result.toString());
    }

    @FXML
    protected void onRandomPasswordButtonCheck() {
        String randPassword = new RandomPasswordGenerator().getPassword();
        newPassword.setText(randPassword);
    }
}