package com.montaury.citadels.round.action;

import com.montaury.citadels.CardPile;
import com.montaury.citadels.district.Card;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;

public class Pick2CardsEffect extends Effect {
    @Override
    public void execute(Group group, CardPile pioche, GameRoundAssociations groups) {
        group.player().add(pioche.draw(2));
    }
}
