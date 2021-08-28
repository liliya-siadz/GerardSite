package com.gerard.site.controller.filter;

import com.gerard.site.controller.command.CommandIdentifier;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class AuthAccessedCommandsFilter implements Filter {
    private static final Logger LOGGER
            = LogManager.getLogger(AuthAccessedCommandsFilter.class);
    private String defaultLocation;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String defaultLocationParameterName = "defaultLocation";
        this.defaultLocation = filterConfig.getInitParameter(
                defaultLocationParameterName);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String authAccessedParameterName = "command";
        String authAccessedParameterValue
                = request.getParameter(authAccessedParameterName);
        if(authAccessedParameterValue!=null) {
            boolean isAuthAccessedCommandRequested = Arrays.stream(CommandIdentifier.values())
                    .filter(CommandIdentifier::isAuthAccessedOnly)
                    .anyMatch(commandIdentifier ->
                            commandIdentifier.name().equalsIgnoreCase(authAccessedParameterValue));
            if(isAuthAccessedCommandRequested) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpSession session = ((HttpServletRequest) request).getSession();
                String sessionRoleIdentifierAttributeName = "sessionRoleIdentifier";
                if(Objects.isNull(session.getAttribute(sessionRoleIdentifierAttributeName))) {
                    httpResponse.sendRedirect(httpRequest.getServletContext() + defaultLocation);
                    LOGGER.warn("Denied access try past.");
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        defaultLocation = null;
    }
}
