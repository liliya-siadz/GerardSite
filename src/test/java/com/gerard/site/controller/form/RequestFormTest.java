package com.gerard.site.controller.form;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RequestFormTest {
    private static final Logger LOGGER = LogManager.getLogger(RequestFormTest.class);

    @Test
    public void testValidateFormClientSideValidForm() {
        String name = "lolita";
        String surname = "milly";
        String email = "lolita.milyavckaya@gmail.com";
        String patronymic = null;
        String phone = "297841651";
        String content = "            Test test 1234";
        RequestForm requestForm = new RequestForm();
        requestForm.setEmail(email);
        requestForm.setSurname(surname);
        requestForm.setPatronymic(patronymic);
        requestForm.setContent(content);
        requestForm.setName(name);
        requestForm.setPhone(phone);
        LOGGER.info(requestForm);
        assertFalse(requestForm.validateForm().containsValue(false));
    }
}