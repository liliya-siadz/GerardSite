package com.gerard.site.controller;

import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.DogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public enum DisplayPuppyAction implements Action {
    INSTANCE("chosenPuppy",
            "chosenPuppyDisplay","puppy");

    private final String targetParameterName;
    private final String viewAttributeName;
    private final String changedAttributeName;
    DisplayPuppyAction(String targetParameterName,
                       String viewAttributeName,
                       String changedAttributeName) {
        this.targetParameterName = targetParameterName;
        this.viewAttributeName = viewAttributeName;
        this.changedAttributeName = changedAttributeName;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        //SELECT name = request parameter name
//option value = request parameter value

        int selectedDogId = Integer.parseInt(request.getParameter(targetParameterName));
        Optional<DogEntity> dogEntity = DogService.getInstance().find(selectedDogId);
        if (dogEntity.isPresent()) {
            request.setAttribute(viewAttributeName, dogEntity.get());
            request.setAttribute(changedAttributeName, dogEntity.get());
        }
        return Page.MAKE_REQUEST.getUrl();
    }
}
