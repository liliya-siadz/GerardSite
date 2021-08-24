package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.controller.form.RequestForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import static com.gerard.site.validator.Fields.*;

public enum MakeRequestCommand implements Command {
    INSTANCE;
    private String targetParameterName = "chosenPuppy";
    private String viewAttributeResultName = "isRequestMade";
    private String viewAttributeRequestName = "request";
    private String validationMapNameAttributeName="requestValidationMap";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        String email = request.getParameter(EMAIL_PARAMETER_NAME);
        String content = request.getParameter(CONTENT_PARAMETER_NAME);
        String name = request.getParameter(APP_USER_NAME_PARAMETER_NAME);
        String surname = request.getParameter(APP_USER_SURNAME_PARAMETER_NAME);
        String patronymic = request.getParameter(APP_USER_PATRONYMIC_PARAMETER_NAME);
        String phone  = request.getParameter(PHONE_PARAMETER_NAME);
        RequestForm requestForm = new RequestForm(
                email, content, name, surname, patronymic,phone);
        Map<String, Boolean> validationMap = requestForm.validateForm();
        if (validationMap.containsValue(false)) {
            session.setAttribute(validationMapNameAttributeName, validationMap);
        } else {
            DogEntity dogEntity = (DogEntity) request.getSession().getAttribute(targetParameterName);
            request.getSession().setAttribute(viewAttributeResultName,true);

            //call service RequestService.createRequest to make request
            //RequestAndAppUserWithMessage result
            // = createRequest(Integer dogId, RequestForm requestForm)
            //put to session request and content attribute
            //parse RequestAndAppUserWithMessage
            //request attrs,user info, message
        }
        return Page.MAKE_REQUEST.getPageUrl();
    }
}
