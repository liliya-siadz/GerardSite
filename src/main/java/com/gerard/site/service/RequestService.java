package com.gerard.site.service;


import com.gerard.site.service.entity.RequestAndAppUserAndDog;

import java.util.List;

public interface RequestService {

    List<RequestAndAppUserAndDog> provideAllRequests() throws ServiceException;
    List<RequestAndAppUserAndDog> provideAllPendingRequests() throws ServiceException;
}
