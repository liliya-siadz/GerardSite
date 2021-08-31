package com.gerard.site.dao.impl;

import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.view.Dog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DogDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(DogDaoImplTest.class);

    @Test(enabled = false)
    public void testFind() throws DaoException {
        DogEntity existingDogEntity = new DogEntity();
        existingDogEntity.setId(1);
        Optional<DogEntity> dogEntity = DogDaoImpl.getInstance().find(existingDogEntity);
        LOGGER.info(dogEntity);
        assertTrue(dogEntity.isPresent());
    }

    @Test(enabled = false)
    public void testSelectAllDogsWithPhotos() throws DaoException {
        List<Dog> dogsWithPhotos = DogDaoImpl.getInstance().selectAllDogsWithPhotos();
        LOGGER.info(dogsWithPhotos);
        assertFalse(dogsWithPhotos.isEmpty());
    }
}