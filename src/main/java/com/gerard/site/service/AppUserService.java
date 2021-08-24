package com.gerard.site.service;

import com.gerard.site.controller.form.LoginForm;

public interface AppUserService {
    boolean authenticate(LoginForm loginForm) throws ServiceException;
}
