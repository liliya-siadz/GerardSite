package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public enum LogoutCommand implements Command {
    INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        session.invalidate();
        return Page.HOME.getPageUrl();
    }
}
