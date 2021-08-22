package com.gerard.site.dao;

import com.gerard.site.dto.PhotoWithDog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class PhotoDaoTest {
    private static final Logger LOGGER = LogManager.getLogger(PhotoDaoTest.class);

    @Test
    public void testSelectAllPhotosWithDogs() throws DaoException {
        List<PhotoWithDog> photoWithDogList = PhotoDao.getInstance().selectAllPhotosWithDogs();
        LOGGER.info(photoWithDogList);
        assertNotNull(photoWithDogList);
    }
}