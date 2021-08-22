package com.gerard.site.service;

import com.gerard.site.dao.DaoException;
import com.gerard.site.dao.DogDao;
import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DogService implements Service {
    private static DogService instance;
    private static final Logger LOGGER = LogManager.getLogger(DogService.class);

    private DogService(){
    }

    public static DogService getInstance() {
        if(instance == null){
            instance = new DogService();
        }
        return instance;
    }

    public List<DogEntity> provideAllDogs() throws ServiceException {
        try {
            List<DogEntity> allDogs = DogDao.getInstance().selectAll();
            return allDogs;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }
}
