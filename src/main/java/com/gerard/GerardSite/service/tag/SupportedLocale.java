package com.gerard.GerardSite.service.tag;

public enum SupportedLocale {
    EN("locale"),
    RU("locale_ru"),
    BE("locale_be");

    private final String bundleName;
    SupportedLocale(String bundleName) {
        this.bundleName = bundleName;

    }

    public String getBundleName(){
        return bundleName;
    }

}