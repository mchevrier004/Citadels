package com.montaury.citadels.round.action;

import com.montaury.citadels.CardPile;
import com.montaury.citadels.district.Card;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;

public class BuildDistrictEffect extends Effect {
    @Override
    public void execute(Group group, CardPile pioche, GameRoundAssociations groups) {
        Card card = group.player().controller.selectAmong(group.player().buildableDistrictsInHand());
        group.player().buildDistrict(card);
    }
    @Override
    public boolean isExecutable(Group group, CardPile pioche, GameRoundAssociations groups) {
        return !group.player().buildableDistrictsInHand().isEmpty();
    }
}
