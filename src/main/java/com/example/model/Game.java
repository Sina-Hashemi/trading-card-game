package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.example.model.Card.GameCharacter;

public class Game {

    public static class PlayerVars {
        private User player;
        private GameCharacter character;
        private Card[] map;
        private ArrayList<Card> deck;
        private ArrayList<Integer> wreckedHouses;
        private int HP, remainTurn, dmg, deckSize;
        private boolean isHide;

        public PlayerVars(User player) {
            this.player = player;
            character = GameCharacter.None;
            map = new Card[21];
            deck = new ArrayList<>();
            wreckedHouses = new ArrayList<>();
            wreckedHouses.add(new Random().nextInt(21));
            HP = player.getMaxHP();
            remainTurn = 4;
            dmg = 0;
            deckSize = 5;
            isHide = false;
        }

        public void renew() {
            map = new Card[21];
            wreckedHouses = new ArrayList<>();
            wreckedHouses.add(new Random().nextInt(21));
            remainTurn = 4;
            dmg = 0;
            deckSize = 5;
            isHide = false;
        }

        public User getPlayer() {
            return player;
        }

        public void setPlayer(User player) {
            this.player = player;
        }

        public GameCharacter getCharacter() {
            return character;
        }

        public void setCharacter(GameCharacter character) {
            this.character = character;
        }

        public Card[] getMap() {
            return map;
        }

        public void setMap(Card[] map) {
            this.map = map;
        }

        public ArrayList<Card> getDeck() {
            return deck;
        }

        public void setDeck(ArrayList<Card> deck) {
            this.deck = deck;
        }

        public ArrayList<Integer> getWreckedHouses() {
            return wreckedHouses;
        }

        public void setWreckedHouses(ArrayList<Integer> wreckedHouses) {
            this.wreckedHouses = wreckedHouses;
        }

        public int getHP() {
            return HP;
        }

        public void setHP(int hP) {
            HP = hP;
        }

        public int getRemainTurn() {
            return remainTurn;
        }

        public void setRemainTurn(int remainTurn) {
            this.remainTurn = remainTurn;
        }

        public int getDmg() {
            return dmg;
        }

        public void setDmg(int dmg) {
            this.dmg = dmg;
        }

        public int getDeckSize() {
            return deckSize;
        }

        public void setDeckSize(int deckSize) {
            this.deckSize = deckSize;
        }

        public boolean isHide() {
            return isHide;
        }

        public void setHide(boolean isHide) {
            this.isHide = isHide;
        }
    }

    private static PlayerVars hostPlayer, guestPlayer, currentPlayer, currentEnemy;
    private static int bet = 0;

    public static void startGame(User player1, User player2) {
        hostPlayer = new PlayerVars(player1);
        guestPlayer = new PlayerVars(player2);
        currentPlayer = hostPlayer;
        currentEnemy = guestPlayer;

        int x = new Random().nextInt(5);
        if(x > 2) changeSides();

        configureDeck(hostPlayer);
        configureDeck(guestPlayer);
    }

    public static void changeSides() {
        PlayerVars temp = currentPlayer;
        currentPlayer = currentEnemy;
        currentEnemy = temp;
    }

    public static void configureDeck(PlayerVars player) {
        int spellCount = 0, n;
        for (Card card : player.deck) {
            if(card.getCharacter() == GameCharacter.None) spellCount++;
        }
        while (true) {
            if(player.deck.size() >= player.deckSize) break;
            n = new Random().nextInt(player.player.getCards().size());
            if(player.player.getCards().get(n).getLevel() != 0 || (player.player.getCards().get(n).getLevel() == 0 && spellCount < 2)) {
                if((player.player.getCards().get(n).getCard().getName().equals("HoleChanger") && hostPlayer.wreckedHouses.isEmpty() && guestPlayer.wreckedHouses.isEmpty()) || (player.player.getCards().get(n).getCard().getName().equals("Repairman") && player.wreckedHouses.isEmpty())) continue;
                player.deck.add(player.player.getCards().get(n).getCard().clone());
                for(int i = 1; i < player.player.getCards().get(n).getLevel(); i++) {
                    player.deck.get(player.deck.size() - 1).buff();
                }
            }
        }
    }

