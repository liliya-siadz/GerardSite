package com.gerard.site.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ContentFieldValidatorTest {
    @DataProvider(name = "dataProviderIsValid")
    public Object[][] dataProviderIsValid() {
        return new Object[][] {
                {"Hello this is my message.", true},
                {"Здравствуйте.", true},
                {"Hello dfgdfg !", true},
                {"sdfsdf 1512154f48sd fsdfeef4646131", true},
                {"GDFGDFGDFG, SFEFEFES 1231 23", true},
                {"////d123123sfgdfgdfg", false},
                {"            ", false},
                {null, false},
                {"",false},
                {"f".repeat(500),false}
        };
    }

    @Test(dataProvider ="dataProviderIsValid")
    public void testIsValid(String content, boolean expected) {
        boolean actual = ContentFieldValidator.INSTANCE.isValid(content);
        assertEquals(actual, expected);
    }
}