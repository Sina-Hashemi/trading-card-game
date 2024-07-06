package com.example.model;

public class Card implements Cloneable {

    public enum GameCharacter {
        Electricity,
        Posion,
        Fire,
        Ice,
        None;
    }

    // private final int ID;
    private String name;
    private int attack, playerDamage;
    private int duration, basePrice, upgradeLevel, upgradeCost;
    private GameCharacter character;

    public Card() {}

    public Card(String name, int duration) {
        this.name = name;
        this.attack = 0;
        this.playerDamage = 0;
        this.duration = duration;
        this.basePrice = 100;
        this.upgradeLevel = 0;
        this.upgradeCost = 0;
        this.character = GameCharacter.None;
    }

    public Card(String name, int attack, int playerDamage, int duration, int basePrice, int upgradeLevel, int upgradeCost, GameCharacter character) {
        this.name = name;
        this.attack = attack;
        this.playerDamage = playerDamage;
        this.duration = duration;
        this.basePrice = basePrice;
        this.upgradeLevel = upgradeLevel;
        this.upgradeCost = upgradeCost;
        this.character = character;
    }

    public void buff() {
        attack += 5;
        playerDamage *= 1.25;
    }

    public void nerf() {
        attack -= 5;
        playerDamage /= 1.25;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getPlayerDamage() {
        return playerDamage;
    }

    public void setPlayerDamage(int playerDamage) {
        this.playerDamage = playerDamage;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return name + "\t" + duration + "\t" + character;
    }

    @Override
    public Card clone() {
        return new Card(name, attack, playerDamage, duration, basePrice, upgradeLevel, upgradeCost, character);
    }
}
