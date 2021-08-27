package com.gerard.site.service.impl;

import com.gerard.site.dao.impl.AppUserDaoImpl;
import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.dao.impl.RequestDaoImpl;
import com.gerard.site.service.entity.AppUserEntity;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.view.admin.Request;
import com.gerard.site.service.entity.RequestEntity;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.RequestService;
import com.gerard.site.service.util.mail.AppMailMessage;
import com.gerard.site.service.util.mail.AppMailMessageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RequestServiceImpl implements RequestService {
    private static RequestServiceImpl instance;
    private static final Logger LOGGER =
            LogManager.getLogger(RequestServiceImpl.class);

    private RequestServiceImpl() {
    }

    public static RequestServiceImpl getInstance() {
        if (instance == null) {
            instance = new RequestServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Request> provideAllRequestsForAdmin()
            throws ServiceException {
        try {
            List<Request> requestsAndAppUserAndDogList
                    = RequestDaoImpl.getInstance()
                    .selectAllRequests();
            return requestsAndAppUserAndDogList;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }

    @Override
    public List<Request> provideAllPendingRequests()
            throws ServiceException {
        try {
            List<Request> requestsAndAppUserAndDogList
                    = RequestDaoImpl.getInstance()
                    .selectAllRequests();
            List<Request> pendingRequestsAndAppUserAndDogList =
                    requestsAndAppUserAndDogList.stream()
                            .filter(row -> row.getRequestStatus()
                                    .equals(RequestEntity.RequestStatus.PENDING))
                            .toList();
            return pendingRequestsAndAppUserAndDogList;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }

    @Override
    public boolean sendRequest(DogEntity dogEntity,
                               RequestEntity requestEntity,
                               AppUserEntity appUserEntity)
            throws ServiceException {
        try {
            AppUserDaoImpl.getInstance()
                    .createIfExistsOtherwiseUpdate(appUserEntity);
            boolean isRequestCreated =
                    RequestDaoImpl.getInstance().create(requestEntity);
            boolean isMessageWasSent = false;
            if(isRequestCreated) {
               AppMailMessage appMailMessageForAdmin =
                       AppMailMessageFactory.createAdminMessage(
                               dogEntity, requestEntity, appUserEntity);
                isMessageWasSent = appMailMessageForAdmin.send();
           }
           return isRequestCreated && isMessageWasSent;
        } catch (DaoException exception) {
            LOGGER.error("Unable to send request ! "
                    + "Request :" + requestEntity
                    + "App user: " + appUserEntity + " . "
                    + exception.getMessage(), exception);
            throw new ServiceException("Unable to send request ! "
                    + "Request :" + requestEntity
                    + "App user: " + appUserEntity + " . "
                    + exception.getMessage(), exception);
        }
    }
}
