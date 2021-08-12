package com.gerard.site.controller.action.impl;

import com.gerard.site.controller.action.Action;
import com.gerard.site.service.Language;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.gerard.site.service.Language.RU;

public enum SwitchLocaleToRuAction implements Action {

    INSTANCE;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cookie locale = new Cookie(Language.LOCALE_CODE_COOKIE_NAME, RU.name().toLowerCase());
        Cookie bundle = new Cookie(Language.BUNDLE_COOKIE_NAME, RU.getBundleBaseName());
        response.addCookie(locale);
        response.addCookie(bundle);
        return Action.getRefererUrl(request);
    }
}
