package com.gerard.site.dao.impl;

import com.gerard.site.service.entity.RequestEntity;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Date;

import static org.testng.Assert.*;

public class RequestDaoImplTest {
    @DataProvider(name = "requestToCreate")
    public Object[][] requestToCreate() {
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setEmail("sidelnikova.liliya2.@gmail.com");
        requestEntity.setContent("Запрос №6");
        requestEntity.setDateFact(Date.valueOf("2021-08-12"));
        requestEntity.setDogId(5);
        return new Object[][]{
                {requestEntity},
        };
    }

    @Test(dataProvider = "requestToCreate", enabled = false)
    public void testCreate(RequestEntity requestEntity) throws DaoException {
        boolean expected =
                RequestDaoImpl.getInstance().create(requestEntity);
        assertTrue(expected);
    }
}