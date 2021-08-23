package com.gerard.site.validator.field;

import java.util.HashMap;
import java.util.Map;

import static com.gerard.site.validator.field.Fields.*;

public class FieldsValidators {

    private static final Map<String, FieldValidator> validators = new HashMap<>();
    private static FieldsValidators instance;

    static {
        validators.put(EMAIL_PARAMETER_NAME, EmailFieldValidator.getInstance());
        validators.put(PASSWORD_PARAMETER_NAME, PasswordFieldValidator.getInstance());
        validators.put(CONTENT_PARAMETER_NAME, ContentFieldValidator.getInstance());
        validators.put(APP_USER_NAME_PARAMETER_NAME, NameFieldValidator.getInstance());
        validators.put(APP_USER_SURNAME_PARAMETER_NAME, SurnameFieldValidator.getInstance());
        validators.put(PHONE_PARAMETER_NAME, PhoneFieldValidator.getInstance());
        validators.put(APP_USER_PATRONYMIC_PARAMETER_NAME, PatronymicFieldValidator.getInstance());
    }

    public static FieldValidator getValidator(String fieldName){
        return validators.get(fieldName);
    }

    public static void init() {
        if(instance == null) {
            instance = new FieldsValidators();
        }
    }
}

