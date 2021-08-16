package com.gerard.site.dao;

import com.gerard.site.connection.ConnectionException;
import com.gerard.site.connection.ConnectionPool;
import com.gerard.site.dto.UserWithRequests;
import com.gerard.site.entity.UserEntity;

import java.sql.Connection;
import java.util.Optional;

public class AppUserDao {
    //select * where login == ' ' and password == ' '
    //add database name to exception and logs
    //add table name to exception and logs
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "select * from app_user where ";

    public static UserWithRequests findUserByLoginAndPassword(
            String login, String hashedPassword) throws DaoException {
        try {
            Connection connection = ConnectionPool.getInstance().giveOutConnection();

        } catch (ConnectionException exception) {
            throw new DaoException("Unable to getConnection from database. "
                    + exception.getMessage(), exception);
        }

        return null;
    }
}
