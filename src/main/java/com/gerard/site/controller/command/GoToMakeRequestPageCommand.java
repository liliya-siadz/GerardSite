package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.impl.DogServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToMakeRequestPageCommand implements Command {
    INSTANCE;
    private final String viewAttributeName = "allPuppies";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServiceException {
        List<DogEntity> allPuppies =  DogServiceImpl.getInstance().provideActivePuppies();
        InvalidateSessionCommand.INSTANCE.execute(request,response);
        request.setAttribute(viewAttributeName, allPuppies);
        return Page.MAKE_REQUEST.getPageUrl();
    }
}
