package com.gerard.site.controller;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ActionFactoryTest {

    @DataProvider(name = "testGetActionDataProvider")
    public Object[][] testGetActionDataProvider() {
        return new Object[][]{
                {"LOGIN", SignInAction.INSTANCE},
                {"LOGOUT", SignOutAction.INSTANCE},
                {"unknown command", Error404PageAction.INSTANCE},
                {"im a danger hacker :)", Error404PageAction.INSTANCE},
                {null, Error404PageAction.INSTANCE}
            };
        }

    @Test(dataProvider = "testGetActionDataProvider")
    public void testGetAction(String commandName, Action expected){
        Action actual = ActionFactory.INSTANCE.getAction(commandName);
        assertEquals(actual, expected);
    }
}