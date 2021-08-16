package com.gerard.site.service.impl;

import com.gerard.site.dao.AppUserDao;
import com.gerard.site.dao.DaoException;
import com.gerard.site.dto.UserWithRequests;
import com.gerard.site.service.ServiceException;
import com.gerard.site.util.BCrypt;

import java.util.Optional;

public class AppUserService {
    public Optional<UserWithRequests> findUser(String login, String password) throws ServiceException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try {
            return Optional.ofNullable(AppUserDao.findUserByLoginAndPassword(
                    login, hashedPassword));
        } catch (DaoException exception) {
            throw new ServiceException("Unable to retrieve data from database. "
                    + exception.getMessage(), exception);
        }
    }
}
