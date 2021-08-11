package com.gerard.site.connection;

import java.util.TimerTask;

public class AntiLeakingConnectionTimerTask extends TimerTask {
    @Override
    public void run() {
        ConnectionPool.getInstance().offerLeakedConnections();
    }
}
