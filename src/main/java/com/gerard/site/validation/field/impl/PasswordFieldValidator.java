package com.gerard.site.validation.field.impl;

import com.gerard.site.validation.field.FieldValidator;

public class PasswordFieldValidator implements FieldValidator<String> {

    public boolean isValid(String password) {
        return !password.isEmpty();
    }
}
