package com.gerard.site.validation.form.impl;

import com.gerard.site.form.RequestForm;
import com.gerard.site.validation.field.FieldsValidators;
import com.gerard.site.validation.form.FormValidator;

import java.util.HashMap;
import java.util.Map;

import static com.gerard.site.form.RequestForm.*;

public class RequestFormValidator implements FormValidator<RequestForm> {
    @Override
    public Map<String, Boolean> validateForm(RequestForm requestForm) {
        Map<String, Boolean> validationResult = new HashMap<>();
        validationResult.put(EMAIL_PARAMETER_NAME, FieldsValidators.getValidator(EMAIL_PARAMETER_NAME).isValid(requestForm.getEmail()));
        validationResult.put(CONTENT_PARAMETER_NAME, FieldsValidators.getValidator(CONTENT_PARAMETER_NAME).isValid(requestForm.getContent()));
        validationResult.put(APP_USER_NAME_PARAMETER_NAME, FieldsValidators.getValidator(APP_USER_NAME_PARAMETER_NAME).isValid(requestForm.getName()));
        validationResult.put(APP_USER_SURNAME_PARAMETER_NAME, FieldsValidators.getValidator(APP_USER_SURNAME_PARAMETER_NAME).isValid(requestForm.getSurname()));
        validationResult.put(APP_USER_PATRONYMIC_PARAMETER_NAME, FieldsValidators.getValidator(APP_USER_PATRONYMIC_PARAMETER_NAME).isValid(requestForm.getSurname()));
        validationResult.put(PHONE_PARAMETER_NAME, FieldsValidators.getValidator(PHONE_PARAMETER_NAME).isValid(requestForm.getPhone()));
        return validationResult;
    }
}
