package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.entity.AppUserEntity;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.ServiceException;
import com.gerard.site.controller.form.RequestForm;
import com.gerard.site.service.entity.RequestEntity;
import com.gerard.site.service.impl.RequestServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import static com.gerard.site.validator.ValidatorIdentifier.*;

public enum MakeRequestCommand implements Command {
    INSTANCE;
    private String chosenPuppyAttributeName = "chosenPuppy";
    private String isRequestMadeAttributeName = "isRequestMade";
    private String validationMapNameAttributeName = "requestValidationMap";
    private String viewResultEmailAttributeName = "email";
    private String viewResultPhoneAttributeName = "phone";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        String email = request.getParameter(EMAIL_PARAMETER_NAME);
        String content = request.getParameter(CONTENT_PARAMETER_NAME);
        String name = request.getParameter(APP_USER_NAME_PARAMETER_NAME);
        String surname = request.getParameter(APP_USER_SURNAME_PARAMETER_NAME);
        String patronymic = request.getParameter(APP_USER_PATRONYMIC_PARAMETER_NAME);
        String phone = request.getParameter(PHONE_PARAMETER_NAME);
        RequestForm requestForm = new RequestForm();
        requestForm.setEmail(email);
        requestForm.setContent(content);
        requestForm.setName(name);
        requestForm.setSurname(surname);
        requestForm.setPatronymic(patronymic);
        requestForm.setPhone(phone);
        Map<String, Boolean> validationMap = requestForm.validateForm();
        if (validationMap.containsValue(false)) {
            session.setAttribute(validationMapNameAttributeName, validationMap);
        } else {
            DogEntity dogEntity =
                    (DogEntity) session.getAttribute(chosenPuppyAttributeName);
            AppUserEntity appUserEntity = new AppUserEntity();
            appUserEntity.setEmail(email);
            appUserEntity.setSurname(surname);
            appUserEntity.setName(name);
            appUserEntity.setPatronymic(patronymic);
            appUserEntity.setPhone(phone);
            RequestEntity requestEntity = new RequestEntity();
            requestEntity.setDogId(dogEntity.getId());
            requestEntity.setDateFact(Date.valueOf(LocalDate.now()));
            requestEntity.setEmail(email);
            requestEntity.setContent(content);
            boolean isRequestWasMade =
                    RequestServiceImpl.getInstance()
                            .sendRequest(dogEntity, requestEntity, appUserEntity);
            session.setAttribute(isRequestMadeAttributeName, isRequestWasMade);
            session.setAttribute(viewResultEmailAttributeName,email);
            session.setAttribute(viewResultPhoneAttributeName,phone);
        }
        return Page.MAKE_REQUEST.getPageUrl();
    }
}
