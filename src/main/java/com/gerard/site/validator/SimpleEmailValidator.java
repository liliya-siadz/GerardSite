package com.gerard.site.validator;

import org.apache.commons.validator.routines.EmailValidator;

public enum SimpleEmailValidator implements Validator<String> {
    INSTANCE;

    @Override
    public boolean isValid(String email) {
        EmailValidator emailValidator
                = EmailValidator.getInstance(false, true);
        boolean isEmailValid = emailValidator.isValid(email);
        return isEmailValid;
    }
}
