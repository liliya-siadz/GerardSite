package com.gerard.site.dao;

import com.gerard.site.dto.RequestAndAppUserWithMessageDto;
import com.gerard.site.entity.AppUserEntity;
import com.gerard.site.entity.RequestEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
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

    public Optional<RequestAndAppUserWithMessageDto> createRequestWithUser(RequestEntity entity, AppUserEntity appUserEntity) throws DaoException {
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
