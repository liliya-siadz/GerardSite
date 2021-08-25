package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.service.entity.DogEntity;

public interface DogDao {
    boolean updateDogStatus(DogEntity dogEntity) throws DaoException;
}
