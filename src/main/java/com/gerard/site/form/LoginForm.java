package com.gerard.site.form;

import com.gerard.site.form.validation.field.FieldsValidators;
import com.gerard.site.form.validation.form.impl.LoginFormValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import static com.gerard.site.form.validation.field.Fields.*;

public class LoginForm extends AbstractForm<LoginFormValidator> {


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
