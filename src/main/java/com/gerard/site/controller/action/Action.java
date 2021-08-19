package com.gerard.site.controller.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Action {

    String REQUEST_HEADER_REFERER_HEADER_NAME = "Referer";
    String APPLICATION_CONTEXT = "/gerard";

    static String getRefererUrl(HttpServletRequest request){
        String requestRefererUrl = request.getHeader(REQUEST_HEADER_REFERER_HEADER_NAME);
        return requestRefererUrl;
    }

    /**
     *
     * @param request
     * @param response
     * @return url
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
