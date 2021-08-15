package com.gerard.site.dao;

import com.gerard.site.connection.ConnectionException;
import com.gerard.site.connection.ConnectionPool;
import com.gerard.site.dto.DogDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DogDao {
    private static final String SELECT_ALL_DOGS = "select * from dogs";
    public static List<DogDto> selectAllDogs() throws ConnectionException {
        Connection connection = ConnectionPool.getInstance().giveOutConnection();
        try {
            Statement statement = connection.prepareStatement(SELECT_ALL_DOGS);
     //       ResultSet dogs = statement.exe
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
