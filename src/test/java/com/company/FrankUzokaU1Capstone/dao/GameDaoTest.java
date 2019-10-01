package com.company.FrankUzokaU1Capstone.dao;

import com.company.FrankUzokaU1Capstone.dao.GameDao;
import com.company.FrankUzokaU1Capstone.models.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameDaoTest {

    @Autowired
    GameDao gameDao;

    @Before
    public void setUp() throws Exception {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGame(game.getGameId());
        }
    }

    @Test
    public void addGetDeleteGame() {
        Game game = new Game();
        game.setTitle("Just Dance 2020");
        game.setErsbRating("Everyone");
        game.setDescription("A dance rhythm game developed by Ubisoft");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Game Studio");
        game.setQuantity(5);

        game = gameDao.addGame(game);

        Game game1 = gameDao.getGame(game.getGameId());
        assertEquals(game1, game);

        gameDao.deleteGame(game.getGameId());
        game1 = gameDao.getGame(game.getGameId());
        assertNull(game1);
    }

    @Test
    public void getAllGames() {
        Game game = new Game();
        game.setTitle("Just Dance 2019");
        game.setErsbRating("Everyone");
        game.setDescription("A dance rhythm game developed by Ubisoft");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Game Studio");
        game.setQuantity(5);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("Just Dance 2020");
        game.setErsbRating("Everyone");
        game.setDescription("A dance rhythm game developed by Ubisoft");
        game.setPrice(new BigDecimal("24.99"));
        game.setStudio("Game Studio");
        game.setQuantity(10);
        gameDao.addGame(game);

        List<Game> gameList = gameDao.getAllGames();
        assertEquals(2, gameList.size());
    }

    @Test
    public void updateGame() {
        Game game = new Game();
        game.setTitle("Just Dance 2019");
        game.setErsbRating("Everyone");
        game.setDescription("A dance rhythm game developed by Ubisoft");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Game Studio");
        game.setQuantity(5);

        game = gameDao.addGame(game);

        game.setPrice(new BigDecimal("22.99"));
        gameDao.updateGame(game);

        Game game1 = gameDao.getGame(game.getGameId());
        assertEquals(game1, game);
    }

    @Test
    public void getGamesByStudio() {
        Game game = new Game();
        game.setTitle("Just Dance 2019");
        game.setErsbRating("Everyone");
        game.setDescription("A dance rhythm game developed by Ubisoft");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Game Studio");
        game.setQuantity(5);

        game = gameDao.addGame(game);

        Game game1 = new Game();
        game1.setTitle("Just Dance II");
        game1.setErsbRating("Everyone");
        game1.setDescription("A dance rhythm game developed by Ubisoft");
        game1.setPrice(new BigDecimal("14.99"));
        game1.setStudio("Another Game Studio");
        game1.setQuantity(3);
        gameDao.addGame(game1);

        List<Game> gameList = gameDao.getGamesByStudio(game.getStudio());
        assertEquals(1, gameList.size());
    }

    @Test
    public void getGamesByEsrbRating() {
        Game game = new Game();
        game.setTitle("Just Dance 2019");
        game.setErsbRating("Everyone");
        game.setDescription("A dance rhythm game developed by Ubisoft");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Game Studio");
        game.setQuantity(5);

        game = gameDao.addGame(game);

        Game game1 = new Game();
        game1.setTitle("Marvel Ultimate Alliance 3: The Black Order");
        game1.setErsbRating("Teen");
        game1.setDescription("Super Heroes and Super Villains battle Thanos and The Black Order across the Marvel Universe");
        game1.setPrice(new BigDecimal("59.99"));
        game1.setStudio("Game Studio");
        game1.setQuantity(5);
        gameDao.addGame(game1);

        List<Game> gameList = gameDao.getGamesByErsbRating(game.getErsbRating());
        assertEquals(1, gameList.size());
    }

    @Test
    public void getGamesByTitle() {
        Game game = new Game();
        game.setTitle("Just Dance 2019");
        game.setErsbRating("Everyone");
        game.setDescription("A dance rhythm game developed by Ubisoft");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Game Studio");
        game.setQuantity(5);

        game = gameDao.addGame(game);

        List<Game> gameList = gameDao.getGamesByTitle(game.getTitle());
        assertEquals(1, gameList.size());
    }
}