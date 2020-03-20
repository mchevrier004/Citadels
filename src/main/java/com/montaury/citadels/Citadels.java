package com.montaury.citadels;

import com.montaury.citadels.character.Character;
import com.montaury.citadels.character.RandomCharacterSelector;
import com.montaury.citadels.district.Card;
import com.montaury.citadels.district.District;
import com.montaury.citadels.player.ComputerController;
import com.montaury.citadels.player.HumanController;
import com.montaury.citadels.player.Player;
import com.montaury.citadels.round.ActionType;
import com.montaury.citadels.round.GameRoundAssociations;
import com.montaury.citadels.round.Group;
import io.vavr.Tuple;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import com.montaury.citadels.character.CharacterChoose;

import java.util.Collections;
import java.util.Scanner;
import java.util.EnumSet;



public class Citadels {
    public static void printLine(String whatToPrint) {
        System.out.println(whatToPrint);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        Player p = new Player(scanner, board); //Création du joueur
        p.human = true;

        //Procédure de création des joueurs(ordinateur) et ajout dans la liste de joueurs
        List<Player> players = List.of(p);
        printLine("Saisir le nombre d'adversaires (entre 3 et 7): "); //Il existe des règles particulières pour 2 et 3 joueurs qui n'ont pas été implémentée
        int nbOpponents = 0;
        nbOpponents = getNbOpponents(scanner);
        for (int joueurs = 0; joueurs < nbOpponents; joueurs += 1) {
            Player player = new Player("Ordinateur " + joueurs, 35, new City(board), new ComputerController());
            player.computer = true;
            players = players.append(player);
        }
        //Initialisation du jeu

        //Sélection des personnages présent dans le jeu
        printLine("Sélection des personnages");
        CharacterChoose characterSelection = new CharacterChoose();
        characterSelection.buildCharacterDeck();

        //création de la pioche et distribution
        CardPile pioche = new CardPile(Card.all().toList().shuffle());
        setUpPlayers(pioche, players);

        //remise de la couronne et définition du tour du jeu
        Player crown = players.maxBy(Player::age).get();
        List<Group> roundAssociations;
        //lancer la partie
        do {
            java.util.List<Player> list = players.asJavaMutable();
            Collections.rotate(list, -players.indexOf(crown));
            List<Player> playersInOrder = List.ofAll(list);
            RandomCharacterSelector randomCharacterSelector = new RandomCharacterSelector();
            List<Character> availableCharacters = characterSelection.getCharacterDeck();
            List<Character> availableCharacters1 = availableCharacters;
            List<Character> discardedCharacters = List.empty();
            //on met une carte de coté et maj des cartes disponibles
            //on utilise plusieurs fois une variable avec le même nom
            Character aDiscardedCharacter = randomCharacterSelector.among(availableCharacters1);
            discardedCharacters = discardedCharacters.append(aDiscardedCharacter);
            Character faceDownDiscardedCharacter = discardedCharacters.head();
            availableCharacters = availableCharacters.remove(faceDownDiscardedCharacter);

            List<Character> availableCharacters11 = availableCharacters.remove(Character.KING);
            List<Character> discardedCharacters1 = List.empty();
            //retourner carte visible
            for (int i = 0; i < 7 - playersInOrder.size() - 1; i++) {
                Character discardedCharacter = randomCharacterSelector.among(availableCharacters11);
                discardedCharacters1 = discardedCharacters1.append(discardedCharacter);
                availableCharacters11 = availableCharacters11.remove(discardedCharacter);
            }
            List<Character> faceUpDiscardedCharacters = discardedCharacters1;
            availableCharacters = availableCharacters.removeAll(faceUpDiscardedCharacters);

            //choix des personnages et définition du tour
            List<Group> associations1 = List.empty();
            List<Group> associations = chooseCharacter(playersInOrder, availableCharacters, faceDownDiscardedCharacter, faceUpDiscardedCharacters, associations1);
            GameRoundAssociations groups = new GameRoundAssociations(associations);

            for (int iii = 0; iii < 8; iii++) {
                for (int ii = 0; ii < associations.size(); ii++) {
                    if (iii + 1 == associations.get(ii).character.number() && !associations.get(ii).isMurdered()) {
                        Group group = associations.get(ii);
                        associations.get(ii).thief().peek(thief -> thief.steal(group.player()));
                        EnumSet<ActionType> baseActions = EnumSet.of(ActionType.draw2CardsAndKeep1, ActionType.receive2Coins);
                        List<District> districts = group.player().city().districts();
                        EnumSet<ActionType> availableActions = baseActions;
                        observatoryAppendAction(districts, availableActions);
                        // keep only actions that player can realize
                        List<ActionType> possibleActions = List.empty();
                        for (ActionType action : availableActions) {
                            if (action.isExecutable(group, pioche, groups))
                                possibleActions = possibleActions.append(action);
                        }
                        ActionType chosenAction = group.player().controller.selectActionAmong(possibleActions.toList());
                        chosenAction.execute(group, pioche, groups);
                        actionExecuted(group, chosenAction, associations);

                        // receive powers from the character
                        List<ActionType> powers = group.character.getPowers();
                        List<ActionType> extraActions = List.empty();
                        districtAppendAction(extraActions, group);
                        Set<ActionType> availableActions11 = Group.OPTIONAL_ACTIONS
                                .addAll(powers)
                                .addAll(extraActions);
                        ActionType actionType11;
                        do {
                            Set<ActionType> availableActions1 = availableActions11;
                            // keep only actions that player can realize
                            List<ActionType> possibleActions2 = List.empty();
                            possibleActions2 = appendActionExecutable(pioche, group, possibleActions2, availableActions1, groups);
                            ActionType actionChoisie = group.player().controller.selectActionAmong(possibleActions2.toList());
                            // execute selected action
                            actionChoisie.execute(group, pioche, groups);
                            actionExecuted(group, actionChoisie, associations);
                            actionType11 = actionChoisie;
                            availableActions11 = availableActions11.remove(actionType11);
                        }
                        while (!availableActions11.isEmpty() && !actionType11.equals(ActionType.endRound));
                    }
                }
            }
            roundAssociations = associations;
            crown = roundAssociations.find(a -> a.character == Character.KING).map(Group::player).getOrElse(crown);
        } while (!players.map(Player::city).exists(City::isComplete));

        // classe les joueurs par leur score
        // si ex-aequo, le premier est celui qui n'est pas assassiné
        // si pas d'assassiné, le gagnant est le joueur ayant eu le personnage avec le numéro d'ordre le plus petit au dernier tour
        printLine("Classement: " + roundAssociations.sortBy(a -> Tuple.of(a.player().score(), !a.isMurdered(), a.character))
                .reverse()
                .map(Group::player));
    }

