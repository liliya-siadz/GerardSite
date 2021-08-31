package com.gerard.site.controller.command;

import com.gerard.site.service.ServiceException;
import com.gerard.site.service.impl.PhotoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum DeletePhotoCommand implements Command {
    INSTANCE;
    private final String requestParameterToProcessName = "photoPath";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String photoPathToDelete
                = request.getParameter(requestParameterToProcessName);
        PhotoServiceImpl.getInstance().deletePhoto(photoPathToDelete);
        return GoToAdminPhotosPageCommand.INSTANCE.execute(request, response);
    }
}
