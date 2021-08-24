package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.service.entity.PhotoAndDog;

import java.util.List;

public interface PhotoDao {
    List<PhotoAndDog> selectAllPhotosAndDogs() throws DaoException;
}
