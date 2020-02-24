package com.montaury.citadels.round.action;

import com.montaury.citadels.CardPile;
import com.montaury.citadels.character.Character;
import com.montaury.citadels.district.Card;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;
import io.vavr.collection.List;

public class KillPlayerEffect extends Effect {
    @Override
    public void execute(Group group, CardPile pioche, GameRoundAssociations groups) {
        Character characterToMurder = group.player().controller.selectAmong(List.of(Character.THIEF, Character.MAGICIAN, Character.KING, Character.BISHOP, Character.MERCHANT, Character.ARCHITECT, Character.WARLORD));
        groups.associationToCharacter(characterToMurder).peek(Group::murder);
    }
}
