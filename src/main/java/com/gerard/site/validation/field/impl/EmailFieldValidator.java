package com.gerard.site.validation.field.impl;

import com.gerard.site.validation.field.FieldValidator;

public class EmailFieldValidator implements FieldValidator {

    //todo: think about null | empty | blank inside validate
    //PHASE: design
    public boolean isValid(String login) {
        if(login.equals("lucia")){
            return true;
        }
        return false;
    }
}
