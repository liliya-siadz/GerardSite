package com.gerard.site.controller.action.impl;

import com.gerard.site.controller.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum LoginAction implements Action {
    INSTANCE;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lila= "LILA";
        request.setAttribute("lila",lila);
        //session saves role
        String requestRefererUrl = request.getHeader("Referer");
        return "http://localhost:8080/gerard/client/my_requests";
    }
}
