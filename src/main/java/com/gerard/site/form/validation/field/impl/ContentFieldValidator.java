package com.gerard.site.form.validation.field.impl;

import com.gerard.site.form.validation.field.FieldValidator;

public class ContentFieldValidator implements FieldValidator<String> {
    @Override
    public boolean isValid(String content) {
        return true;
    }
}
