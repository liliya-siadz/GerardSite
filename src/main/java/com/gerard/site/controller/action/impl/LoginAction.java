package com.gerard.site.controller.action.impl;

import com.gerard.site.controller.action.Action;
import com.gerard.site.validation.Field;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public enum LoginAction implements Action {
    INSTANCE;
    private static final String LOGIN_PARAMETER_NAME = "login";
    private static final String LOGIN_PARAMETER_PASSWORD = "password";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //session saves role
        //1 read login and password from request parameters
        //2 validate it
        //3 if ok: send redirect to home page
        //4 if not ok: redirect to same pages with mark map of errors fields
        String login = request.getParameter(LOGIN_PARAMETER_NAME);
        String password = request.getParameter(LOGIN_PARAMETER_PASSWORD);
        Field.init();
        HttpSession session = request.getSession();
        Map<String, Boolean> loginCommandErrors = new HashMap<>();
        loginCommandErrors.put("login", Field.validators.get("login").isValid(login));
        loginCommandErrors.put("password", Field.validators.get("password").isValid(password));
        if(loginCommandErrors.containsValue(false)) {
            session.setAttribute("loginCommandErrors", loginCommandErrors);
            session.setAttribute("validationResult", "login fields NOT OK");
            return Action.getRefererUrl(request);
        } else {
            session.setAttribute("loginCommandErrors", loginCommandErrors);
            session.setAttribute("validationResult", "login fields ok");
            return Action.APPLICATION_CONTEXT + "/authaccessed/my_profile";
        }
    }
}
