package com.gerard.site.util.mail;

import com.gerard.site.connection.ConnectionPool;
import com.gerard.site.util.IdentifierUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class MailProperties {
    private static final Logger LOGGER = LogManager.getLogger(MailProperties.class);


    private MailProperties(){
        String mailPropertiesResourcePath = "/mail.properties";
        try {
        Properties mailProperties = IdentifierUtil.getPropertiesByPath(
                this, mailPropertiesResourcePath);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

}
