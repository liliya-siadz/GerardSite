package com.gerard.site.controller;

import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.form.RequestForm;
import com.gerard.site.service.DogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.Map;
import java.util.Optional;

public enum MakeRequestAction implements Action {
    INSTANCE;
    private String targetParameterName = "chosenPuppy";
    private String viewAttributeResultName = "isRequestMade";
    private String viewAttributeRequestName = "request";
    private String validationMapNameAttributeName="requestValidationMap";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        RequestForm requestForm = new RequestForm(request);
        Map<String, Boolean> validationMap = requestForm.getValidationMap();
        if (validationMap.containsValue(false)) {
            session.setAttribute(validationMapNameAttributeName, validationMap);

        } else {
            DogEntity dogEntity = (DogEntity) request.getSession().getAttribute(targetParameterName);
            request.getSession().setAttribute(viewAttributeResultName,true);
            //call service RequestService.createRequest to make request (put from chosen puppy id)
            //put to session request and content attribute
            // RequestWithUserDto (content + puppy + appUser )
        }
       // DogEntity dogEntity = (DogEntity) request.getSession().getAttribute(targetParameterName);
        //request.getSession().setAttribute(viewAttributeResultName,true);

        return Page.MAKE_REQUEST.getUrl();
    }
}
