package com.gerard.site.validation.field.impl;

import com.gerard.site.validation.field.FieldValidator;

public class EmailFieldValidator implements FieldValidator {

    //todo: think about null | empty | blank inside validate
    public boolean isValid(String login) {
        return !login.isEmpty();
    }
}
