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
import com.gerard.site.service.util.mail.NotificationFactory;
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
    public List<Request> provideAllNotPendingRequests()
            throws ServiceException {
        try {
            List<Request> requestsAndAppUserAndDogList
                    = RequestDaoImpl.getInstance()
                    .selectAllRequests();
            requestsAndAppUserAndDogList.removeIf(
                    request -> request.getRequestStatus()
                            == RequestEntity.RequestStatus.ACCEPTED);
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
            requestsAndAppUserAndDogList.removeIf(
                    request -> request.getRequestStatus()
                            != RequestEntity.RequestStatus.PENDING);
            return requestsAndAppUserAndDogList;
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
            if (isRequestCreated) {
                AppMailMessage appMailMessageForAdmin =
                        NotificationFactory.createNotificationForAdminNewRequest(
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

    @Override
    public boolean acceptRequest(int requestId) throws ServiceException {
        try {
            boolean isRequestWasAccepted
                    = RequestDaoImpl.getInstance()
                    .setRequestStatusToAccepted(requestId);
            if (isRequestWasAccepted) {
                Request requestToSendNotificationOn
                        = RequestDaoImpl.getInstance().findRequestByPK(requestId).get();
                AppMailMessage appMailMessageForAdmin =
                        NotificationFactory
                                .createClientNotificationForRequest(
                                        requestToSendNotificationOn);
                boolean isMessageWasSent = appMailMessageForAdmin.send();
                return isMessageWasSent;
            }
            return false;
        } catch (DaoException exception) {
            LOGGER.error("Unable to accept request ! "
                    + "Request id :" + requestId
                    + exception.getMessage(), exception);
            throw new ServiceException("Unable to accept request ! "
                    + "Request id :" + requestId
                    + exception.getMessage(), exception);
        }
    }

    @Override
    public boolean rejectRequest(int requestId) throws ServiceException {
        try {
            boolean isRequestWasRejected
                    = RequestDaoImpl.getInstance().setRequestStatusToRejected(requestId);

            //send email to user
            return isRequestWasRejected;
        } catch (DaoException exception) {
            LOGGER.error("Unable to reject request ! "
                    + "Request id :" + requestId
                    + exception.getMessage(), exception);
            throw new ServiceException("Unable to reject request ! "
                    + "Request id :" + requestId
                    + exception.getMessage(), exception);
        }
    }
}
