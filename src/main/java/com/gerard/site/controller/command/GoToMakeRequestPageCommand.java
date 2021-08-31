package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.impl.DogServiceImpl;
import com.gerard.site.service.view.Dog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public enum GoToMakeRequestPageCommand implements Command {
    INSTANCE;
    private final String viewAttributeName = "puppies";
    private final String isPuppyChosenAttributeName = "isPuppyChosen";
    private String isRequestMadeAttributeName = "isRequestMade";
    private final String chosenPuppyAttributeName = "chosenPuppy";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServiceException {
        List<Dog> puppies = DogServiceImpl.getInstance().provideAllPuppiesForView();
        InvalidateSessionCommand.INSTANCE.execute(request, response);
        HttpSession session = request.getSession();
        session.setAttribute(isPuppyChosenAttributeName, false);
        request.setAttribute(viewAttributeName, puppies);
        session.removeAttribute(chosenPuppyAttributeName);
        session.setAttribute(isRequestMadeAttributeName, false);
        return Page.MAKE_REQUEST.getPageUrl();
    }
}
