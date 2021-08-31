package com.gerard.site.controller.listener;

import com.gerard.site.validator.ValidatorFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Initializes validators for inputs (fields)
 * which are using on application page's forms .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
@WebListener
public class StartAppListener
        implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ValidatorFactory.init();
    }
}
