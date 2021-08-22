package com.gerard.site.controller;

import com.gerard.site.dto.PhotoWithDog;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.PhotoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

enum GetAllPhotosAction implements Action {

    INSTANCE("allPhotosWithDogsName");

    GetAllPhotosAction(String viewAttributeName){
        this.viewAttributeName = viewAttributeName;
    }

    private final String viewAttributeName;;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<PhotoWithDog> allPhotosWithDogsName =  PhotoService.getInstance().provideAllPhotosWithDogs();
        request.setAttribute(viewAttributeName, allPhotosWithDogsName);
        return Page.PHOTOS.getUrl();
    }
}
