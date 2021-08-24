package com.gerard.site.service;

import com.gerard.site.service.entity.PhotoAndDog;
import com.gerard.site.service.entity.PhotoEntity;

import java.util.List;

public interface PhotoService {
    List<PhotoAndDog> provideAllPhotosOfDogs() throws ServiceException;
    List<PhotoEntity> provideAllPhotos() throws ServiceException;
}
