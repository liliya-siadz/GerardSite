package com.gerard.site.validator;

import java.util.HashMap;
import java.util.Map;

import static com.gerard.site.validator.ValidatorIdentifier.APP_USER_NAME_PARAMETER_NAME;
import static com.gerard.site.validator.ValidatorIdentifier.APP_USER_PATRONYMIC_PARAMETER_NAME;
import static com.gerard.site.validator.ValidatorIdentifier.APP_USER_SURNAME_PARAMETER_NAME;
import static com.gerard.site.validator.ValidatorIdentifier.CONTENT_PARAMETER_NAME;
import static com.gerard.site.validator.ValidatorIdentifier.EMAIL_PARAMETER_NAME;
import static com.gerard.site.validator.ValidatorIdentifier.PASSWORD_PARAMETER_NAME;
import static com.gerard.site.validator.ValidatorIdentifier.PHONE_PARAMETER_NAME;
import static com.gerard.site.validator.ValidatorIdentifier.REPLY_PARAMETER_NAME;

public class ValidatorFactory {

    private static final Map<String, Validator> validators = new HashMap<>();
    private static ValidatorFactory instance;

    static {
        validators.put(EMAIL_PARAMETER_NAME, EmailValidator.INSTANCE);
        validators.put(REPLY_PARAMETER_NAME, PasswordValidator.INSTANCE);
        validators.put(CONTENT_PARAMETER_NAME, ContentValidator.INSTANCE);
        validators.put(APP_USER_NAME_PARAMETER_NAME, NameValidator.INSTANCE);
        validators.put(APP_USER_SURNAME_PARAMETER_NAME, NameValidator.INSTANCE);
        validators.put(PHONE_PARAMETER_NAME, PhoneValidator.INSTANCE);
        validators.put(PASSWORD_PARAMETER_NAME, PasswordValidator.INSTANCE);
        validators.put(APP_USER_PATRONYMIC_PARAMETER_NAME, PatronymicValidator.INSTANCE);
    }

    public static Validator getValidator(String fieldName){
        return validators.get(fieldName);
    }

    public static void init() {
        if(instance == null) {
            instance = new ValidatorFactory();
        }
    }
}

