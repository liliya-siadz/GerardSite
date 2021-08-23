package com.gerard.site.controller;

public enum Page {
    ADMIN_DOGS("/admin_dogs"),
    ADMIN_REQUESTS ("/admin_requests"),
    DOGS("/dogs"),
    HOME("/home"),
    LOGIN( "/login"),
    PHOTOS( "/photos"),
    PUPPIES( "/puppies"),
    ERROR( "/error"),
    ERROR_404("/error-404"),
    MAKE_REQUEST( "/make_request");

    private final String pageUrl;

    Page(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }
}
