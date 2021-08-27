package com.gerard.site.service;

import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.view.Dog;

import java.util.List;
import java.util.Optional;

public interface DogService {
    Optional<DogEntity> find(int id) throws ServiceException;

    List<Dog> provideAllDogsForView() throws ServiceException;

    List<Dog> provideAllDogsForAdmin() throws ServiceException;

    List<Dog> provideAlPuppiesForView() throws ServiceException;

}
