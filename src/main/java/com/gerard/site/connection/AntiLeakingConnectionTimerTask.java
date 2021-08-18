package com.gerard.site.connection;

import java.util.TimerTask;

class AntiLeakingConnectionTimerTask extends TimerTask {

    @Override
    public void run() {
        ConnectionPool.getInstance().offerLeakedConnections();
    }

    AntiLeakingConnectionTimerTask() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public String toString() {
        return "AntiLeakingConnectionTimerTask{}";
    }
}
