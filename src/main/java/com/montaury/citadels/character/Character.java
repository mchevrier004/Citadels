package com.montaury.citadels.character;

import com.montaury.citadels.district.DistrictType;
import io.vavr.collection.List;
import io.vavr.control.Option;

public enum Character {
    ASSASSIN(1, "Assassin", List.of("Kill")),
    THIEF(2, "Thief", List.of("Rob")),
    MAGICIAN(3, "Magician", List.of("Exchange cards with other player", "Exchange cards with pile")),
    KING(4, "King", List.of("Receive income"), DistrictType.NOBLE),
    BISHOP(5, "Bishop", List.of("Receive income"), DistrictType.RELIGIOUS),
    MERCHANT(6, "Merchant", List.of("Receive income", "Receive 1 gold"), DistrictType.TRADE),
    ARCHITECT(7, "Architect", List.of("Pick 2 cards", "Build district", "Build district")),
    WARLORD(8, "Warlord", List.of("Receive income", "Destroy district"), DistrictType.MILITARY);

    Character(int number, String name, List<String> powers)
    {
        this(number, name, powers, null);
    }

    Character(int number, String name,List<String> powers, DistrictType associatedDistrictType)
    {
        this.number = number;
        this.name = name;
        this.powers = powers;
        this.associatedDistrictType = Option.of(associatedDistrictType);
    }

    public int number() {
        return number;
    }

    public String getName() {
        return name;
    }

    public List<String> getPowers() { return powers;}

    public Option<DistrictType> associatedDistrictType() {
        return associatedDistrictType;
    }

    private final int number;
    private final String name;
    private final List<String> powers;
    private final Option<DistrictType> associatedDistrictType;
}