package com.company.FrankUzokaU1Capstone.controller;

import com.company.FrankUzokaU1Capstone.service.GameStoreInventoryService;
import com.company.FrankUzokaU1Capstone.viewmodel.GameViewModel;
import com.company.FrankUzokaU1Capstone.viewmodel.TShirtViewModel;
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
@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameStoreInventoryService repo;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

    }
    @Test
    public void createTShirt() throws Exception {

        TShirtViewModel  inShirt = new TShirtViewModel();
        inShirt.setSize("Extra Large");
        inShirt.setColor("Blue");
        inShirt.setDescription("Slim fitting shirt");
        inShirt.setPrice(new BigDecimal(14.99));
        inShirt.setQuantity(8);

        String inputJson = mapper.writeValueAsString(inShirt);

        TShirtViewModel outShirt = new TShirtViewModel();
        outShirt.setSize("Extra Large");
        outShirt.setColor("Blue");
        outShirt.setDescription("Slim fitting shirt");
        outShirt.setPrice(new BigDecimal(14.99));
        outShirt.setQuantity(8);

        String outputJson = mapper.writeValueAsString(outShirt);

        when(repo.saveTShirt(inShirt)).thenReturn(outShirt);

        this.mockMvc.perform(post("/tshirt").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));


    }

    @Test
    public void getAllTShirts() throws Exception {

        TShirtViewModel  tShirt = new TShirtViewModel();
        tShirt.setSize("Extra Large");
        tShirt.setColor("Blue");
        tShirt.setDescription("Slim fitting shirt");
        tShirt.setPrice(new BigDecimal(14.99));
        tShirt.setQuantity(8);


        TShirtViewModel  tShirt2 = new TShirtViewModel();
        tShirt2.setSize("Extra Large");
        tShirt2.setColor("Blue");
        tShirt2.setDescription("Slim fitting shirt");
        tShirt2.setPrice(new BigDecimal(14.99));
        tShirt2.setQuantity(8);


        List<TShirtViewModel> listChecker = new ArrayList<>();
        listChecker.add(tShirt);
        listChecker.add(tShirt2);

        when(repo.findAllTShirts()).thenReturn(listChecker);

        List<TShirtViewModel> listChecker2 = new ArrayList<>();

        listChecker2.addAll(listChecker);

        String outputJson = mapper.writeValueAsString(listChecker2);

        this.mockMvc.perform(get("/tshirt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void getTShirt() throws Exception{


        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Extra Large");
        tShirt.setColor("Blue");
        tShirt.setDescription("Slim fitting shirt");
        tShirt.setPrice(new BigDecimal(14.99));
        tShirt.setQuantity(8);

        Optional<TShirtViewModel> returnVal = Optional.of(tShirt);
        String outputJson = mapper.writeValueAsString(tShirt);
        when(repo.findTShirtById(9)).thenReturn(returnVal.get());

        this.mockMvc.perform(get("/tshirt/9"))
                .andDo(print())
                .andExpect(status().isOk())
                //use the objectmapper output with the json method
                .andExpect(content().json(outputJson));

    }

    @Test
    public void deleteTShirt() {

        //can't mock the call to delete. it returns void
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.delete("/tshirt/9"))
                    .andDo(print()).andExpect(status().is2xxSuccessful())
                    .andExpect(content().string(""));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateTShirt() throws Exception{

        TShirtViewModel  tShirt = new TShirtViewModel();
        tShirt.setSize("Extra Large");
        tShirt.setColor("Blue");
        tShirt.setDescription("Slim fitting shirt");
        tShirt.setPrice(new BigDecimal(14.99));
        tShirt.setQuantity(8);


        //these will be the same
        String inputJson = mapper.writeValueAsString(tShirt);
        String outputJson = mapper.writeValueAsString(tShirt);

        this.mockMvc.perform(put("/tshirt/" + tShirt.getTShirtId())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getTShirtsByColor() throws Exception{

        TShirtViewModel  tShirt = new TShirtViewModel();
        tShirt.setSize("Extra Large");
        tShirt.setColor("Blue");
        tShirt.setDescription("Slim fitting shirt");
        tShirt.setPrice(new BigDecimal(14.99));
        tShirt.setQuantity(8);


        TShirtViewModel  tShirt2 = new TShirtViewModel();
        tShirt2.setSize("Extra Large");
        tShirt2.setColor("Blue");
        tShirt2.setDescription("Slim fitting shirt");
        tShirt2.setPrice(new BigDecimal(14.99));
        tShirt2.setQuantity(8);


        List<TShirtViewModel> listChecker = new ArrayList<>();
        listChecker.add(tShirt);
        listChecker.add(tShirt2);

        when(repo.findTShirtByColor("Blue")).thenReturn(listChecker);

        List<TShirtViewModel> listChecker2 = new ArrayList<>();

        listChecker2.addAll(listChecker);

        String outputJson = mapper.writeValueAsString(listChecker2);

        this.mockMvc.perform(get("/tshirt/color/Blue"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));



    }

    @Test
    public void getTShirtsBySize() throws Exception{

        TShirtViewModel  tShirt = new TShirtViewModel();
        tShirt.setSize("Xl");
        tShirt.setColor("Blue");
        tShirt.setDescription("Slim fitting shirt");
        tShirt.setPrice(new BigDecimal(14.99));
        tShirt.setQuantity(8);


        TShirtViewModel  tShirt2 = new TShirtViewModel();
        tShirt2.setSize("XL");
        tShirt2.setColor("Blue");
        tShirt2.setDescription("Slim fitting shirt");
        tShirt2.setPrice(new BigDecimal(14.99));
        tShirt2.setQuantity(8);


        List<TShirtViewModel> listChecker = new ArrayList<>();
        listChecker.add(tShirt);
        listChecker.add(tShirt2);

        when(repo.findTShirtByColor("XL")).thenReturn(listChecker);

        List<TShirtViewModel> listChecker2 = new ArrayList<>();

        listChecker2.addAll(listChecker);

        String outputJson = mapper.writeValueAsString(listChecker2);

        this.mockMvc.perform(get("/tshirt/size/XL"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }
}