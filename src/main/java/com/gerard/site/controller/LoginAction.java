package com.gerard.site.controller;

import com.gerard.site.entity.AppUserEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.form.LoginForm;
import com.gerard.site.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

enum LoginAction implements Action {

    INSTANCE("loginValidationMap",
            "isUserFound",
                                      "admin");

    private final String validationMapNameAttributeName;
    private final String authenticationResultAttributeName;
    private final String authenticationIdentifierAttributeName;

    private static final Logger LOGGER = LogManager.getLogger(LoginAction.class);

    LoginAction(String validationMapNameAttributeName,
                String authenticationResultAttributeName,
                String authenticationIdentifierAttributeName) {
        this.validationMapNameAttributeName = validationMapNameAttributeName;
        this.authenticationResultAttributeName = authenticationResultAttributeName;
        this.authenticationIdentifierAttributeName = authenticationIdentifierAttributeName;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginForm loginForm = new LoginForm(request);
        Map<String, Boolean> validationMap = loginForm.getValidationMap();
        if (validationMap.containsValue(false)) {
            session.setAttribute(validationMapNameAttributeName, validationMap);
            return Action.getRefererUrl(request);
        } else {
            try {
                Optional<AppUserEntity> possibleAuthenticatedUser
                        = AppUserService.getInstance().authenticate(loginForm);
                if (possibleAuthenticatedUser.isEmpty()) {
                    session.setAttribute(authenticationResultAttributeName, false);
                    return Action.getRefererUrl(request);
                } else {
                    session.setAttribute(authenticationResultAttributeName, true);
                    session.setAttribute(authenticationIdentifierAttributeName, true);
                   return Page.HOME.getUrl();
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
