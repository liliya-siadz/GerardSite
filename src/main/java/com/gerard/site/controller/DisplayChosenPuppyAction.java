package com.gerard.site.controller;

import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.DogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public enum DisplayChosenPuppyAction implements Action {
    INSTANCE;

    private final String targetParameterName = "chosenPuppyId";
    private final String viewAttributeName = "chosenPuppy";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        int selectedDogId = Integer.parseInt(request.getParameter(targetParameterName));
        Optional<DogEntity> dogEntity = DogService.getInstance().find(selectedDogId);
        if (dogEntity.isPresent()) {
            request.getSession().setAttribute(viewAttributeName, dogEntity.get());
        }
        return Page.MAKE_REQUEST.getUrl();
    }
}
