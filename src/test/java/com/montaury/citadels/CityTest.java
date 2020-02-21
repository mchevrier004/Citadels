package com.montaury.citadels;

import com.montaury.citadels.district.Card;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CityTest {

    @Test
    public void district_construction_score() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);

        city.buildDistrict(Card.CASTLE_1);//4; NOBLE
        city.buildDistrict(Card.WATCHTOWER_1);//1; MILITARY
        city.buildDistrict(Card.DOCKS_1);//3; TRADE
        city.buildDistrict(Card.CATHEDRAL_1);//5; RELIGIOUS
        city.buildDistrict(Card.CATHEDRAL_2);//5; RELIGIOUS
        //=18

        Possession possession = new Possession(0,null);

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(18);

    }

    @Test
    public void city_has_5_different_coulors() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);

        city.buildDistrict(Card.CASTLE_1);//4; NOBLE
        city.buildDistrict(Card.WATCHTOWER_1);//1; MILITARY
        city.buildDistrict(Card.DOCKS_1);//3; TRADE
        city.buildDistrict(Card.CATHEDRAL_1);//5; RELIGIOUS
        city.buildDistrict(Card.OBSERVATORY);//4; SPECIAL
        //=17

        Possession possession = new Possession(0,null);

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(17+5);

    }

    @Test
    public void first_player_having_completed_city() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);
        board.isFirst(city);

        city.buildDistrict(Card.CASTLE_1);//4; NOBLE
        city.buildDistrict(Card.WATCHTOWER_1);//1; MILITARY
        city.buildDistrict(Card.DOCKS_1);//3; TRADE
        city.buildDistrict(Card.CATHEDRAL_1);//5; RELIGIOUS
        city.buildDistrict(Card.CATHEDRAL_2);//5; RELIGIOUS
        //==18
        city.buildDistrict(Card.MANOR_3);//3; NOBLE
        city.buildDistrict(Card.MANOR_2);//3; NOBLE
        //=24

        Possession possession = new Possession(0,null);

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(24+4);
    }

    @Test
    public void other_player_having_completed_city() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);

        city.buildDistrict(Card.CASTLE_1);//4; NOBLE
        city.buildDistrict(Card.WATCHTOWER_1);//1; MILITARY
        city.buildDistrict(Card.DOCKS_1);//3; TRADE
        city.buildDistrict(Card.CATHEDRAL_1);//5; RELIGIOUS
        city.buildDistrict(Card.CATHEDRAL_2);//5; RELIGIOUS
        //=18
        city.buildDistrict(Card.MANOR_3);//3; NOBLE
        city.buildDistrict(Card.MANOR_2);//3; NOBLE
        //=24

        Possession possession = new Possession(0,null);

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(24+2);
    }

    @Test
    public void bonus_merveille_dragon_gate() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);

        city.buildDistrict(Card.CASTLE_1);//4; NOBLE
        city.buildDistrict(Card.WATCHTOWER_1);//1; MILITARY
        city.buildDistrict(Card.DOCKS_1);//3; TRADE
        city.buildDistrict(Card.CATHEDRAL_1);//5; RELIGIOUS
        city.buildDistrict(Card.CATHEDRAL_2);//5; RELIGIOUS
        //=18
        city.buildDistrict(Card.DRAGON_GATE);//+8


        Possession possession = new Possession(0, null);

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(26);
    }

    @Test
    public void bonus_merveille_university() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);

        city.buildDistrict(Card.CASTLE_1);//4; NOBLE
        city.buildDistrict(Card.WATCHTOWER_1);//1; MILITARY
        city.buildDistrict(Card.DOCKS_1);//3; TRADE
        city.buildDistrict(Card.CATHEDRAL_1);//5; RELIGIOUS
        city.buildDistrict(Card.CATHEDRAL_2);//5; RELIGIOUS
        //=18
        city.buildDistrict(Card.UNIVERSITY);//+8


        Possession possession = new Possession(0, null);

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(26);
    }

    @Test
    public void bonus_merveille_treasury() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);

        city.buildDistrict(Card.CASTLE_1);//4; NOBLE
        city.buildDistrict(Card.WATCHTOWER_1);//1; MILITARY
        city.buildDistrict(Card.DOCKS_1);//3; TRADE
        city.buildDistrict(Card.CATHEDRAL_1);//5; RELIGIOUS
        city.buildDistrict(Card.CATHEDRAL_2);//5; RELIGIOUS
        //=18
        city.buildDistrict(Card.TREASURY);//+5


        Possession possession = new Possession(2, null);//=+2

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(18+5+2);
    }

    @Test
    public void bonus_merveille_map_room() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);
        Set<Card> laMain = HashSet.of(Card.DOCKS_2);


        city.buildDistrict(Card.CASTLE_1);//4; NOBLE
        city.buildDistrict(Card.WATCHTOWER_1);//1; MILITARY
        city.buildDistrict(Card.DOCKS_1);//3; TRADE
        city.buildDistrict(Card.CATHEDRAL_1);//5; RELIGIOUS
        city.buildDistrict(Card.CATHEDRAL_2);//5; RELIGIOUS
        //=18
        city.buildDistrict(Card.MAP_ROOM);//+5


        Possession possession = new Possession(0, laMain);//=+1

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(18+5+1);
    }
}