package com.gerard.site.service.util;

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

/**
 * Custom util for servicing different identifiers
 * such as url and path.
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class AppIdentifierUtil {
    private static final Logger LOGGER =
            LogManager.getLogger(AppIdentifierUtil.class);

    private AppIdentifierUtil() {
    }

    /**
     * Checks url validness by it's string representation .
     * Creates and uses {@code UrlValidator} object
     * {@link UrlValidator} inside .
     *
     * @param url string representation of url to checks it's validness
     * @return returns true if url is valid,
     * otherwise returns false
     */
    public static boolean isUrlValid(String url) {
        UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
        boolean isUrlValid = urlValidator.isValid(url);
        LOGGER.trace("URL: " + url + " is " + (isUrlValid ? "valid" : "not valid"));
        return isUrlValid;
    }

    /**
     * Provides properties, that is located in resource folder .
     * <b>May produce RuntimeException {@link RuntimeException}.</b>
     *
     * @param objectToCallOn object which class will use these properties
     * @param path           relative to resources folder path of properties
     * @return returns provided properties if:
     * <ul>
     *     <li>path is readable</li>
     *     <li>using input stream was not null</li>
     *     <li>loaded properties is not empty</li>
     * </ul> , otherwise throws RuntimeException
     * @throws RuntimeException
     */
    public static Properties getPropertiesByPath(
            Object objectToCallOn, String path) {
        Class<?> classToLoad = objectToCallOn.getClass();
        URL resourceUrl = classToLoad.getResource(path);
        boolean isResourceReadable = false;
        boolean isInputStreamNull = true;
        boolean isPropertiesEmpty = true;
        try {
            isResourceReadable = isResourceReadable(resourceUrl);
            if (isResourceReadable) {
                try (InputStream inputStream = classToLoad.getResourceAsStream(path)) {
                    isInputStreamNull = (inputStream == null);
                    if (!isInputStreamNull) {
                        Properties properties = new Properties();
                        properties.load(inputStream);
                        isPropertiesEmpty = (properties.isEmpty());
                        if (!isPropertiesEmpty) {
                            return properties;
                        }
                    }
                } catch (IOException exception) {
                    LOGGER.error("Errors while loading resource: " + path
                    + exception.getMessage(), exception);
                }
            }
        } catch (URISyntaxException exception) {
            LOGGER.error("Errors while loading resource: " + path
                    + exception.getMessage(), exception);
        }
        LOGGER.info("Specified path: " + path + " is "
                + (isResourceReadable ? "readable" : "not readable"));
        LOGGER.info("Resources input stream: " + "is "
                + (isInputStreamNull ? "null" : "not null"));
        LOGGER.info("Properties " + (isPropertiesEmpty ? "was loaded" : "was not loaded"));
        LOGGER.fatal("Properties didn't load correctly! " + " Resource FILE: '" + path + "'");
        throw new RuntimeException("Properties didn't load correctly! "
                + " Resource FILE: '" + path + "'");
    }

    /**
     * Checks resources readability by it's url representation .
     * <b>May return null value .</b>
     *
     * @param resourceUrl resource's url
     * @return false if resource's url is null,
     * otherwise returns result
     * of method {@code Files.isReadable(java.nio.file.Path)},
     * {@link Files#isReadable(Path)}
     */
    public static boolean isResourceReadable(URL resourceUrl)
            throws URISyntaxException {
        if (resourceUrl == null) {
            LOGGER.trace("Parameter 'resourceUrl' is null");
            LOGGER.warn("Resource's url is null");
            return false;
        } else {
            URI uri = resourceUrl.toURI();
            LOGGER.trace("Resource's uri:" + uri);
            Path path = Paths.get(uri);
            LOGGER.trace("Resource's path:" + path);
            return Files.isReadable(path);
        }
    }
}
