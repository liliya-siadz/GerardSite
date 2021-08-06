package com.gerard.GerardSite.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Optional;

public class CustomHttpServletRequestUtil {

    public static Cookie getCookieFromRequestByName(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieValue = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst();
        return cookieValue.isPresent() ? cookieValue.get() : null;
    }
}
