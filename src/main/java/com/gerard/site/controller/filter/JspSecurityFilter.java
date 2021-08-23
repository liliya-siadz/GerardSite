package com.gerard.site.controller.filter;

import jakarta.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class JspSecurityFilter implements Filter {
    private String defaultLocation;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String defaultLocationParameterName="defaultLocation";
        this.defaultLocation = filterConfig.getInitParameter(defaultLocationParameterName);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getServletContext() + defaultLocation);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        defaultLocation = null;
    }
}
