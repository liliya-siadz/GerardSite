package com.gerard.site.service.mail;

import com.gerard.site.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AppMailSender {
    private static final String messageType= "text/html";
    private static final Message.RecipientType recipientType =  Message.RecipientType.TO;
    private static final Logger LOGGER = LogManager.getLogger(AppMailSender.class);
    private String subject;
    private String content;
    private String recipient;

    public AppMailSender(String subject, String content, String recipient) {
        this.subject = subject;
        this.content = content;
        this.recipient = recipient;
    }

    public boolean send() throws ServiceException {
        Session session = MailSessionFactory.createSession();
        session.setDebug(true);    //todo what is it
        MimeMessage message = new MimeMessage(session);
        try {
            message.setSubject(subject);
            message.setContent(content, messageType);
            Address address = new InternetAddress(recipient);
            message.setRecipient(recipientType, address);
            Transport.send(message);
            LOGGER.info("Message: '" + content + "' was " + "sent to address: " + address);
            return true;
        } catch (MessagingException exception) {
            LOGGER.error("Unable to send message! "
                            + exception.getMessage(), exception);
            throw new ServiceException("Unable to send message! "
                    + exception.getMessage(), exception);
        }
    }
}
