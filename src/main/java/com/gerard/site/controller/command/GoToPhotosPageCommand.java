package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.view.Photo;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.impl.PhotoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToPhotosPageCommand implements Command{
    INSTANCE;
    private final String viewAttributeName = "photosForView";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Photo> photosForView =
                PhotoServiceImpl.getInstance().provideAllPhotosForView();
        request.setAttribute(viewAttributeName, photosForView);
        return Page.PHOTOS.getPageUrl();
    }
}
