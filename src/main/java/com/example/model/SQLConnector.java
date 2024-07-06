package com.example.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
                    recordList.add(Integer.parseInt(recordss[i]));
                }

                App.getUsers().add(new User(ID, username, password, email, nickname, new SecurityQuestion(passwordRecoveryQuestion, passwordRecoveryAns), level, maxHP, XP, money, cards, recordList));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
