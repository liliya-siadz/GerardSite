package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.service.entity.RequestAndAppUserAndDog;

import java.util.List;

public interface RequestDao {
    List<RequestAndAppUserAndDog> selectAllRequestsAndAppUserAndDog()
            throws DaoException;
}
