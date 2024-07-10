package com.example.interactor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.Main;
import com.example.controller.MainMenuController;
import com.example.model.App;
import com.example.model.Result;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MainMenuInteractor implements Initializable {
    @FXML
    protected Label coins ;
    @FXML
    protected Label experience ;
    @FXML
    protected ImageView profilePicture ;

    @FXML
    protected void onStartButtonClick() throws IOException {
        Main.setRoot("BetMenu");
    }

    @FXML
    protected void onHistoryButtonClick() throws IOException {
        MainMenuController.enterGameHistoryMenu();
        Main.setRoot("GameHistoryMenu");
    }

    @FXML
    protected void onShopButtonClick() {

    }

    @FXML
    protected void onProfileButtonClick() throws IOException {
        MainMenuController.enterProfileMenu();
        Main.setRoot("ProfileMenu");
    }

    @FXML
    protected void onSettingButtonClick() throws IOException {
        Main.setRoot("SettingMenu");
    }

    @FXML
    protected void onSLogOutButtonClick() throws IOException {
        MainMenuController.logout();
        Main.setRoot("StartMenu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Result starterPack = MainMenuController.StarterPack();
        if(starterPack.isSuccessful()) {
            // Create an alert
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText(starterPack.getMessage());

            // Show the alert
            alert.showAndWait();
        }

        coins.setText("Money: " + Integer.toString(App.getLoggedInUser().getMoney()));
        experience.setText("level: " + Integer.toString(App.getLoggedInUser().getLevel()) + " XP: " + Integer.toString(App.getLoggedInUser().getXP()));
    }
}