package com.gerard.site.controller.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Filters direct access to files with ".jsp" extension
 * (application pages)
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
@WebFilter
public class JspSecurityFilter implements Filter {
    private String defaultLocation;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String defaultLocationParameterName = "defaultLocation";
        this.defaultLocation =
                filterConfig.getInitParameter(defaultLocationParameterName);
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(
                httpRequest.getServletContext() + defaultLocation);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        defaultLocation = null;
    }
}
