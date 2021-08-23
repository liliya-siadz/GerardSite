package com.gerard.site.service.mail;

import com.gerard.site.util.AppIdentifierUtil;

import java.util.Properties;

class MailProperties {
    static Properties mailProperties;
    static String userName;
    static String password;
    private static final String MAIL_PROPERTIES_RESOURCE_PATH = "/mail.properties";
    private static MailProperties instance;

    private MailProperties() {
        mailProperties = AppIdentifierUtil.getPropertiesByPath(this, MAIL_PROPERTIES_RESOURCE_PATH);
        String userNamePropertyKey = "mail.user";
        String passwordPropertyKey = "password";
        userName = mailProperties.getProperty(userNamePropertyKey);
        password = mailProperties.getProperty(passwordPropertyKey);
    }

    static void init() {
        if (instance == null) {
            instance = new MailProperties();
        }
    }
}