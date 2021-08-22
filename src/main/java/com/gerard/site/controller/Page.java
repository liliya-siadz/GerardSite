package com.gerard.site.controller;

enum Page {
    ADMIN_DOGS(true, "/admin_dogs"),
    ADMIN_REQUESTS(true, "/admin_requests"),
    DOGS(false, "/dogs"),
    HOME(false,"/home"),
    LOGIN(false, "/login"),
    PHOTOS(false, "/photos"),
    PUPPIES(false, "/puppies"),
    ERROR(false, "/error"),
    ERROR_404(false, "/error-404"),
    MAKE_REQUEST(false, "/make_request");

    private final boolean hasAuthAccess;
    private final String url;

    Page(boolean hasAuthAccess, String url) {
        this.hasAuthAccess = hasAuthAccess;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
