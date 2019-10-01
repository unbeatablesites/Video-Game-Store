package com.company.FrankUzokaU1Capstone.dao;

import com.company.FrankUzokaU1Capstone.dao.TShirtDao;
import com.company.FrankUzokaU1Capstone.models.TShirt;
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
public class TShirtDaoTest {

    @Autowired
    TShirtDao tShirtDao;

    @Before
    public void setUp() throws Exception {
        List<TShirt> tShirts = tShirtDao.getAllTShirts();
        for (TShirt tShirt: tShirts) {
            tShirtDao.deleteTShirt(tShirt.getTShirtId());
        }
    }

    @Test
    public void addGetDeleteTShirt() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("White");
        tShirt.setDescription("Cotton-made tShirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(50);

        tShirt = tShirtDao.addTShirt(tShirt);

        TShirt tShirt1 = tShirtDao.getTShirt(tShirt.getTShirtId());
        assertEquals(tShirt1, tShirt);

        tShirtDao.deleteTShirt(tShirt.getTShirtId());
        tShirt1 = tShirtDao.getTShirt(tShirt.getTShirtId());
        assertNull(tShirt1);
    }

    @Test
    public void getAllTShirts() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("White");
        tShirt.setDescription("Cotton-made tShirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(50);

        tShirt = tShirtDao.addTShirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Black");
        tShirt.setDescription("Cotton-made tShirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(20);
        tShirtDao.addTShirt(tShirt);

        List<TShirt> tShirtList = tShirtDao.getAllTShirts();
        assertEquals(2, tShirtList.size());
    }

    @Test
    public void updateTShirt() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("White");
        tShirt.setDescription("Cotton-made tShirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(50);

        tShirt = tShirtDao.addTShirt(tShirt);

        tShirt.setSize("L");
        tShirt.setColor("Red");
        tShirtDao.updateTShirt(tShirt);

        TShirt tShirt1 = tShirtDao.getTShirt(tShirt.getTShirtId());
        assertEquals(tShirt1, tShirt);
    }

    @Test
    public void getTShirtByColor() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("White");
        tShirt.setDescription("Cotton-made tShirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(50);

        tShirt = tShirtDao.addTShirt(tShirt);

        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("L");
        tShirt1.setColor("Black");
        tShirt1.setDescription("Cotton-made tShirt");
        tShirt1.setPrice(new BigDecimal("15.00"));
        tShirt1.setQuantity(20);

        tShirt1 = tShirtDao.addTShirt(tShirt1);

        List<TShirt> tShirtList = tShirtDao.getTShirtByColor(tShirt.getColor());
        assertEquals(1, tShirtList.size());
    }

    @Test
    public void getTShirtBySize() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("White");
        tShirt.setDescription("Cotton-made tShirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(50);

        tShirt = tShirtDao.addTShirt(tShirt);

        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("L");
        tShirt1.setColor("Black");
        tShirt1.setDescription("Cotton-made tShirt");
        tShirt1.setPrice(new BigDecimal("15.00"));
        tShirt1.setQuantity(20);

        tShirt1 = tShirtDao.addTShirt(tShirt1);

        List<TShirt> tShirtList = tShirtDao.getTShirtBySize(tShirt.getSize());
        assertEquals(1, tShirtList.size());
    }
}