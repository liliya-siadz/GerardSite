package com.gerard.site.util.mail;

import com.gerard.site.util.AppIdentifierUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class MailProperties {
    private static final Logger LOGGER = LogManager.getLogger(MailProperties.class);


    private MailProperties(){
        String mailPropertiesResourcePath = "/mail.properties";
        Properties mailProperties = AppIdentifierUtil.getPropertiesByPath(this, mailPropertiesResourcePath);

    }

}
