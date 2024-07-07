package com.example.model;

import java.time.LocalDateTime;

public class History {

    public enum GameResult {
        LOST,
        WON
    }

    private final int ID, rivalID;
    private User rival = null;
    private final GameResult gameResult;
    private final LocalDateTime gameTime;
    private final String RewardsPenalties;

    public History(int iD, int rivalID, GameResult gameResult, LocalDateTime gameTime, String rewardsPenalties) {
        ID = iD;
        this.rivalID = rivalID;
        this.gameResult = gameResult;
        this.gameTime = gameTime;
        RewardsPenalties = rewardsPenalties;

        for (User user : App.getUsers()) {
            if(user.getID() == rivalID) {
                rival = user;
                break;
            }
        }
    }

    public History(int rivalID, GameResult gameResult, String rewardsPenalties) {
        ID = App.getGameHistories().get(App.getGameHistories().size() - 1).getID() + 1;
        this.rivalID = rivalID;
        this.gameResult = gameResult;
        this.gameTime = LocalDateTime.now();
        RewardsPenalties = rewardsPenalties;

        for (User user : App.getUsers()) {
            if(user.getID() == rivalID) {
                rival = user;
                break;
            }
        }
    }

    @Override
    public String toString() {
        if(gameResult == GameResult.WON)
        return gameTime.toString() + " \u001B[32m" + gameResult + "\u001B[0m " + rival.getUsername() + " " + rival.getLevel() + " " + RewardsPenalties;
        else
        return gameTime.toString() + " \u001B[31m" + gameResult + "\u001B[0m " + rival.getUsername() + " " + rival.getLevel() + " " + RewardsPenalties;
    }

    public int getID() {
        return ID;
    }

    public int getRivalID() {
        return rivalID;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public LocalDateTime getGameTime() {
        return gameTime;
    }

    public String getRewardsPenalties() {
        return RewardsPenalties;
    }

    public User getRival() {
        return rival;
    }
}
