package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.impl.DogServiceImpl;
import com.gerard.site.service.view.Dog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToAdminDogsPageCommand implements Command{
    INSTANCE;
    private final String viewAttributeName = "dogsForAdmin";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Dog> dogsForAdmin =  DogServiceImpl.getInstance().provideAllDogsForAdmin();
        request.getSession().setAttribute(viewAttributeName, dogsForAdmin);
        return Page.ADMIN_DOGS.getPageUrl();
    }
}
