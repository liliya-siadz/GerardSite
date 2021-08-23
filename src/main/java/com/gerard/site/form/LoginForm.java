package com.gerard.site.form;

import com.gerard.site.validation.field.FieldsValidators;
import com.gerard.site.validation.form.impl.LoginFormValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class LoginForm extends AbstractForm<LoginFormValidator> {

    public static final String EMAIL_PARAMETER_NAME = "email";
    public static final String PASSWORD_PARAMETER_NAME = "password";
    private String email;
    private String password;

    public LoginForm(HttpServletRequest request) {
        super(request, List.of(EMAIL_PARAMETER_NAME, PASSWORD_PARAMETER_NAME), new LoginFormValidator());
        FieldsValidators.init();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    protected void readInputs(HttpServletRequest request) {
        email = request.getParameter(EMAIL_PARAMETER_NAME);
        password = request.getParameter(PASSWORD_PARAMETER_NAME);
    }

}
