package com.gerard.site.validator;

public enum PatronymicValidator implements Validator<String> {
    INSTANCE;

    @Override
    public boolean isValid(String name) {
        if (name == null || name.isBlank()) {
            return true;
        } else {
            int maxLength = 250;
            int minLength = 3;
            String validRegex = "[a-zA-Z]*|[ЁёА-я]*";
            return (name.length() >= minLength)
                    && (name.length() <= maxLength)
                    && (name.matches(validRegex));
        }
    }
}
