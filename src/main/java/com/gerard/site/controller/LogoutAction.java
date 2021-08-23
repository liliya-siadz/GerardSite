package com.gerard.site.controller;

import com.gerard.site.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum LogoutAction implements Action {
    INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return InvalidateSessionAction.INSTANCE.execute(request,response);
    }
}
