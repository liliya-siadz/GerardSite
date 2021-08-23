package com.gerard.site.service;

import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.impl.DogServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DogServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(DogServiceImplTest.class);

    @Test
    public void testProvideAllDogs() throws ServiceException {
        List<DogEntity> dogEntityList = DogServiceImpl.getInstance().provideAllDogs();
        LOGGER.info(dogEntityList);
        assertFalse(dogEntityList.isEmpty());
    }

    @Test
    public void testProvideAllPuppies() throws ServiceException {
        List<DogEntity> allPuppies = DogServiceImpl.getInstance().provideAllPuppies();
        LOGGER.info(allPuppies);
        assertFalse(allPuppies.isEmpty());
    }

}