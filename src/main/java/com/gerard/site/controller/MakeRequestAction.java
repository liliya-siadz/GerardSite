package com.gerard.site.controller;

import com.gerard.site.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum MakeRequestAction implements Action {
    INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return null;
    }
}
