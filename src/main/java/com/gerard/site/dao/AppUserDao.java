package com.gerard.site.dao;

import com.gerard.site.connection.ConnectionException;
import com.gerard.site.connection.ConnectionPool;
import com.gerard.site.entity.AppUserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppUserDao extends AbstractDao<AppUserEntity> {
    private static AppUserDao instance;
     static final String TABLE_NAME = "gerard.app_user";
     static final String COLUMN_LABEL_1 = "app_user_id";
     static final String COLUMN_LABEL_2 = "email";
     static final String COLUMN_LABEL_3 = "password";
     static final String COLUMN_LABEL_4 = "name";
     static final String COLUMN_LABEL_5 = "surname";
     static final String COLUMN_LABEL_6 = "patronymic";
     static final String COLUMN_LABEL_7 = "phone";
     static final String COLUMN_LABEL_8 = "is_admin";

    private static final Logger LOGGER = LogManager.getLogger(AppUserDao.class);

    private AppUserDao() {
        super();
    }

    public static AppUserDao getInstance() {
        if (instance == null) {
            instance = new AppUserDao();
        }
        return instance;
    }

    @Override
    public AppUserEntity find(AppUserEntity user) throws DaoException {
        if (user == null) {
            throw new DaoException("Parameter 'user' is null");
        }
        final String selectUserByEmail
                = "SELECT * FROM " + TABLE_NAME + " where email =?";
        String email = user.getEmail();
        try (Connection connection
                     = ConnectionPool.getInstance().giveOutConnection()) {
            try (PreparedStatement preparedStatement
                         = connection.prepareStatement(selectUserByEmail)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    AppUserEntity selectedUser = null;
                    while (resultSet.next()) {
                        selectedUser = parseResultSet(resultSet);
                    }
                    return selectedUser;
                } else {
                    LOGGER.info("No records were found in table: "
                            + TABLE_NAME + ". "
                            + "Used next columns values: "
                            + TABLE_NAME + "." + COLUMN_LABEL_2 + " : " + email
                            + " . ");
                    LOGGER.warn("Null will be returned");
                    return null;
                }
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: "
                    + TABLE_NAME + " ! "
                    + exception.getMessage(), exception);
        }
    }

    @Override
    public List<AppUserEntity> selectAll() throws DaoException {
        final String selectAllUsers = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = ConnectionPool.getInstance().giveOutConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(selectAllUsers);
                if (resultSet.isBeforeFirst()) {
                    List<AppUserEntity> selectedUsers = new ArrayList<>();
                    while (resultSet.next()) {
                        selectedUsers.add(parseResultSet(resultSet));
                    }
                    return selectedUsers;
                } else {
                    LOGGER.info("No records were found in table: "
                            + TABLE_NAME + ". ");
                    LOGGER.warn("Null will be returned");
                    return null;
                }
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: "
                    + TABLE_NAME + " ! "
                    + "Reason: " + exception.getMessage(), exception);
        }
    }


    public String selectUserPasswordByEmail(String token, String email)
            throws DaoException {
        String passwordToken = "electronDance18";
        if (passwordToken.equals(token)) {
            if (email == null) {
                throw new DaoException("Parameter 'email' is null");
            }
            final String selectUserPasswordByEmail
                    = "SELECT " + COLUMN_LABEL_3 + " FROM " + TABLE_NAME + " where email =?";
            try (Connection connection = ConnectionPool.getInstance().giveOutConnection()) {
                try (PreparedStatement preparedStatement
                             = connection.prepareStatement(selectUserPasswordByEmail)) {
                    preparedStatement.setString(1, email);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.isBeforeFirst()) {
                        String password = null;
                        while (resultSet.next()) {
                            password = resultSet.getString(COLUMN_LABEL_3);
                        }
                        return password;
                    } else {
                        LOGGER.info("No records were found in table: "
                                + TABLE_NAME + ". "
                                + "Used next columns values: "
                                + TABLE_NAME + "." + COLUMN_LABEL_2 + " : " + email
                                + " . ");

                        LOGGER.warn("Null will be returned");
                        return null;
                    }
                }
            } catch (SQLException | ConnectionException throwables) {
                throw new DaoException("Unable to get data from table: "
                        + TABLE_NAME + " ! "
                        + throwables.getMessage(), throwables);
            }
        } else {
            LOGGER.warn("Unknown access for service! "
                    + ". Used token : " + token);
            throw new DaoException("Unknown access for service! . Access denied!");
        }
    }


    @Override
    public boolean update(AppUserEntity user, AppUserEntity newUserVersion) throws DaoException {
        if (user == null) {
            throw new DaoException("Parameter 'user' is null");
        }
        if (newUserVersion == null) {
            throw new DaoException("Parameter 'newUserVersion' is null");
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(AppUserEntity user) throws DaoException {
        if (user == null) {
            throw new DaoException("Parameter 'user' is null");
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public AppUserEntity parseResultSet(ResultSet resultSet) throws SQLException {
        int appUserId = resultSet.getInt(COLUMN_LABEL_1);
        String email = resultSet.getString(COLUMN_LABEL_2);
        String name = resultSet.getString(COLUMN_LABEL_4);
        String surname = resultSet.getString(COLUMN_LABEL_5);
        String patronymic = resultSet.getString(COLUMN_LABEL_6);
        int phone = resultSet.getInt(COLUMN_LABEL_7);
        boolean isAdmin = resultSet.getBoolean(COLUMN_LABEL_8);
        AppUserEntity appUserEntity = new AppUserEntity.Builder()
                .id(appUserId)
                .email(email)
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .phone(phone)
                .isAdmin(isAdmin)
                .build();
        return appUserEntity;
    }
}
