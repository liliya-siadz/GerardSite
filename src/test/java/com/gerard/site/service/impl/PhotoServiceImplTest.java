package com.gerard.site.service.impl;

import com.gerard.site.entity.PhotoAndDog;
import com.gerard.site.entity.PhotoEntity;
import com.gerard.site.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class PhotoServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(PhotoServiceImplTest.class);

    @Test
    public void testProvideAllPhotosWithDogs() throws ServiceException {
        List<PhotoAndDog> allPhotosWithDogsName =  PhotoServiceImpl.getInstance().provideAllPhotosOfDogs();
        LOGGER.info(allPhotosWithDogsName);
        assertFalse(allPhotosWithDogsName.isEmpty());
    }

    @Test
    public void testProvideAllPhotos() throws ServiceException {
        List<PhotoEntity> allPhotos =  PhotoServiceImpl.getInstance().provideAllPhotos();
        LOGGER.info(allPhotos);
        assertFalse(allPhotos.isEmpty());
    }
}