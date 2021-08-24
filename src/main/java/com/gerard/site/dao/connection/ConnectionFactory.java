package com.gerard.site.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.gerard.site.dao.connection.ConnectionProperties.dbConnectionProperties;
import static com.gerard.site.dao.connection.ConnectionProperties.quantityOfTriesToCreateConnection;
import static com.gerard.site.dao.connection.ConnectionProperties.quantityOfTriesToCreateValidConnection;
import static com.gerard.site.dao.connection.ConnectionProperties.url;

final class ConnectionFactory {
    private static ConnectionFactory instance;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);

    private ConnectionFactory() {
    }

    static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    ProxyConnection createValidConnection() {
        int pastTriesToCreateValidConnection = 0;
        while (pastTriesToCreateValidConnection < quantityOfTriesToCreateValidConnection) {
            ProxyConnection connection = createConnection();
            try {
                boolean isConnectionValid = connection.isValid(0);
                if (isConnectionValid) {
                    return connection;
                }
            } catch (SQLException exception) {
                LOGGER.error("Connection: " + connection + " is invalid! "
                        + exception.getMessage(), exception);
                LOGGER.debug("Try to create valid connection again.");
                pastTriesToCreateValidConnection++;
            }
        }
        LOGGER.trace(quantityOfTriesToCreateValidConnection + " tries had past to create valid connection.");
        LOGGER.fatal("Unable to create valid connection!");
        throw new RuntimeException("Unable to create valid connection!");
    }


    static ProxyConnection replaceInvalidConnection(ProxyConnection connection) {
        connection.strictClose();
        LOGGER.trace("Connection: " + connection + "was strictly closed.");
        ProxyConnection extraConnection = getInstance().createValidConnection();
        LOGGER.info("Created extra connection: " + extraConnection);
        return extraConnection;
    }

    private ProxyConnection createConnection() {
        for (int i = 0; i < quantityOfTriesToCreateConnection;) {
            try {
                Connection connection = DriverManager.getConnection(url, dbConnectionProperties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                return proxyConnection;
            } catch (SQLException exception) {
                LOGGER.error("Database connection errors while creating connection! "
                        + exception.getMessage(), exception);
                LOGGER.debug(i + " try to create valid connection again");
                i++;
            }
        }
        LOGGER.trace(quantityOfTriesToCreateConnection + " tries had past to create database connection.");
        LOGGER.fatal("Unable to create database connection!");
        throw new RuntimeException("Unable to create database connection!");
    }

}
