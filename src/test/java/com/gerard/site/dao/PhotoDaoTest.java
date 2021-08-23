package com.gerard.site.dao;

import com.gerard.site.entity.PhotoAndDog;
import com.gerard.site.entity.PhotoEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class PhotoDaoTest {
    private static final Logger LOGGER = LogManager.getLogger(PhotoDaoTest.class);

    @Test
    public void testSelectAllPhotosWithDogs() throws DaoException {
        List<PhotoAndDog> photoAndDogList = PhotoDao.getInstance().selectAllPhotosAndDogs();
        LOGGER.info(photoAndDogList);
        assertFalse(photoAndDogList.isEmpty());
    }

    @Test
    public void testSelectAll() throws DaoException {
        List<PhotoEntity> allPhotos =
                PhotoDao.getInstance().selectAll();
        LOGGER.info(allPhotos);
        assertFalse(allPhotos.isEmpty());
    }
}