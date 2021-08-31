package com.gerard.site.service.impl;

import com.gerard.site.service.view.Photo;
import com.gerard.site.service.entity.PhotoEntity;
import com.gerard.site.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;

public class PhotoServiceImplTest {
    private static final Logger LOGGER
            = LogManager.getLogger(PhotoServiceImplTest.class);

    @Test(enabled = false)
    public void provideAllPhotosForView() throws ServiceException {
        List<Photo> allPhotosForView =
                PhotoServiceImpl.getInstance().provideAllPhotosForView();
        LOGGER.info(allPhotosForView);
        assertFalse(allPhotosForView.isEmpty());
    }

    @Test(enabled = false)
    public void testProvideAllPhotos() throws ServiceException {
        List<PhotoEntity> allPhotos
                = PhotoServiceImpl.getInstance().provideAllPhotosForAdmin();
        LOGGER.info(allPhotos);
        assertFalse(allPhotos.isEmpty());
    }
}