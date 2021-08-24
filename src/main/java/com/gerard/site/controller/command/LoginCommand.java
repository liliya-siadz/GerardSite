package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.ServiceException;
import com.gerard.site.controller.form.LoginForm;
import com.gerard.site.service.impl.AppUserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.gerard.site.validator.field.FieldIdentifier.*;

public enum LoginCommand implements Command {
    INSTANCE;
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private final String validationMapNameAttributeName = "loginValidationMap";
    private final String authenticationResultAttributeName = "isUserFound";
    private final String authenticationIdentifierAttributeName = "admin";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.trace("Login command was called.");
        HttpSession session = request.getSession();
        String email = request.getParameter(EMAIL_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);
        LoginForm loginForm = new LoginForm(email, password);
        Map<String, Boolean> loginValidationMap = loginForm.validateForm();
        if (loginValidationMap.containsValue(false)) {
            session.setAttribute(validationMapNameAttributeName, loginValidationMap);
            LOGGER.trace("Login form: " + loginForm +  " has validation errors: " + loginValidationMap);
            return Page.LOGIN.getPageUrl();
        } else {
               boolean userWasAuthenticated =
                       AppUserServiceImpl.getInstance().authenticate(loginForm);
               if(userWasAuthenticated) {
                   LOGGER.trace("User was authenticated.");
                   session.setAttribute(authenticationResultAttributeName, true);
                   session.setAttribute(authenticationIdentifierAttributeName, true);
                   return Page.HOME.getPageUrl();
               } else {
                   LOGGER.trace("User was NOT authenticated.");
                   session.setAttribute(authenticationResultAttributeName, false);
                   return Page.LOGIN.getPageUrl();
               }
       }
    }
}
