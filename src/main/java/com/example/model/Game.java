package com.example.model;

public class Game {
    private static User hostPlayer = null, guestPlayer = null, currentPlayer = null, currentEnemy = null;
    private static int bet = 0;

    public static void startGame(User player1, User player2) {
        hostPlayer = player1;
        guestPlayer = player2;
        currentPlayer = hostPlayer;
        currentEnemy = guestPlayer;
    }

    public static User getHostPlayer() {
        return hostPlayer;
    }

    public static void setHostPlayer(User hostPlayer) {
        Game.hostPlayer = hostPlayer;
    }

    public static User getGuestPlayer() {
        return guestPlayer;
    }

    public static void setGuestPlayer(User guestPlayer) {
        Game.guestPlayer = guestPlayer;
    }

    public static User getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(User currentPlayer) {
        Game.currentPlayer = currentPlayer;
    }

    public static User getCurrentEnemy() {
        return currentEnemy;
    }

    public static void setCurrentEnemy(User currentEnemy) {
        Game.currentEnemy = currentEnemy;
    }

    public static int getBet() {
        return bet;
    }

    public static void setBet(int bet) {
        Game.bet = bet;
    }
}
