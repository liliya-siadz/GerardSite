package com.gerard.site.controller.listener;

import com.gerard.site.service.util.mail.MailProperties;
import com.gerard.site.validator.field.FieldValidatorFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Initializes validators for inputs (fields)
 * that used on application page's forms .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class FieldsValidatorsInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        FieldValidatorFactory.init();
    }
}
