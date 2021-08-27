package com.gerard.site.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NameValidatorTest {
    @DataProvider(name = "dataProviderIsValid")
    public Object[][] dataProviderIsValid() {
        return new Object[][] {
                {"sidelnikova.liliya@gmail.com", false},
                {"valeriy", true},
                {"va", false},
                {"Ira", true},
                {"kotLeta", true},
                {"Ирина", true},
                {"1223", false},
                {"////d123123sfgdfgdfg", false},
                {" ferg           ", false},
                {"seemslikmail", true},
                {"",false},
                {null,false},
                {"f".repeat(251),false}
        };
    }

    @Test(dataProvider ="dataProviderIsValid")
    public void testIsValid(String name, boolean expected) {
        boolean actual = NameValidator.INSTANCE.isValid(name);
        assertEquals(actual, expected);
    }
}