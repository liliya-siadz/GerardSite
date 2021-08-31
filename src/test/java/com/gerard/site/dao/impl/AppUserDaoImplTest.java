package com.gerard.site.dao.impl;

import com.gerard.site.service.entity.AppUserEntity;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AppUserDaoImplTest {
    @DataProvider(name = "existingOrNewValidUsers")
    public Object[][] existingOrNewValidUsers() {
        AppUserEntity newUser = new AppUserEntity();
        newUser.setEmail("valentina.sobchak@gmail.com");
        newUser.setSurname("Sobchak");
        newUser.setName("Valentina");
        newUser.setPatronymic("Petrovna");
        newUser.setPhone("441457896");

        AppUserEntity existingUser = new AppUserEntity();
        existingUser.setEmail("sidelnikova.liliya2.@gmail.com");
        existingUser.setSurname("Сидельникова");
        existingUser.setName("Лилия");
        existingUser.setPatronymic("");
        existingUser.setPhone("290000000");
        return new Object[][]{
                {existingUser},
                {existingUser}
        };
    }

    @Test(dataProvider = "existingOrNewValidUsers", enabled = false)
    public void testCreateIfExistsOtherwiseUpdate(AppUserEntity validUser)
            throws DaoException {
        boolean expected =
                AppUserDaoImpl.getInstance()
                        .createIfExistsOtherwiseUpdate(validUser);
        assertTrue(expected);
    }
}