package com.gerard.site.util;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Optional;

public class HttpServletRequestUtil {
    private static final Logger LOGGER =
            LogManager.getLogger(CustomDocumentUtil.class);

    //docs: https://datatracker.ietf.org/doc/html/rfc2616#page-29
    //where any two-letter primary-tag is an ISO-639 language abbreviation
    public static String[] getAcceptedLanguagesCodesFromHeader(HttpServletRequest request) {
        String requestHeaderName = "Accept-Language";
        String localeCodeSeparator = ",";
        int localeCodeLettersQuantity = 2;
        String acceptedLanguagesRow = request.getHeader(requestHeaderName);
        String[] acceptedLanguages = CustomStringUtil.splitByRegex(acceptedLanguagesRow,
                localeCodeSeparator, true);
        String[] acceptedLanguagesCodes = new String[acceptedLanguages.length];
        for (int i = 0; i < acceptedLanguages.length; i++) {
            acceptedLanguagesCodes[i] = acceptedLanguages[i].substring(0,
                    localeCodeLettersQuantity);
        }
        return acceptedLanguagesCodes;
    }

    //null return
    public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
         Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if(cookies[i].getName().equals(cookieName)){
                LOGGER.info("Cookie: " + cookieName + " was found.");
                return cookies[i];
            }
        }
        LOGGER.warn("Null will be returned!");
        return null;
    }
}
