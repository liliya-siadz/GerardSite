package com.gerard.site.controller.command;

import com.gerard.site.controller.Controller;
import com.gerard.site.controller.Router;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.util.localization.Language;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.gerard.site.service.util.localization.Language.BUNDLE_BASE_NAME_COOKIE_NAME;
import static com.gerard.site.service.util.localization.Language.LANGUAGE_CODE_COOKIE_NAME;

/**
 * This functional interface services commands
 * that came to router {@link Router} .
 * It's implementations should be used with {@code CommandFactory}
 * {@link CommandFactory}
 * <p>
 * Has static method that changes localization
 * by specified Language {@link Language}
 * {@link Command#changeLocale(HttpServletRequest, HttpServletResponse, Language)} .
 * </p>
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
@FunctionalInterface
public interface Command {
    String REQUEST_HEADER_REFERER_HEADER_NAME = "Referer";

    /**
     * Services commands that came to router .
     * Typically implementation may be like this:
     * <ol>
     *     <li>Taking some information from the request
     *     (from requests parameters | session | cookies) </li>
     *     <li>Executing some logic on this information. It could be business-logic,
     *      information from database or smth else. </li>
     *     <li>*(Optional step) Putting the result from the previous step
     *     to request (to request attributes or session and etc.) or response (cookies and etc.) </li>
     *     <li>Returning target url page. </li>
     * </ol>
     */
    String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException;

    /**
     * Gets came request's referer application context url
     *
     * @param request come request
     * @return url of come request
     */
    static String getRefererAppContextUrl(HttpServletRequest request) {
        String requestRefererUrl =
                request.getHeader(REQUEST_HEADER_REFERER_HEADER_NAME);
        String applicationContextUrl = requestRefererUrl.replace(
                Router.SCHEMA + Router.DOMAIN + Controller.APPLICATION_CONTEXT, "");
        return applicationContextUrl;
    }

    /**
     * Changes app locale to cookie specified  .
     * Sets locale value and bundle base name .
     *
     * @param request  came request
     * @param response response to this request
     * @param language language from enum {@code Language}
     *                 {@link Language} to set
     * @return come request's url (could be used for routing to the same page)
     * @see Language#LANGUAGE_CODE_COOKIE_NAME
     * @see Language#BUNDLE_BASE_NAME_COOKIE_NAME
     */
    static String changeLocale(HttpServletRequest request,
                               HttpServletResponse response, Language language) {
        Cookie locale =
                new Cookie(LANGUAGE_CODE_COOKIE_NAME, language.name().toLowerCase());
        Cookie bundle =
                new Cookie(BUNDLE_BASE_NAME_COOKIE_NAME, language.getBundleBaseName());
        response.addCookie(locale);
        response.addCookie(bundle);
        return getRefererAppContextUrl(request);
    }
}
