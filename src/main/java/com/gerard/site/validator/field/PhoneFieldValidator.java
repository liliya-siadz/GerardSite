package com.gerard.site.validator.field;

import com.gerard.site.validator.FieldValidator;

public enum PhoneFieldValidator implements FieldValidator<String> {
    INSTANCE;
    @Override
    public boolean isValid(String phone) {
        if (phone == null || phone.isBlank()) {
            return false;
        }
        int strongLength = 9;
        String validRegex = "^[25|44|33|29]\\d*";
        return (phone.length() == strongLength)
                && (phone.matches(validRegex));
    }
}
