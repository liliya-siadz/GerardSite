package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.service.view.admin.Request;

import java.util.List;

public interface RequestDao {
    List<Request> selectAllRequests() throws DaoException;
}
