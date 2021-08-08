package com.gerard.GerardSite.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomDocumentUtil {
    private static final Logger LOGGER = LogManager.getLogger(CustomDocumentUtil.class);

    //to-do rewrite to norm checker
    public static Properties loadResourcePropertiesByObject(
            Object objectToCallOn, String resourcePath)
            throws IOException, URISyntaxException {
        Class<?> classToLoad = objectToCallOn.getClass();
        URL resourceUrl = classToLoad.getResource(resourcePath);
        if (isResourceValidByUrl(resourceUrl)) {
            try (InputStream inputStream = classToLoad.getResourceAsStream(resourcePath)) {
                Properties properties = new Properties();
                properties.load(inputStream);
                return properties;
            }
        }
        else {
            LOGGER.error("File path: " + resourcePath  +  "is null or not readable");
            LOGGER.debug("Null will be returned");
            return null;
        }
    }

    public static boolean isUrlValid(String url){
        UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
        boolean isUrlValid = urlValidator.isValid(url);
        LOGGER.trace("URL: " + url + " is " + (isUrlValid? "valid":"not valid"));
        return isUrlValid;
    }
    public static boolean isResourceValidByUrl(URL resourceUrl){
        if(resourceUrl == null){
            LOGGER.info("Resource url is null");
            return false;
        }
        try {
            URI uri = resourceUrl.toURI();
            LOGGER.trace("uri:" + uri);
            Path path = Paths.get(uri);
            LOGGER.trace("path:" + path);
            boolean isResourceReadable = Files.isReadable(path);
            return isResourceReadable;
        } catch (URISyntaxException exception) {
            LOGGER.info(exception.getMessage() + ". Resource url: "
                    + resourceUrl, exception);
            return false;
        }
    }
}
