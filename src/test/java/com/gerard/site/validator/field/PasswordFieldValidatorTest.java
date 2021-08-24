package com.gerard.site.validator.field;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PasswordFieldValidatorTest {

    @DataProvider(name = "dataProviderIsValid")
    public Object[][] dataProviderIsValid() {
        return new Object[][] {
                {"sweety1234", true},
                {"sunshine0", true},
                {"candis90", true},
                {"skyline3", true},
                {"strekoza18", true},
                {"////d123123sfgdfgdfg", false},
                {" ferg           ", false},
                {"seemslikemail@.com", false},
                {"",false},
                {null,false}
        };
    }

    @Test(dataProvider ="dataProviderIsValid")
    public void testIsValid(String password, boolean expected) {
        boolean actual = PasswordFieldValidator.INSTANCE.isValid(password);
        assertEquals(actual, expected);
    }
}