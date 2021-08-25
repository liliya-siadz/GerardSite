package com.gerard.site.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * Sets up if needs default character encoding
 * of the request of the response
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
@WebFilter
public class EncodingFilter implements Filter {
    /**
     * Default encoding value
     */
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParameterName = "encoding";
        encoding = filterConfig.getInitParameter(encodingParameterName);
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String requestCharacterEncoding = request.getCharacterEncoding();
        if ((this.encoding != null)
             && (!requestCharacterEncoding.equals(encoding))) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
