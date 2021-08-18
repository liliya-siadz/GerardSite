package com.gerard.site.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.gerard.site.connection.ConnectionFactory.replaceInvalidConnection;
import static com.gerard.site.connection.ConnectionProperties.antiLeakingConnectionsPeriodMin;
import static com.gerard.site.connection.ConnectionProperties.antiLeakingConnectionsStartMin;
import static com.gerard.site.connection.ConnectionProperties.poolSize;
import static com.gerard.site.connection.ConnectionProperties.quantityOfTriesToOfferFreeConnections;

public final class ConnectionPool {
    private static ConnectionPool instance;
    private static final AtomicBoolean isInstanceInitialized =
            new AtomicBoolean(false);
    private static final AtomicBoolean isDestroyCalled =
            new AtomicBoolean(false);
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> givenConnections;
    private final Timer offerLeakedConnections;

    static {
        ConnectionProperties.init();
    }

    private ConnectionPool() {
        givenConnections = new LinkedBlockingQueue<>();
        freeConnections = new LinkedBlockingQueue<>(poolSize);
        offerLeakedConnections = new Timer();
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        offerFreeConnections();
        scheduleTimerForLeakedConnectionsOffering();
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
            LOGGER.debug("The thread that tried to get connection will be interrupted.");
            Thread.currentThread().interrupt();
            throw new ConnectionException("Try to get connection while pool destroying!");
        } else {
            ProxyConnection connection;
            try {
                connection = freeConnections.take();
                LOGGER.trace("Connection:" + connection + " was taken from free connections queue.");
            } catch (InterruptedException exception) {
                LOGGER.warn("Unable to take connection from free connections queue!");
                throw new ConnectionException("Unable to take connection from free connections queue!");
            }
            try {
                givenConnections.put(connection);
                LOGGER.trace("Connection" + connection + "was put into given connections queue.");
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
                LOGGER.trace("Connection:" + proxyConnection + " was put into free connections queue.");
                LOGGER.info("Connection:" + proxyConnection + " was get back to pool.");
                return true;
            } catch (InterruptedException exception) {
                LOGGER.warn("Connection: " + proxyConnection + " wasn't put into free connections queue! "
                        + exception.getMessage(), exception);
                LOGGER.debug("Connection will be replaced to extra one.");
                proxyConnection = replaceInvalidConnection(proxyConnection);
                LOGGER.debug("Try to put extra connection into free connections queue.");
                try {
                    freeConnections.put(proxyConnection);
                    LOGGER.trace("Connection:" + proxyConnection + " was put into free connections queue.");
                    LOGGER.info("Connection:" + proxyConnection + " was get back to pool.");
                    return true;
                } catch (InterruptedException connectionException) {
                    LOGGER.error("Unable to return connection into pool! "
                            + connectionException.getMessage(), connectionException);
                    throw new ConnectionException("Unable to return connection into pool! "
                            + connectionException.getMessage(), connectionException);
                }
            }
        } else {
            LOGGER.error("Connection wasn't returned cause is not instanceof 'ProxyConnection' (or null)!");
            return false;
        }
    }

    //todo call: in Listener
    public void destroy() {
        isDestroyCalled.set(true);
        offerLeakedConnections.cancel();
        try {
            for (int i = 0; i < poolSize; i++) {
                freeConnections.remove().strictClose();
            }
        } catch (NoSuchElementException exception) {
            LOGGER.info("Connection pool was destroyed");
        } finally {
            deregisterDrivers();
        }
    }

    void offerLeakedConnections() {
        int leakedConnectionsQuantity = poolSize - (givenConnections.size() + freeConnections.size());
        for (int i = 0; i < leakedConnectionsQuantity; i++) {
            ProxyConnection extraConnection = ConnectionFactory.getInstance().createValidConnection();
            try {
                freeConnections.put(extraConnection);
                LOGGER.trace("Connection: " + extraConnection
                        + "was put to free connections queue to reduce risk of leaking connection.");
            } catch (InterruptedException e) {
                LOGGER.trace("No space for another extra connection.");
                break;
            }
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                LOGGER.info("Drivers was deregistered");
            } catch (SQLException exception) {
                LOGGER.error("Unable to deregister drivers! " + exception.getMessage(), exception);
            }
        });

    }

    private void scheduleTimerForLeakedConnectionsOffering() {
        offerLeakedConnections.schedule(
                new AntiLeakingConnectionTimerTask(),
                TimeUnit.MINUTES.toMinutes(antiLeakingConnectionsStartMin),
                TimeUnit.MINUTES.toMinutes(antiLeakingConnectionsPeriodMin));
    }

    private void offerFreeConnections() {
        int quantityOfPastTriesToOfferFreeConnections = 0;
        int quantityOfSuccessfullyOfferedConnections = 0;
        while (quantityOfSuccessfullyOfferedConnections != poolSize
                && quantityOfPastTriesToOfferFreeConnections < quantityOfTriesToOfferFreeConnections) {
            for (int i = 0; i < poolSize; i++) {
                ProxyConnection connection =
                        ConnectionFactory.getInstance().createValidConnection();
                if (freeConnections.offer(connection)) {
                    quantityOfSuccessfullyOfferedConnections++;
                }
            }
            quantityOfPastTriesToOfferFreeConnections++;
        }
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "ConnectionPool{"
                + "freeConnections=" + freeConnections
                + ", givenConnections=" + givenConnections
                + ", offerLeakedConnections=" + offerLeakedConnections
                + '}';
    }
}
