package com.gerard.site.form.validation.form.impl;

import com.gerard.site.form.LoginForm;
import com.gerard.site.form.validation.form.FormValidator;
import com.gerard.site.form.validation.field.FieldsValidators;

import java.util.HashMap;
import java.util.Map;
import static com.gerard.site.form.validation.field.Fields.*;

public class LoginFormValidator implements FormValidator<LoginForm> {
    @Override
    public Map<String, Boolean> validateForm(LoginForm loginForm) {
        Map<String, Boolean> validationResult = new HashMap<>();
        validationResult.put(EMAIL_PARAMETER_NAME, FieldsValidators.getValidator(EMAIL_PARAMETER_NAME).isValid(loginForm.getEmail()));
        validationResult.put(PASSWORD_PARAMETER_NAME, FieldsValidators.getValidator(PASSWORD_PARAMETER_NAME).isValid(loginForm.getPassword()));
        return validationResult;
    }

}
