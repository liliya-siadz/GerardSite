package com.gerard.site.service.impl;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.dao.impl.DogDaoImpl;
import com.gerard.site.service.entity.AppUserEntity;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.view.admin.Request;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.entity.RequestEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RequestServiceImplTest {
    private static final Logger LOGGER
            = LogManager.getLogger(PhotoServiceImplTest.class);

    @Test(enabled = false)
    public void testProvideAllPendingRequests()
            throws ServiceException {
        List<Request> requestsAndAppUserAndDogList =
                RequestServiceImpl.getInstance().provideAllPendingRequests();
        LOGGER.info(requestsAndAppUserAndDogList);
        assertFalse(requestsAndAppUserAndDogList.isEmpty());
    }

    @DataProvider(name = "dataProviderSendRequest")
    public Object[][] dataProviderSendRequest() throws DaoException {
        DogEntity bottleDogEntity = new DogEntity();
        bottleDogEntity.setId(1);
        DogEntity existingDogEntity
                = DogDaoImpl.getInstance().find(bottleDogEntity).get();

        AppUserEntity existingUser = new AppUserEntity();
        existingUser.setEmail("vechni.sputnik@mail.ru");
        existingUser.setSurname("Vechni");
        existingUser.setName("Sputnik");
        existingUser.setPatronymic("");
        existingUser.setPhone("290000000");

        RequestEntity request = new RequestEntity();
        request.setEmail("vechni.sputnik@mail.ru");
        request.setContent("Запрос на питомца.");
        request.setDateFact(Date.valueOf("2021-08-12"));
        request.setDogId(1);

        return new Object[][]{
                {existingDogEntity, request, existingUser},
        };
    }

    @Test(dataProvider = "dataProviderSendRequest")
    public void testSendRequest(
            DogEntity dogEntity,
            RequestEntity requestEntity,
            AppUserEntity appUserEntity)
            throws ServiceException {
        boolean actual = RequestServiceImpl.getInstance()
                .sendRequest(dogEntity, requestEntity, appUserEntity);
        assertTrue(actual);
    }

    @Test(enabled = false)
    public void testTestProvideAllPendingRequests() throws ServiceException {
        List<Request> allPendingRequests
                = RequestServiceImpl.getInstance().provideAllPendingRequests();
        LOGGER.info(allPendingRequests);
        assertFalse(allPendingRequests.isEmpty());
    }
}