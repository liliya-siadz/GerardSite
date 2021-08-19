package com.gerard.site.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

enum SignOutAction implements Action {
    INSTANCE;
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }
}
