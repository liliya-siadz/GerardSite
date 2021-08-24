package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.controller.form.LoginForm;
import com.gerard.site.service.impl.AppUserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.gerard.site.validator.Fields.*;

public enum LoginCommand implements Command {

    INSTANCE;

    private final String validationMapNameAttributeName = "loginValidationMap";
    private final String authenticationResultAttributeName = "isUserFound";
    private final String authenticationIdentifierAttributeName = "admin";

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String email = request.getParameter(EMAIL_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);
        LoginForm loginForm = new LoginForm(email, password);
        Map<String, Boolean> validationMap = loginForm.validateForm();
        if (validationMap.containsValue(false)) {
            session.setAttribute(validationMapNameAttributeName, validationMap);
            return Page.LOGIN.getPageUrl();
        } else {
            try {
               boolean userWasAuthenticated = AppUserServiceImpl.getInstance().authenticate(loginForm);
               if(userWasAuthenticated) {
                   session.setAttribute(authenticationResultAttributeName, true);
                   session.setAttribute(authenticationIdentifierAttributeName, true);
                   return Page.HOME.getPageUrl();
               } else {
                   session.setAttribute(authenticationResultAttributeName, false);
                   return Page.LOGIN.getPageUrl();
               }
            } catch (ServiceException exception) {
                LOGGER.fatal("Unable to execute authentication."
                        + "Request:  " + request + ". Login form: " + loginForm
                        + exception.getMessage(), exception);
                throw new RuntimeException("Unable to execute authentication."
                        + "Request:  " + request + ". Login form: " + loginForm
                        + exception.getMessage(), exception);
            }
        }
    }
}
