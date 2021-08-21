package com.gerard.site.service;

import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DogServiceTest {

    @Test
    public void testProvideAllDogs() throws ServiceException {
        List<DogEntity> dogEntityList = DogService.getInstance().provideAllDogs();
        System.out.println(dogEntityList);
        assertNotNull(dogEntityList);
    }
}