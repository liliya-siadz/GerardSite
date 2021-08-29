package com.gerard.site.service;


import com.gerard.site.service.entity.AppUserEntity;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.view.admin.Request;
import com.gerard.site.service.entity.RequestEntity;

import java.util.List;

public interface RequestService {

    List<Request> provideAllNotPendingRequests() throws ServiceException;

    List<Request> provideAllPendingRequests() throws ServiceException;

    boolean sendRequest(DogEntity dogEntity,
                        RequestEntity requestEntity,
                        AppUserEntity appUserEntity) throws ServiceException;

    boolean acceptRequest(int requestId) throws ServiceException;
    boolean rejectRequest(int requestId) throws ServiceException;
}
