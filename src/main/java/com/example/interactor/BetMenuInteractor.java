package com.example.interactor;

import java.io.IOException;

import com.example.Main;
import com.example.controller.MainMenuController;
import com.example.model.Result;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BetMenuInteractor {
    @FXML
    private Label error;
    @FXML
    private Button betButton;
    @FXML
    private TextField betAmountTextField;
    @FXML
    private TextField username;
    @FXML
    private TextField password;


    @FXML
    protected void onBetButtonClick() throws IOException {
        Result result = MainMenuController.preparePlayerTwo(username.getText());
        if(result.isSuccessful()) {
            result = MainMenuController.checkSecondUser(password.getText(), betAmountTextField.getText());
            if(result.isSuccessful()) {
                Main.setRoot("SelectCharacterMenu");
            }
            else {
                error.setText(result.toString());
            }
        }
        else {
            error.setText(result.toString());
        }
    }
}