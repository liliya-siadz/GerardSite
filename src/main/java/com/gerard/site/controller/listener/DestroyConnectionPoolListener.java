package com.gerard.site.controller.listener;

import com.gerard.site.dao.connection.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Calls database connection pool destroying .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 * @see ConnectionPool#destroy() for details
 */
public class DestroyConnectionPoolListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().destroy();
    }
}
