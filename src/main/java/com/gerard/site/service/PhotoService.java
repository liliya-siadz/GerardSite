package com.gerard.site.service;

import com.gerard.site.dto.PhotoAndDog;
import com.gerard.site.exception.ServiceException;

import java.util.List;

public interface PhotoService {
    List<PhotoAndDog> provideAllPhotosAndDogs() throws ServiceException;
}
