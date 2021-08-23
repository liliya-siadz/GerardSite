package com.gerard.site.form.validation.field.impl;

import com.gerard.site.form.validation.field.FieldValidator;

public class PasswordFieldValidator implements FieldValidator<String> {

    public boolean isValid(String password) {
        return true;
    }
}
