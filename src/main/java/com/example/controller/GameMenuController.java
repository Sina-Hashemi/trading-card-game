package com.example.controller;

import com.example.model.*;
import com.example.model.Card.GameCharacter;
import com.example.model.Game.PlayerVars;

import java.util.Random;

import com.example.enums.*;

public class GameMenuController {

    public static boolean areCharactersOK() {
        if(Game.getHostPlayer().getCharacter() == GameCharacter.None || Game.getGuestPlayer().getCharacter() == GameCharacter.None) return false;
        return true;
    }

    public static Result handleCharacter(String character) {
        if(Game.getHostPlayer().getCharacter() == GameCharacter.None) {
            switch (character) {
                case "Electricity":
                    Game.getHostPlayer().setCharacter(GameCharacter.Electricity);
                    break;
                    case "Posion":
                    Game.getHostPlayer().setCharacter(GameCharacter.Posion);
                    break;
                    case "Fire":
                    Game.getHostPlayer().setCharacter(GameCharacter.Fire);
                    break;
                    case "Ice":
                    Game.getHostPlayer().setCharacter(GameCharacter.Ice);
                    break;

                default:
                    return new Result(false, "wrong character!");
            }
            return new Result(true, "Host character set successfully");
        }
        switch (character) {
            case "Electricity":
                Game.getCurrentPlayer().setCharacter(GameCharacter.Electricity);
                break;
                case "Posion":
                Game.getCurrentPlayer().setCharacter(GameCharacter.Posion);
                break;
                case "Fire":
                Game.getCurrentPlayer().setCharacter(GameCharacter.Fire);
                break;
                case "Ice":
                Game.getCurrentPlayer().setCharacter(GameCharacter.Ice);
                break;

            default:
                return new Result(false, "wrong character!");
        }
        return new Result(true, "Guest character set successfully");
    }

    public static Result showGame() {
        String output = "";

        output += "Player One: " + " HP = " + Game.getHostPlayer().getHP() + " Damage = " + Game.getHostPlayer().getDmg() + " Remaining Turn = " + Game.getHostPlayer().getRemainTurn() + " Character = " + Game.getHostPlayer().getCharacter() + "\n";
        int n = 1;
        for (Card card : Game.getHostPlayer().getDeck()) {
            if(card.getCharacter() == GameCharacter.None) {
                output += n + ": " + card.getName() + " Spell Card " + Spell.valueOf(card.getName()).getDescription() + " Duration = " + card.getDuration() + "\n";
            }
            else {
                output += n + ": " + card.getName() + " Normal Card" + " Duration = " + card.getDuration() + " Player Damage = " + card.getPlayerDamage() + "Attack/Defense point =" + card.getAttack() + "\n";
            }
            n++;
        }

        for (int i = 0; i < 21; i++) {
            output += "Row" + (i + 1) + ":\t";
            if(Game.getHostPlayer().getWreckedHouses().contains(i)) output += "Wrecked";
            else if(Game.getHostPlayer().getMap()[i] == null) output += "Empty";
            else output += "Dmg " + Game.getHostPlayer().getMap()[i].getPlayerDamage() + " A/D " + Game.getHostPlayer().getMap()[i].getAttack();
            output += "\t";
            if(Game.getGuestPlayer().getWreckedHouses().contains(i)) output += "Wrecked";
            else if(Game.getGuestPlayer().getMap()[i] == null) output += "Empty";
            else output += "Dmg " + Game.getGuestPlayer().getMap()[i].getPlayerDamage() + " A/D " + Game.getGuestPlayer().getMap()[i].getAttack();
            output += "\n";
        }

        output += "Player Two: " + " HP = " + Game.getGuestPlayer().getHP() + " Damage = " + Game.getGuestPlayer().getDmg() + " Remaining Turn = " + Game.getGuestPlayer().getRemainTurn() + " Character = " + Game.getGuestPlayer().getCharacter() + "\n";
        n = 1;
        for (Card card : Game.getGuestPlayer().getDeck()) {
            if(card.getCharacter() == GameCharacter.None) {
                output += n + ": " + card.getName() + " Spell Card " + Spell.valueOf(card.getName()).getDescription() + " Duration = " + card.getDuration() + "\n";
            }
            else {
                output += n + ": " + card.getName() + " Normal Card" + " Duration = " + card.getDuration() + " Player Damage = " + card.getPlayerDamage() + "Attack/Defense point =" + card.getAttack() + "\n";
            }
            n++;
        }

        return new Result(true, output);
    }

