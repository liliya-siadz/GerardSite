package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.entity.PhotoEntity;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.impl.PhotoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToAdminPhotosPageCommand implements Command {
    INSTANCE;
    private final String viewAttributeName = "photosForAdmin";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<PhotoEntity> photosForAdmin =  PhotoServiceImpl.getInstance().provideAllPhotosForAdmin();
        request.getSession().setAttribute(viewAttributeName, photosForAdmin);
        return Page.ADMIN_PHOTOS.getPageUrl();
    }
}
