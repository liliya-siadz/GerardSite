package com.gerard.site.service;

import com.gerard.site.dto.PhotoWithDog;
import com.gerard.site.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class PhotoServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(PhotoServiceTest.class);

    @Test
    public void testProvideAllPhotosWithDogs() throws ServiceException {
        List<PhotoWithDog> allPhotosWithDogsName =  PhotoService.getInstance().provideAllPhotosWithDogs();
        LOGGER.info(allPhotosWithDogsName);
        assertNotNull(allPhotosWithDogsName);
    }
}