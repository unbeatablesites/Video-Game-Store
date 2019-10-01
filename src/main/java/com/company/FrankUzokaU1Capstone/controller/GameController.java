package com.company.FrankUzokaU1Capstone.controller;

import com.company.FrankUzokaU1Capstone.exception.NotFoundException;
import com.company.FrankUzokaU1Capstone.service.GameStoreInventoryService;
import com.company.FrankUzokaU1Capstone.viewmodel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameStoreInventoryService gameStoreInventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel createGame(@RequestBody @Valid GameViewModel gameVM) {
        return gameStoreInventoryService.saveGame(gameVM);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGames() {
        return gameStoreInventoryService.findAllGames();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGame(@PathVariable("id") int gameId) {
        GameViewModel gameViewModel = gameStoreInventoryService.findGameById(gameId);
        if (gameViewModel == null) {
            throw new NotFoundException("Game could not be retrieved for id " + gameId);
        }
        return gameViewModel;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable("id") int gameId) {
        gameStoreInventoryService.removeGame(gameId);
    }

    @PutMapping("/{id}")//Another way to set the Rest API Put mapping
    @ResponseStatus(HttpStatus.OK)
    public void updateGame(@PathVariable("id") int gameId, @RequestBody @Valid GameViewModel gameViewModel) {
        if (gameViewModel.getGameId() == 0)
            gameViewModel.setGameId(gameId);
        if (gameId != gameViewModel.getGameId()) {
            throw new IllegalArgumentException("Game ID on path must match the ID in the Game object");
        }
        gameStoreInventoryService.updateGame(gameViewModel);
    }

    @GetMapping("/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByStudio(@PathVariable("studio") String studio) {
        List<GameViewModel> games = gameStoreInventoryService.findGameByStudio(studio);
        if (games != null && games.size() == 0)
            throw new NotFoundException("Game could not be retrieved for studio " + studio);
        return games;
    }

    @GetMapping("/ersbRating/{ersbRating}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByErsbRating(@PathVariable("ersbRating") String ersbRating) {
        List<GameViewModel> games = gameStoreInventoryService.findGameByErsbRating(ersbRating);
        if (games != null && games.size() == 0)
            throw new NotFoundException("Game could not be retrieved for ERSB rating " + ersbRating);
        return games;
    }

    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByTitle(@PathVariable("title") String title) {
        List<GameViewModel> games = gameStoreInventoryService.findGameByTitle(title);
        if (games != null && games.size() == 0)
            throw new NotFoundException("Game could not be retrieved for title " + title);
        return games;
    }
}
