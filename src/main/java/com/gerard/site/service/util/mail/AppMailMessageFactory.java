package com.gerard.site.service.util.mail;

import com.gerard.site.service.ServiceException;
import com.gerard.site.service.entity.AppUserEntity;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.entity.RequestEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppMailMessageFactory {
    private static final Logger LOGGER = LogManager.getLogger(AppMailMessageFactory.class);

    private AppMailMessageFactory(){};
    public static AppMailMessage createAdminMessage(
            DogEntity dogEntity, RequestEntity requestEntity,
            AppUserEntity appUserEntity)
            throws ServiceException {
        if(dogEntity == null || requestEntity ==null || appUserEntity == null) {
            LOGGER.error("Unable to createMessage!"
                    + "Parameters has null values!");
            throw new ServiceException("Unable to createMessage! "
                    + "Parameters has null values!");
        }
        String subject = "New request for puppy!";
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
        AppMailMessage newRequestForDogMessage =
                new AppMailMessage(subject, content, recipient);
        return newRequestForDogMessage;
    }
}
