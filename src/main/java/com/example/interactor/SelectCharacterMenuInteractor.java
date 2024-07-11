package com.example.interactor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.Main;
import com.example.controller.GameMenuController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class SelectCharacterMenuInteractor implements Initializable {
    @FXML
    protected Label playerName;
    @FXML
    private ChoiceBox<String> choiceFirst;
    @FXML
    private ChoiceBox<String> choiceSecond;
    @FXML
    private Button startbtn;

    @FXML
    protected void onFirstButtonClick() throws IOException {
        GameMenuController.handleCharacter(choiceFirst.getValue());
        GameMenuController.handleCharacter(choiceSecond.getValue());
        Main.setRoot("GameMenu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceFirst.getItems().addAll(new String[]{"Ice", "Fire", "Posion", "Electricity"}) ;
        choiceSecond.getItems().addAll(new String[]{"Ice", "Fire", "Posion", "Electricity"}) ;
    }
}