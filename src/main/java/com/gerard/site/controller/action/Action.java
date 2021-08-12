package com.gerard.site.controller.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Action {

    String requestHeaderRefererHeaderName = "Referer";
    static String getRefererUrl(HttpServletRequest request){
        String requestRefererUrl = request.getHeader(requestHeaderRefererHeaderName);
        return requestRefererUrl;
    }

    String execute(HttpServletRequest request, HttpServletResponse response);
}
