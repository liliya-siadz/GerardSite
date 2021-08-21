package com.gerard.site;

import com.gerard.site.connection.ConnectionException;
import com.gerard.site.connection.ConnectionPool;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.mail.AppMailSender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import javax.mail.internet.MimeMessage;
import java.sql.Connection;

public class Main {


    public static void main(String[] args) throws ServiceException, ConnectionException {
        // Connection connection = ConnectionPool.getInstance().giveOutConnection();

        String recipient = "sidelnikova.liliya@gmail.com";
        String subject = "test mail service";
        String content = "Hello, this is email from javvva!";
        AppMailSender appMailSender = new AppMailSender(subject, content, recipient);
        appMailSender.send();
    }
}
