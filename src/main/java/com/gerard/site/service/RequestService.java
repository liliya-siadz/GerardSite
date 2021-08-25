package com.gerard.site.service;


import com.gerard.site.service.entity.AppUserEntity;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.entity.RequestAndAppUserAndDog;
import com.gerard.site.service.entity.RequestEntity;

import java.util.List;

public interface RequestService {

    List<RequestAndAppUserAndDog> provideAllRequests() throws ServiceException;

    List<RequestAndAppUserAndDog> provideAllPendingRequests() throws ServiceException;

    boolean sendRequest(DogEntity dogEntity,
                              RequestEntity requestEntity,
                              AppUserEntity appUserEntity)
            throws ServiceException;
}
