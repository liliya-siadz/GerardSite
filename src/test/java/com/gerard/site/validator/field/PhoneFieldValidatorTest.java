package com.gerard.site.validator.field;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PhoneFieldValidatorTest {

    @DataProvider(name = "dataProviderIsValid")
    public Object[][] dataProviderIsValid() {
        return new Object[][] {
                {"fesfefsf", false},
                {"2324", false},
                {"1232134432", false},
                {"291523", false},
                {"293060661", true},
                {"333060661", true},
                {"253060661", true},
                {"443060661", true},
                {"253060661", true},
                {"2530606613", false},
                {"2530606613rgr", false},
                {"25306066", false},
                {"",false},
                {null,false}
        };
    }

    @Test(dataProvider ="dataProviderIsValid")
    public void testIsValid(String phone, boolean expected) {
        boolean actual = PhoneFieldValidator.INSTANCE.isValid(phone);
        assertEquals(actual, expected);
    }
}