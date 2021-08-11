package com.gerard.site.connection;


import com.gerard.site.util.CustomDocumentUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory instance;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);
    private final String DB_CONNECTION_RESOURCE_PATH = "/db_connection.properties";
    private final String URL_PROPERTY_KEY = "url";
    private final String QUANTITY_OF_TRIES_TO_CREATE_CONNECTION_PROPERTY_KEY = "connectionCreationTries.quantity";
    private final String QUANTITY_OF_TRIES_TO_CREATE_VALID_CONNECTION_PROPERTY_KEY = "connectionCreationValidTries.quantity";
    private String url;
    private Properties dbConnectionProperties;
    private int quantityOfTriesToCreateConnection;
    private int quantityOfTriesToCreateValidConnection;

    private ConnectionFactory(){
        try {
            dbConnectionProperties = CustomDocumentUtil.loadResourcePropertiesByObject(this, DB_CONNECTION_RESOURCE_PATH);
            quantityOfTriesToCreateValidConnection = Integer.parseInt(dbConnectionProperties.getProperty(QUANTITY_OF_TRIES_TO_CREATE_VALID_CONNECTION_PROPERTY_KEY));
            url = dbConnectionProperties.getProperty(URL_PROPERTY_KEY);
            quantityOfTriesToCreateConnection = Integer.parseInt(dbConnectionProperties.getProperty(QUANTITY_OF_TRIES_TO_CREATE_CONNECTION_PROPERTY_KEY));
        } catch (NullPointerException | IOException | URISyntaxException e) {
            LOGGER.fatal("Database connection properties file: " + DB_CONNECTION_RESOURCE_PATH
            + "is invalid!");
            throw new RuntimeException("Database connection properties file: "
                    + DB_CONNECTION_RESOURCE_PATH + "is invalid!");
        }
    }
    static ConnectionFactory getInstance(){
        if(instance == null){
            instance = new ConnectionFactory();
        }return instance;
    }

    ProxyConnection createValidConnection() {
        int pastTriesToCreateValidConnection = 0;
        while (pastTriesToCreateValidConnection < quantityOfTriesToCreateValidConnection){
            ProxyConnection connection = createConnection();
            try {
                boolean isConnectionValid = connection.isValid(0);
                if(isConnectionValid){
                    return connection;
                }
            } catch (SQLException exception) {
                LOGGER.error("Connection: " + connection + " is invalid! " + exception.getMessage(), exception);
                LOGGER.debug("Try to create valid connection again.");
                pastTriesToCreateValidConnection ++;
            }
        }
        LOGGER.info(quantityOfTriesToCreateValidConnection + " tries had past to create valid connection.");
        LOGGER.fatal("Unable to create valid connection!");
        throw new RuntimeException("Unable to create valid connection!");
    }


    static ProxyConnection replaceInvalidConnection(ProxyConnection connection) {
        connection.strictClose();
        LOGGER.trace("Connection: " + connection + "was strictly closed.");
        ProxyConnection extraConnection = getInstance().createValidConnection();
        LOGGER.trace("Created extra connection: " + extraConnection);
        return extraConnection;
    }

    //todo better: guarantee creation connection boarded times
    private ProxyConnection createConnection(){
        for(int i = 0; i < quantityOfTriesToCreateConnection;){
            try {
                Connection connection = DriverManager.getConnection(url, dbConnectionProperties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                return proxyConnection;
            } catch (SQLException exception) {
                LOGGER.error("Database connection errors while creating connection! " + exception.getMessage(), exception);
                LOGGER.debug(i + " try to create valid connection again");
                i ++;
            }
        }
        LOGGER.info(quantityOfTriesToCreateConnection + " tries had past to create database connection.");
        LOGGER.fatal("Unable to create database connection!");
        throw new RuntimeException("Unable to create database connection!");
    }

}
