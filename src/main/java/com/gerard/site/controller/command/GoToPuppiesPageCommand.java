package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.impl.DogServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToPuppiesPageCommand implements Command {
    INSTANCE;
    private final String viewAttributeName = "allPuppies";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        List<DogEntity> allPuppies =  DogServiceImpl.getInstance().provideActivePuppies();
        request.setAttribute(viewAttributeName, allPuppies);
        return Page.PUPPIES.getPageUrl();
    }
}
