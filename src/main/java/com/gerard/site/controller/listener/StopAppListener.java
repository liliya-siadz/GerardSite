package com.gerard.site.controller.listener;

import com.gerard.site.dao.connection.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Calls database connection pool destroying .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 * @see ConnectionPool#destroy() for details
 */
@WebListener
public class StopAppListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().destroy();
    }
}
