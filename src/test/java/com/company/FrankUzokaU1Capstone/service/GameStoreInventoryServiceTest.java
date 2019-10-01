package com.company.FrankUzokaU1Capstone.service;

import com.company.FrankUzokaU1Capstone.dao.*;
import com.company.FrankUzokaU1Capstone.models.*;
import com.company.FrankUzokaU1Capstone.viewmodel.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameStoreInventoryServiceTest {

    ConsoleDao consoleDao;
    GameDao gameDao;
    TShirtDao tShirtDao;
    InvoiceDao invoiceDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;

    GameStoreInventoryService gameStoreInventoryService;

    @Before
    public void setUp() throws Exception {
        setUpConsoleDaoMock();
        setUpGameDaoMock();
        setUpTShirtDaoMock();
        setUpInvoiceDaoMock();
        setUpProcessingFeeDaoMock();
        setUpSalesTaxRateDaoMock();

        gameStoreInventoryService = new GameStoreInventoryService(consoleDao, gameDao, tShirtDao, invoiceDao, processingFeeDao, salesTaxRateDao);
    }

    private void setUpConsoleDaoMock() {
        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        Console console = new Console();
        console.setConsoleId(1);
        console.setModel("Nintendo Switch");
        console.setManufacturer("Nintendo");
        console.setMemory_amount("32GB");
        console.setProcessor("NVIDIA Custom Tegra");
        console.setPrice(new BigDecimal(299.99).setScale(2, RoundingMode.HALF_UP));
        console.setQuantity(25);

        Console console1 = new Console();
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("Nintendo");
        console1.setMemory_amount("32GB");
        console1.setProcessor("NVIDIA Custom Tegra");
        console1.setPrice(new BigDecimal(299.99).setScale(2, RoundingMode.HALF_UP));
        console1.setQuantity(25);

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console);

        doReturn(console).when(consoleDao).addConsole(console1);
        doReturn(console).when(consoleDao).getConsole(1);
        doReturn(consoleList).when(consoleDao).getAllConsoles();
        doReturn(consoleList).when(consoleDao).getConsolesByManufacturer("Nintendo");
    }

    private void setUpGameDaoMock() {
        gameDao = mock(GameDaoJdbcTemplateImpl.class);

        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Just Dance 2020");
        game.setErsbRating("Everyone");
        game.setDescription("A dance rhythm game developed by Ubisoft");
        game.setPrice(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP));
        game.setStudio("Game Studio");
        game.setQuantity(5);

        Game game1 = new Game();
        game1.setTitle("Just Dance 2020");
        game1.setErsbRating("Everyone");
        game1.setDescription("A dance rhythm game developed by Ubisoft");
        game1.setPrice(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP));
        game1.setStudio("Game Studio");
        game1.setQuantity(5);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        doReturn(game).when(gameDao).addGame(game1);
        doReturn(game).when(gameDao).getGame(1);
        doReturn(gameList).when(gameDao).getAllGames();
        doReturn(gameList).when(gameDao).getGamesByStudio("Game Studio");
        doReturn(gameList).when(gameDao).getGamesByErsbRating("Everyone");
        doReturn(gameList).when(gameDao).getGamesByTitle("Just Dance 2020");
    }

    private void setUpTShirtDaoMock() {
        tShirtDao = mock(TShirtDaoJdbcTemplateImpl.class);

        TShirt tShirt = new TShirt();
        tShirt.setTShirtId(2);
        tShirt.setSize("M");
        tShirt.setColor("White");
        tShirt.setDescription("Cotton-made tShirt");
        tShirt.setPrice(new BigDecimal(15.00).setScale(2, RoundingMode.HALF_UP));
        tShirt.setQuantity(50);

        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("M");
        tShirt1.setColor("White");
        tShirt1.setDescription("Cotton-made tShirt");
        tShirt1.setPrice(new BigDecimal(15.00).setScale(2, RoundingMode.HALF_UP));
        tShirt1.setQuantity(50);

        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(tShirt);

        doReturn(tShirt).when(tShirtDao).addTShirt(tShirt1);
        doReturn(tShirt).when(tShirtDao).getTShirt(2);
        doReturn(tShirtList).when(tShirtDao).getAllTShirts();
        doReturn(tShirtList).when(tShirtDao).getTShirtByColor("White");
        doReturn(tShirtList).when(tShirtDao).getTShirtBySize("M");
    }

    private void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setName("Jane Smith");
        invoice.setStreet("123 Main Street");
        invoice.setCity("Jersey City");
        invoice.setState("NJ");
        invoice.setZipcode("07000");
        invoice.setItemType("Games");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal(40.00).setScale(2, RoundingMode.HALF_UP));
        invoice.setTax(new BigDecimal(2.00).setScale(2, RoundingMode.HALF_UP));
        invoice.setProcessingFee(new BigDecimal(1.49).setScale(2, RoundingMode.HALF_UP));
        invoice.setTotal(new BigDecimal(43.49).setScale(2, RoundingMode.HALF_UP));

        Invoice invoice1 = new Invoice();
        invoice1.setName("Jane Smith");
        invoice1.setStreet("123 Main Street");
        invoice1.setCity("Jersey City");
        invoice1.setState("NJ");
        invoice1.setZipcode("07000");
        invoice1.setItemType("Games");
        invoice1.setItemId(1);
        invoice1.setUnitPrice(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP));
        invoice1.setQuantity(2);
        invoice1.setSubtotal(new BigDecimal(40.00).setScale(2, RoundingMode.HALF_UP));
        invoice1.setTax(new BigDecimal(2.00).setScale(2, RoundingMode.HALF_UP));
        invoice1.setProcessingFee(new BigDecimal(1.49).setScale(2, RoundingMode.HALF_UP));
        invoice1.setTotal(new BigDecimal(43.49).setScale(2, RoundingMode.HALF_UP));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
        doReturn(invoice).when(invoiceDao).getInvoice(1);
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();
    }

    private void setUpProcessingFeeDaoMock() {
        processingFeeDao = mock(ProcessingFeeDaoJdbcTemplateImpl.class);

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("Games");
        processingFee.setFee(new BigDecimal(1.49).setScale(2, RoundingMode.HALF_UP));

        ProcessingFee processingFee1 = new ProcessingFee();
        processingFee1.setProductType("Games");
        processingFee1.setFee(new BigDecimal(1.49).setScale(2, RoundingMode.HALF_UP));

        doReturn(processingFee).when(processingFeeDao).getProcessingFee("Games");
    }

    private void setUpSalesTaxRateDaoMock() {
        salesTaxRateDao = mock(SalesTaxRateDaoJdbcTemplateImpl.class);

        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NJ");
        salesTaxRate.setRate(new BigDecimal(.05).setScale(2, RoundingMode.HALF_UP));

        SalesTaxRate salesTaxRate1 = new SalesTaxRate();
        salesTaxRate1.setState("NJ");
        salesTaxRate1.setRate(new BigDecimal(.05).setScale(2, RoundingMode.HALF_UP));


        doReturn(salesTaxRate).when(salesTaxRateDao).getSalesTaxRate("NJ");
    }

    @Test
    public void saveFindFindAllConsole() {
        ConsoleViewModel consoleVM = new ConsoleViewModel();
        consoleVM.setModel("Nintendo Switch");
        consoleVM.setManufacturer("Nintendo");
        consoleVM.setMemory_amount("32GB");
        consoleVM.setProcessor("NVIDIA Custom Tegra");
        consoleVM.setPrice(new BigDecimal(299.99).setScale(2, RoundingMode.HALF_UP));
        consoleVM.setQuantity(25);

        consoleVM = gameStoreInventoryService.saveConsole(consoleVM);
        ConsoleViewModel fromService = gameStoreInventoryService.findConsoleById(consoleVM.getConsoleId());
        assertEquals(consoleVM, fromService);

        List<ConsoleViewModel> consoleViewModelList = gameStoreInventoryService.findAllConsoles();
        assertEquals(1, consoleViewModelList.size());
        assertEquals(consoleVM, consoleViewModelList.get(0));
    }

    @Test
    public void findConsoleByManufacturer() {
        ConsoleViewModel consoleVM = new ConsoleViewModel();
        consoleVM.setModel("Nintendo Switch");
        consoleVM.setManufacturer("Nintendo");
        consoleVM.setMemory_amount("32GB");
        consoleVM.setProcessor("NVIDIA Custom Tegra");
        consoleVM.setPrice(new BigDecimal(299.99).setScale(2, RoundingMode.HALF_UP));
        consoleVM.setQuantity(25);

        consoleVM = gameStoreInventoryService.saveConsole(consoleVM);
        List<ConsoleViewModel> consoles = gameStoreInventoryService.findConsoleByManufacturer("Nintendo");
        assertEquals(1, consoles.size());
        assertEquals(consoleVM, consoles.get(0));
    }

    @Test
    public void saveFindFindAllGame() {
        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("Just Dance 2020");
        gameVM.setErsbRating("Everyone");
        gameVM.setDescription("A dance rhythm game developed by Ubisoft");
        gameVM.setPrice(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("Game Studio");
        gameVM.setQuantity(5);

        gameVM = gameStoreInventoryService.saveGame(gameVM);
        GameViewModel fromService = gameStoreInventoryService.findGameById(gameVM.getGameId());
        assertEquals(gameVM, fromService);

        List<GameViewModel> gameViewModelList = gameStoreInventoryService.findAllGames();
        assertEquals(1, gameViewModelList.size());
        assertEquals(gameVM, gameViewModelList.get(0));
    }

    @Test
    public void findGameByStudio() {
        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("Just Dance 2020");
        gameVM.setErsbRating("Everyone");
        gameVM.setDescription("A dance rhythm game developed by Ubisoft");
        gameVM.setPrice(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("Game Studio");
        gameVM.setQuantity(5);

        gameVM = gameStoreInventoryService.saveGame(gameVM);
        List<GameViewModel> games = gameStoreInventoryService.findGameByStudio("Game Studio");
        assertEquals(1, games.size());
        assertEquals(gameVM, games.get(0));
    }

    @Test
    public void findGameByErsbRating() {
        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("Just Dance 2020");
        gameVM.setErsbRating("Everyone");
        gameVM.setDescription("A dance rhythm game developed by Ubisoft");
        gameVM.setPrice(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("Game Studio");
        gameVM.setQuantity(5);

        gameVM = gameStoreInventoryService.saveGame(gameVM);
        List<GameViewModel> games = gameStoreInventoryService.findGameByErsbRating("Everyone");
        assertEquals(1, games.size());
        assertEquals(gameVM, games.get(0));
    }

    @Test
    public void findGameByTitle() {
        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("Just Dance 2020");
        gameVM.setErsbRating("Everyone");
        gameVM.setDescription("A dance rhythm game developed by Ubisoft");
        gameVM.setPrice(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("Game Studio");
        gameVM.setQuantity(5);

        gameVM = gameStoreInventoryService.saveGame(gameVM);
        List<GameViewModel> games = gameStoreInventoryService.findGameByTitle("Just Dance 2020");
        assertEquals(1, games.size());
        assertEquals(gameVM, games.get(0));
    }

    @Test
    public void saveFindFindAllTShirt() {
        TShirtViewModel tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("M");
        tShirtVM.setColor("White");
        tShirtVM.setDescription("Cotton-made tShirt");
        tShirtVM.setPrice(new BigDecimal(15.00).setScale(2, RoundingMode.HALF_UP));
        tShirtVM.setQuantity(50);

        tShirtVM = gameStoreInventoryService.saveTShirt(tShirtVM);
        TShirtViewModel fromService = gameStoreInventoryService.findTShirtById(tShirtVM.getTShirtId());
        assertEquals(tShirtVM, fromService);

        List<TShirtViewModel> tShirtViewModelList = gameStoreInventoryService.findAllTShirts();
        assertEquals(1, tShirtViewModelList.size());
        assertEquals(tShirtVM, tShirtViewModelList.get(0));
    }

    @Test
    public void findTShirtByColor() {
        TShirtViewModel tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("M");
        tShirtVM.setColor("White");
        tShirtVM.setDescription("Cotton-made tShirt");
        tShirtVM.setPrice(new BigDecimal(15.00).setScale(2, RoundingMode.HALF_UP));
        tShirtVM.setQuantity(50);

        tShirtVM = gameStoreInventoryService.saveTShirt(tShirtVM);
        List<TShirtViewModel> tShirts = gameStoreInventoryService.findTShirtByColor("White");
        assertEquals(1, tShirts.size());
        assertEquals(tShirtVM, tShirts.get(0));

    }

    @Test
    public void findTShirtBySize() {
        TShirtViewModel tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("M");
        tShirtVM.setColor("White");
        tShirtVM.setDescription("Cotton-made tShirt");
        tShirtVM.setPrice(new BigDecimal(15.00).setScale(2, RoundingMode.HALF_UP));
        tShirtVM.setQuantity(50);

        tShirtVM = gameStoreInventoryService.saveTShirt(tShirtVM);
        List<TShirtViewModel> tShirts = gameStoreInventoryService.findTShirtBySize("M");
        assertEquals(1, tShirts.size());
        assertEquals(tShirtVM, tShirts.get(0));
    }

    @Test
    public void saveInvoice() {
        OrderViewModel orderVM = new OrderViewModel();
        orderVM.setName("Jane Smith");
        orderVM.setStreet("123 Main Street");
        orderVM.setCity("Jersey City");
        orderVM.setState("NJ");
        orderVM.setZipcode("07000");
        orderVM.setItemType("Games");
        orderVM.setItemId(1);
        orderVM.setQuantity(2);

        InvoiceViewModel fromService = gameStoreInventoryService.saveInvoice(orderVM);
        InvoiceViewModel invoiceVM = new InvoiceViewModel();
        invoiceVM.setInvoiceId(1);
        invoiceVM.setName("Jane Smith");
        invoiceVM.setStreet("123 Main Street");
        invoiceVM.setCity("Jersey City");
        invoiceVM.setState("NJ");
        invoiceVM.setZipcode("07000");
        invoiceVM.setItemType("Games");
        invoiceVM.setItemId(1);
        invoiceVM.setQuantity(2);
        invoiceVM.setUnitPrice(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP));
        invoiceVM.setSubtotal(new BigDecimal(40.00).setScale(2, RoundingMode.HALF_UP));
        invoiceVM.setTax(new BigDecimal(2.00).setScale(2, RoundingMode.HALF_UP));
        invoiceVM.setProcessingFee(new BigDecimal(1.49).setScale(2, RoundingMode.HALF_UP));
        invoiceVM.setTotal(new BigDecimal(43.49).setScale(2, RoundingMode.HALF_UP));

        assertEquals(invoiceVM, fromService);
    }
}