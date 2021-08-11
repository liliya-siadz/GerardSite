package com.gerard.site.connection;

import com.gerard.site.util.CustomDocumentUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.gerard.site.connection.ConnectionFactory.*;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static final AtomicBoolean isInstanceInitialized = new AtomicBoolean(false);
    private static final AtomicBoolean isDestroyCalled = new AtomicBoolean(false);
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final String DB_CONNECTION_POOL_RESOURCE_PATH = "/db_connection_pool.properties";
    private static final String POOL_SIZE_PROPERTY_KEY = "size";
    private static final String ANTI_LEAKING_CONNECTIONS_START_MIN_PROPERTY_KEY = "antiLeakingConnectionsStart.min";
    private static final String ANTI_LEAKING_CONNECTIONS_PERIOD_MIN_PROPERTY_KEY = "antiLeakingConnectionsPeriod.min";
    private static final String QUANTITY_OF_TRIES_TO_OFFER_FREE_CONNECTIONS = "triesToOfferFreeConnections.quantity";

    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> givenConnections = new LinkedBlockingQueue<>();
    private final int poolSize;
    private long antiLeakingConnectionsStartMin;
    private long antiLeakingConnectionsPeriodMin;
    private int quantityOfTriesToOfferFreeConnections;
    private Timer offerLeakedConnections = new Timer();


    //todo commit: after testing
    //todo test: test pool throw Mockito and TestNG
    private ConnectionPool() {
        try {
            Properties dbConnectionPoolProperties = CustomDocumentUtil.loadResourcePropertiesByObject(this, DB_CONNECTION_POOL_RESOURCE_PATH);
            poolSize = Integer.parseInt(dbConnectionPoolProperties.getProperty(POOL_SIZE_PROPERTY_KEY));
            quantityOfTriesToOfferFreeConnections = Integer.parseInt(dbConnectionPoolProperties.getProperty(QUANTITY_OF_TRIES_TO_OFFER_FREE_CONNECTIONS));
            freeConnections = new LinkedBlockingQueue<>(poolSize);
            antiLeakingConnectionsStartMin = Long.parseLong(
                    dbConnectionPoolProperties.getProperty(ANTI_LEAKING_CONNECTIONS_START_MIN_PROPERTY_KEY));
            antiLeakingConnectionsPeriodMin = Long.parseLong(
                    dbConnectionPoolProperties.getProperty(ANTI_LEAKING_CONNECTIONS_PERIOD_MIN_PROPERTY_KEY));
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (NullPointerException | SQLException | IOException | URISyntaxException exception) {
            LOGGER.fatal("Database connection pool properties resource file: "
                    + DB_CONNECTION_POOL_RESOURCE_PATH + "is invalid");
            throw new RuntimeException("Database connection pool properties resource file: "
                    + DB_CONNECTION_POOL_RESOURCE_PATH + "is invalid");
        }

        offerFreeConnections();
        scheduleTimerForLeakedConnectionsOffering();
    }

    public int getPoolSize() {
        return poolSize;
    }

    public static ConnectionPool getInstance() {
        while (instance == null) {
            if (isInstanceInitialized.compareAndSet(false, true)) {
                instance = new ConnectionPool();
            }
        }
        return instance;
    }

    public Connection giveOutConnection() throws ConnectionException {
        if (isDestroyCalled.get()) {
            LOGGER.warn("Try to get connection while pool destroying.");
            LOGGER.debug("Thread that tried to get connection will be interrupted");
            Thread.currentThread().interrupt();
            throw new ConnectionException("Try to get connection while pool destroying!");
        } else {
            ProxyConnection connection = null;
            try {
                connection = freeConnections.take();
                LOGGER.info("Connection:" + connection + " was taken from free connections queue.");
            } catch (InterruptedException exception) {
                LOGGER.warn("Unable to take connection from free connections queue!");
                throw new ConnectionException("Unable to take connection from free connections queue!");
            }
            try {
                    givenConnections.put(connection);
                    LOGGER.info("Connection" + connection + "was put into given connections queue.");
                } catch (InterruptedException exception) {
                    LOGGER.warn("Unable to put given connection: " + connection + "into given connections queue!");
                }
            LOGGER.info(connection + " given out from pool");
            return connection;
        }
    }

    public boolean getBackConnection(Connection connection) throws ConnectionException {
        if (connection instanceof ProxyConnection proxyConnection) {
            if (!givenConnections.remove(proxyConnection)) {
                LOGGER.warn("Unable to remove connection:" + proxyConnection + " from given connections queue!");
                LOGGER.debug("Connection will be replaced to extra one.");
                proxyConnection = replaceInvalidConnection(proxyConnection);
            }
            try {
                freeConnections.put(proxyConnection);
                LOGGER.info("Connection:" + proxyConnection + " was put into free connections queue.");
                return true;
            } catch (InterruptedException exception) {
                LOGGER.warn("Connection: " + proxyConnection + " wasn't put into free connections queue! " + exception.getMessage(), exception);
                LOGGER.debug("Connection will be replaced to extra one.");
                proxyConnection = replaceInvalidConnection(proxyConnection);
                LOGGER.debug("Try to put extra connection into free connections queue.");
                try {
                    freeConnections.put(proxyConnection);
                    LOGGER.info("Connection:" + proxyConnection + " was put into free connections queue.");
                    return true;
                } catch (InterruptedException connectionException) {
                    LOGGER.error("Unable to return connection into pool! " + connectionException.getMessage(), connectionException);
                    throw new ConnectionException("Unable to return connection into pool! " + connectionException.getMessage(), connectionException);
                }
            }
        } else {
            LOGGER.error("Connection wasn't returned cause is not instance of 'ProxyConnection' (or null)");
            return false;
        }
    }

    void offerLeakedConnections() {
        int leakedConnectionsQuantity = poolSize - (givenConnections.size() + freeConnections.size());
        for (int i = 0; i < leakedConnectionsQuantity; i++) {
            ProxyConnection extraConnection = ConnectionFactory.getInstance().createValidConnection();
            try {
                freeConnections.put(extraConnection);
            } catch (InterruptedException e) {
                LOGGER.debug("No space for another extra connection.");
                break;
            }
        }
    }

    //todo call: in servlet destroy() or in Listener
    public void destroy() throws ConnectionException {
        isDestroyCalled.set(true);
        offerLeakedConnections.cancel();
        try {
            for (int i = 0; i < poolSize; i++) {
                freeConnections.remove().strictClose();
            }
        } catch (NoSuchElementException exception) {
            LOGGER.info("Connection pool was destroying");
        } finally {
            deregisterDrivers();
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException exception) {
                LOGGER.error(exception.getMessage(), exception);
            }
        });
        LOGGER.info("Drivers was deregistered");
    }

    private void scheduleTimerForLeakedConnectionsOffering() {
        offerLeakedConnections.schedule(new AntiLeakingConnectionTimerTask()
                , TimeUnit.MINUTES.toMinutes(antiLeakingConnectionsStartMin)
                , TimeUnit.MINUTES.toMinutes(antiLeakingConnectionsPeriodMin));
    }

    private void offerFreeConnections() {
        int quantityOfPastTriesToOfferFreeConnections = 0;
        int quantityOfSuccessfullyOfferedConnections = 0;
        while (quantityOfSuccessfullyOfferedConnections != poolSize && quantityOfPastTriesToOfferFreeConnections < quantityOfTriesToOfferFreeConnections) {
            for (int i = 0; i < poolSize; i++) {
                ProxyConnection connection = ConnectionFactory.getInstance().createValidConnection();
                if (freeConnections.offer(connection)) {
                    quantityOfSuccessfullyOfferedConnections++;
                }
            }
            quantityOfPastTriesToOfferFreeConnections++;
        }
    }
}
