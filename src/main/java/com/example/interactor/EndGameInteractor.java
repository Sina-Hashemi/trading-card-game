package com.example.interactor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.Main;

public class EndGameInteractor implements Initializable {

    public static String output = "";

    @FXML
    private Label endingText;
    @FXML
    private Button returnBackButton;

    @FXML 
    private void onReturnBackButtonClick () throws IOException{
        Main.setRoot("MainMenu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        endingText.setText(output);
    }
}