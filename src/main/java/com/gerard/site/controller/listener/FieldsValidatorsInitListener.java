package com.gerard.site.controller.listener;

import com.gerard.site.validator.field.FieldsValidators;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class FieldsValidatorsInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FieldsValidators.init();
    }
}
