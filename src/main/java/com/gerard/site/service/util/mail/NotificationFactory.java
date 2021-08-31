package com.gerard.site.service.util.mail;

import com.gerard.site.service.ServiceException;
import com.gerard.site.service.entity.AppUserEntity;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.entity.RequestEntity;
import com.gerard.site.service.view.admin.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NotificationFactory {
    private static final Logger LOGGER
            = LogManager.getLogger(NotificationFactory.class);

    private NotificationFactory(){};

    public static AppMailMessage createNotificationForAdminNewRequest(
            DogEntity dogEntity, RequestEntity requestEntity,
            AppUserEntity appUserEntity)
            throws ServiceException {
        if(dogEntity == null || requestEntity ==null || appUserEntity == null) {
            LOGGER.error("Unable to createMessage!"
                    + "Parameters has null values!");
            throw new ServiceException("Unable to createMessage! "
                    + "Parameters has null values!");
        }
        String subject = "New request!";
        String recipient = "gerard.kennel@inbox.ru";
        String content = new StringBuffer()
                .append("<------------Request for puppy------------->")
                .append("\nbirthday: ").append(dogEntity.getBirthday())
                .append("\nnickname: ").append(dogEntity.getNickname())
                .append("\nsex:" ).append(dogEntity.getDogSex().name().toLowerCase())
                .append("\nfullname:" ).append(dogEntity.getFullname())
                .append("-------------------")
                .append("\ncontent: ").append(requestEntity.getContent())
                .append("\ndate: ").append(requestEntity.getDateFact())
                .append("\n--------------------")
                .append("\nfrom :")
                .append(appUserEntity.getName()).append(" ")
                .append(appUserEntity.getSurname()).append(" ")
                .append(appUserEntity.getPatronymic()).append("\n")
                .append("\n[--------------------")
                .append("\nCONTACTS" )
                .append("\nemail : ").append(appUserEntity.getEmail())
                .append("\nphone :" ).append(appUserEntity.getPhone())
                .append("\n--------------------]")
                .toString();
        AppMailMessage newRequestForPuppyMessage =
                new AppMailMessage(subject, content, recipient);
        return newRequestForPuppyMessage;
    }

    public static AppMailMessage createClientNotificationForRequest(Request request)
            throws ServiceException {
        if(request == null) {
            LOGGER.error("Unable to createMessage!"
                    + "Parameters 'request' is null!");
            throw new ServiceException("Unable to createMessage!"
                    + "Parameters 'request' is null!");
        }
        String subject = "ANSWER FOR PUPPY REQUEST FROM gerard.com";
        String recipient = request.getEmail();
        String content = new StringBuffer()
                .append("<------------Request for puppy------------->")
                .append("\nbirthday: ").append(request.getBirthday())
                .append("\nnickname: ").append(request.getNickname())
                .append("\nsex:" ).append(request.getDogSex().name().toLowerCase())
                .append("\nfullname:" ).append(request.getFullname())
                .append("\ncontent: ").append(request.getContent())
                .append("\ndate: ").append(request.getDateFact())
                .append("\n----------------------------------------->")
                .append("\nOUR REPLY:").append(request.getReply())
                .toString();
        AppMailMessage replyOnPuppyRequest =
                new AppMailMessage(subject, content, recipient);
        return replyOnPuppyRequest;
    }
}
