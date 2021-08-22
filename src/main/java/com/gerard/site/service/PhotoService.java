package com.gerard.site.service;

import com.gerard.site.dao.DaoException;
import com.gerard.site.dao.PhotoDao;
import com.gerard.site.dto.PhotoWithDog;
import com.gerard.site.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PhotoService implements Service {
    private static PhotoService instance;
    private static final Logger LOGGER = LogManager.getLogger(PhotoService.class);

    private PhotoService(){
    }

    public static PhotoService getInstance() {
        if(instance == null){
            instance = new PhotoService();
        }
        return instance;
    }

    public List<PhotoWithDog> provideAllPhotosWithDogs() throws ServiceException {
        try {
            List<PhotoWithDog> allPhotosWithDogs = PhotoDao.getInstance().selectAllPhotosWithDogs();
            return allPhotosWithDogs;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }
}
