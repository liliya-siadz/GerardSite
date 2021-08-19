package com.gerard.site.controller;

import com.gerard.site.entity.UserEntity;
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

enum SignInAction implements Action {

    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(SignInAction.class);


    //todo : url white list of roles
    //todo: filter ?

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginForm loginForm = new LoginForm(request);
        Map<String, Boolean> loginValidationErrors = loginForm.getValidationMap();
        if (loginValidationErrors.containsValue(false)) {
            //todo: transfer literal to constant
            session.setAttribute("loginValidationErrors", loginValidationErrors);
            return Action.getRefererUrl(request);
        } else {
            try {
                Optional<UserEntity> possibleAuthenticatedUser
                        = AppUserService.getInstance().authenticate(loginForm);
                if (possibleAuthenticatedUser.isEmpty()) {
                    //todo: make attr for "no user found" message
                    session.setAttribute("isUserFound", false);
                    return Action.getRefererUrl(request);
                } else {
                    //todo: add attr for user params
                    //todo: add welcome form on home page
                    //todo: ?where store user params
                    //todo: fill user profile on my profile page
                    UserEntity authenticatedUser
                            = possibleAuthenticatedUser.get();
                    UserEntity.AppUserRole authenticatedUserRole
                            = authenticatedUser.getAppUserRole();
                    session.setAttribute("role",
                            authenticatedUserRole.toString().toLowerCase());
                    session.setAttribute("email",authenticatedUser.getEmail());
                    return Action.APPLICATION_CONTEXT + "/home";
                }
            } catch (ServiceException exception) {
                LOGGER.fatal("Unable to execute authentication."
                        + "Request:  " + request + ". Login form: " + loginForm
                        + " . Reason: " + exception.getMessage(), exception);
                throw new RuntimeException("Unable to execute authentication."
                        + "Request:  " + request + ". Login form: " + loginForm
                        + " . Reason: " + exception.getMessage(), exception);
            }
        }
    }
}
