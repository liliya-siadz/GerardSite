package com.gerard.site.dao.impl;

import com.gerard.site.service.view.admin.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertTrue;

public class RequestDaoImplTest {
    private static final Logger LOGGER
            = LogManager.getLogger(RequestDaoImplTest.class);

    @Test(enabled = false)
    public void testSetRequestStatusToAccepted() throws DaoException {
        boolean actual = RequestDaoImpl.getInstance().setRequestStatusToRejected(7);
        assertTrue(actual);
    }

    @Test(enabled = false)
    public void testSetRequestStatusToRejected() throws DaoException {
        boolean actual = RequestDaoImpl.getInstance().setRequestStatusToAccepted(8);
        assertTrue(actual);
    }

    @Test(enabled = false)
    public void testFindRequestByPK() throws DaoException {
        Optional<Request> selectedRequestByPk
                = RequestDaoImpl.getInstance().findRequestByPK(3);
        LOGGER.info(selectedRequestByPk);
        assertTrue(selectedRequestByPk.isPresent());
    }
}