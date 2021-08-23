package com.gerard.site.dao;

import com.gerard.site.connection.ConnectionException;
import com.gerard.site.connection.ConnectionPool;
import com.gerard.site.entity.RequestAndAppUserAndDog;
import com.gerard.site.entity.DogEntity;
import com.gerard.site.entity.RequestEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestDao extends AbstractDao<RequestEntity> {

    private static RequestDao instance;
    static final String TABLE_NAME = "gerard.request";
    static final String COLUMN_LABEL_1 = "request_id";
    static final String COLUMN_LABEL_2 = "request_status";
    static final String COLUMN_LABEL_3 = "request_type";
    static final String COLUMN_LABEL_4 = "content";
    static final String COLUMN_LABEL_5 = "date_fact";
    static final String COLUMN_LABEL_6 = "reply";
    static final String COLUMN_LABEL_7 = "dog_id";
    static final String COLUMN_LABEL_8 = "email";
    static final Logger LOGGER = LogManager.getLogger(RequestDao.class);
    private static final String SELECT_ALL_REQUESTS =
            "select request_id, request_status, request_type, content,"
                    + "date_fact, reply, dog_id, email from request";

    private static final String SELECT_ALL_REQUESTS_AND_APP_USERS_AND_DOGS =
            "select request_id, request_status, request_type, content,"
                    + "date_fact, reply, request.email, "
                    + "app_user.name," + "app_user.surname, app_user.patronymic, "
                    + "app_user.phone, "
                    + "dog.nickname, " + "dog.fullname ," + " dog.dog_sex, " + " dog.birthday, "
                    + "dog.avatar_photo_path "
                    + "from request "
                    + "inner join app_user "
                    + "on request.email = app_user.email "
                    + "inner join dog "
                    + "on dog.dog_id = request.dog_id";
    private RequestDao() {
        super();
    }

    public static RequestDao getInstance() {
        if (instance == null) {
            instance = new RequestDao();
        }
        return instance;
    }

    @Override
    public Optional<RequestEntity> find(RequestEntity entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<RequestEntity> selectAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().giveOutConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_REQUESTS_AND_APP_USERS_AND_DOGS);
                List<RequestEntity> selectedUsers = new ArrayList<>();
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {
                        selectedUsers.add(parseResultSet(resultSet));
                    }
                }
                return selectedUsers;
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: "
                    + TABLE_NAME + " ! "
                    + "Reason: " + exception.getMessage(), exception);
        }
    }

    public List<RequestAndAppUserAndDog> selectAllRequestsAndAppUserAndDog() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().giveOutConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_REQUESTS_AND_APP_USERS_AND_DOGS);
                List<RequestAndAppUserAndDog> selectedUsers = new ArrayList<>();
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {
                        selectedUsers.add(parseResultSetRequestAndAppUserAndDog(resultSet));
                    }
                }
                return selectedUsers;
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: "
                    + TABLE_NAME + " ! "
                    + "Reason: " + exception.getMessage(), exception);
        }
    }

    public RequestAndAppUserAndDog parseResultSetRequestAndAppUserAndDog(
            ResultSet resultSet) throws SQLException {
        int requestId = resultSet.getInt(COLUMN_LABEL_1);
        RequestEntity.RequestStatus requestStatus =
                RequestEntity.RequestStatus.valueOf(
                        resultSet.getString(COLUMN_LABEL_2).toUpperCase());
        RequestEntity.RequestType requestType =
                RequestEntity.RequestType.valueOf(
                        resultSet.getString(COLUMN_LABEL_3).toUpperCase());
        String content = resultSet.getString(COLUMN_LABEL_4);
        Date dateFact = resultSet.getDate(COLUMN_LABEL_5);
        String reply = resultSet.getString(COLUMN_LABEL_6);
        String email = resultSet.getString(COLUMN_LABEL_8);
        String name = resultSet.getString(AppUserDao.COLUMN_LABEL_4);
        String surname = resultSet.getString(AppUserDao.COLUMN_LABEL_5);
        String patronymic = resultSet.getString(AppUserDao.COLUMN_LABEL_6);
        String phone = resultSet.getString(AppUserDao.COLUMN_LABEL_7);
        String nickname = resultSet.getString(DogDao.COLUMN_LABEL_3);
        String fullname = resultSet.getString(DogDao.COLUMN_LABEL_4);
        DogEntity.DogSex dogSex = DogEntity.DogSex.valueOf(resultSet.getString(DogDao.COLUMN_LABEL_2).toUpperCase());
        Date birthday = resultSet.getDate(DogDao.COLUMN_LABEL_5);
        String avatarPhotoPath = resultSet.getString(DogDao.COLUMN_LABEL_6);
        RequestAndAppUserAndDog requestAndAppUserAndDog = new RequestAndAppUserAndDog.Builder()
                .requestId(requestId)
                .requestStatus(requestStatus)
                .requestType(requestType)
                .content(content)
                .dateFact(dateFact)
                .reply(reply)
                .email(email)
                .name(name)
                .phone(phone)
                .surname(surname)
                .patronymic(patronymic)
                .nickname(nickname)
                .fullname(fullname)
                .dogSex(dogSex)
                .birthday(birthday)
                .avatarPhotoPath(avatarPhotoPath)
                .build();
        return requestAndAppUserAndDog;
    }

    @Override
    public boolean update(RequestEntity entity, RequestEntity newEntityVersion) throws DaoException {
        return false;
    }

    @Override
    public Optional<RequestEntity> create(RequestEntity entity) throws DaoException {
        if (entity == null) {
            throw new DaoException("Parameter 'entity' is null");
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public RequestEntity parseResultSet(ResultSet resultSet) throws SQLException {
        int requestId = resultSet.getInt(COLUMN_LABEL_1);
        RequestEntity.RequestStatus requestStatus =
                RequestEntity.RequestStatus.valueOf(
                        resultSet.getString(COLUMN_LABEL_2).toUpperCase());
        RequestEntity.RequestType requestType =
                RequestEntity.RequestType.valueOf(
                        resultSet.getString(COLUMN_LABEL_3).toUpperCase());
        String content = resultSet.getString(COLUMN_LABEL_4);
        Date dateFact = resultSet.getDate(COLUMN_LABEL_5);
        String reply = resultSet.getString(COLUMN_LABEL_6);
        int dogId = resultSet.getInt(COLUMN_LABEL_7);
        String email = resultSet.getString(COLUMN_LABEL_8);
        RequestEntity requestEntity = new RequestEntity.Builder()
                .id(requestId)
                .requestStatus(requestStatus)
                .requestType(requestType)
                .content(content)
                .dateFact(dateFact)
                .reply(reply)
                .dogId(dogId)
                .email(email)
                .build();
        return requestEntity;
    }
}
