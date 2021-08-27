package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.impl.DogServiceImpl;
import com.gerard.site.service.view.Dog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToDogsPageCommand implements Command {
    INSTANCE;
    private final String viewAttributeName = "dogsForView";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws ServiceException {
        List<Dog> dogsForView =  DogServiceImpl.getInstance().provideAllDogsForView();
        request.setAttribute(viewAttributeName, dogsForView);
        return Page.DOGS.getPageUrl();
    }
}
