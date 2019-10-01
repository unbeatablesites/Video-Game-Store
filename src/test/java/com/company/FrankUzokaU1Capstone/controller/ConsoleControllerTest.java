package com.company.FrankUzokaU1Capstone.controller;

import com.company.FrankUzokaU1Capstone.models.Console;
import com.company.FrankUzokaU1Capstone.service.GameStoreInventoryService;
import com.company.FrankUzokaU1Capstone.viewmodel.ConsoleViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
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
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameStoreInventoryService repo;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {


    }
    @Test
    public void createConsole() throws Exception{

        ConsoleViewModel inconsole = new ConsoleViewModel();
        inconsole.setModel("N64");
        inconsole.setManufacturer("Nintendo");
        inconsole.setMemory_amount("40gigs");
        inconsole.setProcessor("Intell");
        inconsole.setPrice(new BigDecimal(40.99));
        inconsole.setQuantity(8);

        String inputJson = mapper.writeValueAsString(inconsole);

        ConsoleViewModel outconsole = new ConsoleViewModel();
        outconsole.setConsoleId(9);
        outconsole.setModel("N64");
        outconsole.setManufacturer("Nintendo");
        outconsole.setMemory_amount("40gigs");
        outconsole.setProcessor("Intell");
        outconsole.setPrice(new BigDecimal(40.99));
        outconsole.setQuantity(8);

        String outputJson = mapper.writeValueAsString(outconsole);

        when(repo.saveConsole(inconsole)).thenReturn(outconsole);

        this.mockMvc.perform(post("/console").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void getAllConsoles() throws Exception{


        ConsoleViewModel console = new ConsoleViewModel();
        console.setConsoleId(9);
        console.setModel("N64");
        console.setManufacturer("Nintendo");
        console.setMemory_amount("40gigs");
        console.setProcessor("Intell");
        console.setPrice(new BigDecimal(40.99));
        console.setQuantity(8);


        ConsoleViewModel console2 = new ConsoleViewModel();
        console2.setConsoleId(9);
        console2.setModel("N64");
        console2.setManufacturer("Nintendo");
        console2.setMemory_amount("40gigs");
        console2.setProcessor("Intell");
        console2.setPrice(new BigDecimal(40.99));
        console2.setQuantity(8);


        List<ConsoleViewModel> listChecker = new ArrayList<>();
        listChecker.add(console);
        listChecker.add(console2);

        when(repo.findAllConsoles()).thenReturn(listChecker);

        List<ConsoleViewModel> listChecker2 = new ArrayList<>();

        listChecker2.addAll(listChecker);

        String outputJson = mapper.writeValueAsString(listChecker2);

        this.mockMvc.perform(get("/console"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));






    }

    @Test
    public void getConsole() throws Exception{


        ConsoleViewModel console = new ConsoleViewModel();
        console.setConsoleId(9);
        console.setModel("N64");
        console.setManufacturer("Nintendo");
        console.setMemory_amount("40gigs");
        console.setProcessor("Intell");
        console.setPrice(new BigDecimal(40.99));
        console.setQuantity(8);

        Optional<ConsoleViewModel> returnVal = Optional.of(console);
        String outputJson = mapper.writeValueAsString(console);
        when(repo.findConsoleById(9)).thenReturn(returnVal.get());

        this.mockMvc.perform(get("/console/9"))
                .andDo(print())
                .andExpect(status().isOk())
                //use the objectmapper output with the json method
                .andExpect(content().json(outputJson));

    }

    @Test
    public void deleteConsole() {

        //can't mock the call to delete. it returns void
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.delete("/console/9"))
                    .andDo(print()).andExpect(status().is2xxSuccessful())
                    .andExpect(content().string(""));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void updateConsole() throws Exception{

        ConsoleViewModel console = new ConsoleViewModel();

        console.setModel("N64");
        console.setManufacturer("Nintendo");
        console.setMemory_amount("40gigs");
        console.setProcessor("Intell");
        console.setPrice(new BigDecimal(40.99));
        console.setQuantity(8);
        console.setConsoleId(9);


        //these will be the same
        String inputJson = mapper.writeValueAsString(console);
        String outputJson = mapper.writeValueAsString(console);

        this.mockMvc.perform(put("/console/" + console.getConsoleId())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void getConsoleByManufacturer() {

        ConsoleViewModel console = new ConsoleViewModel();

        console.setModel("N64");
        console.setManufacturer("Nintendo");
        console.setMemory_amount("40gigs");
        console.setProcessor("Intell");
        console.setPrice(new BigDecimal(40.99));
        console.setQuantity(8);
        console.setConsoleId(9);


    }
}