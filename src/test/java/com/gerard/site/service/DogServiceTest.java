package com.gerard.site.service;

import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DogServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(DogServiceTest.class);

    @Test
    public void testProvideAllDogs() throws ServiceException {
        List<DogEntity> dogEntityList = DogService.getInstance().provideAllDogs();
        LOGGER.info(dogEntityList);
        assertNotNull(dogEntityList);
    }
}