package com.gerard.site.service.impl;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.dao.impl.RequestDaoImpl;
import com.gerard.site.service.entity.RequestAndAppUserAndDog;
import com.gerard.site.service.entity.RequestEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RequestServiceImpl implements RequestService {
    private static RequestServiceImpl instance;
    private static final Logger LOGGER = LogManager.getLogger(RequestServiceImpl.class);

    private RequestServiceImpl(){
    }

    public static RequestServiceImpl getInstance() {
        if(instance == null){
            instance = new RequestServiceImpl();
        }
        return instance;
    }

    @Override
    public List<RequestAndAppUserAndDog> provideAllRequests() throws ServiceException {
        try {
            List<RequestAndAppUserAndDog> requestsAndAppUserAndDogList
                    = RequestDaoImpl.getInstance().selectAllRequestsAndAppUserAndDog();
            return requestsAndAppUserAndDogList;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }

    @Override
    public List<RequestAndAppUserAndDog> provideAllPendingRequests() throws ServiceException {
        try {
            List<RequestAndAppUserAndDog> requestsAndAppUserAndDogList
                    = RequestDaoImpl.getInstance().selectAllRequestsAndAppUserAndDog();
            List<RequestAndAppUserAndDog> pendingRequestsAndAppUserAndDogList =
                    requestsAndAppUserAndDogList.stream()
                    .filter(row->row.getRequestStatus()
                            .equals(RequestEntity.RequestStatus.PENDING))
                    .toList();
            return pendingRequestsAndAppUserAndDogList;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }
}