    public static Result showNowPlayer() {
        return new Result(true, "It's " + Game.getCurrentPlayer().getPlayer().getNickname() + " turn!");
    }

    public static Result selectCard(String cardNum, String playerNum) {
        PlayerVars player;
        if(playerNum.equals("1")) player = Game.getHostPlayer();
        else if(playerNum.equals("2")) player = Game.getGuestPlayer();
        else return new Result(false, "Player number is wrong!");
        int n;
        try {
            n = Integer.parseInt(cardNum);
        } catch (Exception e) {
            return new Result(false, "Card number is not a valid number!");
        }
        if(n - 1 < 0 || n - 1 > player.getDeck().size() - 1) return new Result(false, "Card number is wrong!");
        String output = "";
        if(player.getDeck().get(n - 1).getCharacter() == GameCharacter.None) {
            output += n + ": " + player.getDeck().get(n - 1).getName() + " Spell Card " + Spell.valueOf(player.getDeck().get(n - 1).getName()).getDescription() + " Duration = " + player.getDeck().get(n - 1).getDuration() + "\n";
        }
        else {
            output += n + ": " + player.getDeck().get(n - 1).getName() + " Normal Card" + " Duration = " + player.getDeck().get(n - 1).getDuration() + " Player Damage = " + player.getDeck().get(n - 1).getPlayerDamage() + "Attack/Defense point =" + player.getDeck().get(n - 1).getAttack() + "\n";
        }
        return new Result(true, output);
    }

    public static Result placeCard(String cardNum, String blockNum) {
        int n, m;
        try {
            n = Integer.parseInt(cardNum);
        } catch (Exception e) {
            return new Result(false, "Card number is not a valid number!");
        }
        if(n - 1 < 0 || n - 1 > Game.getCurrentPlayer().getDeck().size() - 1) return new Result(false, "Card number is wrong!");
        try {
            m = Integer.parseInt(blockNum);
        } catch (Exception e) {
            return new Result(false, "Block number is not a valid number!");
        }
        if(m - 1 < 0 || m - 1 > Game.getCurrentPlayer().getMap().length - 1) return new Result(false, "Block number is wrong!");

        String output = "";
        int numCard = n - 1, numBlock = m - 1;

        for (int i = 0; i < Game.getCurrentPlayer().getDeck().get(numCard).getDuration(); i++) {
            if(i + numBlock > Game.getCurrentPlayer().getMap().length - 1) return new Result(false, "Not a valid move!");
            if(Game.getCurrentPlayer().getMap()[numBlock + i] != null) return new Result(false, "Another card placed there!");
            if(!Game.getCurrentPlayer().getDeck().get(numCard).getName().equals("Repairman") && Game.getCurrentPlayer().getWreckedHouses().contains(numBlock + i)) return new Result(false, "One of the houses is wrecked!");
            if(Game.getCurrentPlayer().getDeck().get(numCard).getName().equals("Repairman") && !Game.getCurrentPlayer().getWreckedHouses().contains(numBlock + i)) return new Result(false, "The house is not wrecked!");
        }

        if(Game.getCurrentPlayer().getDeck().get(numCard).getCharacter() == Game.getCurrentPlayer().getCharacter()) {
            Game.getCurrentPlayer().getDeck().get(numCard).buff();
            output += "Card buffed becuase of having same character with player!\n";
        }
        if(Game.getCurrentPlayer().getDeck().size() == 5 && Game.getCurrentPlayer().getDeck().get(numCard).getCharacter() != GameCharacter.None && Game.getCurrentPlayer().getDeck().get(2).getCharacter() == Game.getCurrentPlayer().getDeck().get(numCard).getCharacter()) {
            int x = new Random().nextInt(5);
            if(x < 2) {
                Game.getCurrentPlayer().getDeck().get(numCard).buff();
                output += "Card buffed becuase of having same character with middle card!\n";
            }
        }

        if(Game.getCurrentPlayer().getDeck().get(n - 1).getCharacter() == GameCharacter.None) {
            output += "placed card: " + Game.getCurrentPlayer().getDeck().get(n - 1).getName() + " Spell Card " + Spell.valueOf(Game.getCurrentPlayer().getDeck().get(n - 1).getName()).getDescription() + " Duration = " + Game.getCurrentPlayer().getDeck().get(n - 1).getDuration() + "\n";
        }
        else {
            output += "placed card: " + Game.getCurrentPlayer().getDeck().get(n - 1).getName() + " Normal Card" + " Duration = " + Game.getCurrentPlayer().getDeck().get(n - 1).getDuration() + " Player Damage = " + Game.getCurrentPlayer().getDeck().get(n - 1).getPlayerDamage() + "Attack/Defense point =" + Game.getCurrentPlayer().getDeck().get(n - 1).getAttack() + "\n";
        }

        Game.placeCard(numCard, numBlock);
        Game.nextTurn();

        return new Result(true, output);
    }

