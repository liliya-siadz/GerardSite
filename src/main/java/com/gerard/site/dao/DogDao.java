package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.service.view.Dog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DogDao {
    List<Dog> selectAllDogsWithPhotos() throws DaoException;

    Dog parseResultSetDog(ResultSet resultSet) throws SQLException;
}

