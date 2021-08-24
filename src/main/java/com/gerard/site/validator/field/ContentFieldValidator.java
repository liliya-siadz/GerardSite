package com.gerard.site.validator.field;

import com.gerard.site.validator.FieldValidator;

public enum ContentFieldValidator implements FieldValidator<String> {
    INSTANCE;

    @Override
    public boolean isValid(String content) {
        if (content == null || content.isBlank()) {
            return false;
        }
        int maxLength = 450;
        int minLength = 10;
        String validRegex = "[\\w!.?,:\s]*";
        return (content.length() >= minLength)
                && (content.length() <= maxLength)
                && (content.matches(validRegex));
    }

    //length between 10 and 450 symbols inclusively
    //may has space or _ or , or ?  or !
}
