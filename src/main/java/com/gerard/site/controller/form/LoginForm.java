package com.gerard.site.controller.form;

import com.gerard.site.validator.field.FieldValidatorFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.gerard.site.validator.field.FieldIdentifier.EMAIL_PARAMETER_NAME;
import static com.gerard.site.validator.field.FieldIdentifier.PASSWORD_PARAMETER_NAME;

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
                FieldValidatorFactory.getValidator(EMAIL_PARAMETER_NAME)
                        .isValid(email));
        validationResult.put(PASSWORD_PARAMETER_NAME,
                FieldValidatorFactory.getValidator(PASSWORD_PARAMETER_NAME)
                        .isValid(password));
        return validationResult;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        LoginForm loginForm = (LoginForm) object;
        return Objects.equals(email, loginForm.email)
                && Objects.equals(password, loginForm.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginForm{");
        sb.append("email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
