package com.gerard.GerardSite.controller.action.impl;

import com.gerard.GerardSite.controller.action.Action;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum SwitchLocaleToEnAction implements Action {
    INSTANCE;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(new Cookie("bundle", "locale"));
        response.addCookie(new Cookie("locale", "en"));
        return null;

    }
}
