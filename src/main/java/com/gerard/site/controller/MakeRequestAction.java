package com.gerard.site.controller;

import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.DogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public enum MakeRequestAction implements Action {
    INSTANCE;

    private String targetParameterName = "chosenPuppy";
    private String viewAttributeResultName = "isRequestMade";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        DogEntity dogEntity = (DogEntity) request.getSession().getAttribute(targetParameterName);
        request.getSession().setAttribute(viewAttributeResultName,true);
        String content = request.getParameter("content");
        request.getSession().setAttribute("content", content);
        return Page.MAKE_REQUEST.getUrl();
    }
}
