package com.gerard.site.service.impl;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.dao.impl.PhotoDaoImpl;
import com.gerard.site.service.view.Photo;
import com.gerard.site.service.entity.PhotoEntity;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.PhotoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PhotoServiceImpl implements PhotoService {
    private static PhotoServiceImpl instance;
    private static final String PEDIGREE_FOLDER_NAME = "pedigrees";
    private static final String AVATAR_FOLDER_NAME = "avatars";
    private static final String PHOTO_FOLDER_NAME = "photos";
    private static final Logger LOGGER = LogManager.getLogger(PhotoServiceImpl.class);

    private PhotoServiceImpl() {
    }

    public static PhotoServiceImpl getInstance() {
        if (instance == null) {
            instance = new PhotoServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Photo> provideAllPhotosForView() throws ServiceException {
        try {
            List<Photo> allPhotosForView =
                    PhotoDaoImpl.getInstance().selectAllPhotosAndDogs();
            return allPhotosForView;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }

    @Override
    public List<PhotoEntity> provideAllPhotosForAdmin() throws ServiceException {
        try {
            List<PhotoEntity> allPhotosForAmin = PhotoDaoImpl.getInstance().selectAll();
            return allPhotosForAmin;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }
}
