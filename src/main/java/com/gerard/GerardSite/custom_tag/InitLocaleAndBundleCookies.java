package com.gerard.GerardSite.custom_tag;

import com.gerard.GerardSite.util.CustomHttpServletRequestUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.jsp.tagext.TagSupport;

import java.util.List;

import static com.gerard.GerardSite.util.CustomStringUtil.filterMatchedEnumElementsList;
import static com.gerard.GerardSite.util.CustomStringUtil.splitByRegex;

public class InitLocaleAndBundleCookies extends TagSupport {

    private static final Logger LOGGER = LogManager.getLogger(InitLocaleAndBundleCookies.class);
    private static final String TARGET_REQUEST_HEADER_NAME = "Accept-Language";
    private static final String LOCALE_CODE_SEPARATOR = ",";
    private static final String LOCALE_COOKIE_NAME = "locale";
    private static final String BUNDLE_COOKIE_NAME = "bundle";

    //docs: https://datatracker.ietf.org/doc/html/rfc2616#page-29
    //where any two-letter primary-tag is an ISO-639 language abbreviation
    private static final int LOCALE_CODE_LETTERS_QUANTITY = 2;
    private static final SupportedLocale defaultLocale = SupportedLocale.EN;

    //if cookie has no elements than assign from header
    @Override
    public int doStartTag() {
        HttpServletRequest request = (HttpServletRequest) super.pageContext.getRequest();
        Cookie locale = CustomHttpServletRequestUtil.getCookieFromRequestByName(
                request, LOCALE_COOKIE_NAME);
        Cookie bundle = CustomHttpServletRequestUtil.getCookieFromRequestByName(
                request, BUNDLE_COOKIE_NAME);
        if (locale == null || bundle == null) {
            List<SupportedLocale> supportedLocales =
                    getAcceptedLocalesFromRequestHeader();
            SupportedLocale assignedLocale =
                    supportedLocales.size() == 0 ? defaultLocale : supportedLocales.get(0);
            String localeValue = assignedLocale.name().toLowerCase();
            String bundleBaseName = assignedLocale.getBundleName();
            locale = new Cookie(LOCALE_COOKIE_NAME, localeValue);
            bundle = new Cookie(BUNDLE_COOKIE_NAME, bundleBaseName);
            HttpServletResponse response = (HttpServletResponse) super.pageContext.getResponse();
            response.addCookie(locale);
            response.addCookie(bundle);
        }
        HttpSession session = pageContext.getSession();
        session.setAttribute(LOCALE_COOKIE_NAME, locale.getValue());
        session.setAttribute(BUNDLE_COOKIE_NAME, bundle.getValue());
        return SKIP_BODY;
    }

    private List<SupportedLocale> getAcceptedLocalesFromRequestHeader() {
        HttpServletRequest request = (HttpServletRequest) super.pageContext.getRequest();
        String acceptedLocalesInRow = request.getHeader(TARGET_REQUEST_HEADER_NAME);
        String[] acceptedLocales = splitByRegex(acceptedLocalesInRow,
                LOCALE_CODE_SEPARATOR, true);
        String[] acceptedLocalesCodes = new String[acceptedLocales.length];
        for (int i = 0; i < acceptedLocales.length; i++) {
            acceptedLocalesCodes[i] = acceptedLocales[i].substring(0,
                    LOCALE_CODE_LETTERS_QUANTITY);
        }
        List<SupportedLocale> supportedLocales =
                filterMatchedEnumElementsList(acceptedLocalesCodes,
                        SupportedLocale.values());
        return supportedLocales;
    }

}
