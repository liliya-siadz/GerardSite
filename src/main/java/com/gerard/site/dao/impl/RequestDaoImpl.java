package com.gerard.site.dao.impl;

import com.gerard.site.dao.AbstractDao;
import com.gerard.site.dao.RequestDao;
import com.gerard.site.dao.connection.ConnectionException;
import com.gerard.site.dao.connection.ConnectionPool;
import com.gerard.site.service.view.admin.Request;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.entity.RequestEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RequestDaoImpl extends AbstractDao<RequestEntity>
        implements RequestDao {
    private static RequestDaoImpl instance;
    static final String TABLE_NAME = "gerard.request";
    static final String COLUMN_LABEL_1 = "request_id";
    static final String COLUMN_LABEL_2 = "request_status";
    static final String COLUMN_LABEL_3 = "request_type";
    static final String COLUMN_LABEL_4 = "content";
    static final String COLUMN_LABEL_5 = "date_fact";
    static final String COLUMN_LABEL_6 = "reply";
    static final String COLUMN_LABEL_7 = "dog_id";
    static final String COLUMN_LABEL_8 = "email";
    static final Logger LOGGER = LogManager.getLogger(RequestDaoImpl.class);
    private static final String SELECT_ALL_REQUESTS_AND_APP_USERS_AND_DOGS =
            """
                    select request_id, request_status, request_type, content,
                    date_fact, reply, request.email,
                    app_user.name, app_user.surname, app_user.patronymic,
                    app_user.phone,
                    dog.nickname, dog.fullname , dog.dog_sex, dog.birthday,
                    photo.photo_path
                    from request
                    inner join app_user
                    on request.email = app_user.email
                    inner join dog
                    on dog.dog_id = request.dog_id
                    inner join photo on dog.dog_id = photo.dog_id
                    where photo.photo_type ='avatar'
                    order by request_status asc, date_fact desc
                    """;

    private static final String CREATE_NEW_REQUEST =
            "insert into request (email, content,dog_id, date_fact)"
                    + "values(?,?,?,?)";

    private static final String SET_REQUEST_STATUS_TO_ACCEPTED =
            """
                    update request set request_status = 'accepted',
                    reply='Your request was accepted. Please contact us by our mobile phone.' 
                    where request_id =? 
                    """;

    private static final String SET_REQUEST_STATUS_TO_REJECTED =
            """
                    update request set request_status = 'rejected',
                    reply='Sorry. Your request was rejected now. 
                    We put it to the end ot the queue. May be later we accept it ' 
                    where request_id =? 
                    """;

    private static final String SELECT_REQUEST_BY_PK =
            """
                    select request_id, request_status, request_type, content,
                        date_fact, reply, request.email,
                        app_user.name, app_user.surname, app_user.patronymic,
                        app_user.phone,
                        dog.nickname, dog.fullname , dog.dog_sex, dog.birthday,
                        photo.photo_path
                        from request
                        inner join app_user
                        on request.email = app_user.email
                        inner join dog
                        on dog.dog_id = request.dog_id
                        inner join photo on dog.dog_id = photo.dog_id
                        where request_id = ?
                    """;

    private RequestDaoImpl() {
        super();
    }

    public static RequestDaoImpl getInstance() {
        if (instance == null) {
            instance = new RequestDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Request> selectAllRequests() throws DaoException {
        try (Connection connection =
                     ConnectionPool.getInstance().giveOutConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    SELECT_ALL_REQUESTS_AND_APP_USERS_AND_DOGS);
            if (resultSet.isBeforeFirst()) {
                List<Request> selectedUsers = new ArrayList<>();
                while (resultSet.next()) {
                    selectedUsers.add(parseResultSetRequest(resultSet));
                }
                return selectedUsers;
            } else {
                return Collections.emptyList();
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: "
                    + TABLE_NAME + " ! "
                    + "Reason: " + exception.getMessage(), exception);
        }
    }

    @Override
    public boolean setRequestStatusToAccepted(int requestId) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().giveOutConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            SET_REQUEST_STATUS_TO_ACCEPTED);
            preparedStatement.setInt(1, requestId);
            connection.setAutoCommit(false);
            int updatedRowsQuantity = preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            ConnectionPool.getInstance().getBackConnection(connection);
            LOGGER.info(updatedRowsQuantity
                    + " rows was updated in table " + TABLE_NAME);
        } catch (ConnectionException | SQLException exception) {
            try {
                if (connection != null) {
                    connection.rollback();
                    LOGGER.trace("Unsuccessfully");
                    return false;
                } else {
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
    public boolean setRequestStatusToRejected(int requestId) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().giveOutConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            SET_REQUEST_STATUS_TO_REJECTED);
            preparedStatement.setInt(1, requestId);
            connection.setAutoCommit(false);
            int updatedRowsQuantity = preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            ConnectionPool.getInstance().getBackConnection(connection);
            LOGGER.info(updatedRowsQuantity
                    + " rows was updated in table " + TABLE_NAME);
        } catch (ConnectionException | SQLException exception) {
            try {
                if (connection != null) {
                    connection.rollback();
                    LOGGER.trace("Unsuccessfully");
                    return false;
                } else {
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
    public Optional<Request> findRequestByPK(int requestId) throws DaoException {
        try (Connection connection =
                     ConnectionPool.getInstance().giveOutConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_REQUEST_BY_PK)) {
            preparedStatement.setInt(1, requestId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Request selectedRequest = null;
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    selectedRequest = parseResultSetRequest(resultSet);
                }
            }
            return Optional.ofNullable(selectedRequest);
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: "
                    + TABLE_NAME + " ! "
                    + exception.getMessage(), exception);
        }
    }

    @Override
    public Request parseResultSetRequest(ResultSet resultSet) throws SQLException {
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
        String name = resultSet.getString(AppUserDaoImpl.COLUMN_LABEL_4);
        String surname = resultSet.getString(AppUserDaoImpl.COLUMN_LABEL_5);
        String patronymic = resultSet.getString(AppUserDaoImpl.COLUMN_LABEL_6);
        String phone = resultSet.getString(AppUserDaoImpl.COLUMN_LABEL_7);
        String nickname = resultSet.getString(DogDaoImpl.COLUMN_LABEL_3);
        String fullname = resultSet.getString(DogDaoImpl.COLUMN_LABEL_4);
        DogEntity.DogSex dogSex =
                DogEntity.DogSex.valueOf(
                        resultSet.getString(DogDaoImpl.COLUMN_LABEL_2).toUpperCase());
        Date birthday = resultSet.getDate(DogDaoImpl.COLUMN_LABEL_5);
        String avatarPhotoPath = resultSet.getString(PhotoDaoImpl.COLUMN_LABEL_2);
        Request request =
                new Request.Builder()
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
        return request;
    }

    @Override
    public boolean create(RequestEntity entity) throws DaoException {
        if (entity == null) {
            throw new DaoException("Parameter 'entity' is null");
        }
        String email = entity.getEmail();
        String content = entity.getContent();
        int dogId = entity.getDogId();
        Date dateFact = entity.getDateFact();
        Connection connection = null;
        try {
            connection =
                    ConnectionPool.getInstance().giveOutConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(CREATE_NEW_REQUEST);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, content);
            preparedStatement.setInt(3, dogId);
            preparedStatement.setDate(4, dateFact);
            connection.setAutoCommit(false);
            int updatedRowsQuantity = preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            ConnectionPool.getInstance().getBackConnection(connection);
            LOGGER.info(updatedRowsQuantity
                    + " rows was updated in table " + TABLE_NAME
                    + "Used entity: " + entity);
            return (updatedRowsQuantity >= 1);
        } catch (ConnectionException | SQLException exception) {
            try {
                if (connection != null) {
                    connection.rollback();
                    LOGGER.trace("Unsuccessfully");
                    return false;
                } else {
                    LOGGER.warn("Connection is null");
                }
            } catch (SQLException throwables) {
                LOGGER.error("Unable to rollback transaction! "
                        + throwables.getMessage(), throwables);
            }
            throw new DaoException("Unable to get data from table: "
                    + TABLE_NAME + " ! "
                    + exception.getMessage(), exception);
        }
    }

    @Override
    protected RequestEntity parseResultSetEntity(ResultSet resultSet) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<RequestEntity> find(RequestEntity user) throws DaoException {
        throw new UnsupportedOperationException();
    }
}
