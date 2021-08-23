package com.gerard.site.form.validation.field;

import com.gerard.site.form.validation.field.impl.*;

import java.util.HashMap;
import java.util.Map;

import static com.gerard.site.form.validation.field.Fields.*;

public class FieldsValidators {

    //todo store them as already initialized once
    private static final Map<String, FieldValidator> validators = new HashMap<>();
    private static FieldsValidators instance;

    static {
        validators.put(EMAIL_PARAMETER_NAME, new EmailFieldValidator());
        validators.put(PASSWORD_PARAMETER_NAME, new PasswordFieldValidator());
        validators.put(CONTENT_PARAMETER_NAME, new ContentFieldValidator());
        validators.put(APP_USER_NAME_PARAMETER_NAME, new NameValidator());
        validators.put(APP_USER_SURNAME_PARAMETER_NAME, new SurnameValidator());
        validators.put(PHONE_PARAMETER_NAME, new PhoneValidator());
        validators.put(APP_USER_PATRONYMIC_PARAMETER_NAME, new PatronymicValidator());
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

