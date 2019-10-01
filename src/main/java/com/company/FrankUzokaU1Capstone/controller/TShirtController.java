package com.company.FrankUzokaU1Capstone.controller;

import com.company.FrankUzokaU1Capstone.exception.NotFoundException;
import com.company.FrankUzokaU1Capstone.service.GameStoreInventoryService;
import com.company.FrankUzokaU1Capstone.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tshirt")
public class TShirtController {

    @Autowired
    GameStoreInventoryService gameStoreInventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TShirtViewModel createTShirt(@RequestBody @Valid TShirtViewModel tShirtVM) {
        return gameStoreInventoryService.saveTShirt(tShirtVM);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getAllTShirts() {
        return gameStoreInventoryService.findAllTShirts();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirtViewModel getTShirt(@PathVariable("id") int tShirtId) {
        TShirtViewModel tShirtViewModel = gameStoreInventoryService.findTShirtById(tShirtId);
        if (tShirtViewModel == null) {
            throw new NotFoundException("T-Shirt could not be retrieved for id " + tShirtId);
        }
        return tShirtViewModel;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable("id") int tShirtId) {
        gameStoreInventoryService.removeTShirt(tShirtId);
    }

    @PutMapping("/{id}")//Another way to set the Rest API Put mapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@PathVariable("id") int tShirtId, @RequestBody @Valid TShirtViewModel tShirtViewModel) {
        if (tShirtViewModel.getTShirtId() == 0)
            tShirtViewModel.setTShirtId(tShirtId);
        if (tShirtId != tShirtViewModel.getTShirtId()) {
            throw new IllegalArgumentException("T-Shirt ID on path must match the ID in the T-Shirt object");
        }
        gameStoreInventoryService.updateTShirt(tShirtViewModel);
    }

    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtsByColor(@PathVariable("color") String color) {
        List<TShirtViewModel> tShirts = gameStoreInventoryService.findTShirtByColor(color);
        if (tShirts != null && tShirts.size() == 0)
            throw new NotFoundException("T-Shirt could not be retrieved for color " + color);
        return tShirts;
    }

    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtsBySize(@PathVariable("size") String size) {
        List<TShirtViewModel> tShirts = gameStoreInventoryService.findTShirtBySize(size);
        if (tShirts != null && tShirts.size() == 0)
            throw new NotFoundException("T-Shirt could not be retrieved for size " + size);
        return tShirts;
    }

}
