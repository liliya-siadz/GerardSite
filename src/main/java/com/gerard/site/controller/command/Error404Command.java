package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum Error404Command implements Command {
    INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.ERROR_404.getPageUrl();
    }
}
