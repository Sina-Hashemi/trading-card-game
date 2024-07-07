package com.example.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.example.enums.Spell;
import com.example.model.Card.GameCharacter;
import com.example.model.History.GameResult;
import com.example.model.User.CardLevel;

public class SQLConnector {

    private static String url = "jdbc:mysql://localhost:3306/trading-card-game";
    private static String username = "root";
    private static String password = "";

    private static Connection connection;
    private static Statement statement;

    public static void initialize() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Database connected");

        initializeCards();
        initializeHistory();
        initializeUsers();
    }

    private static void initializeCards() {

        for (Spell spell : Spell.values()) {
            App.getCards().add(spell.getCard());
        }

        try {
            statement = connection.createStatement();
            String query = "SELECT * FROM CARD";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                int attack = resultSet.getInt("attack");
                int damage = resultSet.getInt("damage");
                int duration = resultSet.getInt("duration");
                int price = resultSet.getInt("price");
                int upLevel = resultSet.getInt("upLevel");
                int upCost = resultSet.getInt("upCost");
                String gameChar = resultSet.getString("gameChar");

                App.getCards().add(new Card(ID, name, attack, damage, duration, price, upLevel, upCost, GameCharacter.valueOf(gameChar)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void initializeHistory() {
        try {
            statement = connection.createStatement();
            String query = "SELECT * FROM GAMEHISTORY";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                int rivalID = resultSet.getInt("rivalID");
                String gameResult = resultSet.getString("gameResult");
                String gameTime = resultSet.getString("gameTime");
                String RewardsPenalties = resultSet.getString("RewardsPenalties");

                App.getGameHistories().add(new History(ID, rivalID, GameResult.valueOf(gameResult), LocalDateTime.parse(gameTime), RewardsPenalties));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void initializeUsers() {
        try {
            statement = connection.createStatement();
            String query = "SELECT * FROM USER";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String nickname = resultSet.getString("nickname");
                String email = resultSet.getString("email");
                int passwordRecoveryQuestion = resultSet.getInt("passwordRecoveryQuestion");
                String passwordRecoveryAns = resultSet.getString("passwordRecoveryAns");
                int level = resultSet.getInt("level");
                int maxHP = resultSet.getInt("maxHP");
                int XP = resultSet.getInt("XP");
                int money = resultSet.getInt("money");
                String cardLevel = resultSet.getString("CardLevel");
                String records = resultSet.getString("records");

                ArrayList<CardLevel> cards = new ArrayList<>();
                ArrayList<Integer> recordList = new ArrayList<>();

                String[] cardLevels = cardLevel.split(" ");
                for (int i = 0; i < cardLevels.length / 2; i++) {
                    Card card = null;
                    for (Card tempCard : App.getCards()) {
                        if(tempCard.getID() == Integer.parseInt(cardLevels[i])) {
                            card = tempCard;
                            break;
                        }
                    }
                    cards.add(new CardLevel(card, Integer.parseInt(cardLevels[i + 1])));
                }

                String[] recordss = records.split(" ");
                for (int i = 0; i < recordss.length; i++) {
                    if(recordss[i].equals("")) break;
                    recordList.add(Integer.parseInt(recordss[i]));
                }

                App.getUsers().add(new User(ID, username, password, email, nickname, new SecurityQuestion(passwordRecoveryQuestion, passwordRecoveryAns), level, maxHP, XP, money, cards, recordList));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void save() {
        saveCards();
        saveHistory();
        saveUsers();
    }

    private static void saveHistory() {
        for (History history : App.getGameHistories()) {
            try {
                String query = String.format("SELECT ID FROM GAMEHISTORY WHERE ID = %d", history.getID());
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    query = String.format("UPDATE `GAMEHISTORY` SET `rivalID`='%d',`gameResult`='%s',`gameTime`='%s',`RewardsPenalties`='%s' WHERE ID = %d",
                            history.getRivalID(), history.getGameResult().toString(), history.getGameTime().toString(), history.getRewardsPenalties(), history.getID());
                    statement.executeUpdate(query);
                }
                else {
                    query = String.format("INSERT INTO `GAMEHISTORY`(`ID`, `rivalID`, `gameResult`, `gameTime`, `RewardsPenalties`) VALUES ('%d','%d','%s','%s','%s')",
                            history.getID(), history.getRivalID(), history.getGameResult().toString(), history.getGameTime().toString(), history.getRewardsPenalties());
                    statement.executeUpdate(query);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void saveCards() {
        for (Card card : App.getCards()) {
            if(card.getID() >= 1000) continue;
            try {
                String query = String.format("SELECT ID FROM CARD WHERE ID = %d", card.getID());
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    query = String.format("UPDATE `CARD` SET `name`='%s',`attack`='%d',`damage`='%d',`duration`='%d',`price`='%d',`upLevel`='%d',`upCost`='%d',`gameChar`='%s' WHERE ID = %d",
                            card.getName(), card.getAttack(), card.getPlayerDamage(), card.getDuration(), card.getBasePrice(), card.getUpgradeLevel(), card.getUpgradeCost(), card.getCharacter().toString(), card.getID());
                    statement.executeUpdate(query);
                }
                else {
                    query = String.format("INSERT INTO `CARD`(`ID`, `name`, `attack`, `damage`, `duration`, `price`, `upLevel`, `upCost`, `gameChar`) VALUES ('%d','%s','%d','%d','%d','%d','%d','%d','%s')",
                            card.getID(), card.getName(), card.getAttack(), card.getPlayerDamage(), card.getDuration(), card.getBasePrice(), card.getUpgradeLevel(), card.getUpgradeCost(), card.getCharacter().toString());
                    statement.executeUpdate(query);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void saveUsers() {
        for (User user : App.getUsers()) {
            try {
                String query = String.format("SELECT ID FROM USER WHERE ID = %d", user.getID());
                ResultSet resultSet = statement.executeQuery(query);

                String cards = "", records = "";

                for (CardLevel card : user.getCards()) {
                    cards += card.getCard().getID() + " " + card.getLevel() + " ";
                }cards = cards.trim();

                for (Integer h : user.getRecords()) {
                    records += h + " ";
                }records = records.trim();

                if (resultSet.next()) {
                    query = String.format("UPDATE `USER` SET `username`='%s',`password`='%s',`nickname`='%s',`email`='%s',`passwordRecoveryQuestion`='%d',`passwordRecoveryAns`='%s',`level`='%d',`maxHP`='%d',`XP`='%d',`money`='%d',`CardLevel`='%s',`records`='%s' WHERE ID = %d",
                            user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getPasswordRecoveryQuestion().getKey(), user.getPasswordRecoveryQuestion().getAns(), user.getLevel(), user.getMaxHP(), user.getXP(), user.getMoney(), cards, records, user.getID());
                    statement.executeUpdate(query);
                }
                else {
                    query = String.format("INSERT INTO `USER` (`ID`, `username`, `password`, `nickname`, `email`, `passwordRecoveryQuestion`, `passwordRecoveryAns`, `level`, `maxHP`, `XP`, `money`, `CardLevel`, `records`) VALUES ('%d', '%s', '%s', '%s', '%s', '%d', '%s', '%d', '%d', '%d', '%d', '%s', '%s')",
                            user.getID(), user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getPasswordRecoveryQuestion().getKey(), user.getPasswordRecoveryQuestion().getAns(), user.getLevel(), user.getMaxHP(), user.getXP(), user.getMoney(), cards, records);
                    statement.executeUpdate(query);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
