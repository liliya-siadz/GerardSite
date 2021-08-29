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

import static org.testng.Assert.*;

public class RequestServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(PhotoServiceImplTest.class);

    @Test
    public void testProvideAllPendingRequests() throws ServiceException {
        List<Request> requestsAndAppUserAndDogList =
                RequestServiceImpl.getInstance().provideAllPendingRequests();
        LOGGER.info(requestsAndAppUserAndDogList);
        assertFalse(requestsAndAppUserAndDogList.isEmpty());
    }

    @DataProvider(name = "dataProviderSendRequest")
    public Object[][] dataProviderSendRequest() throws DaoException {
        DogEntity bottleDogEntity = new DogEntity();
        bottleDogEntity.setId(1);
        DogEntity existingDogEntity = DogDaoImpl.getInstance().find(bottleDogEntity).get();

        AppUserEntity existingUser = new AppUserEntity();
        existingUser.setEmail("sidelnikova.liliya2.@gmail.com");
        existingUser.setSurname("Сидельникова");
        existingUser.setName("Лилия");
        existingUser.setPatronymic("");
        existingUser.setPhone("290000000");

        RequestEntity request = new RequestEntity();
        request.setEmail("sidelnikova.liliya2.@gmail.com");
        request.setContent("Запрос №6");
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
        boolean actual = RequestServiceImpl.getInstance().sendRequest(
                dogEntity, requestEntity,  appUserEntity);
        assertTrue(actual);
    }

    @Test
    public void testProvideAllRequests() throws ServiceException {
        List<Request> requests = RequestServiceImpl.getInstance().provideAllNotPendingRequests();
        LOGGER.info(requests);
        assertFalse(requests.isEmpty());
    }

    @Test
    public void testTestProvideAllPendingRequests() throws ServiceException {
        List<Request> pendingRequests
                = RequestServiceImpl.getInstance().provideAllPendingRequests();
        LOGGER.info(pendingRequests);
        assertFalse(pendingRequests.isEmpty());
    }
}