package com.montaury.citadels.round.action;

import com.montaury.citadels.CardPile;
import com.montaury.citadels.district.District;
import com.montaury.citadels.district.DistrictType;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;

public class ReceiveIncomeEffect extends Effect {
    @Override
    public void execute(Group group, CardPile pioche, GameRoundAssociations groups) {
        DistrictType type = group.character.getAssociatedDistrictType();
        if (type != null) {
            for (District d : group.player().city().districts()) {
                if (d.districtType() == type) {
                    group.player().add(1);
                }
            }
            if (group.player().city().districts().contains(District.MAGIC_SCHOOL)) {
                group.player().add(1);
            }
        }
    }
    @Override
    public boolean isExecutable(Group group, CardPile pioche, GameRoundAssociations groups) {
        return !group.player().buildableDistrictsInHand().isEmpty();
    }
}
