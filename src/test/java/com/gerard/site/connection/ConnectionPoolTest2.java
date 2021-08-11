package com.gerard.site.connection;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConnectionPoolTest2 {

    private ConnectionPool connectionPool;

    @BeforeClass
    public void callConnectionPoolInstance() {
        connectionPool = ConnectionPool.getInstance();
    }

    @Test(groups = "Opened connections",
            description = "Check that pool creates fixed connections quantity",
            invocationTimeOut = 5000, threadPoolSize = 4,
            invocationCount = 4, timeOut = 5000, successPercentage = 100)
    public void testGiveOutConnectionConnectionsNotNull() throws ConnectionException {
        assertNotNull(connectionPool.giveOutConnection());
    }

    @AfterClass
    public void closeOpenedConnections() throws ConnectionException {
        connectionPool.destroy();
    }
}