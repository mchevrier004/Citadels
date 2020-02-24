package com.montaury.citadels.round.action;

import com.montaury.citadels.CardPile;
import com.montaury.citadels.district.Card;
import com.montaury.citadels.district.District;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

public class Draw2CardsAndKeep1Effect extends Effect {
    @Override
    public void execute(Group group, CardPile pioche, GameRoundAssociations groups) {
        Set<Card> cardsDrawn = pioche.draw(2);
        if (!group.player().city().has(District.LIBRARY)) {
            Card keptCard = group.player().controller.selectAmong(cardsDrawn);
            pioche.discard(cardsDrawn.remove(keptCard).toList());
            cardsDrawn = HashSet.of(keptCard);
        }
        group.player().add(cardsDrawn);
    }
}
