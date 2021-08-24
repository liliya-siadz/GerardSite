package com.gerard.site.controller.command;

import com.gerard.site.service.util.localization.Language;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum SetLocaleToBeCommand implements Command {
    INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Command.changeLocale(request, response, Language.BE);
    }
}
