package com.gerard.site.controller.listener;

import com.gerard.site.connection.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class DestroyConnectionPoolListener implements ServletContextListener {
    //todo how check does it works
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
    }
}
