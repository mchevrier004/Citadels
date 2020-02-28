package com.montaury.citadels.round.action;

import com.montaury.citadels.CardPile;
import com.montaury.citadels.district.Card;
import com.montaury.citadels.player.Player;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;

public class Discard2For2CoinsEffect extends Effect {
    @Override
    public void execute(Group group, CardPile pioche, GameRoundAssociations groups) {
        Player player = group.player();
        Card card = player.controller.selectAmong(player.cards());
        player.cards = player.cards().remove(card);
        pioche.discard(card);
        player.add(2);
    }
    @Override
    public boolean isExecutable(Group group, CardPile pioche, GameRoundAssociations groups) {
        return !group.player().buildableDistrictsInHand().isEmpty();
    }
}
