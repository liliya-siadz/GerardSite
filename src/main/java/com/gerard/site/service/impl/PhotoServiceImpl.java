package com.gerard.site.service.impl;

import com.gerard.site.dao.DaoException;
import com.gerard.site.dao.PhotoDao;
import com.gerard.site.dto.PhotoAndDog;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.PhotoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PhotoServiceImpl implements PhotoService {
    private static PhotoServiceImpl instance;
    private static final Logger LOGGER = LogManager.getLogger(PhotoServiceImpl.class);

    private static final String PEDIGREE_FOLDER_NAME = "pedigrees";
    private static final String AVATAR_FOLDER_NAME = "avatars";
    private static final String PHOTO_FOLDER_NAME = "photos";

    private PhotoServiceImpl(){
    }

    public static PhotoServiceImpl getInstance() {
        if(instance == null){
            instance = new PhotoServiceImpl();
        }
        return instance;
    }

    @Override
    public List<PhotoAndDog> provideAllPhotosAndDogs() throws ServiceException {
        try {
            List<PhotoAndDog> allPhotosWithDogs =
                    PhotoDao.getInstance().selectAllPhotosWithDogs();
            allPhotosWithDogs = allPhotosWithDogs.stream()
                    .filter(photoAndDog ->
                            photoAndDog.getPhotoPath().contains(PHOTO_FOLDER_NAME))
                    .toList();
            return allPhotosWithDogs;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }

}
