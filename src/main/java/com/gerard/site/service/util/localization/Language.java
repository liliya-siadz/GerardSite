package com.gerard.site.service.util.localization;

import java.util.Optional;

/**
 * Enum for storing supported app-languages,
 * it's instance names presents languages codes (locale) in upper case.
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public enum Language {
    EN("locale"),
    RU("locale_ru"),
    BE("locale_be");

    /**
     * Cookie name for storing language code
     */
    public static final String LANGUAGE_CODE_COOKIE_NAME = "locale";

    /**
     * Cookie name for storing bundle base name
     */
    public static final String BUNDLE_BASE_NAME_COOKIE_NAME = "bundle";

    /**
     * Bundle base name attached to language code (locale)
     */
    private final String bundleName;

    Language(String bundleBaseName) {
        this.bundleName = bundleBaseName;

    }

    /**
     * @return bundle base name {@link Language#bundleName}
     */
    public String getBundleBaseName() {
        return bundleName;
    }

    /**
     * Finds instance by it's name, if no instance
     * was found returns {@code Optional.empty()} {@link Optional#empty()}
     *
     * @param languageCode language code that uses for find instance,
     *                     must exactly matched to instance name
     * @return {@code Optional.of(Language instance)}
     *         if languageCode was matched to any instance name,
     *         otherwise returns {@code Optional.empty()}
     */
    public static Optional<Language> getLanguage(String languageCode) {
        for (Language value : values()) {
            if (value.name().equals(languageCode)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}