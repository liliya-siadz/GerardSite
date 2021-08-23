package com.gerard.site.service;


import com.gerard.site.dao.DaoException;
import com.gerard.site.entity.RequestAndAppUserAndDog;
import com.gerard.site.exception.ServiceException;

import java.util.List;

public interface RequestService {

    List<RequestAndAppUserAndDog> provideAllRequests() throws DaoException, ServiceException;
    List<RequestAndAppUserAndDog> provideAllPendingRequests() throws ServiceException;
}