    public static PlayerVars getHostPlayer() {
        return hostPlayer;
    }

    public static PlayerVars getGuestPlayer() {
        return guestPlayer;
    }

    public static PlayerVars getCurrentPlayer() {
        return currentPlayer;
    }

    public static PlayerVars getCurrentEnemy() {
        return currentEnemy;
    }

    public static void setBet(int bet) {
        hostPlayer.player.setMoney(hostPlayer.player.getMoney() - bet);
        guestPlayer.player.setMoney(guestPlayer.player.getMoney() - bet);
        Game.bet = bet;
    }

    public static int getBet() {
        return bet;
    }

    public static void placeCard(int cardNum, int blockNum) {
        if(currentPlayer.deck.get(cardNum).getCharacter() == GameCharacter.None) {
            if(currentPlayer.deck.get(cardNum).getName().equals("Shield")) {
                currentPlayer.map[blockNum].setAttack(999);
            }
            else if(currentPlayer.deck.get(cardNum).getName().equals("Heal")) {
                currentPlayer.map[blockNum] = currentPlayer.deck.get(cardNum);
            }
            else if(currentPlayer.deck.get(cardNum).getName().equals("PowerBuff")) {
                while(true) {
                    int x = new Random().nextInt(21);
                    if(currentPlayer.map[x] == null) continue;
                    currentPlayer.map[x].buff();
                    break;
                }
            }
            else if(currentPlayer.deck.get(cardNum).getName().equals("HoleChanger")) {
                start: for (int i = 0; i < hostPlayer.wreckedHouses.size();) {
                    while (true) {
                        int x = new Random().nextInt(21);
                        if(hostPlayer.wreckedHouses.contains(x)) continue;
                        if(hostPlayer.map[x] == null) {
                            hostPlayer.wreckedHouses.add(x);
                            hostPlayer.wreckedHouses.remove(i);
                            break start;
                        }
                    }
                }
                start: for (int i = 0; i < guestPlayer.wreckedHouses.size();) {
                    while (true) {
                        int x = new Random().nextInt(21);
                        if(guestPlayer.wreckedHouses.contains(x)) continue;
                        if(guestPlayer.map[x] == null) {
                            guestPlayer.wreckedHouses.add(x);
                            guestPlayer.wreckedHouses.remove(i);
                            break start;
                        }
                    }
                }
            }
            else if(currentPlayer.deck.get(cardNum).getName().equals("Repairman")) {
                currentPlayer.wreckedHouses.remove(blockNum);
            }
            else if(currentPlayer.deck.get(cardNum).getName().equals("RoundReducer")) {
                currentPlayer.remainTurn -= 1;
                currentEnemy.remainTurn -= 1;
                if(currentPlayer.remainTurn < 0) currentPlayer.remainTurn = 0;
                if(currentEnemy.remainTurn < 0) currentEnemy.remainTurn = 0;
            }
            else if(currentPlayer.deck.get(cardNum).getName().equals("CardRemover")) {
                int x = new Random().nextInt(currentEnemy.deck.size());
                currentPlayer.deck.add(currentEnemy.deck.get(x));
                currentEnemy.deck.remove(x);
                currentEnemy.deckSize -= 1;
            }
            else if(currentPlayer.deck.get(cardNum).getName().equals("CardNerfer")) {
                int x = new Random().nextInt(currentEnemy.deck.size());
                currentEnemy.deck.get(x).nerf();
            }
            else if(currentPlayer.deck.get(cardNum).getName().equals("Copier")) {
                int x = new Random().nextInt(currentEnemy.deck.size());
                currentPlayer.deck.add(currentEnemy.deck.get(x).clone());
            }
            else if(currentPlayer.deck.get(cardNum).getName().equals("Hider")) {
                currentEnemy.isHide = true;
                Collections.shuffle(currentEnemy.deck);
            }
            else if(currentPlayer.deck.get(cardNum).getName().equals("FirstBlood")) {
                currentPlayer.deck.get(cardNum).setAttack(20);
                currentPlayer.deck.get(cardNum).setPlayerDamage(50);;
                if(blockNum == 0) currentPlayer.deck.get(cardNum).buff();
                currentPlayer.map[blockNum] = currentPlayer.deck.get(cardNum);
            }
        }
        else {
            for (int i = 0; i < currentPlayer.deck.get(cardNum).getDuration(); i++) {
                currentPlayer.map[i + blockNum] = currentPlayer.deck.get(cardNum);
            }
        }
        currentPlayer.deck.remove(cardNum);
        configureDeck(currentPlayer);
    }

