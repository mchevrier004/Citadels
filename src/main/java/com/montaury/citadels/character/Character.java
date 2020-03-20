package com.montaury.citadels.character;

import com.montaury.citadels.district.DistrictType;
import com.montaury.citadels.round.ActionType;
import io.vavr.collection.List;

public enum Character {
    //Les personnages du jeu de base
    ASSASSIN(1, "Assassin", List.of(ActionType.killPlayer)),
    THIEF(2, "Thief", List.of(ActionType.robPlayer)),
    MAGICIAN(3, "Magician", List.of(ActionType.exchangeCards, ActionType.exchangeCardsWithPile)),
    KING(4, "King", List.of(ActionType.receiveIncome), DistrictType.NOBLE),
    BISHOP(5, "Bishop", List.of(ActionType.receiveIncome), DistrictType.RELIGIOUS),
    MERCHANT(6, "Merchant", List.of(ActionType.receiveIncome, ActionType.receive1Gold), DistrictType.TRADE),
    ARCHITECT(7, "Architect", List.of(ActionType.pick2Cards, ActionType.buildDistrict, ActionType.buildDistrict)),
    WARLORD(8, "Warlord", List.of(ActionType.receiveIncome, ActionType.destroyDistrict), DistrictType.MILITARY),
    //Les personnages du DLC
    WIZARD(1, "Wizard", List.of()),
    TAX_COLLECTOR(2, "Tax Collector", List.of()),
    ALCHIMIST(6, "Alchimist", List.of()),
    ABBOT(5, "Abbot", List.of()),
    EMPOROR(4, "Emporor", List.of()),
    ARTIST(10, "Artist", List.of());

    Character(int number, String name, List<ActionType> powers)
    {
        this(number, name, powers, null);
    }

    Character(int number, String name, List<ActionType> powers, DistrictType districtType)
    {
        this.number = number;
        this.name = name;
        this.powers = powers;
        this.associatedDistrictType = districtType;
    }

    public int number() {
        return number;
    }

    public String getName() {
        return name;
    }

    public List<ActionType> getPowers() { return powers;}

    public DistrictType getAssociatedDistrictType() {
        return associatedDistrictType;
    }

    private final int number;
    private final String name;
    private final List<ActionType> powers;
    private final DistrictType associatedDistrictType;
}