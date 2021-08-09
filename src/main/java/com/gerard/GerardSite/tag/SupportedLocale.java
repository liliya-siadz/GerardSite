package com.gerard.GerardSite.custom_tag;

public enum SupportedLocale {
    EN("locale"),
    RU("locale_ru"),
    BE("locale_be");

    private String bundleName;
    SupportedLocale(String bundleName) {
        this.bundleName = bundleName;

    }

    public String getBundleName(){
        return bundleName;
    }

}