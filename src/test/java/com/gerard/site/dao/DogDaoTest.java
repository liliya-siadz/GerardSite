package com.gerard.site.dao;

import com.gerard.site.entity.DogEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DogDaoTest {
    private static final Logger LOGGER = LogManager.getLogger(PhotoDaoTest.class);

    @Test
    public void testFind() throws DaoException {
        DogEntity existingDogEntity = new DogEntity();
        existingDogEntity.setId(1);
        DogEntity dogEntity = DogDao.getInstance().find(existingDogEntity);
        LOGGER.info(dogEntity);
        assertNotNull(dogEntity);
    }
}