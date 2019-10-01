package com.company.FrankUzokaU1Capstone.controller;

import com.company.FrankUzokaU1Capstone.service.GameStoreInventoryService;
import com.company.FrankUzokaU1Capstone.viewmodel.ConsoleViewModel;
import com.company.FrankUzokaU1Capstone.viewmodel.GameViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameStoreInventoryService repo;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

    }
    @Test
    public void createGame() throws Exception{

        GameViewModel inGame = new GameViewModel();
        inGame.setTitle("Super Mario");
        inGame.setErsbRating("E");
        inGame.setDescription("A game that has super mario");
        inGame.setPrice(new BigDecimal(50.00));
        inGame.setStudio("EA Sports");
        inGame.setQuantity(8);

        String inputJson = mapper.writeValueAsString(inGame);

        GameViewModel outGame = new GameViewModel();
        outGame.setTitle("Super Mario");
        outGame.setErsbRating("E");
        outGame.setDescription("A game that has super mario");
        outGame.setPrice(new BigDecimal(50.00));
        outGame.setStudio("EA Sports");
        outGame.setQuantity(8);

        String outputJson = mapper.writeValueAsString(outGame);

        when(repo.saveGame(inGame)).thenReturn(outGame);

        this.mockMvc.perform(post("/game").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void getAllGames() throws Exception{

        GameViewModel game1 = new GameViewModel();
        game1.setTitle("Super Mario");
        game1.setErsbRating("E");
        game1.setDescription("A game that has super mario");
        game1.setPrice(new BigDecimal(50.00));
        game1.setStudio("EA Sports");
        game1.setQuantity(8);

        GameViewModel game2 = new GameViewModel();
        game2.setTitle("Super Mario 2");
        game2.setErsbRating("T");
        game2.setDescription("A game that has super mario");
        game2.setPrice(new BigDecimal(50.00));
        game2.setStudio("EA Sports");
        game2.setQuantity(8);


        List<GameViewModel> listChecker = new ArrayList<>();
        listChecker.add(game1);
        listChecker.add(game2);

        when(repo.findAllGames()).thenReturn(listChecker);

        List<GameViewModel> listChecker2 = new ArrayList<>();

        listChecker2.addAll(listChecker);

        String outputJson = mapper.writeValueAsString(listChecker2);

        this.mockMvc.perform(get("/game"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void getGame() throws Exception{

        GameViewModel game1 = new GameViewModel();
        game1.setGameId(9);
        game1.setTitle("Super Mario");
        game1.setErsbRating("E");
        game1.setDescription("A game that has super mario");
        game1.setPrice(new BigDecimal(50.00));
        game1.setStudio("EA Sports");
        game1.setQuantity(8);

        Optional<GameViewModel> returnVal = Optional.of(game1);
        String outputJson = mapper.writeValueAsString(game1);
        when(repo.findGameById(9)).thenReturn(returnVal.get());

        this.mockMvc.perform(get("/game/9"))
                .andDo(print())
                .andExpect(status().isOk())
                //use the objectmapper output with the json method
                .andExpect(content().json(outputJson));


    }

    @Test
    public void deleteGame() {

        //can't mock the call to delete. it returns void
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.delete("/game/9"))
                    .andDo(print()).andExpect(status().is2xxSuccessful())
                    .andExpect(content().string(""));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateGame() throws Exception{

        GameViewModel game1 = new GameViewModel();
        game1.setTitle("Super Mario");
        game1.setErsbRating("E");
        game1.setDescription("A game that has super mario");
        game1.setPrice(new BigDecimal(50.00));
        game1.setStudio("EA Sports");
        game1.setQuantity(8);


        //these will be the same
        String inputJson = mapper.writeValueAsString(game1);
        String outputJson = mapper.writeValueAsString(game1);

        this.mockMvc.perform(put("/game/" + game1.getGameId())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getGamesByStudio() throws Exception{

        GameViewModel game = new GameViewModel();
        game.setGameId(9);
        game.setTitle("Super Mario");
        game.setErsbRating("E");
        game.setDescription("A clasic game for nintedo");
        game.setPrice(new BigDecimal(49.00));
        game.setStudio("Nintendo");
        game.setQuantity(10);

        GameViewModel game2 = new GameViewModel();
        game2.setGameId(10);
        game2.setTitle("Donkey Kong");
        game2.setErsbRating("M");
        game2.setDescription("Anothe rawesome game by Nintendo");
        game2.setPrice(new BigDecimal(39.99));
        game2.setStudio("EA Sports");
        game2.setQuantity(15);

        GameViewModel game3 = new GameViewModel();
        game3.setGameId(8);
        game3.setTitle("Supper Mario cart");
        game3.setErsbRating("M");
        game3.setDescription("One of the first games to come out for he Nintendo");
        game3.setPrice(new BigDecimal(41.99));
        game3.setStudio("Nintendo");
        game3.setQuantity(13);

        List<GameViewModel> gameList = new ArrayList<>();
        gameList.add(game);
        gameList.add(game2);
        gameList.add(game3);

        when(repo.findGameByStudio("Nintendo")).thenReturn(gameList);

        List<GameViewModel>  gameList2 = new ArrayList<>();

        gameList2.addAll(gameList);

        String outputJson = mapper.writeValueAsString(gameList2);

        this.mockMvc.perform(get("/game/studio/Nintendo"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(outputJson));


        }


    @Test
    public void getGamesByErsbRating() throws Exception{

        GameViewModel game = new GameViewModel();
        game.setGameId(9);
        game.setTitle("Super Mario");
        game.setErsbRating("E");
        game.setDescription("A clasic game for nintedo");
        game.setPrice(new BigDecimal(49.00));
        game.setStudio("Nintendo");
        game.setQuantity(10);

        GameViewModel game2 = new GameViewModel();
        game2.setGameId(10);
        game2.setTitle("Donkey Kong");
        game2.setErsbRating("M");
        game2.setDescription("Anothe rawesome game by Nintendo");
        game2.setPrice(new BigDecimal(39.99));
        game2.setStudio("EA Sports");
        game2.setQuantity(15);

        GameViewModel game3 = new GameViewModel();
        game3.setGameId(8);
        game3.setTitle("Supper Mario cart");
        game3.setErsbRating("M");
        game3.setDescription("One of the first games to come out for he Nintendo");
        game3.setPrice(new BigDecimal(41.99));
        game3.setStudio("Nintendo");
        game3.setQuantity(13);

        List<GameViewModel> gameList = new ArrayList<>();
        gameList.add(game);
        gameList.add(game2);
        gameList.add(game3);

        when(repo.findGameByErsbRating("M")).thenReturn(gameList);

        List<GameViewModel>  gameList2 = new ArrayList<>();

        gameList2.addAll(gameList);

        String outputJson = mapper.writeValueAsString(gameList2);

                        this.mockMvc.perform(get("/game/ersbRating/M/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void getGamesByTitle() throws Exception{

        GameViewModel game = new GameViewModel();
        game.setGameId(9);
        game.setTitle("SuperMario");
        game.setErsbRating("E");
        game.setDescription("A clasic game for nintedo");
        game.setPrice(new BigDecimal(49.00));
        game.setStudio("Nintendo");
        game.setQuantity(10);

        GameViewModel game2 = new GameViewModel();
        game2.setGameId(10);
        game2.setTitle("Donkey Kong");
        game2.setErsbRating("M");
        game2.setDescription("Anothe rawesome game by Nintendo");
        game2.setPrice(new BigDecimal(39.99));
        game2.setStudio("EA Sports");
        game2.setQuantity(15);

        GameViewModel game3 = new GameViewModel();
        game3.setGameId(8);
        game3.setTitle("Supper Mario cart");
        game3.setErsbRating("M");
        game3.setDescription("One of the first games to come out for he Nintendo");
        game3.setPrice(new BigDecimal(41.99));
        game3.setStudio("Nintendo");
        game3.setQuantity(13);

        List<GameViewModel> gameList = new ArrayList<>();
        gameList.add(game);
        gameList.add(game2);
        gameList.add(game3);

        when(repo.findGameByTitle("SuperMario")).thenReturn(gameList);

        List<GameViewModel>  gameList2 = new ArrayList<>();

        gameList2.addAll(gameList);

        String outputJson = mapper.writeValueAsString(gameList2);

        this.mockMvc.perform(get("/game/title/SuperMario"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));


    }
}