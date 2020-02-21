package com.montaury.citadels.round.action;

import com.montaury.citadels.Board;
import com.montaury.citadels.City;
import com.montaury.citadels.district.Card;
import com.montaury.citadels.player.ComputerController;
import com.montaury.citadels.player.HumanController;
import com.montaury.citadels.player.Player;
import com.montaury.citadels.player.PlayerController;
import com.montaury.citadels.round.Group;
import org.junit.Test;

import java.awt.peer.RobotPeer;

import static org.junit.Assert.*;

public class DestroyDistrictActionTest {
    @Test
    public void can_destroy_destructible_district() {
        //GIVEN
        Board board = new Board();
        City city1 = new City(board);
        City city2 = new City(board);

        Player player1 = new Player("Bowser", 123, city1, new HumanController());
        Player player2 = new Player("WiiFitTrainer", 24, city2, new ComputerController());
        //Group group = new Group();

        city1.buildDistrict(Card.CASTLE_1);//4; NOBLE
        city1.buildDistrict(Card.WATCHTOWER_1);//1; MILITARY
        city1.buildDistrict(Card.DOCKS_1);//3; TRADE
        city1.buildDistrict(Card.CATHEDRAL_1);//5; RELIGIOUS
        city1.buildDistrict(Card.CATHEDRAL_2);//5; RELIGIOUS



        //WHEN


        //THEN


    }
}