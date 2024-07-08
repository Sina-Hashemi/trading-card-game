package com.example.controller;

import com.example.model.*;
import com.example.model.Card.GameCharacter;
import com.example.model.Game.PlayerVars;
import com.example.model.History.GameResult;

import java.util.Random;

import com.example.enums.*;

public class GameMenuController {

    public static boolean areCharactersOK() {
        if(Game.getHostPlayer().getCharacter() == GameCharacter.None || Game.getGuestPlayer().getCharacter() == GameCharacter.None) return false;
        return true;
    }

    public static Result handleCharacter(String character) {
        if(Game.getHostPlayer().getCharacter() == GameCharacter.None) {
            try {
                Game.getHostPlayer().setCharacter(GameCharacter.valueOf(character));
            } catch (Exception e) {
                return new Result(false, "wrong character!");
            }
            return new Result(true, "Host character set successfully");
        }
        try {
            Game.getGuestPlayer().setCharacter(GameCharacter.valueOf(character));
        } catch (Exception e) {
            return new Result(false, "wrong character!");
        }
        return new Result(true, "Guest character set successfully");
    }

    public static Result showGame() {
        String output = "";

        output += "\u001B[34mPlayer One: \u001B[0m" + "\u001B[31m HP = " + Game.getHostPlayer().getHP() + "\u001B[32m Damage = " + Game.getHostPlayer().getDmg() + "\u001B[35m Remaining Turn = " + Game.getHostPlayer().getRemainTurn() + "\u001B[0m Character = " + Game.getHostPlayer().getCharacter() + "\n";
        int n = 1;
        for (Card card : Game.getHostPlayer().getDeck()) {
            if(Game.getHostPlayer().isHide()) {
                output += "Your cards are hided!\n";
                Game.getHostPlayer().setHide(false);
                break;
            }

            if(card.getCharacter() == GameCharacter.None) {
                output += String.format("%d: %-15s \u001B[36m Duration = %-5d\u001B[0m %-25s\n", n, card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription());
            }
            else {
                output += String.format("%d: %-15s \u001B[36m Duration = %-5d \u001B[31m Damage = %-5d \u001B[32m A/D point = %-5d\u001B[0m\n", n, card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack());
            }
            n++;
        }

        for (int i = 0; i < 21; i++) {
            output += String.format("\u001B[47m\u001B[30mRow%-2d:\u001B[0m\t", (i + 1));
            String x = "", y = "";
            if(Game.getHostPlayer().getWreckedHouses().contains(i)) x += "Wrecked";
            else if(Game.getHostPlayer().getMap()[i] == null) x += "Empty";
            else if(Game.getHostPlayer().getMap()[i].getCharacter() == GameCharacter.None) x += Game.getHostPlayer().getMap()[i].getName();
            else x += "\u001B[31mDmg " + Game.getHostPlayer().getMap()[i].getPlayerDamage() + " \u001B[32mA/D " + Game.getHostPlayer().getMap()[i].getAttack() + "\u001B[0m";
            // else x += "Dmg " + Game.getHostPlayer().getMap()[i].getPlayerDamage() + " A/D " + Game.getHostPlayer().getMap()[i].getAttack();
            // else x += String.format("\u001B[31mDmg %-5d \u001B[32mA/D %-5d\u001B[0m", Game.getHostPlayer().getMap()[i].getPlayerDamage(), Game.getHostPlayer().getMap()[i].getAttack());

            if(Game.getGuestPlayer().getWreckedHouses().contains(i)) y += "Wrecked";
            else if(Game.getGuestPlayer().getMap()[i] == null) y += "Empty";
            else if(Game.getGuestPlayer().getMap()[i].getCharacter() == GameCharacter.None) y += Game.getGuestPlayer().getMap()[i].getName();
            else y += "\u001B[31mDmg " + Game.getGuestPlayer().getMap()[i].getPlayerDamage() + " \u001B[32mA/D " + Game.getGuestPlayer().getMap()[i].getAttack() + "\u001B[0m";
            // else y += "Dmg " + Game.getGuestPlayer().getMap()[i].getPlayerDamage() + " A/D " + Game.getGuestPlayer().getMap()[i].getAttack();
            // else y += String.format("\u001B[31mDmg %-5d \u001B[32mA/D %-5d\u001B[0m", Game.getGuestPlayer().getMap()[i].getPlayerDamage(), Game.getGuestPlayer().getMap()[i].getAttack());

            output += String.format("%-50s %-50s \n", x, y);
        }

        output += "\u001B[34mPlayer Two: \u001B[0m" + "\u001B[31m HP = " + Game.getGuestPlayer().getHP() + "\u001B[32m Damage = " + Game.getGuestPlayer().getDmg() + "\u001B[35m Remaining Turn = " + Game.getGuestPlayer().getRemainTurn() + "\u001B[0m Character = " + Game.getGuestPlayer().getCharacter() + "\n";
        n = 1;
        for (Card card : Game.getGuestPlayer().getDeck()) {
            if(Game.getGuestPlayer().isHide()) {
                output += "Your cards are hided!\n";
                Game.getGuestPlayer().setHide(false);
                break;
            }

            if(card.getCharacter() == GameCharacter.None) {
                output += String.format("%d: %-15s \u001B[36m Duration = %-5d\u001B[0m %-25s\n", n, card.getName(), card.getDuration(), Spell.valueOf(card.getName()).getDescription());
            }
            else {
                output += String.format("%d: %-15s \u001B[36m Duration = %-5d \u001B[31m Damage = %-5d \u001B[32m A/D point = %-5d\u001B[0m\n", n, card.getName(), card.getDuration(), card.getPlayerDamage(), card.getAttack());
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
            output += n + ": " + player.getDeck().get(n - 1).getName() + " Normal Card" + " Duration = " + player.getDeck().get(n - 1).getDuration() + " Player Damage = " + player.getDeck().get(n - 1).getPlayerDamage() + "A/D point = " + player.getDeck().get(n - 1).getAttack() + " character = " + player.getDeck().get(n - 1).getCharacter() + "\n";
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
            if(!Game.getCurrentPlayer().getDeck().get(numCard).getName().equals("Shield") && Game.getCurrentPlayer().getMap()[numBlock + i] != null) return new Result(false, "Another card placed there!");
            if(Game.getCurrentPlayer().getDeck().get(numCard).getName().equals("Shield") && Game.getCurrentPlayer().getMap()[numBlock + i] == null) return new Result(false, "There is no card to protect!");
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

        output += "\u001B[34mPlayer One: \u001B[0m" + "\u001B[31m HP = " + Game.getHostPlayer().getHP() + "\u001B[32m Damage = " + Game.getHostPlayer().getDmg() + "\n";
        output += "\u001B[34mPlayer Two: \u001B[0m" + "\u001B[31m HP = " + Game.getGuestPlayer().getHP() + "\u001B[32m Damage = " + Game.getGuestPlayer().getDmg() + "\n";

        for(int i = 0; i < 21; i++) {

            output += String.format("\u001B[47m\u001B[30mRow%-2d:\u001B[0m\t", (i + 1));
            String x = "", y = "";
            if(Game.getHostPlayer().getWreckedHouses().contains(i)) x += "Wrecked";
            else if(Game.getHostPlayer().getMap()[i] == null) x += "Empty";
            else if(Game.getHostPlayer().getMap()[i].getCharacter() == GameCharacter.None) x += Game.getHostPlayer().getMap()[i].getName();
            else x += "\u001B[31mDmg " + Game.getHostPlayer().getMap()[i].getPlayerDamage() + " \u001B[32mA/D " + Game.getHostPlayer().getMap()[i].getAttack() + "\u001B[0m";
            if(Game.getGuestPlayer().getWreckedHouses().contains(i)) y += "Wrecked";
            else if(Game.getGuestPlayer().getMap()[i] == null) y += "Empty";
            else if(Game.getGuestPlayer().getMap()[i].getCharacter() == GameCharacter.None) y += Game.getGuestPlayer().getMap()[i].getName();
            else y += "\u001B[31mDmg " + Game.getGuestPlayer().getMap()[i].getPlayerDamage() + " \u001B[32mA/D " + Game.getGuestPlayer().getMap()[i].getAttack() + "\u001B[0m";

            output += String.format("%-50s %-50s \n", x, y);

            Game.calulateTimeline(i);

            output += "\u001B[34mPlayer One: \u001B[0m" + "\u001B[31m HP = " + Game.getHostPlayer().getHP() + "\u001B[32m Damage = " + Game.getHostPlayer().getDmg() + "\n";
            output += "\u001B[34mPlayer Two: \u001B[0m" + "\u001B[31m HP = " + Game.getGuestPlayer().getHP() + "\u001B[32m Damage = " + Game.getGuestPlayer().getDmg() + "\n";

            if(Game.getGuestPlayer().getHP() == 0) {
                output += "Game ended! Player one won!\n";
                Game.getHostPlayer().getPlayer().increaseXP(Game.getGuestPlayer().getPlayer().getMaxHP());
                Game.getGuestPlayer().getPlayer().increaseXP(Game.getHostPlayer().getPlayer().getMaxHP() / 4);
                output += "Player one earned " + Game.getGuestPlayer().getPlayer().getMaxHP() + " XP and player two earned " + (Game.getHostPlayer().getPlayer().getMaxHP() / 4) + " XP\n";

                if(Game.getBet() == 0) {
                    Game.getHostPlayer().getPlayer().setMoney(Game.getHostPlayer().getPlayer().getMoney() + Game.getHostPlayer().getHP());
                    output += "Player one earned " + Game.getHostPlayer().getHP() + " money\n";

                    App.getGameHistories().add(new History(Game.getGuestPlayer().getPlayer().getID(), GameResult.WON, "earned " + Game.getGuestPlayer().getPlayer().getMaxHP() + " XP" + ", " + "earned " + Game.getHostPlayer().getHP() + " money"));
                    Game.getHostPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                    App.getGameHistories().add(new History(Game.getHostPlayer().getPlayer().getID(), GameResult.LOST, "earned " + (Game.getHostPlayer().getPlayer().getMaxHP() / 4) + " XP"));
                    Game.getGuestPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                }
                else {
                    Game.getHostPlayer().getPlayer().setMoney(Game.getHostPlayer().getPlayer().getMoney() + Game.getBet() * 2);
                    output += "Player one earned " + Game.getBet() + " money and player two lost " + Game.getBet() + " money\n";

                    App.getGameHistories().add(new History(Game.getGuestPlayer().getPlayer().getID(), GameResult.WON, "earned " + Game.getGuestPlayer().getPlayer().getMaxHP() + " XP" + ", " + "earned " + Game.getBet() + " money"));
                    Game.getHostPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                    App.getGameHistories().add(new History(Game.getHostPlayer().getPlayer().getID(), GameResult.LOST, "earned " + (Game.getHostPlayer().getPlayer().getMaxHP() / 4) + " XP" + ", " + "lost " + Game.getBet() + " money"));
                    Game.getGuestPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                }

                Game.endGame();
                App.setCurrentMenu(Menu.MainMenu);
                return new Result(true, output);
            }
            if(Game.getHostPlayer().getHP() == 0) {
                output += "Game ended! Player two won!\n";
                Game.getHostPlayer().getPlayer().increaseXP(Game.getGuestPlayer().getPlayer().getMaxHP() / 4);
                Game.getGuestPlayer().getPlayer().increaseXP(Game.getHostPlayer().getPlayer().getMaxHP());
                output += "Player one earned " + (Game.getGuestPlayer().getPlayer().getMaxHP() / 4) + " XP and player two earned " + Game.getHostPlayer().getPlayer().getMaxHP() + " XP\n";

                if(Game.getBet() == 0) {
                    Game.getGuestPlayer().getPlayer().setMoney(Game.getGuestPlayer().getPlayer().getMoney() + Game.getGuestPlayer().getHP());
                    output += "Player two earned " + Game.getHostPlayer().getHP() + " money\n";

                    App.getGameHistories().add(new History(Game.getGuestPlayer().getPlayer().getID(), GameResult.LOST, "earned " + (Game.getGuestPlayer().getPlayer().getMaxHP() / 4) + " XP"));
                    Game.getHostPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                    App.getGameHistories().add(new History(Game.getHostPlayer().getPlayer().getID(), GameResult.WON, "earned " + Game.getHostPlayer().getPlayer().getMaxHP() + " XP" + ", " + "earned " + Game.getGuestPlayer().getHP() + " money"));
                    Game.getGuestPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                }
                else {
                    Game.getGuestPlayer().getPlayer().setMoney(Game.getGuestPlayer().getPlayer().getMoney() + Game.getBet() * 2);
                    output += "Player two earned " + Game.getBet() + " money and player one lost " + Game.getBet() + " money\n";

                    App.getGameHistories().add(new History(Game.getGuestPlayer().getPlayer().getID(), GameResult.LOST, "earned " + (Game.getGuestPlayer().getPlayer().getMaxHP() / 4) + " XP" + ", " + "lost " + Game.getBet() + " money"));
                    Game.getHostPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                    App.getGameHistories().add(new History(Game.getHostPlayer().getPlayer().getID(), GameResult.WON, "earned " + Game.getHostPlayer().getPlayer().getMaxHP() + " XP" + ", " + "earned " + Game.getBet() + " money"));
                    Game.getGuestPlayer().getPlayer().getRecords().add(App.getGameHistories().get(App.getGameHistories().size() - 1).getID());
                }

                Game.endGame();
                App.setCurrentMenu(Menu.MainMenu);
                return new Result(true, output);
            }
        }

        return new Result(true, output);
    }
}
