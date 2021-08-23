package com.gerard.site.util.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

import static com.gerard.site.util.mail.MailProperties.password;
import static com.gerard.site.util.mail.MailProperties.userName;

class MailSessionFactory {
    static {
        MailProperties.init();
    }

    private MailSessionFactory() {
    }

    static Session createSession() {
        Properties properties = MailProperties.mailProperties;
        return Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
    }
}