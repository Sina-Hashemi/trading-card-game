package com.example.interactor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ProfileMenuInteractor {
    @FXML
    private Label error;
    @FXML
    private TextArea newName ;
    @FXML
    private TextArea newPassword ;
    @FXML
    private TextArea newNickName ;
    @FXML
    private TextArea newEmail ;

    @FXML
    private Button BackToMainMenu ;

    @FXML
    protected void onChangeButtonClick() {
    }
    @FXML
    protected void onBackToMainMenuButtonClick() {
    }

}