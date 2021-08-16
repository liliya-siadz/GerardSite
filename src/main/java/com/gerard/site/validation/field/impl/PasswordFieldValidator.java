package com.gerard.site.validation.field.impl;

import com.gerard.site.validation.field.FieldValidator;

public class PasswordFieldValidator implements FieldValidator {

    public boolean isValid(String login) {
        return !login.isEmpty();
    }
}
