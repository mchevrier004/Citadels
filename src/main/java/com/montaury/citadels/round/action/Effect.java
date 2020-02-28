package com.montaury.citadels.round.action;

import com.montaury.citadels.CardPile;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;

public class Effect {

    public boolean isExecutable(Group group, CardPile pioche,GameRoundAssociations groups) {
        return true;
    }

    public void execute(Group group, CardPile pioche,GameRoundAssociations groups) {

    }
}
