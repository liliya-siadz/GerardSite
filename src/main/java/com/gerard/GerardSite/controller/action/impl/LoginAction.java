package com.gerard.GerardSite.controller.action.impl;

import com.gerard.GerardSite.controller.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum LoginAction implements Action {
    INSTANCE;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lila= "LILA";
        request.setAttribute("lila",lila);
        return "/sign";
    }
}
