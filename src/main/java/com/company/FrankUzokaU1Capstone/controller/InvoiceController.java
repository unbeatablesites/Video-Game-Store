package com.company.FrankUzokaU1Capstone.controller;

import com.company.FrankUzokaU1Capstone.service.GameStoreInventoryService;
import com.company.FrankUzokaU1Capstone.viewmodel.InvoiceViewModel;
import com.company.FrankUzokaU1Capstone.viewmodel.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/purchase")
public class InvoiceController {

    @Autowired
    GameStoreInventoryService gameStoreInventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoices(@RequestBody @Valid OrderViewModel viewModel) {
        return gameStoreInventoryService.saveInvoice(viewModel);
    }
}
