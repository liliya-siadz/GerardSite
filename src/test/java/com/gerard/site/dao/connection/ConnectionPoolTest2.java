package com.gerard.site.dao.connection;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class ConnectionPoolTest2 {
    private ConnectionPool connectionPool;

    @BeforeClass
    public void callConnectionPoolInstance() {
        connectionPool = ConnectionPool.getInstance();
    }

    @Test(groups = "Opened connections",
            description = "Checking that pool creates quantity "
                    + "of connections not less than pool size number set",
            invocationTimeOut = 5000, threadPoolSize = 4,
            invocationCount = 4, timeOut = 5000, successPercentage = 100)
    public void testGiveOutConnectionConnectionsNotNull() throws ConnectionException {
        assertNotNull(connectionPool.giveOutConnection());
    }

    @AfterClass
    public void closeOpenedConnections() {
        connectionPool.destroy();
    }
}