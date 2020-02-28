package com.montaury.citadels.round.action;

import com.montaury.citadels.CardPile;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;

public class DestroyDistrictEffect extends Effect {
    @Override
    public void execute(Group group, CardPile pioche, GameRoundAssociations groups) {
        //tu met des trucs là
    }
    @Override
    public boolean isExecutable(Group group, CardPile pioche, GameRoundAssociations groups) {
        return DestroyDistrictAction.districtsDestructibleBy(groups, group.player()).exists(districtsByPlayer -> !districtsByPlayer._2().isEmpty());
    }
}
