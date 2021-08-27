package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.service.view.Photo;

import java.util.List;

public interface PhotoDao {
    List<Photo> selectAllPhotosAndDogs() throws DaoException;
}
