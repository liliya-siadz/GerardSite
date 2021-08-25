package com.gerard.site.dao.impl;

import com.gerard.site.service.entity.DogEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

public class DogDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(PhotoDaoImplTest.class);

    @Test(enabled = false)
    public void testFind() throws DaoException {
        DogEntity existingDogEntity = new DogEntity();
        existingDogEntity.setId(1);
        Optional<DogEntity> dogEntity = DogDaoImpl.getInstance().find(existingDogEntity);
        LOGGER.info(dogEntity);
        assertTrue(dogEntity.isPresent());
    }
}