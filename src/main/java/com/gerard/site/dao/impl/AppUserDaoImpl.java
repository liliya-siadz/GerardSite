package com.gerard.site.dao.impl;

import com.gerard.site.dao.AbstractDao;
import com.gerard.site.dao.AppUserDao;
import com.gerard.site.dao.connection.ConnectionException;
import com.gerard.site.dao.connection.ConnectionPool;
import com.gerard.site.service.entity.AppUserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AppUserDaoImpl extends AbstractDao<AppUserEntity> implements AppUserDao {
    private static AppUserDaoImpl instance;
    static final String TABLE_NAME = "app_user";
    static final String COLUMN_LABEL_1 = "app_user_id";
    static final String COLUMN_LABEL_2 = "email";
    static final String COLUMN_LABEL_3 = "password";
    static final String COLUMN_LABEL_4 = "name";
    static final String COLUMN_LABEL_5 = "surname";
    static final String COLUMN_LABEL_6 = "patronymic";
    static final String COLUMN_LABEL_7 = "phone";
    static final String COLUMN_LABEL_8 = "admin";

    private static final String SELECT_USER_PASSWORD_BY_EMAIL
            = "select password from app_user where email =?";

    private static final String SELECT_USER_BY_EMAIL
            = "select app_user_id, email, name, surname, patronymic," +
            "phone, admin from app_user where email =?";

    private static final String SELECT_ALL_USERS
            = "select app_user_id, email, name, surname, patronymic," +
            "phone, admin from app_user";

    private static final Logger LOGGER = LogManager.getLogger(AppUserDaoImpl.class);

    private AppUserDaoImpl() {
        super();
    }

    public static AppUserDaoImpl getInstance() {
        if (instance == null) {
            instance = new AppUserDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<AppUserEntity> find(AppUserEntity user) throws DaoException {
        if (user == null) {
            throw new DaoException("Parameter 'user' is null");
        }
        String email = user.getEmail();
        try (Connection connection =
                     ConnectionPool.getInstance().giveOutConnection() ;
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                AppUserEntity selectedUser = null;
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {
                        selectedUser = parseResultSet(resultSet);
                    }
                }
                return Optional.ofNullable(selectedUser);
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: "
                    + TABLE_NAME + " ! "
                    + exception.getMessage(), exception);
        }
    }

    @Override
    public List<AppUserEntity> selectAll() throws DaoException {
        try (Connection connection =
                     ConnectionPool.getInstance().giveOutConnection();
            Statement statement =
                    connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
                if (resultSet.isBeforeFirst()) {
                    List<AppUserEntity> selectedUsers = new ArrayList<>();
                    while (resultSet.next()) {
                        selectedUsers.add(parseResultSet(resultSet));
                    }
                    return selectedUsers;
                } else {
                    LOGGER.info("No records were found in table: " + TABLE_NAME + ". ");
                    return Collections.emptyList();
                }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: "
                    + TABLE_NAME + " ! "
                    + "Reason: " + exception.getMessage(), exception);
        }
    }

    @Override
    public Optional<String> selectUserPasswordByEmail(String token, String email)
            throws DaoException {
        String passwordToken = "electronDance18";
        if (passwordToken.equals(token)) {
            if (email == null) {
                throw new DaoException("Parameter 'email' is null");
            }
            try (Connection connection =
                         ConnectionPool.getInstance().giveOutConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(SELECT_USER_PASSWORD_BY_EMAIL)) {
                    preparedStatement.setString(1, email);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    String password = null;
                    if (resultSet.isBeforeFirst()) {
                        while (resultSet.next()) {
                            password = resultSet.getString(COLUMN_LABEL_3);
                        }
                    }
                    return Optional.ofNullable(password);
            } catch (SQLException | ConnectionException exception) {
                throw new DaoException("Unable to get data from table: "
                        + TABLE_NAME + " ! "
                        + exception.getMessage(), exception);
            }
        } else {
            LOGGER.warn("Unknown access for service! "
                    + ". Used token : " + token);
            throw new DaoException("Unknown access for service! . Access denied!");
        }
    }


    @Override
    public boolean update(AppUserEntity user, AppUserEntity newUserVersion)
            throws DaoException {
        if (user == null) {
            throw new DaoException("Parameter 'user' is null");
        }
        if (newUserVersion == null) {
            throw new DaoException("Parameter 'newUserVersion' is null");
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<AppUserEntity> create(AppUserEntity user) throws DaoException {
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
        String phone = resultSet.getString(COLUMN_LABEL_7);
        boolean admin = resultSet.getBoolean(COLUMN_LABEL_8);
        AppUserEntity appUserEntity = new AppUserEntity.Builder()
                .id(appUserId)
                .email(email)
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .phone(phone)
                .admin(admin)
                .build();
        return appUserEntity;
    }
}