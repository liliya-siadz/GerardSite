package com.gerard.site.connection;

import com.gerard.site.util.AppIdentifierUtil;

import java.util.Properties;

final class ConnectionProperties {
    static Properties dbConnectionProperties;
    static int poolSize;
    static long antiLeakingConnectionsStartMin;
    static long antiLeakingConnectionsPeriodMin;
    static int quantityOfTriesToOfferFreeConnections;
    static String url;
    static int quantityOfTriesToCreateConnection;
    static int quantityOfTriesToCreateValidConnection;
    private static ConnectionProperties instance;

    private ConnectionProperties() {
        String dbConnectionResourcePath = "/connection.properties";
            dbConnectionProperties = AppIdentifierUtil.getPropertiesByPath(this, dbConnectionResourcePath);
            String poolSizePropertyKey = "size";
            String quantityOfTriesToOfferFreeConnectionsPropertyKey = "triesToOfferFreeConnections.quantity";
            String antiLeakingConnectionsStartMinPropertyKey = "antiLeakingConnectionsStart.min";
            String antiLeakingConnectionsPeriodMinPropertyKey = "antiLeakingConnectionsPeriod.min";
            String quantityOfTriesToCreateValidConnectionPropertyKey = "connectionCreationValidTries.quantity";
            String urlPropertyKey = "url";
            String quantityOfTriesToCreateConnectionPropertyKey = "connectionCreationTries.quantity";
            poolSize = Integer.parseInt(
                    dbConnectionProperties.getProperty(poolSizePropertyKey));
            quantityOfTriesToOfferFreeConnections = Integer.parseInt(
                    dbConnectionProperties.getProperty(quantityOfTriesToOfferFreeConnectionsPropertyKey));
            antiLeakingConnectionsStartMin = Long.parseLong(
                    dbConnectionProperties.getProperty(antiLeakingConnectionsStartMinPropertyKey));
            antiLeakingConnectionsPeriodMin = Long.parseLong(
                    dbConnectionProperties.getProperty(antiLeakingConnectionsPeriodMinPropertyKey));
            quantityOfTriesToCreateValidConnection = Integer.parseInt(
                    dbConnectionProperties.getProperty(quantityOfTriesToCreateValidConnectionPropertyKey));
            url = dbConnectionProperties.getProperty(urlPropertyKey);
            quantityOfTriesToCreateConnection = Integer.parseInt(
                    dbConnectionProperties.getProperty(quantityOfTriesToCreateConnectionPropertyKey));
    }

    static void init() {
        if (instance == null) {
            instance = new ConnectionProperties();
        }
    }
}