    public static void actionExecuted(Group association, ActionType actionType, List<Group> associations) {
        printLine("Player " + association.player().name() + " executed action " + actionType);
        associations.map(Group::player)
                .forEach(Citadels::displayStatus);
    }

    private static void displayStatus(Player player) {
        printLine("  Player " + player.name() + ":\n" +
                "    Gold coins: " + player.gold() + "\n" +
                "    City: " + textCity(player) + "\n" +
                "    Hand size: " + player.cards().size());
        if (player.controller instanceof HumanController) {
            printLine("    Hand: " + textHand(player));
        }
        printLine("");
    }

    private static String textCity(Player player) {
        List<District> districts = player.city().districts();
        return districts.isEmpty() ? "Empty" : districts.map(Citadels::textDistrict).mkString(", ");
    }

    private static String textDistrict(District district) {
        return district.name() + "(" + district.districtType().name() + ", " + district.cost() + ")";
    }

    private static String textHand(Player player) {
        Set<Card> cards = player.cards();
        return cards.isEmpty() ? "Empty" : cards.map(Citadels::textCard).mkString(", ");
    }

    private static List<Group> chooseCharacter(List<Player> playersInOrder, List<Character> availableCharacters, Character faceDownDiscardedCharacter, List<Character> faceUpDiscardedCharacters, List<Group> associations1){
        for (Player player : playersInOrder) {
            printLine(player.name() + " doit choisir un personnage");
            availableCharacters = availableCharacters.size() == 1 && playersInOrder.size() == 7 ? availableCharacters.append(faceDownDiscardedCharacter) : availableCharacters;
            Character selectedCharacter = player.controller.selectOwnCharacter(availableCharacters, faceUpDiscardedCharacters);
            availableCharacters = availableCharacters.remove(selectedCharacter);
            associations1 = associations1.append(new Group(player, selectedCharacter));
        }
        return associations1;
    }

    private static void observatoryAppendAction(List<District> districts, EnumSet<ActionType> availableActions){
        if (districts.contains(District.OBSERVATORY)) {
            availableActions.remove(ActionType.draw2CardsAndKeep1);
            availableActions.add(ActionType.draw3Keep1);
        }
    }


    private static void districtAppendAction(List<ActionType> extraActions, Group group){
        if (group.player().city().districts().contains(District.SMITHY)) {
            extraActions = extraActions.append(ActionType.draw3For2Coins);
        }
        if (group.player().city().districts().contains(District.LABORATORY)) {
            extraActions = extraActions.append(ActionType.discard2For2Coins);
        }
    }
    private static int getNbOpponents(Scanner scanner){
        int nbOpponents;
        do {
            nbOpponents = scanner.nextInt();
        } while (nbOpponents < 2 || nbOpponents > 8);
        return nbOpponents;
    }

    private static void setUpPlayers(CardPile pioche, List<Player> players){
        players.forEach(player -> {
            player.add(2);
            player.add(pioche.draw(2));
        });
    }

    private static List<ActionType> appendActionExecutable(CardPile pioche, Group group, List<ActionType> possibleActions2, Set<ActionType> availableActions1, GameRoundAssociations groups){
        for (ActionType action : availableActions1) {
            if (action.isExecutable(group, pioche, groups))
                possibleActions2 = possibleActions2.append(action);
        }
        return possibleActions2;
    }

    private static String textCard(Card card) {
        return textDistrict(card.district());
    }
}