package com.gerard.site.validator;

public enum PasswordValidator implements Validator<String> {
    INSTANCE;

    @Override
    public boolean isValid(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }
        int maxLength = 15;
        int minLength = 6;
        String validRegex = "[\\w]*";
        return (password.length() >= minLength)
                && (password.length() <= maxLength)
                && (password.matches(validRegex));
    }
}
