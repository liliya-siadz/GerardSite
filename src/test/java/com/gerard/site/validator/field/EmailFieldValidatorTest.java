package com.gerard.site.validator.field;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EmailFieldValidatorTest {
    @DataProvider(name = "dataProviderIsValid")
    public Object[][] dataProviderIsValid() {
        return new Object[][] {
                {"sidelnikova.liliya@gmail.com", true},
                {"valeriy.leontiev@index.ru", true},
                {"leontrtg123iev@index.ru", true},
                {"kotleta", false},
                {"rabbit", false},
                {"////d123123sfgdfgdfg", false},
                {" ferg           ", false},
                {"seemslikemail@.com", false},
                {"",false},
                {null,false}
        };
    }

    @Test(dataProvider ="dataProviderIsValid")
    public void testIsValid(String email, boolean expected) {
        boolean actual = EmailFieldValidator.INSTANCE.isValid(email);
        assertEquals(actual, expected);
    }
}