package com.gerard.site.form.validation.field.impl;

import com.gerard.site.form.validation.field.FieldValidator;

public class PhoneValidator implements FieldValidator<Integer> {
    @Override
    public boolean isValid(Integer field) {
        return true;
    }
}
