package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.entity.DogEntity;
import com.gerard.site.entity.RequestAndAppUserAndDog;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.RequestService;
import com.gerard.site.service.impl.DogServiceImpl;
import com.gerard.site.service.impl.RequestServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToAdminRequestsPageCommand implements Command {
    INSTANCE;
    private final String viewAttributeName = "allRequests";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<RequestAndAppUserAndDog> allRequests =
                RequestServiceImpl.getInstance().provideAllRequests();
        request.setAttribute(viewAttributeName, allRequests);
        return Page.ADMIN_REQUESTS.getPageUrl();
    }
}
