package com.montaury.citadels;

import com.montaury.citadels.district.Card;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CityTest {

    @Test
    public void district_construction_score() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);

        city.buildDistrict(Card.WATCHTOWER_1);//1
        city.buildDistrict(Card.CATHEDRAL_1);//5
        city.buildDistrict(Card.MANOR_1);//3
        city.buildDistrict(Card.DOCKS_1);//3
        city.buildDistrict(Card.CASTLE_1);//4

        Possession possession = new Possession(0,null);

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(16);

    }

    @Test
    public void city_has_5_different_coulors() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);

        city.buildDistrict(Card.WATCHTOWER_1);
        city.buildDistrict(Card.CATHEDRAL_1);
        city.buildDistrict(Card.MANOR_1);
        city.buildDistrict(Card.DOCKS_1);
        city.buildDistrict(Card.MARKET_1);//=19


        Possession possession = new Possession(0,null);

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(19);

    }

    @Test
    public void first_player_having_completed_city() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);
        board.isFirst(city);

        city.buildDistrict(Card.WATCHTOWER_1);
        city.buildDistrict(Card.CATHEDRAL_1);
        city.buildDistrict(Card.MANOR_1);
        city.buildDistrict(Card.DOCKS_1);
        city.buildDistrict(Card.MARKET_1);//=19
        city.buildDistrict(Card.MANOR_3);
        city.buildDistrict(Card.MANOR_2);//=25

        Possession possession = new Possession(0,null);

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(29);//+4
    }

    @Test
    public void other_player_having_completed_city() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);

        city.buildDistrict(Card.WATCHTOWER_1);
        city.buildDistrict(Card.CATHEDRAL_1);
        city.buildDistrict(Card.MANOR_1);
        city.buildDistrict(Card.DOCKS_1);
        city.buildDistrict(Card.MARKET_1);//=19
        city.buildDistrict(Card.MANOR_3);
        city.buildDistrict(Card.MANOR_2);//=25

        Possession possession = new Possession(0,null);

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(27);
    }

    @Test
    public void bonus_merveille() {
        //GIVEN
        Board board = new Board();
        City city = new City(board);

        city.buildDistrict(Card.WATCHTOWER_1);
        city.buildDistrict(Card.CATHEDRAL_1);
        city.buildDistrict(Card.MANOR_1);
        city.buildDistrict(Card.DOCKS_1);
        city.buildDistrict(Card.MARKET_1);//=19
        city.buildDistrict(Card.DRAGON_GATE);//+8


        Possession possession = new Possession(0, null);

        //WHEN
        int scoreConstructionCite = city.score(possession);

        //THEN
        assertThat(scoreConstructionCite).isEqualTo(27);
    }
}