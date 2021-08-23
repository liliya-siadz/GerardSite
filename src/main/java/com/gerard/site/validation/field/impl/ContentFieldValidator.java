package com.gerard.site.validation.field.impl;

import com.gerard.site.validation.field.FieldValidator;

public class ContentFieldValidator implements FieldValidator<String> {
    @Override
    public boolean isValid(String content) {
        return !content.isEmpty();
    }
}