    public static boolean isRoundFinished() {
        if(Game.getHostPlayer().getRemainTurn() <= 0 && Game.getGuestPlayer().getRemainTurn() <= 0) return true;
        return false;
    }

    public static Result moveTimeline() {
        String output = "";

        output += "Player One: " + " HP = " + Game.getHostPlayer().getHP() + " Damage = " + Game.getHostPlayer().getDmg() + "\n";
        output += "Player Two: " + " HP = " + Game.getGuestPlayer().getHP() + " Damage = " + Game.getGuestPlayer().getDmg() + "\n";

        for(int i = 0; i < 21; i++) {

            output += "Row" + (i + 1) + ":\t";
            if(Game.getHostPlayer().getWreckedHouses().contains(i)) output += "Wrecked";
            else if(Game.getHostPlayer().getMap()[i] == null) output += "Empty";
            else output += "Dmg " + Game.getHostPlayer().getMap()[i].getPlayerDamage() + " A/D " + Game.getHostPlayer().getMap()[i].getAttack();
            output += "\t";
            if(Game.getGuestPlayer().getWreckedHouses().contains(i)) output += "Wrecked";
            else if(Game.getGuestPlayer().getMap()[i] == null) output += "Empty";
            else output += "Dmg " + Game.getGuestPlayer().getMap()[i].getPlayerDamage() + " A/D " + Game.getGuestPlayer().getMap()[i].getAttack();
            output += "\n";

            Game.calulateTimeline(i);

            output += "Player One: " + " HP = " + Game.getHostPlayer().getHP() + " Damage = " + Game.getHostPlayer().getDmg() + "\n";
            output += "Player Two: " + " HP = " + Game.getGuestPlayer().getHP() + " Damage = " + Game.getGuestPlayer().getDmg() + "\n";
        }

        if(Game.getGuestPlayer().getHP() == 0) {
            output += "Game ended! Player one won!\n";
            Game.getHostPlayer().getPlayer().increaseXP(Game.getGuestPlayer().getPlayer().getMaxHP());
            Game.getGuestPlayer().getPlayer().increaseXP(Game.getHostPlayer().getPlayer().getMaxHP() / 4);
            output += "Player one earned " + Game.getGuestPlayer().getPlayer().getMaxHP() + " XP and player two earned " + (Game.getHostPlayer().getPlayer().getMaxHP() / 4) + " XP\n";

            if(Game.getBet() == 0) {
                Game.getHostPlayer().getPlayer().setMoney(Game.getHostPlayer().getPlayer().getMoney() + Game.getHostPlayer().getHP());
                output += "Player one earned " + Game.getHostPlayer().getHP() + " money\n";
            }
            else {
                Game.getHostPlayer().getPlayer().setMoney(Game.getHostPlayer().getPlayer().getMoney() + Game.getBet() * 2);
                output += "Player one earned " + Game.getBet() + " money and player two lost " + Game.getBet() + " money\n";
            }

            Game.endGame();
            App.setCurrentMenu(Menu.MainMenu);
        }
        if(Game.getHostPlayer().getHP() == 0) {
            output += "Game ended! Player two won!\n";
            Game.getHostPlayer().getPlayer().increaseXP(Game.getGuestPlayer().getPlayer().getMaxHP() / 4);
            Game.getGuestPlayer().getPlayer().increaseXP(Game.getHostPlayer().getPlayer().getMaxHP());
            output += "Player one earned " + (Game.getGuestPlayer().getPlayer().getMaxHP() / 4) + " XP and player two earned " + Game.getHostPlayer().getPlayer().getMaxHP() + " XP\n";

            if(Game.getBet() == 0) {
                Game.getGuestPlayer().getPlayer().setMoney(Game.getGuestPlayer().getPlayer().getMoney() + Game.getGuestPlayer().getHP());
                output += "Player two earned " + Game.getHostPlayer().getHP() + " money\n";
            }
            else {
                Game.getGuestPlayer().getPlayer().setMoney(Game.getGuestPlayer().getPlayer().getMoney() + Game.getBet() * 2);
                output += "Player two earned " + Game.getBet() + " money and player one lost " + Game.getBet() + " money\n";
            }


            Game.endGame();
            App.setCurrentMenu(Menu.MainMenu);
        }

        return new Result(true, output);
    }
}
