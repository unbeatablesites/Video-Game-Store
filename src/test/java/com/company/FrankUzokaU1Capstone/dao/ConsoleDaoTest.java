package com.company.FrankUzokaU1Capstone.dao;

import com.company.FrankUzokaU1Capstone.dao.ConsoleDao;
import com.company.FrankUzokaU1Capstone.models.Console;
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
public class ConsoleDaoTest {

    @Autowired
    ConsoleDao consoleDao;

    @Before
    public void setUp() throws Exception {
        List<Console> consoles = consoleDao.getAllConsoles();
        for (Console console : consoles) {
            consoleDao.deleteConsole(console.getConsoleId());
        }
    }

    @Test
    public void addGetDeleteConsole() {
        Console console = new Console();
        console.setModel("Nintendo Switch");
        console.setManufacturer("Nintendo");
        console.setMemory_amount("32GB");
        console.setProcessor("NVIDIA Custom Tegra");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(25);

        console = consoleDao.addConsole(console);

        Console console1 = consoleDao.getConsole(console.getConsoleId());
        assertEquals(console1, console);

        consoleDao.deleteConsole(console.getConsoleId());
        console1 = consoleDao.getConsole(console.getConsoleId());
        assertNull(console1);
    }

    @Test
    public void getAllConsoles() {
        Console console = new Console();
        console.setModel("Nintendo Switch");
        console.setManufacturer("Nintendo");
        console.setMemory_amount("32GB");
        console.setProcessor("NVIDIA Custom Tegra");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(25);

        console = consoleDao.addConsole(console);

        console = new Console();
        console.setModel("PS4");
        console.setManufacturer("PlayStation");
        console.setMemory_amount("8GB");
        console.setProcessor("Vr Processor");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(20);
        consoleDao.addConsole(console);

        List<Console> consoleList = consoleDao.getAllConsoles();
        assertEquals(2, consoleList.size());
    }

    @Test
    public void updateConsole() {
        Console console = new Console();
        console.setModel("Nintendo Switch");
        console.setManufacturer("Nintendo");
        console.setMemory_amount("32GB");
        console.setProcessor("NVIDIA Custom Tegra");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(25);

        console = consoleDao.addConsole(console);

        console.setPrice(new BigDecimal("324.99"));
        consoleDao.updateConsole(console);

        Console console1 = consoleDao.getConsole(console.getConsoleId());
        assertEquals(console1, console);
    }

    @Test
    public void getConsolesByManufacturer() {
        Console console = new Console();
        console.setModel("Nintendo Switch");
        console.setManufacturer("Nintendo");
        console.setMemory_amount("32GB");
        console.setProcessor("NVIDIA Custom Tegra");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(25);

        console = consoleDao.addConsole(console);

        Console console1 = new Console();
        console1.setModel("PS4");
        console1.setManufacturer("PlayStation");
        console1.setMemory_amount("8GB");
        console1.setProcessor("Vr Processor");
        console1.setPrice(new BigDecimal("299.99"));
        console1.setQuantity(20);
        consoleDao.addConsole(console1);

        List<Console> consoleList = consoleDao.getConsolesByManufacturer(console.getManufacturer());
        assertEquals(1, consoleList.size());
    }
}