package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.view.admin.Request;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.impl.RequestServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToAdminRequestsPageCommand implements Command {
    INSTANCE;
    private final String viewAttributeName = "requestsForAdmin";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Request> requestsForAdmin =
                RequestServiceImpl.getInstance().provideAllRequestsForAdmin();
        request.getSession().setAttribute(viewAttributeName, requestsForAdmin);
        return Page.ADMIN_REQUESTS.getPageUrl();
    }
}
