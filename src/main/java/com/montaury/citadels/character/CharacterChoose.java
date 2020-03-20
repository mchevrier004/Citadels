package com.montaury.citadels.character;

import com.montaury.citadels.district.Card;
import io.vavr.collection.List;

import java.util.Scanner;

public class CharacterChoose {
    private List<Character> characterDeck;

    public CharacterChoose(){
        this.characterDeck = List.empty();
    }

    public List<Character> getCharacterDeck(){
        return this.characterDeck;
    }

    private void addCharacterToDeck(Character newCharacter) {  this.characterDeck = this.characterDeck.append(newCharacter); }

    public void buildCharacterDeck(){
        //TODO: Lorsqu'un un nouveau personnage est implémenté, on replace la ligne append par la fonction chooseCharacter!
        addCharacterToDeck(Character.ASSASSIN);//1
        System.out.println("Ajout d'un nouveau role : " + getCharacterDeck().get(0));
        chooseCharacter(2);
        System.out.println("Ajout d'un nouveau role : " + getCharacterDeck().get(1));
        addCharacterToDeck(Character.MAGICIAN);//3
        System.out.println("Ajout d'un nouveau role : " + getCharacterDeck().get(2));
        addCharacterToDeck(Character.KING);//4
        System.out.println("Ajout d'un nouveau role : " + getCharacterDeck().get(3));
        addCharacterToDeck(Character.BISHOP);//5
        System.out.println("Ajout d'un nouveau role : " + getCharacterDeck().get(4));
        chooseCharacter(6);
        System.out.println("Ajout d'un nouveau role : " + getCharacterDeck().get(5));
        addCharacterToDeck(Character.ARCHITECT);//7
        System.out.println("Ajout d'un nouveau role : " + getCharacterDeck().get(6));
        addCharacterToDeck(Character.WARLORD);//8
        System.out.println("Ajout d'un nouveau role : " + getCharacterDeck().get(7));
    }

    private void chooseCharacter(int characterNumber){
        List<Character> charactersWithSameNumbers = List.empty();

        //On recherche tous les personnages (normalement, seulement 2!) qui ont le même numero (number)
        for (Character currentCharacter:Character.values()
        ) {
            //Si le personnage courant possède le même nombre que celui passé en paramètre, on l'ajoute
            if (currentCharacter.number() == characterNumber) {
                charactersWithSameNumbers = charactersWithSameNumbers.append(currentCharacter);
            }
        }

        //On demande de choisir le personnage parmis tous les personnages ayant le même numéro!
        int chosenCharacter = askWhichCharacterFrom(charactersWithSameNumbers);

        //En fonction de la saisie (=personnage choisit), on l'ajoute le personnage
        if(chosenCharacter==1){
            addCharacterToDeck(charactersWithSameNumbers.get(0));
        }else{//chosenCharacter==2
            addCharacterToDeck(charactersWithSameNumbers.get(1));
        }
    }

    private int askWhichCharacterFrom(List<Character> charactersToSelect){

        Scanner scanner = new Scanner(System.in);
        int choosenCharacter;

        System.out.println("Plusieurs roles sont disponibles... Faites votre choix !");
        do{//Saisir 1 ou 2
            System.out.println(charactersToSelect.get(0).name() + "[1] | " + charactersToSelect.get(1).name() + " [2]");
            choosenCharacter = scanner.nextInt();
            if(choosenCharacter<1 || choosenCharacter>2){
                System.out.println("Je suis pas sur de comprendre... Vous voulez bien repeter?");
            }
        }while(choosenCharacter<1 || choosenCharacter>2);

        return choosenCharacter;
    }

    public void resetDeck(){//Au cas où, on sait jamais !
        this.characterDeck = List.empty();
    }
}

