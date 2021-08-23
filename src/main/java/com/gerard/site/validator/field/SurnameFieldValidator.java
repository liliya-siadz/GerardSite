package com.gerard.site.validator.field;

public class SurnameFieldValidator implements FieldValidator<String> {
    private static SurnameFieldValidator instance;

    private SurnameFieldValidator() {
    }

     static SurnameFieldValidator getInstance() {
        if (instance == null) {
            instance = new SurnameFieldValidator();
        }
        return instance;
    }

    @Override
    public boolean isValid(String field) {
        return true;
    }
}
