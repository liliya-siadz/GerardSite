package com.gerard.site.dao.impl;

import com.gerard.site.service.entity.RequestEntity;
import com.gerard.site.service.view.admin.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.Optional;

import static org.testng.Assert.*;

public class RequestDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(DogDaoImplTest.class);

    @DataProvider(name = "requestToCreate")
    public Object[][] requestToCreate() {
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setEmail("sidelnikova.liliya2.@gmail.com");
        requestEntity.setContent("Запрос №6");
        requestEntity.setDateFact(Date.valueOf("2021-08-12"));
        requestEntity.setDogId(5);
        return new Object[][]{
                {requestEntity},
        };
    }

    @Test(dataProvider = "requestToCreate")
    public void testCreate(RequestEntity requestEntity) throws DaoException {
        boolean actual =
                RequestDaoImpl.getInstance().create(requestEntity);
        assertTrue(actual);
    }

    @Test
    public void testSetRequestStatusToAccepted() throws DaoException {
        boolean actual = RequestDaoImpl.getInstance().setRequestStatusToRejected(7);
        assertTrue(actual);
    }

    @Test
    public void testSetRequestStatusToRejected() throws DaoException {
        boolean actual = RequestDaoImpl.getInstance().setRequestStatusToAccepted(8);
        assertTrue(actual);
    }

    @Test
    public void testFindRequestByPK() throws DaoException {
        Optional<Request> selectedRequestByPk
                = RequestDaoImpl.getInstance().findRequestByPK(3);
        LOGGER.info(selectedRequestByPk);
        assertTrue(selectedRequestByPk.isPresent());
    }
}