package com.gerard.site.service;

import com.gerard.site.entity.AppUserEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.form.LoginForm;

import java.util.Optional;

public interface AppUserService {
    boolean authenticate(LoginForm loginForm)
            throws ServiceException;
}
