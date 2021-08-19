package com.gerard.site.controller;

import com.gerard.site.localization.Language;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

enum SetLocaleToEnAction implements Action {
    INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Action.changeLocale(request, response, Language.EN);
    }
}
