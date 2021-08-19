package com.gerard.site.controller.action;

import com.gerard.site.localization.Language;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.gerard.site.localization.Language.BE;

public enum SwitchLocaleToBeAction implements Action {

    INSTANCE;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cookie locale = new Cookie(Language.LOCALE_CODE_COOKIE_NAME, BE.name().toLowerCase());
        Cookie bundle = new Cookie(Language.BUNDLE_COOKIE_NAME, BE.getBundleBaseName());
        response.addCookie(locale);
        response.addCookie(bundle);
        return Action.getRefererUrl(request);
    }
}
