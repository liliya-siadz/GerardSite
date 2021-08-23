package com.gerard.site.service.impl;

import com.gerard.site.dao.AppUserDao;
import com.gerard.site.dao.DaoException;
import com.gerard.site.entity.AppUserEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.form.LoginForm;
import com.gerard.site.service.AppUserService;
import com.gerard.site.util.BCrypt;
import com.gerard.site.util.AppIdentifierUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Properties;

public class AppUserServiceImpl implements AppUserService {
    private static AppUserServiceImpl instance;
    private static final Logger LOGGER = LogManager.getLogger(AppUserServiceImpl.class);

    private AppUserServiceImpl() {
    }

    public static AppUserServiceImpl getInstance() {
        if (instance == null) {
            instance = new AppUserServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean authenticate(LoginForm loginForm)
            throws ServiceException {
        if (loginForm == null) {
            throw new ServiceException("Parameter 'loginForm' is null!");
        }
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        if ((email == null) || (password == null)) {
            throw new ServiceException("Login form has null values!");
        }
        AppUserEntity user = new AppUserEntity.Builder().email(email).build();
        try {
            Optional<AppUserEntity> realUser = AppUserDao.getInstance().find(user);
            boolean userWasAuthenticated = false;
            if (realUser.isPresent()) {
                if (!realUser.get().isAdmin()) {
                    return false;
                }
                String token = getToken();
                String userPassword = AppUserDao.getInstance()
                        .selectUserPasswordByEmail(token, email).get();
                boolean isPasswordCorrect = BCrypt.checkpw(password, userPassword);
                if (isPasswordCorrect) {
                    userWasAuthenticated = true;
                }
            }
            return userWasAuthenticated;
        } catch (DaoException exception) {
            throw new ServiceException("Unable to find record from form:  "
                    + loginForm + " . " + exception.getMessage(), exception);
        }
    }

    private String getToken() {
        String securityFileResourcePath = "/security.properties";
        Properties securityProperties = AppIdentifierUtil.getPropertiesByPath(
                instance, securityFileResourcePath);
        String passwordToken = "passwordToken";
        String token = securityProperties.getProperty(passwordToken);
        return token;
    }
}
