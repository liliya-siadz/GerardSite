package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.dao.impl.DogDaoImpl;
import com.gerard.site.service.entity.DogEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

public class DogDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(PhotoDaoImplTest.class);

    @Test
    public void testFind() throws DaoException {
        DogEntity existingDogEntity = new DogEntity();
        existingDogEntity.setId(1);
        Optional<DogEntity> dogEntity = DogDaoImpl.getInstance().find(existingDogEntity);
        LOGGER.info(dogEntity);
        assertTrue(dogEntity.isPresent());
    }
}