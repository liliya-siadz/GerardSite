package com.gerard.site.util;

import com.gerard.site.connection.ConnectionException;
import com.gerard.site.connection.ConnectionPool;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

public class BCryptTest {

    @DataProvider(name = "dataProviderTestHashPwDifferentPasswords")
    public Object[][] dataProviderTestHashPwDifferentPasswords() {
        return new Object[][]{
                {"loesf3dwe4"},
                {"hellp123"},
                {"world_isAwesome!"}
        };
    }

    @Test(dataProvider = "dataProviderTestHashPwDifferentPasswords",
            description = "Testing that password and it's hashed version "
                    + "are not equals")
    public void testHashpw1(String password) {
        String actual = BCrypt.hashpw(password, BCrypt.gensalt());
        assertNotEquals(password, actual);
    }

    @Test(dataProvider = "dataProviderTestHashPwDifferentPasswords",
            description = "Testing that password's hashed version"
                    + "has bigger length")
    public void testHashpw2(String password) {
        String actual = BCrypt.hashpw(password, BCrypt.gensalt());
        assertTrue(actual.length() > password.length());
    }

    @DataProvider(name = "dataProviderTestCheckpwPasswordAndHash")
    public Object[][] dataProviderTestCheckpwPasswordAndHash() {
        return new Object[][]{
                {"lollypop1", BCrypt.hashpw("lollypop1", BCrypt.gensalt())},
                {"rainbowAndLabour56", BCrypt.hashpw("rainbowAndLabour56", BCrypt.gensalt())},
                {"asdasf3650002", BCrypt.hashpw("asdasf3650002", BCrypt.gensalt())}
        };
    }

    @Test(description = "Testing that unhashed password equals to original"
            + "not hashed password",
            dataProvider = "dataProviderTestCheckpwPasswordAndHash")
    public void testCheckpw(String password, String hashedPassword) {
        assertTrue(BCrypt.checkpw(password, hashedPassword));
    }

    @Test
    public void test() throws ConnectionException, SQLException {

        String selectUserPassword = "select password from app_user";
        Connection connection = ConnectionPool.getInstance().giveOutConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectUserPassword);
        List<String> passwords = new ArrayList<>();
        while (resultSet.next()) {
            passwords.add(resultSet.getString(1));
        }

        resultSet.close();
        statement.close();
        ConnectionPool.getInstance().getBackConnection(connection);

        for (String password : passwords) {
            System.out.println(BCrypt.checkpw("strekoza18", password));
        }
    }
}
