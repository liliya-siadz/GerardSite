package com.gerard.site.service.impl;

import com.gerard.site.dto.RequestAndAppUserWithMessageDto;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.form.RequestForm;
import com.gerard.site.service.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

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
    public Optional<RequestAndAppUserWithMessageDto> createRequest(int dogId, RequestForm requestForm)
    throws ServiceException {
        //call  RequestEntity requestEntity =
        // 1 RequestDao.createRequest(RequestEntity requestEntity)
        //          if request was created:
        // 2
        //  add message and create request with message dto
        //            //RequestAndAppUserWithMessage result
        //            // = createRequest(Integer dogId, RequestForm requestForm)
        //
        throw new UnsupportedOperationException();
    }
}
