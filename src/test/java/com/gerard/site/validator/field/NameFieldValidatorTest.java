package com.gerard.site.validator.field;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NameFieldValidatorTest {
    @DataProvider(name = "dataProviderIsValid")
    public Object[][] dataProviderIsValid() {
        return new Object[][] {
                {"sidelnikova.liliya@gmail.com", false},
                {"valeriy", true},
                {"va", false},
                {"Ira", true},
                {"kotLeta", true},
                {"1223", false},
                {"////d123123sfgdfgdfg", false},
                {" ferg           ", false},
                {"seemslikemail", false},
                {"",false},
                {null,false},
                {"f".repeat(250),false}
        };
    }

    @Test(dataProvider ="dataProviderIsValid")
    public void testIsValid(String name, boolean expected) {
        boolean actual = NameFieldValidator.INSTANCE.isValid(name);
        assertEquals(actual, expected);
    }
}