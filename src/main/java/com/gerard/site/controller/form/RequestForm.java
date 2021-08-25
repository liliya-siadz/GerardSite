package com.gerard.site.controller.form;

import com.gerard.site.validator.field.FieldValidatorFactory;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.gerard.site.validator.field.FieldIdentifier.APP_USER_NAME_PARAMETER_NAME;
import static com.gerard.site.validator.field.FieldIdentifier.APP_USER_PATRONYMIC_PARAMETER_NAME;
import static com.gerard.site.validator.field.FieldIdentifier.APP_USER_SURNAME_PARAMETER_NAME;
import static com.gerard.site.validator.field.FieldIdentifier.CONTENT_PARAMETER_NAME;
import static com.gerard.site.validator.field.FieldIdentifier.EMAIL_PARAMETER_NAME;
import static com.gerard.site.validator.field.FieldIdentifier.PHONE_PARAMETER_NAME;

public class RequestForm implements FormValidator, Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    private String email;
    private String content;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;

    public RequestForm(){ }

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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Map<String, Boolean> validateForm() {
        Map<String, Boolean> validationResult = new HashMap<>();
        validationResult.put(EMAIL_PARAMETER_NAME,
                FieldValidatorFactory.getValidator(EMAIL_PARAMETER_NAME)
                        .isValid(email));
        validationResult.put(CONTENT_PARAMETER_NAME,
                FieldValidatorFactory.getValidator(CONTENT_PARAMETER_NAME)
                        .isValid(content));
        validationResult.put(APP_USER_NAME_PARAMETER_NAME,
                FieldValidatorFactory.getValidator(APP_USER_NAME_PARAMETER_NAME)
                        .isValid(name));
        validationResult.put(APP_USER_SURNAME_PARAMETER_NAME,
                FieldValidatorFactory.getValidator(APP_USER_SURNAME_PARAMETER_NAME)
                        .isValid(surname));
        validationResult.put(APP_USER_PATRONYMIC_PARAMETER_NAME,
                FieldValidatorFactory.getValidator(APP_USER_PATRONYMIC_PARAMETER_NAME)
                        .isValid(patronymic));
        validationResult.put(PHONE_PARAMETER_NAME,
                FieldValidatorFactory.getValidator(PHONE_PARAMETER_NAME)
                        .isValid(phone));
        return validationResult;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        RequestForm that = (RequestForm) object;
        return Objects.equals(email, that.email)
                && Objects.equals(content, that.content)
                && Objects.equals(name, that.name)
                && Objects.equals(surname, that.surname)
                && Objects.equals(patronymic, that.patronymic)
                && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, content, name, surname, patronymic, phone);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestForm{");
        sb.append("email='").append(email).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
