package com.gerard.site.validator;

import com.gerard.site.validator.field.*;

import java.util.HashMap;
import java.util.Map;

import static com.gerard.site.validator.Fields.*;

public class FieldsValidators {

    private static final Map<String, FieldValidator> validators = new HashMap<>();
    private static FieldsValidators instance;

    static {
        validators.put(EMAIL_PARAMETER_NAME, EmailFieldValidator.INSTANCE);
        validators.put(PASSWORD_PARAMETER_NAME, PasswordFieldValidator.INSTANCE);
        validators.put(CONTENT_PARAMETER_NAME, ContentFieldValidator.INSTANCE);
        validators.put(APP_USER_NAME_PARAMETER_NAME, NameFieldValidator.INSTANCE);
        validators.put(APP_USER_SURNAME_PARAMETER_NAME, NameFieldValidator.INSTANCE);
        validators.put(PHONE_PARAMETER_NAME, PhoneFieldValidator.INSTANCE);
        validators.put(APP_USER_PATRONYMIC_PARAMETER_NAME, NameFieldValidator.INSTANCE);
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

