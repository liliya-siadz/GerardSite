package com.gerard.site.validation.field;

import com.gerard.site.validation.field.impl.EmailFieldValidator;
import com.gerard.site.validation.field.impl.PasswordFieldValidator;

import java.util.HashMap;
import java.util.Map;

public class FieldsValidators {

    //todo store them as already inited once
    private static final Map<String, FieldValidator> validators = new HashMap<>();
    private static FieldsValidators instance;

    static {
        validators.put("email", new EmailFieldValidator());
        validators.put("mail.user.password", new PasswordFieldValidator());
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

