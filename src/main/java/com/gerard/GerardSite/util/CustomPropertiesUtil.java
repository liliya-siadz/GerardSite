package com.gerard.GerardSite.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomPropertiesUtil {
    private static final Logger LOGGER = LogManager.getLogger(CustomPropertiesUtil.class);

    public static Properties loadPropertiesByObject(
            Object objectToCallOn, String relativeResourcePath)
            throws IOException, URISyntaxException {
        Class<?> classToLoad = objectToCallOn.getClass();
        URL resourceUrl = classToLoad.getResource(relativeResourcePath);
        if ((resourceUrl != null) && Files.isReadable(Paths.get(resourceUrl.toURI()))) {
            try (InputStream inputStream = classToLoad.getResourceAsStream(relativeResourcePath)) {
                Properties properties = new Properties();
                properties.load(inputStream);
                return properties;
            }
        }
        LOGGER.error("File path: " + relativeResourcePath  +  "is null or not readable");
        LOGGER.debug("Null will be returned");
        return null;
    }
}


//}
//
//    public InputStream db_connection = getClass().getClassLoader().getResourceAsStream("db_connection.properties");
//
//    public static void gerard.com.main(String[] args) {
//        gerard.com.main main1 = new gerard.com.main();
//        Properties db_properties = new Properties();
//        try {
//            db_properties.load(main1.db_connection);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (Object o : db_properties.keySet()) {
//            System.out.print(o);
//            System.out.print("= ");
//            System.out.print(db_properties.getProperty(o.toString()));
//            System.out.println("");
//        }
//}
