package com.gerard.site.service;

import com.gerard.site.dto.RequestAndAppUserWithMessageDto;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.form.RequestForm;

import java.util.Optional;

public interface RequestService {
    Optional<RequestAndAppUserWithMessageDto> createRequest(
            int dogId, RequestForm requestForm) throws ServiceException;
}
