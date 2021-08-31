package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.service.entity.RequestEntity;
import com.gerard.site.service.view.admin.Request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RequestDao {
    List<Request> selectAllRequests() throws DaoException;

    boolean setRequestStatusToAccepted(int requestId) throws DaoException;

    boolean setRequestStatusToRejected(int requestId) throws DaoException;

    Optional<Request> findRequestByPK(int requestId) throws DaoException;

    boolean create(RequestEntity entity) throws DaoException;

    Request parseResultSetRequest(ResultSet resultSet) throws SQLException;
}
