package com.gerard.GerardSite.connection;

import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

import static org.testng.Assert.*;

public class ConnectionPoolTest {

    private ConnectionPool connectionPool;
    private int poolSize;
    private BlockingQueue<Connection> givenConnections;

    @BeforeClass
    public void setUpConnectionPoolInstance() {
        connectionPool = ConnectionPool.getInstance();
        poolSize = connectionPool.getPoolSize();
    }

    //todo ? & change: count time for timeout
    //todo change: switch valid for smth else or add expected exception to exclude exception of assertion Error
    //todo doc: 'threadPoolSize' must be bigger than 'poolSize'

    //doc: timeOut : one test separately; invocationTimeOut : all invocations
    @Test(invocationTimeOut = 5000, threadPoolSize = 4 * 5,
            invocationCount = 4 * 5, timeOut = 5000, successPercentage = 80
            , description = "Check that pool creates fixed quantity of connection in in a competitive environment"
//    , expectedExceptions = {NullPointerException.class}
    )
    public void testGiveOutConnection() throws SQLException {
        assertNull(connectionPool.giveOutConnection());
    }


    //todo : realize
    @AfterClass
    public void destroyConnectionPool() throws ConnectionException {
        connectionPool.destroy();
    }
//    //todo: count timeout for test
//    @BeforeMethod(dependsOnMethods = "get back connection from pool", timeOut = 5000)
//    public void setUpGivenConnections(){
//        for(int i = 0 ; i < poolSize ; i ++){
//            givenConnections.offer(connectionPool.giveOutConnection());
//        }
//    }
//
//    //todo ? and change: mb add dataProvider
//    //todo change: count time for timeout and invocationTimOut
//    //todo 0:  mock maven
//    //todo 0.1: mock properties file
//    //todo 1: create mock for connections
//    //todo 2: use mock-connection to get back it for testing multithreading
//    @Test(testName = "get back connection from pool", invocationTimeOut = 1000
//    , invocationCount = 4, threadPoolSize = 4, successPercentage = 100, timeOut = 5000)
//    public void testGetBackConnection() throws InterruptedException {
//        connectionPool.getBackConnection(givenConnections.take());
//        assertTrue(true);
//    }
}