package com.gerard.site.controller.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum TransferToErrorPageAction implements Action {
    INSTANCE;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }
}
