package com.gerard.site.validation.impl;

import com.gerard.site.validation.FieldValidator;

public class LoginValidator implements FieldValidator {

    //todo: think about null | empty | blank inside validate
    //PHASE: design
    public boolean isValid(String login) {
        if(login.equals("lucia")){
            return true;
        }
        return false;
    }
}
