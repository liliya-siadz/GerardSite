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
        //1 read login and password from request parameters
        //2 validate it
        //3 if ok: send redirect to home page
        //4 if not ok: redirect to same pages
        // of this fields in session attributes with map of fields errors
        return "/gerard/client/my_requests";
    }
}
