package com.gerard.site.connection;
//todo doc package
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConnectionPoolTest {

    private ConnectionPool connectionPool;

    @BeforeClass
    public void callConnectionPoolInstance() {
        connectionPool = ConnectionPool.getInstance();
    }

    //todo add description
    @Test(expectedExceptions = ConnectionException.class)
    public void testDestroy() throws ConnectionException {
        connectionPool.destroy();
        connectionPool.giveOutConnection();
    }
}