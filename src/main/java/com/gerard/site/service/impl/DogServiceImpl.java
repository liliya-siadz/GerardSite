package com.gerard.site.service.impl;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.dao.impl.DogDaoImpl;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.DogService;
import com.gerard.site.service.view.Dog;
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
    private static final int PUPPY_AGE_MAX_MONTHS_QUANTITY = 8;
    private static final Logger LOGGER =
            LogManager.getLogger(DogServiceImpl.class);

    private DogServiceImpl() {
    }

    public static DogServiceImpl getInstance() {
        if (instance == null) {
            instance = new DogServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<DogEntity> find(int id) throws ServiceException {
        DogEntity dogEntity = new DogEntity();
        dogEntity.setId(id);
        try {
            Optional<DogEntity> foundDog
                    = DogDaoImpl.getInstance().find(dogEntity);
            return foundDog;
        } catch (DaoException exception) {
            LOGGER.error("Unable to find dog! "
                    + exception.getMessage(), exception);
            throw new ServiceException(
                    "Unable to find dog! "
                            + exception.getMessage(), exception);
        }
    }

    public List<Dog> provideAllDogsForView() throws ServiceException {
        try {
            List<Dog> allDogsForView =
                    DogDaoImpl.getInstance().selectAllDogsWithPhotos();
            allDogsForView.removeIf(dog -> !dog.isActive());
            return allDogsForView;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information about dogs! "
                            + exception.getMessage(), exception);
        }
    }

    @Override
    public List<Dog> provideAllPuppiesForView() throws ServiceException {
        try {
            List<Dog> allPuppiesForView =
                    DogDaoImpl.getInstance().selectAllDogsWithPhotos();
            allPuppiesForView.removeIf(new IsPuppy().negate());
            return allPuppiesForView;
        } catch (DaoException exception) {
            throw new ServiceException(
                    "Unable to provide information about dogs ! "
                            + exception.getMessage(), exception);
        }
    }

    @Override
    public List<Dog> provideAllDogsForAdmin() throws ServiceException {
        try {
            List<Dog> allDogsForAdmin =
                    DogDaoImpl.getInstance().selectAllDogsWithPhotos();
            return allDogsForAdmin;
        } catch (DaoException exception) {
            LOGGER.error("Unable to provide information about dogs! "
                    + exception.getMessage(), exception);
            throw new ServiceException(
                    "Unable to provide dogs information! "
                            + exception.getMessage(), exception);
        }
    }

    public class IsPuppy implements Predicate<Dog> {
        @Override
        public boolean test(Dog dogEntity) {
            long dogAgeInMoths = new DogAge().applyAsLong(dogEntity);
            return dogAgeInMoths < PUPPY_AGE_MAX_MONTHS_QUANTITY;
        }
    }

    public class DogAge implements ToLongFunction<Dog> {
        @Override
        public long applyAsLong(Dog dogEntity) {
            LocalDate currentDate = LocalDate.now();
            LocalDate dogBirthday = dogEntity.getBirthday().toLocalDate();
            long dogAgeInMonths
                    = ChronoUnit.MONTHS.between(dogBirthday, currentDate);
            return dogAgeInMonths;
        }
    }
}
