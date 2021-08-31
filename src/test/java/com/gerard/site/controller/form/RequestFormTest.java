package com.gerard.site.controller.form;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

public class RequestFormTest {
    private static final Logger LOGGER
            = LogManager.getLogger(RequestFormTest.class);

    @Test
    public void testValidateFormClientSideValidForm() {
        String name = "lolita";
        String surname = "milly";
        String email = "lolita.milyavckaya@gmail.com";
        String patronymic = null;
        String phone = "297841651";
        String content = "            Test test 1234";
        RequestForm requestFormWithValidValues = new RequestForm();
        requestFormWithValidValues.setEmail(email);
        requestFormWithValidValues.setSurname(surname);
        requestFormWithValidValues.setPatronymic(patronymic);
        requestFormWithValidValues.setContent(content);
        requestFormWithValidValues.setName(name);
        requestFormWithValidValues.setPhone(phone);
        LOGGER.info(requestFormWithValidValues);
        assertFalse(requestFormWithValidValues.validateForm().containsValue(false));
    }
}