package com.montaury.citadels.round.action;

import com.montaury.citadels.CardPile;
import com.montaury.citadels.district.Card;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;

public class Draw3Keep1Effect extends Effect {
    @Override
    public void execute(Group group, CardPile pioche, GameRoundAssociations groups) {
        Card card = group.player().controller.selectAmong(group.player().buildableDistrictsInHand());
        group.player().buildDistrict(card);
    }
}
