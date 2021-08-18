package com.gerard.site.connection;

import java.util.TimerTask;

class AntiLeakingConnectionTimerTask extends TimerTask {

    @Override
    public void run() {
        ConnectionPool.getInstance().offerLeakedConnections();
    }

    AntiLeakingConnectionTimerTask() {
    }

}
