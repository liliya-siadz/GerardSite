package com.gerard.site.validator.field;

public class PasswordFieldValidator implements FieldValidator<String> {

    private static PasswordFieldValidator instance;

    private PasswordFieldValidator() {
    }

     static PasswordFieldValidator getInstance() {
        if (instance == null) {
            instance = new PasswordFieldValidator();
        }
        return instance;
    }


    @Override
    public boolean isValid(String password) {
        return true;
    }
}
