package com.montaury.citadels.player;

import com.montaury.citadels.character.Character;
import com.montaury.citadels.district.Card;
import com.montaury.citadels.district.DestructibleDistrict;
import com.montaury.citadels.round.ActionType;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Set;

public interface PlayerController {
    Character selectOwnCharacter(List<Character> availableCharacters, List<Character> faceUpRevealedCharacters);

    ActionType selectActionAmong(List<ActionType> actions);

    Card selectAmong(Set<Card> cards);

    Character selectAmong(List<Character> characters);

    Player selectPlayerAmong(List<Player> players);

    Set<Card> selectManyAmong(Set<Card> cards);

    DestructibleDistrict selectDistrictToDestroyAmong(Map<Player, List<DestructibleDistrict>> playersDistricts);

    boolean acceptCard(Card card);
}
