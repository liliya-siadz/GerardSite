package com.gerard.GerardSite.controller.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Action {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
