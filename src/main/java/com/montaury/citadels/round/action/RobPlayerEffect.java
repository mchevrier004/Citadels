package com.montaury.citadels.round.action;

import com.montaury.citadels.CardPile;
import com.montaury.citadels.character.Character;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;
import io.vavr.collection.List;

public class RobPlayerEffect extends Effect {
    @Override
    public void execute(Group group, CardPile pioche, GameRoundAssociations groups) {
        Character character = group.player().controller.selectAmong(List.of(Character.MAGICIAN, Character.KING, Character.BISHOP, Character.MERCHANT, Character.ARCHITECT, Character.WARLORD)
                .removeAll(groups.associations.find(Group::isMurdered).map(Group::character)));
        groups.associationToCharacter(character).peek(association -> association.stolenBy(group.player()));
    }
    @Override
    public boolean isExecutable(Group group, CardPile pioche, GameRoundAssociations groups) {
        return !group.player().buildableDistrictsInHand().isEmpty();
    }
}
