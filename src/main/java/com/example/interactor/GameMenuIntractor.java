package com.example.interactor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.Main;
import com.example.controller.GameMenuController;
import com.example.enums.Spell;
import com.example.model.App;
import com.example.model.Card;
import com.example.model.Card.GameCharacter;
import com.example.model.Game;
import com.example.model.History;
import com.example.model.History.GameResult;
import com.example.model.Result;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameMenuIntractor implements Initializable {
    @FXML
    private Button row1;
    @FXML
    private Button row2;
    @FXML
    private Button row3;
    @FXML
    private Button row4;
    @FXML
    private Button row5;
    @FXML
    private Button row6;
    @FXML
    private Button row7;
    @FXML
    private Button row8;
    @FXML
    private Button row9;
    @FXML
    private Button row10;
    @FXML
    private Button row11;
    @FXML
    private Button row12;
    @FXML
    private Button row13;
    @FXML
    private Button row14;
    @FXML
    private Button row15;
    @FXML
    private Button row16;
    @FXML
    private Button row17;
    @FXML
    private Button row18;
    @FXML
    private Button row19;
    @FXML
    private Button row20;
    @FXML
    private Button row21;
    @FXML
    private Button player1card1button;
    @FXML
    private Button player1card2button;
    @FXML
    private Button player1card3button;
    @FXML
    private Button player1card4button;
    @FXML
    private Button player1card5button;
    @FXML
    private Button player2card1button;
    @FXML
    private Button player2card2button;
    @FXML
    private Button player2card3button;
    @FXML
    private Button player2card4button;
    @FXML
    private Button player2card5button;
    @FXML
    private Label player1Map ;
    @FXML
    private Label player2Map ;
    @FXML
    private Label player1Profile ;
    @FXML
    private Label player2Profile ;
    @FXML
    private Label error ;

    private Button[] p1Buttons = new Button[]{player1card1button, player1card2button, player1card3button, player1card4button, player1card5button};
    private Button[] p2Buttons = new Button[]{player2card1button, player2card2button, player2card3button, player2card4button, player2card5button};
    private Button[] rows = new Button[]{row1, row2, row3, row4, row5, row6, row7, row8, row9, row10, row11, row12, row13, row14, row15, row16, row17, row18, row19, row20, row21};
    private int plN = 0, cN = 0;

    @FXML
    protected void onRow1ButtonClick() {
        placeCard(1);
    }
    @FXML
    protected void onRow2ButtonClick() {
        placeCard(2);
    }
    @FXML
    protected void onRow3ButtonClick() {
        placeCard(3);
    }
    @FXML
    protected void onRow4ButtonClick() {
        placeCard(4);
    }
    @FXML
    protected void onRow5ButtonClick() {
        placeCard(5);
    }
    @FXML
    protected void onRow6ButtonClick() {
        placeCard(6);
    }
    @FXML
    protected void onRow7ButtonClick() {
        placeCard(7);
    }
    @FXML
    protected void onRow8ButtonClick() {
        placeCard(8);
    }
    @FXML
    protected void onRow9ButtonClick() {
        placeCard(9);
    }
    @FXML
    protected void onRow10ButtonClick() {
        placeCard(10);
    }
    @FXML
    protected void onRow11ButtonClick() {
        placeCard(11);
    }
    @FXML
    protected void onRow12ButtonClick() {
        placeCard(12);
    }
    @FXML
    protected void onRow13ButtonClick() {
        placeCard(13);
    }
    @FXML
    protected void onRow14ButtonClick() {
        placeCard(14);
    }
    @FXML
    protected void onRow15ButtonClick() {
        placeCard(15);
    }
    @FXML
    protected void onRow16ButtonClick() {
        placeCard(16);
    }
    @FXML
    protected void onRow17ButtonClick() {
        placeCard(17);
    }
    @FXML
    protected void onRow18ButtonClick() {
        placeCard(18);
    }
    @FXML
    protected void onRow19ButtonClick() {
        placeCard(19);
    }
    @FXML
    protected void onRow20ButtonClick() {
        placeCard(20);
    }
    @FXML
    protected void onRow21ButtonClick() {
        placeCard(21);
    }

    @FXML
    protected void onPlayer1Card1ButtonClick() {
        plN = 1;cN = 1;
        if(Game.getHostPlayer().isHide()) return;
        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);

        Card card = Game.getHostPlayer().getDeck().get(0);
        if(card.getCharacter() == GameCharacter.None) {
            alert.setContentText(String.format("%-15s Duration = %-5d \n %-25s", card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription()));
        }
        else {
            alert.setContentText(String.format("%-15s Duration = %-5d \n Damage = %-5d A/D point = %-5d", card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack()));
        }
        // Show the alert
        alert.showAndWait();
    }

    @FXML
    protected void onPlayer1Card2ButtonClick() {
        plN = 1;cN = 2;
        if(Game.getHostPlayer().isHide()) return;
        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);

        Card card = Game.getHostPlayer().getDeck().get(1);
        if(card.getCharacter() == GameCharacter.None) {
            alert.setContentText(String.format("%-15s Duration = %-5d \n %-25s", card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription()));
        }
        else {
            alert.setContentText(String.format("%-15s Duration = %-5d \n Damage = %-5d A/D point = %-5d", card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack()));
        }
        // Show the alert
        alert.showAndWait();
    }

    @FXML
    protected void onPlayer1Card3ButtonClick() {
        plN = 1;cN = 3;
        if(Game.getHostPlayer().isHide()) return;
        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);

        Card card = Game.getHostPlayer().getDeck().get(2);
        if(card.getCharacter() == GameCharacter.None) {
            alert.setContentText(String.format("%-15s Duration = %-5d \n %-25s", card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription()));
        }
        else {
            alert.setContentText(String.format("%-15s Duration = %-5d \n Damage = %-5d A/D point = %-5d", card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack()));
        }
        // Show the alert
        alert.showAndWait();
    }

    @FXML
    protected void onPlayer1Card4ButtonClick() {
        plN = 1;cN = 4;
        if(Game.getHostPlayer().isHide()) return;
        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);

        Card card = Game.getHostPlayer().getDeck().get(3);
        if(card.getCharacter() == GameCharacter.None) {
            alert.setContentText(String.format("%-15s Duration = %-5d \n %-25s", card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription()));
        }
        else {
            alert.setContentText(String.format("%-15s Duration = %-5d \n Damage = %-5d A/D point = %-5d", card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack()));
        }
        // Show the alert
        alert.showAndWait();
    }

    @FXML
    protected void onPlayer1Card5ButtonClick() {
        plN = 1;cN = 5;
        if(Game.getHostPlayer().isHide()) return;
        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);

        Card card = Game.getHostPlayer().getDeck().get(4);
        if(card.getCharacter() == GameCharacter.None) {
            alert.setContentText(String.format("%-15s Duration = %-5d \n %-25s", card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription()));
        }
        else {
            alert.setContentText(String.format("%-15s Duration = %-5d \n Damage = %-5d A/D point = %-5d", card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack()));
        }
        // Show the alert
        alert.showAndWait();
    }

    @FXML
    protected void onPlayer2Card1ButtonClick() {
        plN = 2;cN = 1;
        if(Game.getGuestPlayer().isHide()) return;
        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);

        Card card = Game.getGuestPlayer().getDeck().get(0);
        if(card.getCharacter() == GameCharacter.None) {
            alert.setContentText(String.format("%-15s Duration = %-5d \n %-25s", card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription()));
        }
        else {
            alert.setContentText(String.format("%-15s Duration = %-5d \n Damage = %-5d A/D point = %-5d", card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack()));
        }
        // Show the alert
        alert.showAndWait();
    }

    @FXML
    protected void onPlayer2Card2ButtonClick() {
        plN = 2;cN = 2;
        if(Game.getGuestPlayer().isHide()) return;
        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);

        Card card = Game.getGuestPlayer().getDeck().get(1);
        if(card.getCharacter() == GameCharacter.None) {
            alert.setContentText(String.format("%-15s Duration = %-5d \n %-25s", card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription()));
        }
        else {
            alert.setContentText(String.format("%-15s Duration = %-5d \n Damage = %-5d A/D point = %-5d", card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack()));
        }
        // Show the alert
        alert.showAndWait();
    }

    @FXML
    protected void onPlayer2Card3ButtonClick() {
        plN = 2;cN = 3;
        if(Game.getGuestPlayer().isHide()) return;
        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);

        Card card = Game.getGuestPlayer().getDeck().get(2);
        if(card.getCharacter() == GameCharacter.None) {
            alert.setContentText(String.format("%-15s Duration = %-5d \n %-25s", card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription()));
        }
        else {
            alert.setContentText(String.format("%-15s Duration = %-5d \n Damage = %-5d A/D point = %-5d", card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack()));
        }
        // Show the alert
        alert.showAndWait();
    }

    @FXML
    protected void onPlayer2Card4ButtonClick() {
        plN = 2;cN = 4;
        if(Game.getGuestPlayer().isHide()) return;
        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);

        Card card = Game.getGuestPlayer().getDeck().get(3);
        if(card.getCharacter() == GameCharacter.None) {
            alert.setContentText(String.format("%-15s Duration = %-5d \n %-25s", card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription()));
        }
        else {
            alert.setContentText(String.format("%-15s Duration = %-5d \n Damage = %-5d A/D point = %-5d", card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack()));
        }
        // Show the alert
        alert.showAndWait();
    }

    @FXML
    protected void onPlayer2Card5ButtonClick() {
        plN = 2;cN = 5;
        if(Game.getGuestPlayer().isHide()) return;
        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);

        Card card = Game.getGuestPlayer().getDeck().get(4);
        if(card.getCharacter() == GameCharacter.None) {
            alert.setContentText(String.format("%-15s Duration = %-5d \n %-25s", card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription()));
        }
        else {
            alert.setContentText(String.format("%-15s Duration = %-5d \n Damage = %-5d A/D point = %-5d", card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack()));
        }
        // Show the alert
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        p1Buttons = new Button[]{player1card1button, player1card2button, player1card3button, player1card4button, player1card5button};
        p2Buttons = new Button[]{player2card1button, player2card2button, player2card3button, player2card4button, player2card5button};
        rows = new Button[]{row1, row2, row3, row4, row5, row6, row7, row8, row9, row10, row11, row12, row13, row14, row15, row16, row17, row18, row19, row20, row21};

        error.setText(GameMenuController.showNowPlayer().toString());

        player1Profile.setText("Player One: " + " HP = " + Game.getHostPlayer().getHP() + " Damage = " + Game.getHostPlayer().getDmg() + "\n Remaining Turn = " + Game.getHostPlayer().getRemainTurn() + " Character = " + Game.getHostPlayer().getCharacter());
        int n = 0;
        for (Card card : Game.getHostPlayer().getDeck()) {
            if(n == Game.getHostPlayer().getDeckSize()) {
                Game.getHostPlayer().setHide(false);
                if(n == 4) p1Buttons[n].setText("NaN");
                break;
            }
            if(Game.getHostPlayer().isHide()) {
                p1Buttons[n].setText("Hided");
            }

            if(card.getCharacter() == GameCharacter.None) {
                p1Buttons[n].setText(String.format("%-15s Duration = %-5d \n %-25s", card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription()));
            }
            else {
                p1Buttons[n].setText(String.format("%-15s Duration = %-5d \n Damage = %-5d A/D point = %-5d", card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack()));
            }
            n++;
        }

        String x = "", y = "";
        for (int i = 0; i < 21; i++) {

            if(Game.getHostPlayer().getWreckedHouses().contains(i)) x += "Wrecked";
            else if(Game.getHostPlayer().getMap()[i] == null) x += "Empty";
            else if(Game.getHostPlayer().getMap()[i].getCharacter() == GameCharacter.None) x += Game.getHostPlayer().getMap()[i].getName();
            else x += "Dmg " + Game.getHostPlayer().getMap()[i].getPlayerDamage() + " A/D " + Game.getHostPlayer().getMap()[i].getAttack();

            if(Game.getGuestPlayer().getWreckedHouses().contains(i)) y += "Wrecked";
            else if(Game.getGuestPlayer().getMap()[i] == null) y += "Empty";
            else if(Game.getGuestPlayer().getMap()[i].getCharacter() == GameCharacter.None) y += Game.getGuestPlayer().getMap()[i].getName();
            else y += "Dmg " + Game.getGuestPlayer().getMap()[i].getPlayerDamage() + " A/D " + Game.getGuestPlayer().getMap()[i].getAttack();

            x += "\n";y += "\n";
        }
        player1Map.setText(x);
        player2Map.setText(y);

        player2Profile.setText("Player Two: " + " HP = " + Game.getGuestPlayer().getHP() + " Damage = " + Game.getGuestPlayer().getDmg() + "\n Remaining Turn = " + Game.getGuestPlayer().getRemainTurn() + " Character = " + Game.getGuestPlayer().getCharacter());
        n = 0;
        for (Card card : Game.getGuestPlayer().getDeck()) {
            if(n == Game.getGuestPlayer().getDeckSize()) {
                Game.getGuestPlayer().setHide(false);
                if(n == 4) p2Buttons[n].setText("NaN");
                break;
            }
            if(Game.getGuestPlayer().isHide()) {
                p2Buttons[n].setText("Hided");
            }

            if(card.getCharacter() == GameCharacter.None) {
                p2Buttons[n].setText(String.format("%-15s Duration = %-5d \n %-25s", card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription()));
            }
            else {
                p2Buttons[n].setText(String.format("%-15s Duration = %-5d \n Damage = %-5d A/D point = %-5d", card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack()));
            }
            n++;
        }

        if(GameMenuController.isRoundFinished()) {
            error.setText("Round ended");

            for(int i = 0; i < 21; i++) {

                x = ""; y = "";
                if(Game.getHostPlayer().getWreckedHouses().contains(i)) x += "Wrecked";
                else if(Game.getHostPlayer().getMap()[i] == null) x += "Empty";
                else if(Game.getHostPlayer().getMap()[i].getCharacter() == GameCharacter.None) x += Game.getHostPlayer().getMap()[i].getName();
                else x += "\u001B[31mDmg " + Game.getHostPlayer().getMap()[i].getPlayerDamage() + " \u001B[32mA/D " + Game.getHostPlayer().getMap()[i].getAttack() + "\u001B[0m";
                if(Game.getGuestPlayer().getWreckedHouses().contains(i)) y += "Wrecked";
                else if(Game.getGuestPlayer().getMap()[i] == null) y += "Empty";
                else if(Game.getGuestPlayer().getMap()[i].getCharacter() == GameCharacter.None) y += Game.getGuestPlayer().getMap()[i].getName();
                else y += "\u001B[31mDmg " + Game.getGuestPlayer().getMap()[i].getPlayerDamage() + " \u001B[32mA/D " + Game.getGuestPlayer().getMap()[i].getAttack() + "\u001B[0m";

                x += "\n";y += "\n";
                player1Map.setText(x);
                player2Map.setText(y);

                Game.calulateTimeline(i);

                player1Profile.setText("Player One: " + " HP = " + Game.getHostPlayer().getHP() + " Damage = " + Game.getHostPlayer().getDmg() + "\n Remaining Turn = " + Game.getHostPlayer().getRemainTurn() + " Character = " + Game.getHostPlayer().getCharacter());
                player2Profile.setText("Player Two: " + " HP = " + Game.getGuestPlayer().getHP() + " Damage = " + Game.getGuestPlayer().getDmg() + "\n Remaining Turn = " + Game.getGuestPlayer().getRemainTurn() + " Character = " + Game.getGuestPlayer().getCharacter());

                EndGameInteractor.output = "";
                if(Game.getGuestPlayer().getHP() == 0) {
                    EndGameInteractor.output += "Game ended! Player one won!\n";
                    Game.getHostPlayer().getPlayer().increaseXP(Game.getGuestPlayer().getPlayer().getMaxHP());
                    Game.getGuestPlayer().getPlayer().increaseXP(Game.getHostPlayer().getPlayer().getMaxHP() / 4);
                    EndGameInteractor.output += "Player one earned " + Game.getGuestPlayer().getPlayer().getMaxHP() + " XP and player two earned " + (Game.getHostPlayer().getPlayer().getMaxHP() / 4) + " XP\n";

                    if(Game.getBet() == 0) {
                        Game.getHostPlayer().getPlayer().setMoney(Game.getHostPlayer().getPlayer().getMoney() + Game.getHostPlayer().getHP());
                        EndGameInteractor.output += "Player one earned " + Game.getHostPlayer().getHP() + " money\n";

                        App.getGameHistories().add(new History(Game.getGuestPlayer().getPlayer().getID(), GameResult.WON, "earned " + Game.getGuestPlayer().getPlayer().getMaxHP() + " XP" + ", " + "earned " + Game.getHostPlayer().getHP() + " money"));
                        Game.getHostPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                        App.getGameHistories().add(new History(Game.getHostPlayer().getPlayer().getID(), GameResult.LOST, "earned " + (Game.getHostPlayer().getPlayer().getMaxHP() / 4) + " XP"));
                        Game.getGuestPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                    }
                    else {
                        Game.getHostPlayer().getPlayer().setMoney(Game.getHostPlayer().getPlayer().getMoney() + Game.getBet() * 2);
                        EndGameInteractor.output += "Player one earned " + Game.getBet() + " money and player two lost " + Game.getBet() + " money\n";

                        App.getGameHistories().add(new History(Game.getGuestPlayer().getPlayer().getID(), GameResult.WON, "earned " + Game.getGuestPlayer().getPlayer().getMaxHP() + " XP" + ", " + "earned " + Game.getBet() + " money"));
                        Game.getHostPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                        App.getGameHistories().add(new History(Game.getHostPlayer().getPlayer().getID(), GameResult.LOST, "earned " + (Game.getHostPlayer().getPlayer().getMaxHP() / 4) + " XP" + ", " + "lost " + Game.getBet() + " money"));
                        Game.getGuestPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                    }

                    Game.endGame();
                    try {
                        Main.setRoot("EndGame");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(Game.getHostPlayer().getHP() == 0) {
                    EndGameInteractor.output += "Game ended! Player two won!\n";
                    Game.getHostPlayer().getPlayer().increaseXP(Game.getGuestPlayer().getPlayer().getMaxHP() / 4);
                    Game.getGuestPlayer().getPlayer().increaseXP(Game.getHostPlayer().getPlayer().getMaxHP());
                    EndGameInteractor.output += "Player one earned " + (Game.getGuestPlayer().getPlayer().getMaxHP() / 4) + " XP and player two earned " + Game.getHostPlayer().getPlayer().getMaxHP() + " XP\n";

                    if(Game.getBet() == 0) {
                        Game.getGuestPlayer().getPlayer().setMoney(Game.getGuestPlayer().getPlayer().getMoney() + Game.getGuestPlayer().getHP());
                        EndGameInteractor.output += "Player two earned " + Game.getHostPlayer().getHP() + " money\n";

                        App.getGameHistories().add(new History(Game.getGuestPlayer().getPlayer().getID(), GameResult.LOST, "earned " + (Game.getGuestPlayer().getPlayer().getMaxHP() / 4) + " XP"));
                        Game.getHostPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                        App.getGameHistories().add(new History(Game.getHostPlayer().getPlayer().getID(), GameResult.WON, "earned " + Game.getHostPlayer().getPlayer().getMaxHP() + " XP" + ", " + "earned " + Game.getGuestPlayer().getHP() + " money"));
                        Game.getGuestPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                    }
                    else {
                        Game.getGuestPlayer().getPlayer().setMoney(Game.getGuestPlayer().getPlayer().getMoney() + Game.getBet() * 2);
                        EndGameInteractor.output += "Player two earned " + Game.getBet() + " money and player one lost " + Game.getBet() + " money\n";

                        App.getGameHistories().add(new History(Game.getGuestPlayer().getPlayer().getID(), GameResult.LOST, "earned " + (Game.getGuestPlayer().getPlayer().getMaxHP() / 4) + " XP" + ", " + "lost " + Game.getBet() + " money"));
                        Game.getHostPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                        App.getGameHistories().add(new History(Game.getHostPlayer().getPlayer().getID(), GameResult.WON, "earned " + Game.getHostPlayer().getPlayer().getMaxHP() + " XP" + ", " + "earned " + Game.getBet() + " money"));
                        Game.getGuestPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                    }

                    Game.endGame();
                    try {
                        Main.setRoot("EndGame");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    public void placeCard(int rX) {
        if(Game.getCurrentPlayer() == Game.getHostPlayer() && plN == 1) {
            Result result = GameMenuController.placeCard(Integer.toString(cN), Integer.toString(rX));
            if(!result.isSuccessful()) error.setText(result.toString());
        }
        if(Game.getCurrentPlayer() == Game.getGuestPlayer() && plN == 2) {
            Result result = GameMenuController.placeCard(Integer.toString(cN), Integer.toString(rX));
            if(!result.isSuccessful()) error.setText(result.toString());
        }

        initialize(null, null);
    }
}