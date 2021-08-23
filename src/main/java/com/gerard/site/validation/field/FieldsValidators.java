package com.gerard.site.validation.field;

import com.gerard.site.validation.field.impl.ContentFieldValidator;
import com.gerard.site.validation.field.impl.EmailFieldValidator;
import com.gerard.site.validation.field.impl.PasswordFieldValidator;

import java.util.HashMap;
import java.util.Map;

public class FieldsValidators {

    //todo store them as already initialized once
    private static final Map<String, FieldValidator> validators = new HashMap<>();
    private static FieldsValidators instance;

    static {
        validators.put("email", new EmailFieldValidator());
        validators.put("password", new PasswordFieldValidator());
        validators.put("content", new ContentFieldValidator());
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

