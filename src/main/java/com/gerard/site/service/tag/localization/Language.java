package com.gerard.site.service.tag.localization;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public enum Language {
    EN("locale"),
    RU("locale_ru"),
    BE("locale_be");
    static final String LOCALE_CODE_COOKIE_NAME = "locale";
    static final String BUNDLE_COOKIE_NAME = "bundle";

    private final String bundleName;

    Language(String bundleBaseName) {
        this.bundleName = bundleBaseName;

    }

    public String getBundleBaseName() {
        return bundleName;
    }

    static Optional<Language> getLanguage(String languageCode) {
        for (Language value : values()) {
            if (value.name().equals(languageCode)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}