package com.gerard.site.validator.field;

public enum NameFieldValidator implements FieldValidator<String> {
     INSTANCE;

    @Override
    public boolean isValid(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        int maxLength = 250;
        int minLength = 3;
        String validRegex = "[\\w]*";
        return (name.length() >= minLength)
                && (name.length() <= maxLength)
                && (name.matches(validRegex));
    }
}
