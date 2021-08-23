package com.gerard.site.service.impl;

import com.gerard.site.entity.RequestAndAppUserAndDog;
import com.gerard.site.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class RequestServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(PhotoServiceImplTest.class);

    @Test
    public void testProvideAllPendingRequests() throws ServiceException {
        List<RequestAndAppUserAndDog> requestsAndAppUserAndDogList =
                RequestServiceImpl.getInstance().provideAllPendingRequests();
        LOGGER.info(requestsAndAppUserAndDogList);
        assertFalse(requestsAndAppUserAndDogList.isEmpty());
    }
}