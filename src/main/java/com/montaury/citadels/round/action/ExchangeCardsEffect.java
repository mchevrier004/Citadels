package com.montaury.citadels.round.action;

import com.montaury.citadels.CardPile;
import com.montaury.citadels.district.Card;
import com.montaury.citadels.player.Player;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;

public class ExchangeCardsEffect extends Effect {
    @Override
    public void execute(Group group, CardPile pioche, GameRoundAssociations groups) {
        Player playerToSwapWith = group.player().controller.selectPlayerAmong(groups.associations.map(Group::player).remove(group.player()));
        group.player().exchangeHandWith(playerToSwapWith);
    }
    @Override
    public boolean isExecutable(Group group, CardPile pioche, GameRoundAssociations groups) {
        return !group.player().buildableDistrictsInHand().isEmpty();
    }
}
