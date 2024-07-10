package com.example.interactor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.Main;
import com.example.controller.ProfileMenuController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProfileMenuInteractor implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBox ;
    @FXML
    private Label error;
    @FXML
    private Label info;
    @FXML
    private TextField newInput;
    @FXML
    private TextField newNewInput;
    @FXML
    private Button BackToMainMenu;

    private static String newPass = "";

    @FXML
    protected void onChangeButtonClick() {
        switch (choiceBox.getValue()) {
            case "Username":
                error.setText(ProfileMenuController.changeUsername(newInput.getText()).toString());
                break;
            case "Nickname":
                error.setText(ProfileMenuController.changeNickname(newInput.getText()).toString());
                break;
            case "Email":
                error.setText(ProfileMenuController.changeEmail(newInput.getText()).toString());
                break;
            case "Password":
                if(newPass.equals("")) {
                    error.setText("Please enter your last password!");
                    newNewInput.setVisible(true);
                }
                else {
                    error.setText(ProfileMenuController.changePassword(newNewInput.getText(), newInput.getText(), newInput.getText()).toString());
                }
                break;

            default:
                break;
        }
    }

    @FXML
    protected void onBackToMainMenuButtonClick() throws IOException {
        newPass = "";
        Main.setRoot("MainMenu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        info.setText(ProfileMenuController.printInfo().toString());
        choiceBox.getItems().addAll(new String[]{"Userame", "Nickname", "Email", "password"}) ;
    }
}