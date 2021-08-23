package com.gerard.site.service;

import com.gerard.site.dto.PhotoAndDog;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.impl.PhotoServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class PhotoServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(PhotoServiceImplTest.class);

    @Test
    public void testProvideAllPhotosWithDogs() throws ServiceException {
        List<PhotoAndDog> allPhotosWithDogsName =  PhotoServiceImpl.getInstance().provideAllPhotosAndDogs();
        LOGGER.info(allPhotosWithDogsName);
        assertFalse(allPhotosWithDogsName.isEmpty());
    }
}