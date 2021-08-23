package com.gerard.site.validation.field.impl;

import com.gerard.site.validation.field.FieldValidator;
import org.apache.commons.validator.routines.EmailValidator;

public class EmailFieldValidator implements FieldValidator<String> {

    public boolean isValid(String email) {
        EmailValidator emailValidator =
                EmailValidator.getInstance(false,true);
        boolean isEmailValid = emailValidator.isValid(email);
        return isEmailValid;
    }
}
