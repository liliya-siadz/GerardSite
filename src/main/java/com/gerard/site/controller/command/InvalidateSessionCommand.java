package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum InvalidateSessionCommand implements Command {
    INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        request.getSession().isNew();
        return Page.HOME.getPageUrl();
    }
}
