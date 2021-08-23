package com.gerard.site.controller.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParameterName="encoding";
        encoding = filterConfig.getInitParameter(encodingParameterName);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestCharacterEncoding = request.getCharacterEncoding();
        if((this.encoding != null)
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
