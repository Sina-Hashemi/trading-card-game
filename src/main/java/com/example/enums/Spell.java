package com.example.enums;

import com.example.model.Card;

public enum Spell { // TODO - ehsan - add simple short description (translate from doc)
    Shield(new Card("Shield", 1), "Protects your card"),
    Heal(new Card("Heal", 1), "Heals you"),
    PowerBuff(new Card("PowerBuff", 0), "Buff one of your cards"),
    HoleChanger(new Card("HoleChanger", 0), "Changes hole location"),
    Repairman(new Card("Repairman", 0), "Repair hole"),
    RoundReducer(new Card("RoundReducer", 0), "Reduce round for both players"),
    CardRemover(new Card("CardRemover", 0), "Steal one of opponent cards"),
    CardNerfer(new Card("CardNerfer", 0), "Nerf one of Opponent cards"),
    Copier(new Card("Copier", 0), "Copy one of opponent card"),
    Hider(new Card("Hider", 0), "Hide Opponent cards");

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
