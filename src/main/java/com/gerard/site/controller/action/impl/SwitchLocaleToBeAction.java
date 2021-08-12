package com.gerard.site.controller.action.impl;

import com.gerard.site.controller.action.Action;
import com.gerard.site.util.HttpServletRequestUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum SwitchLocaleToBeAction implements Action {
    INSTANCE;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cookie locale = new Cookie("locale", "be");
        Cookie bundle = new Cookie("bundle", "locale_be");
        response.addCookie(locale);
        response.addCookie(bundle);
        String requestRefererUrl = request.getHeader("Referer");
        return requestRefererUrl;
    }
}
