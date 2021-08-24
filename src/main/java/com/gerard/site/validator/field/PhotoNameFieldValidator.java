package com.gerard.site.validator.field;

import com.gerard.site.validator.FieldValidator;

public enum PhotoNameFieldValidator implements FieldValidator<String> {
    INSTANCE;
    public boolean isValid(String photoName) {
        if (photoName == null || photoName.isBlank()) {
            return false;
        }
        int maxLength = 45;
        int minLength = 11;
        String validRegex = "^[\\d]{1,4}[photo|pedigree|avatar]\\d{1,3}$.[jpg|png]";
        return (photoName.length() >= minLength)
                && (photoName.length() <= maxLength)
                && (photoName.matches(validRegex));
    }
    //max length 45
    //min length 11
    //starts from number \\d{1,4} [photo|pedigree|avatar] \\d{1,3} $[.[jpg|png]]
}
