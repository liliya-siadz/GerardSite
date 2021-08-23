package com.gerard.site.controller;

import com.gerard.site.controller.command.Command;
import com.gerard.site.controller.command.CommandFactory;
import com.gerard.site.controller.command.Error404PageCommand;
import com.gerard.site.controller.command.LoginCommand;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandFactoryTest {

    @DataProvider(name = "testGetActionDataProvider")
    public Object[][] testGetCommandDataProvider() {
        return new Object[][]{
                {"LOGIN", LoginCommand.INSTANCE},
                {"unknown command", Error404PageCommand.INSTANCE},
                {"im a danger hacker :)", Error404PageCommand.INSTANCE},
                {null, Error404PageCommand.INSTANCE}
            };
        }

    @Test(dataProvider = "testGetCommandDataProvider")
    public void testGetCommand(String commandName, Command expected){
        Command actual = CommandFactory.INSTANCE.getCommand(commandName);
        assertEquals(actual, expected);
    }
}