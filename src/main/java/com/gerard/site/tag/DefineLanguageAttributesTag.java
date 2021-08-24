package com.gerard.site.tag;

import com.gerard.site.service.util.localization.Language;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.jsp.tagext.TagSupport;

import static com.gerard.site.service.util.localization.Language.BUNDLE_BASE_NAME_COOKIE_NAME;
import static com.gerard.site.service.util.localization.Language.LANGUAGE_CODE_COOKIE_NAME;

/**
 * Custom JSP-tag, defines and sets in the app
 * locale and bundle due to this locale .
 *
 *<p> Firstly checks cookies, if no app supported language {@link Language}
 * was found (or null) tries to get language from request header,
 * if no app supported language was found (or null) in the request header
 * uses default language .
 *</p>
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 * @see Language
 */
public class DefineLanguageAttributesTag extends TagSupport {

    /**
     * Default language, is using if no app supported language was found.
     */
    private static final Language DEFAULT_LANGUAGE = Language.EN;

    private static final Logger LOGGER =
            LogManager.getLogger(DefineLanguageAttributesTag.class);

    public DefineLanguageAttributesTag() {
        super();
    }

    @Override
    public int doStartTag() {
        HttpServletRequest request =
                (HttpServletRequest) super.pageContext.getRequest();
        Cookie languageCode = getCookieByName(request, LANGUAGE_CODE_COOKIE_NAME);
            if (((languageCode == null)
                    || (languageCode.getValue() == null))
                    || (Language.getLanguage(languageCode.getValue().toUpperCase()).isEmpty())) {
                LOGGER.trace("Full language information was not found in the cookies .");
                Language assignedLanguage =  DEFAULT_LANGUAGE;
                languageCode = new Cookie(LANGUAGE_CODE_COOKIE_NAME,
                        assignedLanguage.name().toLowerCase());
                Cookie bundle = new Cookie(BUNDLE_BASE_NAME_COOKIE_NAME,
                        assignedLanguage.getBundleBaseName());
                HttpServletResponse response =
                        (HttpServletResponse) super.pageContext.getResponse();
                response.addCookie(languageCode);
                response.addCookie(bundle);
                LOGGER.trace("Language information was put into cookies. "
                        + "Used language: " + assignedLanguage);
            }
        HttpSession session = pageContext.getSession();
        String locale = languageCode.getValue();
        String bundleBaseName = Language.valueOf(
                languageCode.getValue().toUpperCase()).getBundleBaseName();
        session.setAttribute(LANGUAGE_CODE_COOKIE_NAME, locale);
        session.setAttribute(BUNDLE_BASE_NAME_COOKIE_NAME, bundleBaseName);
        LOGGER.info("Language information was set to session. "
                + "Used languageCode: " + locale
                + " . Used bundle: " + bundleBaseName);
        return SKIP_BODY;
    }

    /**
     * Finds cookie in the request by it's name, may return null .
     * <b>May return null value .</b>
     *
     * @param request    request to find cookie in
     * @param cookieName cookie name to find in the specified request
     * @return returns target cookie by specified name,
     *         otherwise returns {@code null}
     */
    private static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies !=null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieName)) {
                    LOGGER.info("Cookie with name: '" + cookieName + "' was found.");
                    return cookies[i];
                }
            }
        }
        LOGGER.warn("Null will be returned . ");
        return null;
    }
}
