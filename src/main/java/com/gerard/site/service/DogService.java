package com.gerard.site.service;

import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface DogService {
    Optional<DogEntity> find(int id) throws ServiceException;

    List<DogEntity> provideAllDogs() throws ServiceException;

    List<DogEntity> provideAllPuppies() throws ServiceException;
}
