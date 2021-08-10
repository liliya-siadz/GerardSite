package com.gerard.GerardSite.connection;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class ConnectionPoolTest1 {

    private ConnectionPool connectionPool;

    @BeforeClass
    public void callConnectionPoolInstance() {
        connectionPool = ConnectionPool.getInstance();
    }

    //doc: 'threadPoolSize' must be bigger than 'poolSize'
    @Test(testName ="testGiveOutConnectionFixedConnectionsQuantity" ,
            groups = "Opened connections",
            description = "Check that pool creates fixed quantity of connections in a competitive environment",
            invocationTimeOut = 5000, threadPoolSize = 4 * 5,
            invocationCount = 4*5, timeOut = 5000, successPercentage = 80,
            expectedExceptions = ConnectionException.class)
    public void testGiveOutConnectionFixedConnectionsQuantity()
            throws ConnectionException {
        assertNull(connectionPool.giveOutConnection());
    }

    @AfterClass
    public void closeOpenedConnections() throws ConnectionException {
        connectionPool.destroy();
    }
}