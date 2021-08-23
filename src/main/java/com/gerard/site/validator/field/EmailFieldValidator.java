package com.gerard.site.validator.field;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailFieldValidator implements FieldValidator<String> {
    private static EmailFieldValidator instance;

    private EmailFieldValidator() {
    }

    static EmailFieldValidator getInstance() {
        if (instance == null) {
            instance = new EmailFieldValidator();
        }
        return instance;
    }

    @Override
    public boolean isValid(String email) {
        EmailValidator emailValidator =
                EmailValidator.getInstance(false,true);
//        boolean isEmailValid = emailValidator.isValid(email);
//        return isEmailValid;
        return true;
    }
}
