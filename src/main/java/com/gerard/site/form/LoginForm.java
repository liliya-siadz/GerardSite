package com.gerard.site.form;

import com.gerard.site.validation.field.FieldsValidators;
import com.gerard.site.validation.form.impl.LoginFormValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

public class LoginForm extends AbstractForm {

    public static final String EMAIL_PARAMETER_NAME = "email";
    public static final String PASSWORD_PARAMETER_NAME = "password";
    private String email;
    private String password;

    public LoginForm(HttpServletRequest request){
        this();
        readInputs(request);
        //put to Listener or init always
        FieldsValidators.init();
    }

    public LoginForm() {
        super(List.of(EMAIL_PARAMETER_NAME, PASSWORD_PARAMETER_NAME));
        setValidator(new LoginFormValidator());
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Map<String, Boolean> getValidationMap(){
        return validator.validateForm(this);
    }

    protected void readInputs(HttpServletRequest request) {
        email = request.getParameter(EMAIL_PARAMETER_NAME);
        password = request.getParameter(PASSWORD_PARAMETER_NAME);
    }

}
