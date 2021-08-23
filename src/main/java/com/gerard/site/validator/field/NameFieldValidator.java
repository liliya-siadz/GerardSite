package com.gerard.site.validator.field;

public class NameFieldValidator implements FieldValidator<String> {
    private static NameFieldValidator instance;

    private NameFieldValidator() {
    }

    public static NameFieldValidator getInstance() {
        if (instance == null) {
            instance = new NameFieldValidator();
        }
        return instance;
    }


    @Override
    public boolean isValid(String field) {
        return true;
    }
}
