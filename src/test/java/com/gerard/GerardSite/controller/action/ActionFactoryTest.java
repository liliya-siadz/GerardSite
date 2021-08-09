package com.gerard.GerardSite.controller.action;

import com.gerard.GerardSite.controller.action.impl.LoginAction;
import com.gerard.GerardSite.controller.action.impl.LogoutAction;
import com.gerard.GerardSite.controller.action.impl.TransferToError404PageAction;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ActionFactoryTest {

    @DataProvider(name = "testGetActionDataProvider")
    public Object[][] testGetActionDataProvider() {
        return new Object[][]{
                {"LOGIN", LoginAction.INSTANCE},
                {"LOGOUT", LogoutAction.INSTANCE},
                {"unknown command", TransferToError404PageAction.INSTANCE},
                {"im a danger hacker :)", TransferToError404PageAction.INSTANCE},
                {null, TransferToError404PageAction.INSTANCE}
            };
        }

    @Test(dataProvider = "testGetActionDataProvider")
    public void testGetAction(String commandName, Action expected){
        Action actual = ActionFactory.INSTANCE.getAction(commandName);
        assertEquals(actual, expected);
    }
}