package com.gerard.site.validator;

public enum EmailValidator implements Validator<String> {
    INSTANCE;

    @Override
    public boolean isValid(String email) {
        org.apache.commons.validator.routines.EmailValidator emailValidator =
                org.apache.commons.validator.routines.EmailValidator.getInstance(false,true);
        boolean isEmailValid = emailValidator.isValid(email);
        return isEmailValid;
    }
}
