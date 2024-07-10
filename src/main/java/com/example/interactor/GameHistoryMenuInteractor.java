package com.example.interactor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.Main;
import com.example.controller.GameHistoryMenuController;
import com.example.model.History;

public class GameHistoryMenuInteractor implements Initializable {
    @FXML
    private TableView <History> tableView ;
    @FXML
    private TableColumn <History, String> timeColumn;
    @FXML
    private TableColumn <History, String> resultColumn;
    @FXML
    private TableColumn <History, String> rivalNameColumn;
    @FXML
    private TableColumn <History, String> rivalLevelColumn;
    @FXML
    private TableColumn <History, String> rewardsColumn;

    ObservableList<History> observableHistories;

    @FXML
    protected void onReturnMenuClick() throws IOException {
        Main.setRoot("MainMenu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameHistoryMenuController.showBoard();
        observableHistories = FXCollections.observableArrayList();

        timeColumn.setCellValueFactory(new PropertyValueFactory<>("gameTime"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("gameResult"));
        rivalNameColumn.setCellValueFactory(new PropertyValueFactory<>("rivalName"));
        rivalLevelColumn.setCellValueFactory(new PropertyValueFactory<>("rivalID"));
        rewardsColumn.setCellValueFactory(new PropertyValueFactory<>("RewardsPenalties"));

        observableHistories.clear();
        observableHistories.addAll(GameHistoryMenuController.records);

        // tableView = new TableView<>(observableHistories);
        tableView.setItems(observableHistories);
    }
}