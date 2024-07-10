package com.example.interactor;

import com.example.Main;

import java.io.IOException;
import javafx.fxml.FXML;

public class StartMenuInteractor {

    @FXML
    protected void onLoginButtonClick() throws IOException {
        Main.setRoot("LoginMenu");
    }

    @FXML
    protected void onSignUpButtonClick() throws IOException {
        Main.setRoot("SignUpMenu");
    }

}