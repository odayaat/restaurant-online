package com.example.demo.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.List;

/**
 * Custom authentication success handler that redirects users based on their roles.
 * <p>
 * This class implements the {@link AuthenticationSuccessHandler} interface
 * and provides a method to handle successful authentication events.
 * Users are redirected to different URLs based on their roles.
 *
 * @see AuthenticationSuccessHandler
 */
public class SuccessHandler implements AuthenticationSuccessHandler {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * Handles successful authentication by redirecting users based on their roles.
     * <p>
     * If the user has an "ADMIN" role, they are redirected to the admin dishes page.
     * Otherwise, they are redirected to the home page.
     *
     * @param request the {@link HttpServletRequest}
     * @param response the {@link HttpServletResponse}
     * @param authentication the {@link Authentication} object containing user details
     * @throws IOException if an input or output error occurs
     * @throws ServletException if a servlet error occurs
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();

        String targetUrl = "";
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ADMIN")) {
                targetUrl = "/admin/dishes";
            } else {
                targetUrl = "/home";
            }
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}
