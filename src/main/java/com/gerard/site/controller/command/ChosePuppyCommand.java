package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.impl.DogServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public enum ChosePuppyCommand implements Command {
    INSTANCE;

    private final String chosenPuppyIdParameterName = "chosenPuppyId";
    private final String chosenPuppyAttributeName = "chosenPuppy";
    private final String isPuppyChosenAttributeName = "isPuppyChosen";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        int selectedDogId = Integer.parseInt(request.getParameter(chosenPuppyIdParameterName));
        Optional<DogEntity> dogEntity = DogServiceImpl.getInstance().find(selectedDogId);
        HttpSession session = request.getSession();
        session.setAttribute(chosenPuppyAttributeName, dogEntity.get());
        session.setAttribute(isPuppyChosenAttributeName, true);
        return Page.MAKE_REQUEST.getPageUrl();
    }
}
