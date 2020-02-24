package com.montaury.citadels.round.action;


import com.montaury.citadels.CardPile;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;

public class EndRoundEffect extends Effect {
    @Override
    public void execute(Group group, CardPile pioche, GameRoundAssociations groups) {
        System.out.println("Fin du round");
    }
}
