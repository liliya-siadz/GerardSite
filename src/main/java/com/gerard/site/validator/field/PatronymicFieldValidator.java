package com.gerard.site.validator.field;

public class PatronymicFieldValidator implements FieldValidator<String> {

    private static PatronymicFieldValidator instance;

    private PatronymicFieldValidator() {
    }

     static PatronymicFieldValidator getInstance() {
        if (instance == null) {
            instance = new PatronymicFieldValidator();
        }
        return instance;
    }

    @Override
    public boolean isValid(String field) {
        return true;

    }
}
