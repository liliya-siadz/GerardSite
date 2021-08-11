package com.gerard.site.connection;

import com.gerard.site.util.CustomDocumentUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

class ConnectionProperties {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionProperties instance;
    static Properties dbConnectionProperties;
    static int poolSize;
    static long antiLeakingConnectionsStartMin;
    static long antiLeakingConnectionsPeriodMin;
    static int quantityOfTriesToOfferFreeConnections;
    static String url;
    static int quantityOfTriesToCreateConnection;
    static int quantityOfTriesToCreateValidConnection;
    final String DB_CONNECTION_RESOURCE_PATH = "/connection.properties";
    final String URL_PROPERTY_KEY = "url";
    final String QUANTITY_OF_TRIES_TO_CREATE_CONNECTION_PROPERTY_KEY = "connectionCreationTries.quantity";
    final String QUANTITY_OF_TRIES_TO_CREATE_VALID_CONNECTION_PROPERTY_KEY = "connectionCreationValidTries.quantity";
    final String POOL_SIZE_PROPERTY_KEY = "size";
    final String ANTI_LEAKING_CONNECTIONS_START_MIN_PROPERTY_KEY = "antiLeakingConnectionsStart.min";
    final String ANTI_LEAKING_CONNECTIONS_PERIOD_MIN_PROPERTY_KEY = "antiLeakingConnectionsPeriod.min";
    final String QUANTITY_OF_TRIES_TO_OFFER_FREE_CONNECTIONS = "triesToOfferFreeConnections.quantity";

    private ConnectionProperties() {
        try {
            dbConnectionProperties = CustomDocumentUtil.loadResourcePropertiesByObject(this, DB_CONNECTION_RESOURCE_PATH);
            if (dbConnectionProperties == null) {
                LOGGER.fatal("Database connection properties resource FILE: "
                        + DB_CONNECTION_RESOURCE_PATH + "is invalid!");
                throw new RuntimeException("Database connection properties resource FILE: "
                        + DB_CONNECTION_RESOURCE_PATH + "is invalid!");
            }
            poolSize = Integer.parseInt(dbConnectionProperties.getProperty(POOL_SIZE_PROPERTY_KEY));
            quantityOfTriesToOfferFreeConnections = Integer.parseInt(dbConnectionProperties.getProperty(QUANTITY_OF_TRIES_TO_OFFER_FREE_CONNECTIONS));
            antiLeakingConnectionsStartMin = Long.parseLong(
                    dbConnectionProperties.getProperty(ANTI_LEAKING_CONNECTIONS_START_MIN_PROPERTY_KEY));
            antiLeakingConnectionsPeriodMin = Long.parseLong(
                    dbConnectionProperties.getProperty(ANTI_LEAKING_CONNECTIONS_PERIOD_MIN_PROPERTY_KEY));
            quantityOfTriesToCreateValidConnection = Integer.parseInt(
                    dbConnectionProperties.getProperty(QUANTITY_OF_TRIES_TO_CREATE_VALID_CONNECTION_PROPERTY_KEY));
            url = dbConnectionProperties.getProperty(URL_PROPERTY_KEY);
            quantityOfTriesToCreateConnection = Integer.parseInt(dbConnectionProperties.getProperty(QUANTITY_OF_TRIES_TO_CREATE_CONNECTION_PROPERTY_KEY));

        } catch (IOException | URISyntaxException exception) {
            LOGGER.fatal("Database connection properties resource file CONTENT: "
                    + DB_CONNECTION_RESOURCE_PATH + "is invalid!");
            throw new RuntimeException("Database connection properties resource file CONTENT: "
                    + DB_CONNECTION_RESOURCE_PATH + "is invalid!");
        }
    }

    static void init(){
        instance = new ConnectionProperties();
    }
}