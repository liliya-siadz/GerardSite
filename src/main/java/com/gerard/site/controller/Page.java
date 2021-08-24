package com.gerard.site.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Presents accessible application
 * pages for routing
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 * @see Router#prepareUrl(HttpServletRequest, HttpServletResponse)  for details of using
 */
public enum Page {
    ADMIN_DOGS("/admin_dogs"),
    ADMIN_REQUESTS ("/admin_requests"),
    ADMIN_PHOTOS ("/admin_photos"),
    DOGS("/dogs"),
    HOME("/home"),
    LOGIN( "/login"),
    PHOTOS( "/photos"),
    PUPPIES( "/puppies"),
    ERROR( "/error"),
    ERROR_404("/error-404"),
    MAKE_REQUEST( "/make_request");

    /**
     * Page's url, using for routing by Router {@link Router}
     */
    private final String pageUrl;

    Page(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }
}
