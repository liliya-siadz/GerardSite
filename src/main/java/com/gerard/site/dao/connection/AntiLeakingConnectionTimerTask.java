package com.gerard.site.dao.connection;

import java.util.TimerTask;

class AntiLeakingConnectionTimerTask extends TimerTask {
    AntiLeakingConnectionTimerTask() {
        super();
    }

    @Override
    public void run() {
        ConnectionPool.getInstance().offerLeakedConnections();
    }
}
