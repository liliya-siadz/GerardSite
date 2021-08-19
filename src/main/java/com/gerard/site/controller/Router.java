package com.gerard.site.controller;

import com.gerard.site.controller.action.Action;
import com.gerard.site.controller.action.ActionFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Class created to route HTTP requests that coming to role controller
 * (Controller class {@link Controller} .
 * <p>
 *    To find needing route extracts special parameter from
 *    request. Then uses this parameter value to find
 *    and execute specified action by calling method
 *    getAction() in class com.gerard.site.controller.action.ActionFactory
 *    {@link ActionFactory#getAction(String)}.
 * </p>
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
class Router {

    /**
     * Extracted request parameter name, using as action identifier
     */
    private static final String REQUEST_PARAMETER_NAME = "command";

    /**
     * Root directory name where must be stored all pages,
     * using for preparing page path
     */
    private static final String DIRECTORY_NAME = "/pages";

    /**
     * Page file extension, using for preparing page path
     */
    private static final String FILE_EXTENSION = ".jsp";

    private Router(){
    }

    /**
     * Extracts specified request's parameter to prepare page url .
     *
     * @param request HTTP request to extract parameter from
     * @param response HTTP response assigned to this HTTP request
     * @return page url
     */
    static String prepareUrl(HttpServletRequest request, HttpServletResponse response){
        String command = request.getParameter(REQUEST_PARAMETER_NAME);
        Action action = ActionFactory.INSTANCE.getAction(command);
        String url = action.execute(request, response);
        return url;
    }

    /**
     * Extracts specified request's parameter to prepare page path .
     *
     * @param request HTTP request to extract parameter from
     * @param response HTTP response assigned to this HTTP request
     * @return page path
     */
    static String preparePath(HttpServletRequest request, HttpServletResponse response) {
        String url = prepareUrl(request, response);
        String path = DIRECTORY_NAME + url + FILE_EXTENSION;
        return path;
    }
}
