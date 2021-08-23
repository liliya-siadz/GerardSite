package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.impl.DogServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToDogsPageCommand implements Command {
    INSTANCE;
    private final String viewAttributeName = "allActiveDogs";
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws ServiceException {
        List<DogEntity> allActiveDogs =  DogServiceImpl.getInstance().provideActiveDogs();
        request.setAttribute(viewAttributeName, allActiveDogs);
        return Page.DOGS.getPageUrl();
    }
}
