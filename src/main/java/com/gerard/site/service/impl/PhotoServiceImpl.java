package com.gerard.site.service.impl;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.dao.impl.PhotoDaoImpl;
import com.gerard.site.service.util.WebappImageDeleterUtil;
import com.gerard.site.service.view.Photo;
import com.gerard.site.service.entity.PhotoEntity;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.PhotoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PhotoServiceImpl implements PhotoService {
    private static PhotoServiceImpl instance;
    private static final Logger LOGGER
            = LogManager.getLogger(PhotoServiceImpl.class);

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
            List<Photo> photos =
                    PhotoDaoImpl.getInstance().selectAllPhotos();
            return photos;
        } catch (DaoException exception) {
            LOGGER.error( "Unable to provide information about photos! "
                    + exception.getMessage(), exception);
            throw new ServiceException(
                    "Unable to provide information about photos! "
                            + exception.getMessage(), exception);
        }
    }

    @Override
    public List<PhotoEntity> provideAllPhotosForAdmin() throws ServiceException {
        try {
            List<PhotoEntity> photos
                    = PhotoDaoImpl.getInstance().selectAll();
            return photos;
        } catch (DaoException exception) {
            LOGGER.error(   "Unable to provide information about photos! "
                    + exception.getMessage(), exception);
            throw new ServiceException(
                    "Unable to provide information about photos! "
                            + exception.getMessage(), exception);
        }
    }

    @Override
    public List<Photo> provideDecimalPieceOfPhotos(int pieceValue)
            throws ServiceException {
        try {
            List<Photo> decimalPieceOfAllPhotos
                    = PhotoDaoImpl.getInstance()
                    .provideDecimalPieceOfPhotos(pieceValue);
            return decimalPieceOfAllPhotos;
        } catch (DaoException exception) {
            LOGGER.error(  "Unable to provide information about photos! "
                    + exception.getMessage(), exception);
            throw new ServiceException(
                    "Unable to provide information about photos! "
                            + exception.getMessage(), exception);
        }
    }

    @Override
    public boolean deletePhoto(String photoPath) throws ServiceException {
        if (photoPath == null) {
            LOGGER.error("Parameter 'photoPath' is null!");
            throw new ServiceException("Parameter 'photoPath' is null!");
        }
        try {
            boolean isPhotoWasDeleted
                    = PhotoDaoImpl.getInstance().deleteByPhotoPath(photoPath);
            LOGGER.info("Photo: " + photoPath
                    + (isPhotoWasDeleted ? " was " : " was not ")
                    + "deleted from database .");
            boolean isPathWasAddedToDeleterList
                    = WebappImageDeleterUtil.addPathToDeleterList(photoPath);
            LOGGER.info("Photo: " + photoPath
                    + (isPhotoWasDeleted ? " was " : " was not ")
                    + " added to deleter list");
            return isPhotoWasDeleted && isPathWasAddedToDeleterList;
        } catch (DaoException exception) {
            LOGGER.error(  "Unable to delete photo! "
                    + exception.getMessage(), exception);
            throw new ServiceException(
                    "Unable to delete photo! "
                            + exception.getMessage(), exception);
        }
    }
}

