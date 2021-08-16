package com.gerard.site.service;

import com.gerard.site.dao.AppUserDao;
import com.gerard.site.dao.DaoException;
import com.gerard.site.entity.UserEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.form.LoginForm;

import java.util.Optional;

public class AppUserService {
    public static Optional<UserEntity> authenticate(LoginForm loginForm)
            throws ServiceException {
        if(loginForm == null) {
            throw new ServiceException("Parameter 'loginForm' is null");
        }
        //todo use hash for password
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        UserEntity userEntity = new UserEntity.Builder()
                .email(email)
                .password(password)
                .build();
        try {
             return Optional.ofNullable(AppUserDao.getInstance().findRecord(userEntity));
        } catch (DaoException exception) {
            throw new ServiceException("Unable to find record from form:  "
                    + loginForm + " . Reason: " + exception.getMessage(), exception);
        }
    }
}
