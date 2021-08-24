package com.gerard.site.service.impl;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.dao.impl.DogDaoImpl;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.DogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;

public class DogServiceImpl implements DogService {
    private static DogServiceImpl instance;
    private static final Logger LOGGER = LogManager.getLogger(DogServiceImpl.class);
    private static final int PUPPY_AGE_MAX_MONTHS_QUANTITY = 8;
    private DogServiceImpl(){
    }

    public static DogServiceImpl getInstance() {
        if(instance == null){
            instance = new DogServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<DogEntity> find(int id) throws ServiceException {
       DogEntity dogEntity = new DogEntity();
       dogEntity.setId(id);
        try {
            Optional<DogEntity> foundDog = DogDaoImpl.getInstance().find(dogEntity);
            return foundDog;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }

    public List<DogEntity> provideActiveDogs() throws ServiceException {
        try {
            List<DogEntity> allDogs = DogDaoImpl.getInstance().selectAll();
            allDogs.removeIf(dogEntity -> !dogEntity.isActive());
            return allDogs;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }

    public List<DogEntity> provideActivePuppies() throws ServiceException {
        try {
            List<DogEntity> allDogs = DogDaoImpl.getInstance().selectAll();
            allDogs.removeIf(new IsPuppy().negate());
            return allDogs;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }

    @Override
    public List<DogEntity> provideAllDogs() throws ServiceException {
        try {
            List<DogEntity> allDogs = DogDaoImpl.getInstance().selectAll();
            return allDogs;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information from database! "
                            + exception.getMessage(), exception);
        }
    }

    @Override
    public List<DogEntity> provideAllPuppies() throws ServiceException {
        List<DogEntity> allDogs = provideAllDogs();
        List<DogEntity> allPuppies =  allDogs.stream().filter(new IsPuppy()).toList();
        return allPuppies;
    }

    public class IsPuppy implements Predicate<DogEntity> {
        @Override
        public boolean test(DogEntity dogEntity) {
             long dogAgeInMoths = new DogAge().applyAsLong(dogEntity);
             return dogAgeInMoths < PUPPY_AGE_MAX_MONTHS_QUANTITY;
        }
    }

    public class DogAge implements ToLongFunction<DogEntity> {
        @Override
        public long applyAsLong(DogEntity dogEntity) {
            LocalDate currentDate = LocalDate.now();
            LocalDate dogBirthday = dogEntity.getBirthday().toLocalDate();
            long dogAgeInMonths = ChronoUnit.MONTHS.between(dogBirthday, currentDate);
            return dogAgeInMonths;
        }
    }
}
