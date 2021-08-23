package com.gerard.site.controller;

import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.DogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.Optional;

public enum MakeRequestAction implements Action {
    INSTANCE;
    private String targetParameterName = "chosenPuppy";
    private String viewAttributeResultName = "isRequestMade";
    private String contentAttributeName="content";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String content = request.getParameter(contentAttributeName);



        DogEntity dogEntity = (DogEntity) request.getSession().getAttribute(targetParameterName);
        request.getSession().setAttribute(viewAttributeResultName,true);
        request.getSession().setAttribute(contentAttributeName, content);
        return Page.MAKE_REQUEST.getUrl();
    }
}
