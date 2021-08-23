package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum GoToAdminRequestsPageCommand implements Command {
    INSTANCE;
    //TODO
    //SHOW ALL + check role
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        return Page.ADMIN_REQUESTS.getPageUrl();
    }
}
