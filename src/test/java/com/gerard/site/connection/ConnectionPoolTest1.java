package com.gerard.site.connection;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class ConnectionPoolTest1 {

    private ConnectionPool connectionPool;

    @BeforeClass
    public void callConnectionPoolInstance() {
        connectionPool = ConnectionPool.getInstance();
    }

    @Test(testName ="testGiveOutConnectionFixedConnectionsQuantity" ,
            groups = "Opened connections",
            description = "Check that pool doesn't create extra connections" +
                    " in a competitive environment",
            invocationTimeOut = 5000, threadPoolSize = 4 * 5,     //'threadPoolSize' must be bigger than 'poolSize'
            invocationCount = 4*5, timeOut = 100000, successPercentage = 20,
            expectedExceptions = ConnectionException.class)
    public void testGiveOutConnectionFixedConnectionsQuantity()
            throws ConnectionException {
        assertNotNull(connectionPool.giveOutConnection());
    }

    @AfterClass
    public void closeOpenedConnections() throws ConnectionException {
        connectionPool.destroy();
    }
}