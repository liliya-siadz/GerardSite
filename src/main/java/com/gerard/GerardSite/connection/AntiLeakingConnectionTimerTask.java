package com.gerard.GerardSite.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.TimerTask;

public class AntiLeakingConnectionTimerTask extends TimerTask {
    private static final Logger LOGGER = LogManager.getLogger(AntiLeakingConnectionTimerTask.class);
    @Override
    public void run() {
        ConnectionPool.getInstance().offerLeakedConnections();
    }
}
