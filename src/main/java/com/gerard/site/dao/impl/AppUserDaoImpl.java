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
import java.util.Optional;

public class AppUserDaoImpl extends AbstractDao<AppUserEntity>
        implements AppUserDao {
    static final String TABLE_NAME = "app_user";
    static final String COLUMN_LABEL_1 = "app_user_id";
    static final String COLUMN_LABEL_2 = "email";
    static final String COLUMN_LABEL_3 = "password";
    static final String COLUMN_LABEL_4 = "name";
    static final String COLUMN_LABEL_5 = "surname";
    static final String COLUMN_LABEL_6 = "patronymic";
    static final String COLUMN_LABEL_7 = "phone";
    static final String COLUMN_LABEL_8 = "admin";
    private static AppUserDaoImpl instance;

    private static final String SELECT_USER_PASSWORD_BY_EMAIL
            = "select password from app_user where email =?";
    private static final String SELECT_USER_BY_EMAIL
            = "select app_user_id, email, name, surname, patronymic,"
            + "phone, admin from app_user where email =?";
    private static final String CREATE_APP_USER_IF_EXISTS_OTHERWISE_UPDATE =
                    """
                    INSERT INTO app_user
                     (email, surname, name, patronymic, phone)
                     VALUES
                     (?, ?, ?, ?, ?)
                     ON DUPLICATE KEY UPDATE
                     surname =?,
                     name =?,
                     patronymic = ?,
                     phone =?
                      """;

    private static final Logger LOGGER
            = LogManager.getLogger(AppUserDaoImpl.class);

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
                     ConnectionPool.getInstance().giveOutConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            AppUserEntity userEntity = null;
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    userEntity = parseResultSetEntity(resultSet);
                }
            }
            return Optional.ofNullable(userEntity);
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to select data from table: "
                    + TABLE_NAME + " ! "
                    + exception.getMessage(), exception);
        }
    }

    @Override
    public Optional<String> selectPasswordByEmail(String token, String email)
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
                throw new DaoException("Unable to select data from table: "
                        + TABLE_NAME + " ! "
                        + exception.getMessage(), exception);
            }
        } else {
            LOGGER.warn("Unknown access for dao! "
                    + ". Used token : " + token);
            throw new DaoException("Unknown access for service! . Access denied!");
        }
    }

    @Override
    public boolean createIfExistsOtherwiseUpdate(
            AppUserEntity appUser) throws DaoException {
        if (appUser == null) {
            throw new DaoException("Parameter 'appUser' is null");
        }
        String email = appUser.getEmail();
        String surname = appUser.getSurname();
        String name = appUser.getName();
        String patronymic = appUser.getPatronymic();
        String phone = appUser.getPhone();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().giveOutConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            CREATE_APP_USER_IF_EXISTS_OTHERWISE_UPDATE);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, patronymic);
            preparedStatement.setString(5, phone);
            preparedStatement.setString(6, surname);
            preparedStatement.setString(7, name);
            preparedStatement.setString(8, patronymic);
            preparedStatement.setString(9, phone);
            connection.setAutoCommit(false);
            int updatedRowsQuantity = preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            ConnectionPool.getInstance().getBackConnection(connection);
            LOGGER.info(updatedRowsQuantity + " rows was updated in table "
                    + TABLE_NAME + " . "
                    + "Used entity: " + appUser);
        } catch (ConnectionException | SQLException exception) {
            try {
                if (connection != null) {
                    connection.rollback();
                    LOGGER.trace("Unsuccessfully");
                    return false;
                }else {
                    LOGGER.warn("Connection is null");
                }
            } catch (SQLException throwables) {
                LOGGER.error("Unable to rollback transaction! "
                        + throwables.getMessage(), throwables);
            }
            throw new DaoException("Unable to update data in table: "
                    + TABLE_NAME + " ! "
                    + exception.getMessage(), exception);
        }
        LOGGER.trace("Successfully");
        return true;
    }

    @Override
    public AppUserEntity parseResultSetEntity(ResultSet resultSet)
            throws SQLException {
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
