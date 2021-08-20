package com.gerard.site.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Queue;
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

/**
 * Final class that manages (gives out and gets back) specified
 * fixed quantity of database connections
 * which are instance of custom class ProxyConnection {@link ProxyConnection} .
 *
 * <p>Uses connection properties from class ConnectionProperties {@link ConnectionProperties}
 * through MySQL JDBC drivers {@link com.mysql.cj.jdbc} .
 * Realized as thread-safe singleton object .
 * </p>
 * <p>Database connections are storing in two queues:
 * <ol>
 *     <li>free connections queue </li>
 *     <li>given connections queue </li>
 * </ol>
 * <p>Giving out connections method {@link ConnectionPool#giveOutConnection()}
 * takes connections from free connection queue
 * and put it to the given connections queue.
 * </p>
 * <p>
 * Getting back method {@link ConnectionPool#getBackConnection(Connection)} ()}
 * works otherwise, it takes connections from given connections queue
 * and puts it to the free connections queue .
 * </p>
 * <p>Can but no guarantee protection from leaking connections
 *, cause database connections has timeout .
 * </p>
 * <p>Connections may be destroyed by using method {@code destroy()}
 * {@link ConnectionPool#destroy()} .
 * </p>
 * <p>Singleton pattern realized through
 * using AtomicBoolean object {@link AtomicBoolean}
 * </p>
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public final class ConnectionPool {
    /**
     * Initializes database connections properties
     * such as connections quantity (pool size) and etc.
     * in class ConnectionProperties {@link ConnectionProperties}
     */
    static {
        ConnectionProperties.init();
    }

    /**
     * Thread-safe marker indicating that connection pool was initialized .
     */
    private static final AtomicBoolean isInstanceInitialized = new AtomicBoolean(false);

    /**
     * Thread-safe marker indicating that connection pool destroying process had started .
     *
     * @see #destroy()
     */
    private static final AtomicBoolean isDestroyCalled = new AtomicBoolean(false);

    /**
     * Class singleton instance .
     */
    private static ConnectionPool instance;

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    /**
     * Queue storage for free connections .
     */
    private final BlockingQueue<ProxyConnection> freeConnections;

    /**
     * Queue storage for given connections .
     */
    private final BlockingQueue<ProxyConnection> givenConnections;

    /**
     * Timer for managing anti-leaking connections protection .
     *
     * @see #offerLeakedConnections()
     */
    private final Timer leakedConnectionsOfferTimer;


    private ConnectionPool() {
        givenConnections = new LinkedBlockingQueue<>();
        freeConnections = new LinkedBlockingQueue<>(poolSize);
        leakedConnectionsOfferTimer = new Timer();
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException exception) {
            LOGGER.fatal("Unable to register JDBC drivers! "
                    + exception.getMessage());
            throw new RuntimeException("Unable to register JDBC drivers!"
                    + exception.getMessage(), exception);
        }
        offerFreeConnections();
        scheduleTimerForLeakedConnectionsOffering();
    }

    /**
     * Provides and initializes pool instance .
     * @return initialized pool instance
     */
    public static ConnectionPool getInstance() {
        while (instance == null) {
            if (isInstanceInitialized.compareAndSet(false, true)) {
                instance = new ConnectionPool();
            }
        }
        return instance;
    }

    /**
     * Gives out database connection from connection pool.
     * Firstly gives connection from free connections queue then
     * put it to given connections queue. If no free connection exists waits
     * <p> For thread-safeness uses {@code take()} {@link BlockingQueue#take()}
     * and {@code put(Object)} {@link BlockingQueue#put(Object)} methods of BlockingQueue class
     * </p>
     * If called while pool destroy process interrupts that thread and
     * throws ConnectionException {@link ConnectionException} .
     * If this implementation is used it's recommended
     * to use transactions to protect of data corrupting .
     *
     * @return taken connection from free connection queue.
     *         If no free connection exists wait .
     * @throws ConnectionException
     *         if method called while pool destroy executing
     */
    public Connection giveOutConnection() throws ConnectionException {
        if (isDestroyCalled.get()) {
            LOGGER.warn("Try to get connection while pool destroying.");
            Thread.currentThread().interrupt();
            LOGGER.debug("Attempt to interrupt this thread have been made.");
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

    /**
     * Gets back connection to connection pool.
     * Firstly check if connection {@code instanceof ProxyConnection} {@link ProxyConnection}
     * if no throws ConnectionException {@link ConnectionException} .
     * Then takes it from given connections queue and put to free connections queue .
     * <p>If removing connection from given connections
     * or putting it to the free connections queue is impossible
     * replaces it by the new one .</p>
     *
     * @param connection to get back to pool
     * @return  false if param connection
     *          is not instanceof ProxyConnection
     *          {@link ProxyConnection}
     *          true if connection was successfully put to free connections queue
     * @throws ConnectionException if catches InterruptedException
     *          which can be thrown while putting connection
     *          into free connection queue
     *          by using method put of BlockingQueue class
     *          {@link BlockingQueue#put(Object)}
     * @see ProxyConnection
     */
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
    //todo ? add to docs: why given connections are not close
    /**
     * Destroys pool by removing connections from free connections queue
     * and really close connections
     * by calling {@code close()} method in Connection class {@link Connection#close()} .
     *
     * Given connections in given connections queue are not closing
     * cause
     * <p>Deregister JDBC drivers in finally block .
     * Used not-thread safe queue method remove() {@link Queue#remove()} </p>
     */
    public void destroy() {
        isDestroyCalled.set(true);
        leakedConnectionsOfferTimer.cancel();
        try {
            for (int i = 0; i < poolSize; i++) {
                freeConnections.remove().strictClose();
            }
        } catch (NoSuchElementException exception) {
            LOGGER.trace("No elements in free connections queue");
            LOGGER.info("Connection pool was destroyed");
        } finally {
            deregisterDrivers();
        }
    }

    void offerLeakedConnections() {
        int existingConnectionsQuantity = givenConnections.size() + freeConnections.size();
        int leakedConnectionsQuantity = poolSize - existingConnectionsQuantity;
        for (int i = 0; i < leakedConnectionsQuantity; i++) {
            ProxyConnection extraConnection = ConnectionFactory.getInstance().createValidConnection();
            try {
                freeConnections.put(extraConnection);
                LOGGER.trace("Connection: " + extraConnection
                        + " was put to free connections queue to reduce risk of leaking connection.");
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
            } catch (SQLException exception) {
                LOGGER.error("Unable to deregister drivers! " + exception.getMessage(), exception);
            }
        });
        LOGGER.info("Drivers were deregistered.");
    }

    private void scheduleTimerForLeakedConnectionsOffering() {
        leakedConnectionsOfferTimer.schedule(
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
    public String toString() {
        return "ConnectionPool{"
                + "freeConnections=" + freeConnections
                + ", givenConnections=" + givenConnections
                + ", offerLeakedConnections=" + leakedConnectionsOfferTimer
                + '}';
    }
}
