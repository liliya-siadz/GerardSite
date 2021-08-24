package com.gerard.site.service.util.mail;

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

public final class AppMailMessage {
    private static final String messageType = "text/html";
    private static final Message.RecipientType recipientType =
            Message.RecipientType.TO;
    private static final Logger LOGGER =
            LogManager.getLogger(AppMailMessage.class);
    private String subject;
    private String content;
    private String recipient;

    public AppMailMessage(String subject, String content, String recipient)
            throws ServiceException {
        if ((subject == null) || (content == null) || (recipient == null)
                || (recipient.isBlank())) {
            LOGGER.trace("Subject: " + subject);
            LOGGER.trace("Content: " + content);
            LOGGER.trace("Recipient: " + recipient);
            throw new ServiceException("Message parameters has null values!"
                    + " See logs for details...");
        }
        this.subject = subject;
        this.content = content;
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getRecipient() {
        return recipient;
    }

    public boolean send() throws ServiceException {
        Session session = MailSessionFactory.createSession();
        session.setDebug(true);
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof AppMailMessage appMailMessage) {
            return subject.equals(appMailMessage.getSubject())
                    && content.equals(appMailMessage.getContent())
                    && recipient.equals(appMailMessage.getRecipient());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int hashcode = hash + 31 * subject.hashCode();
        hashcode = 31 * hashcode + content.hashCode();
        hashcode = 31 * hashcode + recipient.hashCode();
        return hashcode;
    }

    @Override
    public String toString() {
        return "AppMailMessage{"
                + "subject='" + subject + '\''
                + ", content='" + content + '\''
                + ", recipient='" + recipient + '\''
                + '}';
    }
}