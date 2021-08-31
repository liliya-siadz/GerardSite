package com.gerard.site.dao.connection;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConnectionPoolTest {
    private ConnectionPool connectionPool;

    @BeforeClass
    public void callConnectionPoolInstance() {
        connectionPool = ConnectionPool.getInstance();
    }

    @Test(expectedExceptions = ConnectionException.class)
    public void testDestroy() throws ConnectionException {
        connectionPool.destroy();
        connectionPool.giveOutConnection();
    }
}