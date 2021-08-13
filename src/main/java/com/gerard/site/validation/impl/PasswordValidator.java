package com.gerard.site.validation.impl;

import com.gerard.site.validation.FieldValidator;

public class PasswordValidator implements FieldValidator {
    private String regexPattern = "password";
    private int maxLength = 5;
    private int minLength = 3;
    private boolean couldByEmpty = true;

    //todo: think about null inside validate
    public boolean isValid(String login) {
        boolean isNotEmpty;
        if(couldByEmpty){
            if(login == null || login.isBlank() || login.isEmpty()) {
                return true;
            }
        } else {
            isNotEmpty = true;

        }
            return true;
    }
}
