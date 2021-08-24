package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.dao.impl.PhotoDaoImpl;
import com.gerard.site.service.entity.PhotoAndDog;
import com.gerard.site.service.entity.PhotoEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class PhotoDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(PhotoDaoImplTest.class);

    @Test
    public void testSelectAllPhotosWithDogs() throws DaoException {
        List<PhotoAndDog> photoAndDogList = PhotoDaoImpl.getInstance().selectAllPhotosAndDogs();
        LOGGER.info(photoAndDogList);
        assertFalse(photoAndDogList.isEmpty());
    }

    @Test
    public void testSelectAll() throws DaoException {
        List<PhotoEntity> allPhotos =
                PhotoDaoImpl.getInstance().selectAll();
        LOGGER.info(allPhotos);
        assertFalse(allPhotos.isEmpty());
    }
}