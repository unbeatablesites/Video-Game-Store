package com.company.FrankUzokaU1Capstone.service;
// What I have to work with so far
import com.company.FrankUzokaU1Capstone.dao.*;
import com.company.FrankUzokaU1Capstone.models.*;
import com.company.FrankUzokaU1Capstone.viewmodel.*;
import com.company.FrankUzokaU1Capstone.dao.*;
import com.company.FrankUzokaU1Capstone.models.*;
import com.company.FrankUzokaU1Capstone.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameStoreInventoryService {

    private ConsoleDao consoleDao;
    private GameDao gameDao;
    private TShirtDao tShirtDao;
    private InvoiceDao invoiceDao;
    private ProcessingFeeDao processingFeeDao;
    private SalesTaxRateDao salesTaxRateDao;

    @Autowired
    public GameStoreInventoryService(ConsoleDao consoleDao, GameDao gameDao, TShirtDao tShirtDao, InvoiceDao invoiceDao, ProcessingFeeDao processingFeeDao, SalesTaxRateDao salesTaxRateDao) {
        this.consoleDao = consoleDao;
        this.gameDao = gameDao;
        this.tShirtDao = tShirtDao;
        this.invoiceDao = invoiceDao;
        this.processingFeeDao = processingFeeDao;
        this.salesTaxRateDao = salesTaxRateDao;
    }

    // Console API
    @Transactional
    public ConsoleViewModel saveConsole(ConsoleViewModel consoleViewModel) {
        Console console = new Console();
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemory_amount(consoleViewModel.getMemory_amount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());
        console = consoleDao.addConsole(console);

        consoleViewModel.setConsoleId(console.getConsoleId());
        return consoleViewModel;
    }

    public ConsoleViewModel findConsoleById(int id) {
        Console console = consoleDao.getConsole(id);

        if (console == null) {
            return null;
        } else {
            return buildConsoleViewModel(console);
        }
    }

    public List<ConsoleViewModel> findAllConsoles() {
        List<Console> consoleList = consoleDao.getAllConsoles();

        List<ConsoleViewModel> consoleVMList = new ArrayList<>();

        for (Console console: consoleList) {
            ConsoleViewModel consoleVM = buildConsoleViewModel(console);
            consoleVMList.add(consoleVM);
        }
        return consoleVMList;
    }

    public List<ConsoleViewModel> findConsoleByManufacturer(String manufacturer) {
        List<Console> consoles = consoleDao.getConsolesByManufacturer(manufacturer);
        List<ConsoleViewModel> consoleViewModels = new ArrayList<>();

        for (Console console: consoles) {
            consoleViewModels.add(buildConsoleViewModel(console));
        }
        return consoleViewModels;
    }

    @Transactional
    public void updateConsole(ConsoleViewModel consoleViewModel) {
        Console console = new Console();
        console.setConsoleId(consoleViewModel.getConsoleId());
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemory_amount(consoleViewModel.getMemory_amount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());

        consoleDao.updateConsole(console);

    }

    public void removeConsole(int id) {
        consoleDao.deleteConsole(id);
    }

    // Game API
    @Transactional
    public GameViewModel saveGame(GameViewModel gameViewModel) {
        Game game = new Game();
        game.setTitle(gameViewModel.getTitle());
        game.setErsbRating(gameViewModel.getErsbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setStudio(gameViewModel.getStudio());
        game.setQuantity(gameViewModel.getQuantity());
        game = gameDao.addGame(game);

        gameViewModel.setGameId(game.getGameId());
        return gameViewModel;
    }

    public GameViewModel findGameById(int id) {
        Game game = gameDao.getGame(id);

        if (game == null) {
            return null;
        } else {
            return buildGameViewModel(game);
        }
    }

    public List<GameViewModel> findAllGames() {
        List<Game> gameList = gameDao.getAllGames();

        List<GameViewModel> gameVMList = new ArrayList<>();

        for (Game game: gameList) {
            GameViewModel gameVM = buildGameViewModel(game);
            gameVMList.add(gameVM);
        }
        return gameVMList;
    }

    public List<GameViewModel> findGameByStudio(String studio) {
        List<Game> games = gameDao.getGamesByStudio(studio);
        List<GameViewModel> gameViewModels = new ArrayList<>();

        for (Game game: games) {
            gameViewModels.add(buildGameViewModel(game));
        }
        return gameViewModels;
    }

    public List<GameViewModel> findGameByErsbRating(String ersbRating) {
        List<Game> games = gameDao.getGamesByErsbRating(ersbRating);
        List<GameViewModel> gameViewModels = new ArrayList<>();

        for (Game game: games) {
            gameViewModels.add(buildGameViewModel(game));
        }
        return gameViewModels;
    }

    public List<GameViewModel> findGameByTitle(String title) {
        List<Game> games = gameDao.getGamesByTitle(title);
        List<GameViewModel> gameViewModels = new ArrayList<>();

        for (Game game: games) {
            gameViewModels.add(buildGameViewModel(game));
        }
        return gameViewModels;
    }

    @Transactional
    public void updateGame(GameViewModel gameViewModel) {
        Game game = new Game();
        game.setGameId(gameViewModel.getGameId());
        game.setTitle(gameViewModel.getTitle());
        game.setErsbRating(gameViewModel.getErsbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setStudio(gameViewModel.getStudio());
        game.setQuantity(gameViewModel.getQuantity());

        gameDao.updateGame(game);
    }

    public void removeGame(int id) {
        gameDao.deleteGame(id);
    }

    // TShirt API
    @Transactional
    public TShirtViewModel saveTShirt(TShirtViewModel tShirtViewModel) {
        TShirt tShirt = new TShirt();
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());
        tShirt = tShirtDao.addTShirt(tShirt);

        tShirtViewModel.setTShirtId(tShirt.getTShirtId());
        return tShirtViewModel;

    }

    public TShirtViewModel findTShirtById(int id) {
        TShirt tShirt = tShirtDao.getTShirt(id);

        if (tShirt == null) {
            return null;
        } else {
            return buildTShirtViewModel(tShirt);
        }
    }

    public List<TShirtViewModel> findAllTShirts() {
        List<TShirt> tShirtList = tShirtDao.getAllTShirts();

        List<TShirtViewModel> tShirtVMList = new ArrayList<>();

        for (TShirt tShirt: tShirtList) {
            TShirtViewModel tShirtVM = buildTShirtViewModel(tShirt);
            tShirtVMList.add(tShirtVM);
        }
        return tShirtVMList;
    }

    public List<TShirtViewModel> findTShirtByColor(String color) {
        List<TShirt> tShirts = tShirtDao.getTShirtByColor(color);
        List<TShirtViewModel> tShirtViewModels = new ArrayList<>();

        for (TShirt tShirt: tShirts) {
            tShirtViewModels.add(buildTShirtViewModel(tShirt));
        }
        return tShirtViewModels;
    }

    public List<TShirtViewModel> findTShirtBySize(String size) {
        List<TShirt> tShirts = tShirtDao.getTShirtBySize(size);
        List<TShirtViewModel> tShirtViewModels = new ArrayList<>();

        for (TShirt tShirt: tShirts) {
            tShirtViewModels.add(buildTShirtViewModel(tShirt));
        }
        return tShirtViewModels;
    }

    @Transactional
    public void updateTShirt(TShirtViewModel tShirtViewModel) {
        TShirt tShirt = new TShirt();
        tShirt.setTShirtId(tShirtViewModel.getTShirtId());
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());

        tShirtDao.updateTShirt(tShirt);
    }

    public void removeTShirt(int id) {
        tShirtDao.deleteTShirt(id);
    }

    // Invoice API
    @Transactional
    public InvoiceViewModel saveInvoice(OrderViewModel orderViewModel) {
        Invoice invoice = new Invoice();

        invoice.setName(orderViewModel.getName());
        invoice.setStreet(orderViewModel.getStreet());
        invoice.setCity(orderViewModel.getCity());
        invoice.setState(orderViewModel.getState().toUpperCase());
        invoice.setZipcode(orderViewModel.getZipcode());
        invoice.setItemType(orderViewModel.getItemType().toLowerCase());
        invoice.setItemId(orderViewModel.getItemId());
        invoice.setQuantity(orderViewModel.getQuantity());

        // Check for valid state to get sales tax rate
        SalesTaxRate tax = salesTaxRateDao.getSalesTaxRate(invoice.getState());

        if (tax.getRate() == null) {
            throw new IllegalArgumentException("Invalid state entry ");
        }
        invoice.setTax(tax.getRate());

        // Check for valid item types to get processing fee
        switch(invoice.getItemType()) {
            case "console":
            case "consoles":
                invoice.setItemType("Consoles");
                break;
            case "game":
            case "games":
                invoice.setItemType("Games");
                break;
            case "t-shirt":
            case "t-shirts":
            case "tshirt":
            case "tshirts":
                invoice.setItemType("T-Shirts");
                break;
            default:
                throw new IllegalArgumentException("Invalid item-type entry given");
        }

        ProcessingFee processingFee = processingFeeDao.getProcessingFee(invoice.getItemType());
        if (processingFee.getFee() == null) {
            throw new IllegalArgumentException("Invalid item-type entry");
        }

        // Add additional processing fee amount for order quantities greater than 10
        if (invoice.getQuantity() > 10) {
            BigDecimal additionalFee = new BigDecimal(15.49).setScale(2, RoundingMode.HALF_UP);
            processingFee.setFee(processingFee.getFee().add(additionalFee).setScale(2, RoundingMode.HALF_UP));
        }

        invoice.setProcessingFee(processingFee.getFee());

        // Logic regarding quantity and unit price based on item-type
        switch(invoice.getItemType()) {
            case "T-Shirts":
                TShirt tShirt = tShirtDao.getTShirt(invoice.getItemId());
                if (tShirt == null) {
                    throw new IllegalArgumentException("Invalid item-id entry");
                }

                if (tShirt.getQuantity() < invoice.getQuantity()) {
                    throw new IllegalArgumentException("Quantity not available in stock");
                } else {
                    tShirt.setQuantity(tShirt.getQuantity() - invoice.getQuantity());
                    tShirtDao.updateTShirt(tShirt);
                }
                invoice.setUnitPrice(tShirt.getPrice());
                break;

            case "Games":
                Game game = gameDao.getGame(orderViewModel.getItemId());
                if (game == null) {
                    throw new IllegalArgumentException("Invalid item-id entry");
                }

                if (game.getQuantity() < game.getQuantity()) {
                    throw new IllegalArgumentException("Quantity not available in stock");
                } else {
                    game.setQuantity(game.getQuantity() - invoice.getQuantity());
                    gameDao.updateGame(game);
                }
                invoice.setUnitPrice(game.getPrice());
                break;

            case "Consoles":
                Console console = consoleDao.getConsole(invoice.getItemId());
                if (console == null) {
                    throw new IllegalArgumentException("Invalid item-id entry");
                }

                if (console.getQuantity() < console.getQuantity()) {
                    throw new IllegalArgumentException("Quantity not available in stock");
                } else {
                    console.setQuantity(console.getQuantity() - console.getQuantity());
                    consoleDao.updateConsole(console);
                }
                invoice.setUnitPrice(console.getPrice());
                break;
            default:
                throw new IllegalArgumentException("Product type is not valid.");
        }

        // Calculate subtotal
        BigDecimal subtotal;
        subtotal = invoice.getUnitPrice().multiply(BigDecimal.valueOf(invoice.getQuantity())).setScale(2, RoundingMode.HALF_UP);
        invoice.setSubtotal(subtotal);

        // Calculate sales tax
        BigDecimal taxToBeAdded;
        taxToBeAdded = invoice.getSubtotal().multiply(invoice.getTax()).setScale(2, RoundingMode.HALF_UP);
        invoice.setTax(taxToBeAdded);

        // Calculate total
        BigDecimal total;
        total = invoice.getSubtotal().add(invoice.getTax().add(invoice.getProcessingFee())).setScale(2, RoundingMode.HALF_UP);
        invoice.setTotal(total);

        invoice = invoiceDao.addInvoice(invoice);

        return buildInvoiceViewModel(invoice);
    }

    private ConsoleViewModel buildConsoleViewModel(Console console) {
        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
        consoleViewModel.setConsoleId(console.getConsoleId());
        consoleViewModel.setModel(console.getModel());
        consoleViewModel.setManufacturer(console.getManufacturer());
        consoleViewModel.setMemory_amount(console.getMemory_amount());
        consoleViewModel.setProcessor(console.getProcessor());
        consoleViewModel.setPrice(console.getPrice());
        consoleViewModel.setQuantity(console.getQuantity());

        return consoleViewModel;
    }

    private GameViewModel buildGameViewModel(Game game) {
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGameId(game.getGameId());
        gameViewModel.setTitle(game.getTitle());
        gameViewModel.setErsbRating(game.getErsbRating());
        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setPrice(game.getPrice());
        gameViewModel.setStudio(game.getStudio());
        gameViewModel.setQuantity(game.getQuantity());

        return gameViewModel;
    }

    private TShirtViewModel buildTShirtViewModel(TShirt tShirt) {
        TShirtViewModel tShirtViewModel = new TShirtViewModel();
        tShirtViewModel.setTShirtId(tShirt.getTShirtId());
        tShirtViewModel.setSize(tShirt.getSize());
        tShirtViewModel.setColor(tShirt.getColor());
        tShirtViewModel.setDescription(tShirt.getDescription());
        tShirtViewModel.setPrice(tShirt.getPrice());
        tShirtViewModel.setQuantity(tShirt.getQuantity());

        return  tShirtViewModel;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setInvoiceId(invoice.getInvoiceId());
        invoiceViewModel.setName(invoice.getName());
        invoiceViewModel.setStreet(invoice.getStreet());
        invoiceViewModel.setCity(invoice.getCity());
        invoiceViewModel.setState(invoice.getState());
        invoiceViewModel.setZipcode(invoice.getZipcode());
        invoiceViewModel.setItemType(invoice.getItemType());
        invoiceViewModel.setItemId(invoice.getItemId());
        invoiceViewModel.setUnitPrice(invoice.getUnitPrice());
        invoiceViewModel.setQuantity(invoice.getQuantity());
        invoiceViewModel.setSubtotal(invoice.getSubtotal());
        invoiceViewModel.setTax(invoice.getTax());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setTotal(invoice.getTotal());

        return invoiceViewModel;
    }
}
