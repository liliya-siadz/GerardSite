package com.gerard.site.controller.action.impl;

import com.gerard.site.controller.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum TransferToErrorPageAction implements Action {
    INSTANCE;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }
}
