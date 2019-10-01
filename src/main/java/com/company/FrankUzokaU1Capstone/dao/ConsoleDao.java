package com.company.FrankUzokaU1Capstone.dao;

import com.company.FrankUzokaU1Capstone.models.Console;

import java.util.List;

public interface ConsoleDao {

    Console addConsole(Console console);

    Console getConsole(int id);

    List<Console> getAllConsoles();

    void updateConsole(Console console);

    void deleteConsole(int id);

    List<Console> getConsolesByManufacturer(String manufacturer);
}
