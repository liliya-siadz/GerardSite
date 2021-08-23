package com.gerard.site.form.validation.field.impl;

import com.gerard.site.form.validation.field.FieldValidator;

public class SurnameValidator implements FieldValidator<String> {
    @Override
    public boolean isValid(String field) {
        return false;
    }
}
