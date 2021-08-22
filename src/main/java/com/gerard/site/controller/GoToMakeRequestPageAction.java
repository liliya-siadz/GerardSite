package com.gerard.site.controller;

import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.DogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToMakeRequestPageAction implements Action {

    INSTANCE("allPuppies");

    GoToMakeRequestPageAction(String viewAttributeName){
        this.viewAttributeName = viewAttributeName;
    }

    private final String viewAttributeName;

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServiceException {
        List<DogEntity> allPuppies =  DogService.getInstance().provideAllPuppies();
        request.setAttribute(viewAttributeName, allPuppies);
       // request.getSession().setAttribute(viewAttributeName, dogEntity.get());
      //  request.getSession().setAttribute(changedAttributeName, dogEntity.get());
        return Page.MAKE_REQUEST.getUrl();
    }
}
