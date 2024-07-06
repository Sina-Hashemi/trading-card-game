package com.example.enums;

import com.example.model.Card;

public enum Spell {
    Shield(new Card(1000, "Shield", 1), "Protects your card"),
    Heal(new Card(1001, "Heal", 1), "Heals you"),
    PowerBuff(new Card(1002, "PowerBuff", 0), "Buff one of your cards"),
    HoleChanger(new Card(1003, "HoleChanger", 0), "Changes hole location"),
    Repairman(new Card(1004, "Repairman", 0), "Repair hole"),
    RoundReducer(new Card(1005, "RoundReducer", 0), "Reduce round for both players"),
    CardRemover(new Card(1006, "CardRemover", 0), "Steal one of opponent cards"),
    CardNerfer(new Card(1007, "CardNerfer", 0), "Nerf one of Opponent cards"),
    Copier(new Card(1008, "Copier", 0), "Copy one of opponent card"),
    Hider(new Card(1009, "Hider", 0), "Hide Opponent cards");

    private final Card card;
    private final String description;

    Spell(Card card, String description) {
        this.card = card;
        this.description = description;
    }

    public Card getCard() {
        return card;
    }

    public String getDescription() {
        return description;
    }
}
