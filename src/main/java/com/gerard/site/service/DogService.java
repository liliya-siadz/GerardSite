package com.gerard.site.service;

import com.gerard.site.dao.DaoException;
import com.gerard.site.dao.DogDao;
import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

public class DogService implements Service {
    private static DogService instance;
    private static final Logger LOGGER = LogManager.getLogger(DogService.class);
    private static final int PUPPY_AGE_MAX_MONTHS_QUANTITY = 8;
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
