package com.gerard.site.service.util.mail;

import com.gerard.site.service.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.testng.Assert.*;

public class AppMailMessageTest {

    private static String testMessageContent = "test app mail sender";
    private static String testMessageSubject = "mail sender test: " +
            LocalDate.now(ZoneId.of("Europe/Paris"));

    @DataProvider(name = "dataProviderTestSend")
    public Object[][] dataProviderTestSend() {
        return new Object[][]{
                {"sidelnikova.liliya@gmail.com"},
                {"johnny890legend@gmail.com"},
                {"spearsb149@gmail.com"},
                {"gerard.kennel@inbox.ru"}
        };
    }

    @Test(dataProvider = "dataProviderTestSend")
    public void testSendToCorrectRecipients(String recipient) throws ServiceException {
        AppMailMessage message = new AppMailMessage(testMessageSubject,
                testMessageContent, recipient);
        assertTrue(message.send());
    }

    @DataProvider(name = "dataProviderBlankOrEmptyRecipientsTestSend")
    public Object[][] dataProviderBlankOrEmptyRecipientsTestSend() {
        return new Object[][]{
                {"    "},
                {"                  "},
                {""},
                {null}
        };
    }

    @Test(dataProvider = "dataProviderBlankOrEmptyRecipientsTestSend",
            expectedExceptions = ServiceException.class)
    public void testSendToBlankOrEmptyRecipients(String recipient) throws ServiceException {
        AppMailMessage message = new AppMailMessage(testMessageSubject,
                testMessageContent, recipient);
        assert (message.send());
    }
}