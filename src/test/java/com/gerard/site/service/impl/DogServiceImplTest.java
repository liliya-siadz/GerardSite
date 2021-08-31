package com.gerard.site.service.impl;

import com.gerard.site.service.ServiceException;
import com.gerard.site.service.view.Dog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;

public class DogServiceImplTest {
    private static final Logger LOGGER
            = LogManager.getLogger(DogServiceImplTest.class);

    @Test(enabled = false)
    public void testProvideAllDogsForView() throws ServiceException {
        List<Dog> allDogsForView
                = DogServiceImpl.getInstance().provideAllDogsForView();
        LOGGER.info(allDogsForView);
        assertFalse(allDogsForView.isEmpty());
    }

    @Test(enabled = false)
    public void testProvideAlPuppiesForView() throws ServiceException {
        List<Dog> alPuppiesForView
                = DogServiceImpl.getInstance().provideAllPuppiesForView();
        LOGGER.info(alPuppiesForView);
        assertFalse(alPuppiesForView.isEmpty());

    }

    @Test(enabled = false)
    public void testProvideAllDogsForAdmin() throws ServiceException {
        List<Dog> allDogsForAdmin
                = DogServiceImpl.getInstance().provideAllDogsForAdmin();
        LOGGER.info(allDogsForAdmin);
        assertFalse(allDogsForAdmin.isEmpty());
    }
}