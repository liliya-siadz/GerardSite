package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.impl.DogServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public enum GoToMakeRequestPageCommand implements Command {
    INSTANCE;
    private final String viewAttributeName = "allPuppies";
    private final String isPuppyChosenAttributeName = "isPuppyChosen";
    private String isRequestMadeAttributeName = "isRequestMade";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServiceException {
        List<DogEntity> allPuppies =  DogServiceImpl.getInstance().provideActivePuppies();
        InvalidateSessionCommand.INSTANCE.execute(request,response);
        HttpSession session = request.getSession();
        session.setAttribute(isPuppyChosenAttributeName, false);
        request.setAttribute(viewAttributeName, allPuppies);
        session.setAttribute(isRequestMadeAttributeName,false);
        return Page.MAKE_REQUEST.getPageUrl();
    }
}
