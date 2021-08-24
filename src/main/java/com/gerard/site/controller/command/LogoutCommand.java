package com.gerard.site.controller.command;

import com.gerard.site.service.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum LogoutCommand implements Command {
    INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        return InvalidateSessionCommand.INSTANCE.execute(request,response);
    }
}
