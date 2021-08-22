package com.gerard.site.controller;

import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.DogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

enum GetAllDogsAction implements Action {
    INSTANCE("allDogs");

    GetAllDogsAction(String viewAttributeName){
        this.viewAttributeName = viewAttributeName;
    }

    private final String viewAttributeName;

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws ServiceException {
        List<DogEntity> allDogs =  DogService.getInstance().provideAllDogs();
        request.setAttribute(viewAttributeName, allDogs);
        return Page.DOGS.getUrl();
    }
}
