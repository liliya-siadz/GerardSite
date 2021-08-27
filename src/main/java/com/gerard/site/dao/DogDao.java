package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.view.Dog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DogDao {
    boolean updateDogStatus(DogEntity dogEntity) throws DaoException;

    List<Dog> selectAllDogsWithPhotos() throws DaoException;

    Dog parseResultSetDogWithPhotos(ResultSet resultSet) throws SQLException;
}

