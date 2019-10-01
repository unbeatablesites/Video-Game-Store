package com.company.FrankUzokaU1Capstone.dao;

import com.company.FrankUzokaU1Capstone.dao.SalesTaxRateDao;
import com.company.FrankUzokaU1Capstone.models.SalesTaxRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SalesTaxRateDaoTest {

    @Autowired
    SalesTaxRateDao salesTaxRateDao;

    @Test
    public void getSalesTaxRate() {

        SalesTaxRate salesTaxRate = salesTaxRateDao.getSalesTaxRate("NJ");

        SalesTaxRate salesTaxNJ = new SalesTaxRate();
        salesTaxNJ.setState("NJ");
        salesTaxNJ.setRate(new BigDecimal(".05"));

        assertEquals(salesTaxNJ, salesTaxRate);
    }
}