package com.company.FrankUzokaU1Capstone.controller;

import com.company.FrankUzokaU1Capstone.exception.NotFoundException;
import com.company.FrankUzokaU1Capstone.service.GameStoreInventoryService;
import com.company.FrankUzokaU1Capstone.viewmodel.ConsoleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    GameStoreInventoryService gameStoreInventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleViewModel createConsole(@RequestBody @Valid ConsoleViewModel consoleVM) {
        return gameStoreInventoryService.saveConsole(consoleVM);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getAllConsoles() {
        return gameStoreInventoryService.findAllConsoles();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConsoleViewModel getConsole(@PathVariable("id") int consoleId) {
        ConsoleViewModel consoleViewModel = gameStoreInventoryService.findConsoleById(consoleId);
        if (consoleViewModel == null) {
            throw new NotFoundException("Console could not be retrieved for id " + consoleId);
        }
        return consoleViewModel;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable("id") int consoleId) {
        gameStoreInventoryService.removeConsole(consoleId);
    }

    @PutMapping("/{id}")//Another way to set the Rest API Put mapping
    @ResponseStatus(HttpStatus.OK)
    public void updateConsole(@PathVariable("id") int consoleId, @RequestBody @Valid ConsoleViewModel consoleViewModel) {
        if (consoleViewModel.getConsoleId() == 0)
            consoleViewModel.setConsoleId(consoleId);
        if (consoleId != consoleViewModel.getConsoleId()) {
            throw new IllegalArgumentException("Console ID on path must match the ID in the Console object");
        }
        gameStoreInventoryService.updateConsole(consoleViewModel);
    }

    @GetMapping("/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getConsoleByManufacturer(@PathVariable("manufacturer") String manufacturer) {
        List<ConsoleViewModel> consoles = gameStoreInventoryService.findConsoleByManufacturer(manufacturer);
        if (consoles != null && consoles.size() == 0)
            throw new NotFoundException("Console could not be retrieved for manufacturer " + manufacturer);
        return consoles;
    }
}
