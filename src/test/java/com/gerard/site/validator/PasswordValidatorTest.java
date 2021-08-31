package com.gerard.site.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PasswordValidatorTest {

    @DataProvider(name = "dataProviderIsValid")
    public Object[][] dataProviderIsValid() {
        return new Object[][]{
                {"sweety1234", true},
                {"sunshine0", true},
                {"candis90", true},
                {"валера", false},
                {"skyline3", true},
                {"strekoza18", true},
                {"////d123123sfgdfgdfg", false},
                {" ferg           ", false},
                {"seemslikemail@.com", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataProviderIsValid")
    public void testIsValid(String password, boolean expected) {
        boolean actual = PasswordValidator.INSTANCE.isValid(password);
        assertEquals(actual, expected);
    }
}