package com.gerard.site.service.tag.localization;

import com.gerard.site.service.Language;
import com.gerard.site.util.HttpServletRequestUtil;
import com.gerard.site.util.CustomStringUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.jsp.tagext.TagSupport;

import java.util.List;

import static com.gerard.site.service.Language.*;

public class DefineLanguageAttributesTag extends TagSupport {

    private static final Logger LOGGER = LogManager.getLogger(
            DefineLanguageAttributesTag.class);
    private final Language defaultLanguage = Language.EN;

    @Override
    public int doStartTag() {
        HttpServletRequest request = (HttpServletRequest) super.pageContext.getRequest();
        Cookie locale = HttpServletRequestUtil.getCookieByName(
                request, LOCALE_CODE_COOKIE_NAME);
        Cookie bundle = HttpServletRequestUtil.getCookieByName(
                request, BUNDLE_COOKIE_NAME);
        if (locale==null || bundle==null) {
            List<Language> supportedLocales =
                    findSupportedLanguagesFromRequestHeader();
            Language assignedLocale =
                    supportedLocales.size() == 0 ? defaultLanguage : supportedLocales.get(0);
            locale = new Cookie(LOCALE_CODE_COOKIE_NAME, assignedLocale.name().toLowerCase());
            bundle = new Cookie(BUNDLE_COOKIE_NAME, assignedLocale.getBundleBaseName());
            HttpServletResponse response = (HttpServletResponse) super.pageContext.getResponse();
            response.addCookie(locale);
            response.addCookie(bundle);
        }
        HttpSession session = pageContext.getSession();
        session.setAttribute(LOCALE_CODE_COOKIE_NAME, locale.getValue());
        session.setAttribute(BUNDLE_COOKIE_NAME, bundle.getValue());
        return SKIP_BODY;
    }

    private List<Language> findSupportedLanguagesFromRequestHeader() {
        HttpServletRequest request = (HttpServletRequest) super.pageContext.getRequest();
        String[] acceptedLanguagesCodes = HttpServletRequestUtil.
                getAcceptedLanguagesCodesFromHeader(request);
        List<Language> supportedLanguagesFromRequestHeader =
                CustomStringUtil.filterMatchedRowsByEnumElements(
                        acceptedLanguagesCodes, Language.values());
        return supportedLanguagesFromRequestHeader;
    }
}
