package com.gerard.site.controller.form;

import com.gerard.site.validator.FormValidator;
import com.gerard.site.validator.FieldsValidators;

import java.util.HashMap;
import java.util.Map;

import static com.gerard.site.validator.Fields.EMAIL_PARAMETER_NAME;
import static com.gerard.site.validator.Fields.PASSWORD_PARAMETER_NAME;

public class LoginForm implements FormValidator {

    private String email;
    private String password;

    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Map<String, Boolean> validateForm() {
        Map<String, Boolean> validationResult = new HashMap<>();
        validationResult.put(EMAIL_PARAMETER_NAME,
                FieldsValidators.getValidator(EMAIL_PARAMETER_NAME)
                        .isValid(email));
        validationResult.put(PASSWORD_PARAMETER_NAME,
                FieldsValidators.getValidator(PASSWORD_PARAMETER_NAME)
                        .isValid(password));
        return validationResult;
    }
}
