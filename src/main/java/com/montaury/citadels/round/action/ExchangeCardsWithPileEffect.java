package com.montaury.citadels.round.action;

import com.montaury.citadels.CardPile;
import com.montaury.citadels.district.Card;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;
import io.vavr.collection.Set;

public class ExchangeCardsWithPileEffect extends Effect {
    @Override
    public void execute(Group group, CardPile pioche, GameRoundAssociations groups) {
        Set<Card> cardsToSwap = group.player().controller.selectManyAmong(group.player().cards());
        group.player().cards = group.player().cards().removeAll(cardsToSwap);
        group.player().add(pioche.swapWith(cardsToSwap.toList()));
    }
}
