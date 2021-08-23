package com.gerard.site.controller;

import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.DogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToMakeRequestPageAction implements Action {

    INSTANCE;
    private final String viewAttributeName = "allPuppies";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServiceException {
        List<DogEntity> allPuppies =  DogService.getInstance().provideAllPuppies();
        InvalidateSessionAction.INSTANCE.execute(request,response);
        request.setAttribute(viewAttributeName, allPuppies);
        return Page.MAKE_REQUEST.getUrl();
    }
}
