package com.gerard.site.service.impl;

import com.gerard.site.service.ServiceException;
import com.gerard.site.service.view.Dog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DogServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(DogServiceImplTest.class);

    @Test
    public void testProvideAllDogsForView() throws ServiceException {
        List<Dog> dogsForView = DogServiceImpl.getInstance().provideAllDogsForView();
        LOGGER.info(dogsForView);
        assertFalse(dogsForView.isEmpty());
    }

    @Test
    public void testProvideAlPuppiesForView() throws ServiceException {
        List<Dog> puppies = DogServiceImpl.getInstance().provideAlPuppiesForView();
        LOGGER.info(puppies);
        assertFalse(puppies.isEmpty());

    }

    @Test
    public void testProvideAllDogsForAdmin() throws ServiceException {
        List<Dog> dogsForAdmin = DogServiceImpl.getInstance().provideAllDogsForAdmin();
        LOGGER.info(dogsForAdmin);
        assertFalse(dogsForAdmin.isEmpty());
    }
}