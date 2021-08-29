package com.gerard.site.controller.command;

import com.gerard.site.service.ServiceException;
import com.gerard.site.service.impl.RequestServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum AcceptRequestCommand implements Command{
    INSTANCE;
    private final String isRequestWasAcceptedAttributeName = "isRequestWasAccepted";
    private final String requestParameterToProcessName = "requestId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        int requestId = Integer.parseInt(request.getParameter(requestParameterToProcessName));
        boolean isRequestWasAccepted =
                RequestServiceImpl.getInstance().acceptRequest(requestId);
        request.setAttribute(isRequestWasAcceptedAttributeName, isRequestWasAccepted);
        return GoToAdminRequestsPageCommand.INSTANCE.execute(request, response);
    }
}
