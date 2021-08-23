package com.gerard.site.controller.form;

import com.gerard.site.validator.FormValidator;
import com.gerard.site.validator.field.FieldsValidators;

import java.util.HashMap;
import java.util.Map;

import static com.gerard.site.validator.field.Fields.*;
import static com.gerard.site.validator.field.Fields.PHONE_PARAMETER_NAME;

public class RequestForm implements FormValidator {

    private String email;
    private String content;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;


    public RequestForm(String email,
                       String content,
                       String name,
                       String surname,
                       String patronymic,
                       String phone) {
    }

    public String getEmail() {
        return email;
    }



    public String getContent() {
        return content;
    }


    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }


    public String getPatronymic() {
        return patronymic;
    }


    public String getPhone() {
        return phone;
    }

    @Override
    public Map<String, Boolean> validateForm() {
        Map<String, Boolean> validationResult = new HashMap<>();
        validationResult.put(EMAIL_PARAMETER_NAME,
                FieldsValidators.getValidator(EMAIL_PARAMETER_NAME)
                        .isValid(email));
        validationResult.put(CONTENT_PARAMETER_NAME,
                FieldsValidators.getValidator(CONTENT_PARAMETER_NAME)
                        .isValid(content));
        validationResult.put(APP_USER_NAME_PARAMETER_NAME,
                FieldsValidators.getValidator(APP_USER_NAME_PARAMETER_NAME)
                        .isValid(name));
        validationResult.put(APP_USER_SURNAME_PARAMETER_NAME,
                FieldsValidators.getValidator(APP_USER_SURNAME_PARAMETER_NAME)
                        .isValid(surname));
        validationResult.put(APP_USER_PATRONYMIC_PARAMETER_NAME,
                FieldsValidators.getValidator(APP_USER_PATRONYMIC_PARAMETER_NAME)
                        .isValid(patronymic));
        validationResult.put(PHONE_PARAMETER_NAME,
                FieldsValidators.getValidator(PHONE_PARAMETER_NAME)
                        .isValid(phone));
        return validationResult;
    }
}
