package com.company.FrankUzokaU1Capstone.dao;

import com.company.FrankUzokaU1Capstone.dao.ProcessingFeeDao;
import com.company.FrankUzokaU1Capstone.models.ProcessingFee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessingFeeDaoTest {

    @Autowired
    ProcessingFeeDao processingFeeDao;

    @Test
    public void getProcessingFee() {

        ProcessingFee processingFee = processingFeeDao.getProcessingFee("Consoles");

        ProcessingFee consoleFee = new ProcessingFee();
        consoleFee.setProductType("Consoles");
        consoleFee.setFee(new BigDecimal("14.99"));

        assertEquals(consoleFee, processingFee);
    }
}