package com.gerard.site;

import com.gerard.site.connection.ConnectionException;
import com.gerard.site.connection.ConnectionPool;
import com.gerard.site.exception.ServiceException;

import java.sql.Connection;

public class Main {


    public static void main(String[] args) throws ServiceException, ConnectionException {
        Connection connection = ConnectionPool.getInstance().giveOutConnection();
        String selectAllUsers = "";
    }
}
