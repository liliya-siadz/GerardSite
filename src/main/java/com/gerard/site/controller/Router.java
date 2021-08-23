package com.gerard.site.controller;

import com.gerard.site.controller.command.Command;
import com.gerard.site.controller.command.CommandFactory;
import com.gerard.site.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class created to route HTTP requests that coming to controller
 * (Controller class {@link Controller} .
 * <p>
 * To find needing route extracts special parameter from
 * request. Then uses this parameter value to find
 * and execute specified command by calling method
 * getCommand() in class com.gerard.site.controller.CommandFactory
 * {@link CommandFactory#getCommand(String)}.
 * </p>
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class Router {
    /**
     * Request parameter name to be extracted from the request .
     * Is using as command identifier
     */
    private static final String COMMAND_IDENTIFIER_REQUEST_PARAMETER_NAME = "command";

    /**
     * Root directory name where must be stored all pages .
     * Is using for preparing page path
     */
    private static final String DIRECTORY_NAME = "/pages";


    /**
     * File extension for pages. Is using for preparing page path
     */
    static final String FILE_EXTENSION = ".jsp";

    private static final Logger LOGGER = LogManager.getLogger(Router.class);


    private Router() {
    }

    /**
     * Prepares target page's url by extracting specified request's parameter.
     *
     * @param request  HTTP request to extract parameter from
     * @param response HTTP response assigned to this HTTP request
     * @return page's url
     */
    static String prepareUrl(HttpServletRequest request, HttpServletResponse response) {
        String commandName = request.getParameter(COMMAND_IDENTIFIER_REQUEST_PARAMETER_NAME);
        Command command = CommandFactory.INSTANCE.getCommand(commandName);
        String pageUrl;
        try {
            pageUrl = command.execute(request, response);
        } catch (ServiceException exception) {
            LOGGER.fatal("Unable to prepare page url! "
                    + "Command name: " + commandName
                    + "Command: " + command
                    + exception.getMessage(), exception);
            throw new RuntimeException("Unable to prepare page url! "
                    + "Command name: " + commandName
                    + "Command: " + command
                    + "Request: " + request
                    + exception.getMessage(), exception);
        }
        return pageUrl;
    }

    /**
     * Prepares target page's path, uses method, than
     * specifies page's url to it's path
     * {@link #prepareUrl(HttpServletRequest, HttpServletResponse)}
     *
     * @param request  HTTP request
     * @param response HTTP response assigned to this HTTP request
     * @return page path
     */
    static String preparePath(HttpServletRequest request, HttpServletResponse response) {
        String pageUrl = prepareUrl(request, response);
        String pagePath =  DIRECTORY_NAME + pageUrl + FILE_EXTENSION;
        return pagePath;
    }
}
