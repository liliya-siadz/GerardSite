package com.gerard.site.controller.action.impl;

import com.gerard.site.controller.action.Action;
import com.gerard.site.util.HttpServletRequestUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum SwitchLocaleToRuAction implements Action {
    INSTANCE;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cookie locale = new Cookie("locale", "ru");
        Cookie bundle = new Cookie("bundle", "locale_ru");
        response.addCookie(locale);
        response.addCookie(bundle);
        String requestRefererUrl = request.getHeader("Referer");
        return requestRefererUrl;
    }
}
