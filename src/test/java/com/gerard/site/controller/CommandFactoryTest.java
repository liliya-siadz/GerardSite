package com.gerard.site.controller;

import com.gerard.site.controller.command.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandFactoryTest {


    @DataProvider(name = "testGetActionDataProvider")
    public Object[][] testGetCommandDataProvider() {
        return new Object[][]{
                {"LOGIN", LoginCommand.INSTANCE},
                {"unknown command", Error404Command.INSTANCE},
                {"im a danger hacker :)", Error404Command.INSTANCE},
                {null, Error404Command.INSTANCE}
            };
        }

    @Test(dataProvider = "testGetCommandDataProvider")
    public void testGetCommand(String commandName, Command expected){
        Command actual = CommandFactory.INSTANCE.getCommand(commandName);
        assertEquals(actual, expected);
    }

}