package com.gerard.site.dao;

import com.gerard.site.entity.RequestAndAppUserAndDog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;

public class RequestDaoTest {
    private static final Logger LOGGER = LogManager.getLogger(RequestDaoTest.class);

    @Test
    public void testSelectAllRequestsAndAppUserAndDog() throws DaoException {
        List<RequestAndAppUserAndDog> requestsAndAppUserAndDogList =
                RequestDao.getInstance().selectAllRequestsAndAppUserAndDog();
        LOGGER.info(requestsAndAppUserAndDogList);
        assertFalse(requestsAndAppUserAndDogList.isEmpty());
    }
}