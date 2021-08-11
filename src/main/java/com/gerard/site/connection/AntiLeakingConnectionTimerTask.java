package com.gerard.site.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;

public class AntiLeakingConnectionTimerTask extends TimerTask {
    private static final Logger LOGGER = LogManager.getLogger(AntiLeakingConnectionTimerTask.class);
    @Override
    public void run() {
        ConnectionPool.getInstance().offerLeakedConnections();
    }
}
