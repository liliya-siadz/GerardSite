package com.gerard.site.controller.listener;

import com.gerard.site.validator.FieldsValidators;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class FieldsValidatorsInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        FieldsValidators.init();
    }
}
