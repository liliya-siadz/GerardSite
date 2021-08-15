package com.gerard.site.controller.action.impl;

import com.gerard.site.controller.action.Action;
import com.gerard.site.form.AbstractForm;
import com.gerard.site.form.LoginForm;
import com.mysql.cj.log.Log;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.lang.reflect.Field;
import java.util.Map;

public enum LoginAction implements Action {
    INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginForm loginForm = new LoginForm(request);
        Map<String, Boolean> loginValidationErrors = loginForm.getValidationMap();
        if (loginValidationErrors.containsValue(false)) {
            session.setAttribute("loginValidationErrors", loginValidationErrors);
            return Action.getRefererUrl(request);
        } else {
            session.setAttribute(LoginForm.EMAIL_PARAMETER_NAME,loginForm.getEmail());
            return Action.APPLICATION_CONTEXT + "/authaccessed/my_profile";
        }
    }
}
