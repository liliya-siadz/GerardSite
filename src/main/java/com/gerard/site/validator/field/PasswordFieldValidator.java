package com.gerard.site.validator.field;

import com.gerard.site.validator.FieldValidator;

public enum PasswordFieldValidator implements FieldValidator<String> {
    INSTANCE;

    @Override
    public boolean isValid(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }
        int maxLength = 15;
        int minLength = 6;
        String validRegex = "[\\w]*";
        return (password.length() >= minLength)
                && (password.length() <= maxLength)
                && (password.matches(validRegex));
    }

    //
}
