package com.gerard.site.util.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class MailSessionFactory {


    private MailSessionFactory() {

    }

    static Session createSession(Properties properties) {
        final String userName = properties.getProperty("dsf");
        final String userPassword = properties.getProperty("dsf");
        Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
        return null;
    }
}
