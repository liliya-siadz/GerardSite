package com.gerard.site.validator.field;

public enum ContentFieldValidator implements FieldValidator<String> {
    INSTANCE;

    @Override
    public boolean isValid(String content) {
        if (content == null || content.isBlank()) {
            return false;
        }
        int maxLength = 450;
        int minLength = 10;
        String validRegex = "[\\w!.?,:\s]*|[[ЁёА-я]!.?,:\s]*";
        return (content.length() >= minLength)
                && (content.length() <= maxLength)
                && (content.matches(validRegex));
    }
}
