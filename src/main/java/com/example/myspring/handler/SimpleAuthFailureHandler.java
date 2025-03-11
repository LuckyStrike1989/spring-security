package com.example.myspring.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class SimpleAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        String errorMessage = exception.getMessage();
        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

        setDefaultFailureUrl("/system/account/login?error=" + errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}

