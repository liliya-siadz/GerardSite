package com.gerard.site.form;

import com.gerard.site.validation.field.FieldsValidators;
import com.gerard.site.validation.form.impl.LoginFormValidator;
import com.gerard.site.validation.form.impl.RequestFormValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class RequestForm extends AbstractForm<RequestFormValidator> {

    public static final String EMAIL_PARAMETER_NAME = "email";
    public static final String CONTENT_PARAMETER_NAME = "content";
    public static final String APP_USER_NAME_PARAMETER_NAME = "name";
    public static final String APP_USER_SURNAME_PARAMETER_NAME = "surname";
    public static final String APP_USER_PATRONYMIC_PARAMETER_NAME = "patronymic";
    public static final String PHONE_PARAMETER_NAME = "phone";
    private String email;
    private String content;
    private String name;
    private String surname;
    private String patronymic;
    private int phone;


    RequestForm(HttpServletRequest request) {
        super(request, List.of(EMAIL_PARAMETER_NAME,
                CONTENT_PARAMETER_NAME,
                APP_USER_NAME_PARAMETER_NAME,
                APP_USER_SURNAME_PARAMETER_NAME,
                APP_USER_PATRONYMIC_PARAMETER_NAME ,PHONE_PARAMETER_NAME),
                new RequestFormValidator());
        FieldsValidators.init();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    protected void readInputs(HttpServletRequest request) {
        email = request.getParameter(EMAIL_PARAMETER_NAME);
        content = request.getParameter(CONTENT_PARAMETER_NAME);
        name = request.getParameter(APP_USER_NAME_PARAMETER_NAME);
        surname = request.getParameter(APP_USER_SURNAME_PARAMETER_NAME);
        patronymic = request.getParameter(APP_USER_PATRONYMIC_PARAMETER_NAME);
        patronymic = request.getParameter(PHONE_PARAMETER_NAME);

    }
}
