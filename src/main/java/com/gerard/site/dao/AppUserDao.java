package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;

import java.util.Optional;

public interface AppUserDao {
    Optional<String> selectUserPasswordByEmail(String token, String email)
            throws DaoException;
}
