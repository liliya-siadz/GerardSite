package com.gerard.site.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This util services HTTP servlet request {@link HttpServletRequest}
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class HttpServletRequestUtil {
    private static final Logger LOGGER = LogManager.getLogger(AppIdentifierUtil.class);

    private HttpServletRequestUtil() {
    }

    /**
     * Extracts accepted languages codes from request header 'Accept-Language',
     * <a href = "https://datatracker.ietf.org/doc/html/rfc2616#page-29"></a>
     * <i>where any two-letter primary-tag is an ISO-639 language abbreviation</i> .
     * <p>
     * Takes request header 'Accept-Language' value and
     * splits, cleans all non locale codes information .
     * Then put all found locale codes into string array .
     * </p>
     *
     * @param request request to get accepted languages from
     * @return returns array of accepted languages from request header
     */
    public static String[] getAcceptedLanguagesCodesFromHeader(
            HttpServletRequest request) {
        String requestHeaderName = "Accept-Language";
        String localeCodeSeparator = ",";
        String acceptedLanguagesRow = request.getHeader(requestHeaderName);
        String[] acceptedLanguages = CustomStringUtil.splitByRegex(
                acceptedLanguagesRow, localeCodeSeparator, true);
        String[] acceptedLanguagesCodes = new String[acceptedLanguages.length];
        int localeCodeLettersQuantity = 2;
        for (int i = 0; i < acceptedLanguages.length; i++) {
            acceptedLanguagesCodes[i] =
                    acceptedLanguages[i].substring(0, localeCodeLettersQuantity);
        }
        return acceptedLanguagesCodes;
    }

    /**
     * Finds cookie in the request by it's name, may return null .
     * <b>May return null value .</b>
     *
     * @param request    request to find cookie in
     * @param cookieName cookie name to find in the specified request
     * @return returns target cookie by specified name,
     * otherwise returns {@code null}
     */
    public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(cookieName)) {
                LOGGER.info("Cookie with name: '" + cookieName + "' was found.");
                return cookies[i];
            }
        }
        LOGGER.warn("Null will be returned . ");
        return null;
    }
}
