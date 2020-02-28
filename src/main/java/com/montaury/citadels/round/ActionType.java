package com.montaury.citadels.round;
import com.montaury.citadels.CardPile;
import com.montaury.citadels.round.action.*;

public enum ActionType {
    draw2CardsAndKeep1(new Draw2CardsAndKeep1Effect()),
    receive2Coins(new Receive2CoinsEffect()),
    draw3Keep1(new Draw3Keep1Effect()),
    exchangeCardsWithPile(new ExchangeCardsWithPileEffect()),
    receiveIncome(new ReceiveIncomeEffect()),
    pick2Cards(new Pick2CardsEffect()),
    buildDistrict(new BuildDistrictEffect()),
    destroyDistrict(new DestroyDistrictEffect()),
    draw3For2Coins(new Draw3For2CoinsEffect()),
    discard2For2Coins(new Discard2For2CoinsEffect()),
    endRound(new EndRoundEffect()),
    exchangeCards(new ExchangeCardsEffect()),
    killPlayer(new KillPlayerEffect()),
    receive1Gold(new Receive1GoldEffect()),
    robPlayer(new RobPlayerEffect());



    public final Effect effect;

    ActionType(Effect effect) {
        this.effect = effect;
    }

    public void execute(Group group, CardPile pioche, GameRoundAssociations groups){
        this.effect.execute(group, pioche, groups);
    }
    public boolean isExecutable(Group group, CardPile pioche, GameRoundAssociations groups){
        return this.effect.isExecutable(group, pioche, groups);
    }
}
