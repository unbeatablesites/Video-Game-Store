package com.company.FrankUzokaU1Capstone.controller;

import com.company.FrankUzokaU1Capstone.service.GameStoreInventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameStoreInventoryService repo;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

    }
    @Test
    public void createInvoices() {
    }
}