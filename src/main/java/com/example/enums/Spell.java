package com.example.enums;

import com.example.model.Card;

public enum Spell {
    Shield(new Card("Shield", 1)),
    Heal(new Card("Heal", 1)),
    PowerBuff(new Card("Power buff", 0)),
    HoleChanger(new Card("Hole location changer", 0)),
    Repairman(new Card("Repairman", 0)),
    RoundReducer(new Card("Round reducer", 0)),
    CardRemover(new Card("Opponent card remover", 0)),
    CardNerfer(new Card("Opponent cards nerfer", 0)),
    Copier(new Card("Opponent card copier", 0)),
    Hider(new Card("Opponent card hider", 0));

    private final Card card;

    Spell(Card card) {
        this.card = card;
    }
}
