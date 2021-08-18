package com.gerard.site.dao;

import com.gerard.site.connection.ConnectionException;
import com.gerard.site.connection.ConnectionPool;
import com.gerard.site.entity.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.gerard.site.entity.UserEntity.AppUserRole;
import static com.gerard.site.entity.UserEntity.AppUserStatus;
import static com.gerard.site.entity.UserEntity.AppUserSex;

public class AppUserDao extends AbstractDao<UserEntity> {
    //todo check methods for null args
    private static AppUserDao instance;
    private static final String TABLE_NAME = "gerard.app_user";
    private static final String COLUMN_LABEL_1 = "app_user_id";
    private static final String COLUMN_LABEL_2 = "app_user_status";
    private static final String COLUMN_LABEL_3 = "app_user_role";
    private static final String COLUMN_LABEL_4 = "email";
    private static final String COLUMN_LABEL_5 = "password";
    private static final String COLUMN_LABEL_6 = "name";
    private static final String COLUMN_LABEL_7 = "surname";
    private static final String COLUMN_LABEL_8 = "patronymic";
    private static final String COLUMN_LABEL_9 = "phone";
    private static final String COLUMN_LABEL_10 = "app_user_sex";
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
    public UserEntity findRecord(UserEntity user) throws DaoException {
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
                    UserEntity selectedUser = null;
                    while (resultSet.next()) {
                        selectedUser = parseResultSet(resultSet);
                    }
                    return selectedUser;
                } else {
                    LOGGER.info("No records were found in table: "
                            + TABLE_NAME + ". "
                            + "Used next columns values: "
                            + TABLE_NAME + "." + COLUMN_LABEL_4 + " : " + email
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
    public List<UserEntity> selectAllRecords() throws DaoException {
        final String selectAllUsers = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = ConnectionPool.getInstance().giveOutConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(selectAllUsers);
                if (resultSet.isBeforeFirst()) {
                    List<UserEntity> selectedUsers = new ArrayList<>();
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

    @Override
    public boolean update(UserEntity user, UserEntity newUserVersion) throws DaoException {
        if (user == null) {
            throw new DaoException("Parameter 'user' is null");
        }
        if (newUserVersion == null) {
            throw new DaoException("Parameter 'newUserVersion' is null");
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(UserEntity user) throws DaoException {
        if (user == null) {
            throw new DaoException("Parameter 'user' is null");
        }
        throw new UnsupportedOperationException();
    }

    public String selectUserPasswordByEmail(String token, String email)
            throws DaoException {
        String passwordToken = "electronDance18";
        if (passwordToken.equals(token)) {
            if (email == null) {
                throw new DaoException("Parameter 'email' is null");
            }
            final String selectUserPasswordByEmail
                    = "SELECT " + COLUMN_LABEL_5
                    + " FROM " + TABLE_NAME + " where email =?";
            try (Connection connection = ConnectionPool.getInstance().giveOutConnection()) {
                try (PreparedStatement preparedStatement
                        = connection.prepareStatement(selectUserPasswordByEmail)) {
                    preparedStatement.setString(1, email);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.isBeforeFirst()) {
                        String password = null;
                        while (resultSet.next()) {
                            password = resultSet.getString(COLUMN_LABEL_5);
                        }
                        return password;
                    } else {
                        LOGGER.info("No records were found in table: "
                                + TABLE_NAME + ". "
                                + "Used next columns values: "
                                + TABLE_NAME + "." + COLUMN_LABEL_4 + " : " + email
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
    public UserEntity parseResultSet(ResultSet resultSet) throws SQLException {
        int appUserId = resultSet.getInt(COLUMN_LABEL_1);
        AppUserStatus appUserStatus = AppUserStatus.valueOf(
                resultSet.getString(COLUMN_LABEL_2).toUpperCase());
        AppUserRole appUserRole = AppUserRole.valueOf(
                resultSet.getString(COLUMN_LABEL_3).toUpperCase());
        String email = resultSet.getString(COLUMN_LABEL_4);
        String name = resultSet.getString(COLUMN_LABEL_6);
        String surname = resultSet.getString(COLUMN_LABEL_7);
        String patronymic = resultSet.getString(COLUMN_LABEL_8);
        int phone = resultSet.getInt(COLUMN_LABEL_9);
        AppUserSex appUserSex = AppUserSex.valueOf(
                resultSet.getString(COLUMN_LABEL_10).toUpperCase());
        UserEntity user = new UserEntity.Builder()
                .id(appUserId)
                .appUserStatus(appUserStatus)
                .appUserRole(appUserRole)
                .email(email)
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .phone(phone)
                .appUserSex(appUserSex)
                .build();
        return user;
    }
}