    public static void nextTurn() {
        currentPlayer.remainTurn -= 1;
        checkMaps();
        changeSides();
    }

    private static void checkMaps() {
        hostPlayer.dmg = 0;
        guestPlayer.dmg = 0;
        for (int i = 0; i < 21; i++) {
            if(hostPlayer.map[i] != null && guestPlayer.map[i] != null) {
                if(hostPlayer.map[i].getAttack() > guestPlayer.map[i].getAttack()) {
                    hostPlayer.dmg += hostPlayer.map[i].getPlayerDamage();
                }
                else if(hostPlayer.map[i].getAttack() < guestPlayer.map[i].getAttack()) {
                    guestPlayer.dmg += guestPlayer.map[i].getPlayerDamage();
                }
            }
            else if(hostPlayer.map[i] == null && guestPlayer.map[i] != null) {
                guestPlayer.dmg += guestPlayer.map[i].getPlayerDamage();
            }
            else if(hostPlayer.map[i] != null && guestPlayer.map[i] == null) {
                hostPlayer.dmg += hostPlayer.map[i].getPlayerDamage();
            }
        }
    }

    public static void calulateTimeline(int i) {

        if(hostPlayer.map[i] != null && guestPlayer.map[i] != null) {
            if(hostPlayer.map[i].getAttack() > guestPlayer.map[i].getAttack()) {
                hostPlayer.dmg -= hostPlayer.map[i].getPlayerDamage();
                guestPlayer.HP -= hostPlayer.map[i].getPlayerDamage();
            }
            else if(hostPlayer.map[i].getAttack() < guestPlayer.map[i].getAttack()) {
                guestPlayer.dmg -= guestPlayer.map[i].getPlayerDamage();
                hostPlayer.HP -= guestPlayer.map[i].getPlayerDamage();
            }
        }
        else if(hostPlayer.map[i] == null && guestPlayer.map[i] != null) {
            guestPlayer.dmg -= guestPlayer.map[i].getPlayerDamage();
            hostPlayer.HP -= guestPlayer.map[i].getPlayerDamage();
        }
        else if(hostPlayer.map[i] != null && guestPlayer.map[i] == null) {
            hostPlayer.dmg -= hostPlayer.map[i].getPlayerDamage();
            guestPlayer.HP -= hostPlayer.map[i].getPlayerDamage();
        }

        if(hostPlayer.map[i] != null && hostPlayer.map[i].getName().equals("Heal")) hostPlayer.HP += 50;
        if(guestPlayer.map[i] != null && guestPlayer.map[i].getName().equals("Heal")) guestPlayer.HP += 50;

        if(hostPlayer.HP < 0) hostPlayer.HP = 0;
        if(guestPlayer.HP < 0) guestPlayer.HP = 0;
    }

    public static void endGame() {
        bet = 0;
    }

    public static void newRound() {
        hostPlayer.renew();
        guestPlayer.renew();
    }
}
