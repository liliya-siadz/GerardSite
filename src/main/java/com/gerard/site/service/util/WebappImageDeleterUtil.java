package com.gerard.site.service.util;

import com.gerard.site.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class WebappImageDeleterUtil {
    private static final String IMAGE_FOLDER = "src/main/webapp/";
    private static final String DELETER_LIST_PATH = "/deleter_list.txt";
    private static final Logger LOGGER =
            LogManager.getLogger(WebappImageDeleterUtil.class);

    private WebappImageDeleterUtil() {
    }

    public static boolean addPathToDeleterList(String path)
            throws ServiceException {
        if (path == null) {
            LOGGER.error("Parameter 'path' is null!");
            throw new ServiceException("Parameter 'path' is null");
        }
        try {
            FileWriter fileWriter = new FileWriter(getDeleterFileName());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(IMAGE_FOLDER + path);
            bufferedWriter.newLine();
            bufferedWriter.close();
            return true;
        } catch (URISyntaxException | IOException exception) {
            LOGGER.error("Path: " + path + " wasn't added to deleter list!");
            throw new ServiceException(
                    "Path: " + path + " wasn't added to deleter list!");
        }
    }

    public static boolean execute() throws ServiceException {
        try {
            FileReader fileReader = new FileReader(getDeleterFileName());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> allFilesWereDeleter
                    = bufferedReader.lines().collect(Collectors.toList());
            allFilesWereDeleter.removeIf(path -> {
                try {
                    boolean wasFileDeleted = Files.deleteIfExists(Path.of(path));
                    return wasFileDeleted;
                } catch (IOException exception) {
                    LOGGER.error("Unable to delete file: " + path
                            + exception.getMessage(), exception);
                    return false;
                }
            });
            return allFilesWereDeleter.isEmpty();
        } catch (URISyntaxException | IOException exception) {
            LOGGER.error("Unable to delete files in specified list. "
                    + "List's file resource: " + DELETER_LIST_PATH
                    + exception.getMessage(), exception);
            throw new ServiceException("Unable to delete files in specified list. "
                    + "List's file resource: " + DELETER_LIST_PATH
                    + exception.getMessage(), exception);
        }
    }

    private static File getDeleterFileName()
            throws ServiceException, URISyntaxException {
        URL deleterList = WebappImageDeleterUtil.class.getResource(DELETER_LIST_PATH);
        if (deleterList == null) {
            LOGGER.error("Deleter list's file:" + DELETER_LIST_PATH + "is missing!");
            throw new ServiceException(
                    "Deleter list's file:" + DELETER_LIST_PATH + "is missing!");
        }
        File deleterFile = Paths.get(deleterList.toURI()).toFile();
        LOGGER.info("Deleter file: " + deleterFile + " was created .");
        return deleterFile;
    }
}
