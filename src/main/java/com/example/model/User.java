package com.example.model;

import java.util.ArrayList;
import java.util.Random;

public class User {

    public static class CardLevel {
        private final Card card;
        private int level;

        public CardLevel(Card card) {
            this.card = card;
            this.level = 0;
        }

        public CardLevel(Card card, int level) {
            this.card = card;
            this.level = level;
        }

        public Card getCard() {
            return card;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }

    private final int ID;
    private String username, password, email, nickname; // TODO - Sina - change password type to hash
    private SecurityQuestion passwordRecoveryQuestion;
    private int level, maxHP, XP, money;
    private ArrayList<CardLevel> cards;
    private ArrayList<Integer> records;

    public User(String username, String password, String email, String nickname) {
        ID = App.getUsers().get(App.getUsers().size() - 1).getID() + 1;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;

        this.cards = new ArrayList<>();
        this.records = new ArrayList<>();
        this.level = 1;
        this.maxHP = 100;
        this.XP = 0;
        this.money = 100;
    }

    public User(int iD, String username, String password, String email, String nickname,
            SecurityQuestion passwordRecoveryQuestion, int level, int maxHP, int xP, int money,
            ArrayList<CardLevel> cards, ArrayList<Integer> records) {
        ID = iD;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
        this.level = level;
        this.maxHP = maxHP;
        XP = xP;
        this.money = money;
        this.cards = cards;
        this.records = records;
    }



    public int getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ArrayList<CardLevel> getCards() {
        return cards;
    }

    public ArrayList<Integer> getRecords() {
        return records;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int xP) {
        XP = xP;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public SecurityQuestion getPasswordRecoveryQuestion() {
        return passwordRecoveryQuestion;
    }

    public void setPasswordRecoveryQuestion(SecurityQuestion passwordRecoveryQuestion) {
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
    }

    @Override
    public String toString() {
        return username + "\t" + level + "\t" + money;
    }

    public void increaseXP(int xp) {
        XP += xp;
        while (XP > level * 500) {
            XP -= level * 500;
            levelUp();
        }
    }

    private void levelUp() {
        // TODO - Sina - It's not standard to print in a model class
        level++;
        int x = new Random().nextInt(maxHP);
        System.out.println(nickname + " leveled up! level: " + level + " earned money: " + (maxHP + x));

        money += maxHP + x;
        maxHP *= 2;
    }
}
