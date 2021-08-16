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
    public UserEntity select(UserEntity entity) throws DaoException {
        final String selectUserByEmailAndPassword
                = "SELECT * FROM " + TABLE_NAME + " where email =? and password =?";
        String email = entity.getEmail();
        String password = entity.getPassword();
        try (Connection connection = ConnectionPool.getInstance().giveOutConnection()){
            try (PreparedStatement preparedStatement
                         = connection.prepareStatement(selectUserByEmailAndPassword)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    UserEntity selectedUser = null;
                    while (resultSet.next()) {
                        selectedUser = parseResultSet(resultSet);
                    }
                    return selectedUser;
                } else {
                    LOGGER.info("No records were found in table: " + TABLE_NAME + ". "
                            + "Used next columns values: "
                            + TABLE_NAME + "." + COLUMN_LABEL_4 + " : " + email
                            + " , "
                            + TABLE_NAME + "." + COLUMN_LABEL_5 + " : " + password + " .");
                    LOGGER.warn("Null will be returned");
                    return null;
                }
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: " + TABLE_NAME + " ! "
                    + "Reason: " + exception.getMessage(), exception);
        }
    }

    @Override
    public List<UserEntity> selectAll() throws DaoException {
        final String selectAllUsers = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = ConnectionPool.getInstance().giveOutConnection()){
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(selectAllUsers);
                if (resultSet.isBeforeFirst()) {
                    List<UserEntity> selectedUsers = new ArrayList<>();
                    while (resultSet.next()) {
                        selectedUsers.add(parseResultSet(resultSet));
                    }
                    return selectedUsers;
                } else {
                    LOGGER.info("No records were found in table: " + TABLE_NAME + ". ");
                    LOGGER.warn("Null will be returned");
                    return null;
                }
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: " + TABLE_NAME + " ! "
                    + "Reason: " + exception.getMessage(), exception);
        }
    }

    @Override
    public boolean update(UserEntity entity, UserEntity newEntityVersion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(UserEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity parseResultSet(ResultSet resultSet) throws SQLException {
        int appUserId = resultSet.getInt(COLUMN_LABEL_1);
        AppUserStatus appUserStatus = AppUserStatus.valueOf(
                resultSet.getString(COLUMN_LABEL_2).toUpperCase());
        AppUserRole appUserRole = AppUserRole.valueOf(
                resultSet.getString(COLUMN_LABEL_3).toUpperCase());
        String email = resultSet.getString(COLUMN_LABEL_4);
        String password = resultSet.getString(COLUMN_LABEL_5);
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
                .password(password)
                .patronymic(patronymic)
                .phone(phone)
                .appUserSex(appUserSex)
                .build();
        return user;
    }
}
