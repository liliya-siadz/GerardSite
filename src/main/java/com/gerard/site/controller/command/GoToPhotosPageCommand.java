package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.entity.PhotoAndDog;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.impl.PhotoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToPhotosPageCommand implements Command{
    INSTANCE;
    private final String viewAttributeName = "allPhotosWithDogsName";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<PhotoAndDog> allPhotosWithDogsName =  PhotoServiceImpl.getInstance().provideAllPhotosOfDogs();
        request.setAttribute(viewAttributeName, allPhotosWithDogsName);
        return Page.PHOTOS.getPageUrl();
    }
}
