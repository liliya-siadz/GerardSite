package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.dao.impl.RequestDaoImpl;
import com.gerard.site.service.entity.RequestAndAppUserAndDog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;

public class RequestDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(RequestDaoImplTest.class);

    @Test
    public void testSelectAllRequestsAndAppUserAndDog() throws DaoException {
        List<RequestAndAppUserAndDog> requestsAndAppUserAndDogList =
                RequestDaoImpl.getInstance().selectAllRequestsAndAppUserAndDog();
        LOGGER.info(requestsAndAppUserAndDogList);
        assertFalse(requestsAndAppUserAndDogList.isEmpty());
    }
}