package com.example.myspring.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        String errorMessage = "";
        if (exception instanceof BadCredentialsException) {
            // 자격 증명이 유효하지 않아 인증 요청이 거부되는 경우 발생합니다.
            errorMessage = "등록되지 않은 사용자 ID이거나 사용자 ID 또는 비밀번호를 잘못 입력했습니다.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            // 내부적으로 발생한 시스템 문제로 인해 인증 요청을 처리할 수 없는 경우 발생합니다.
            errorMessage = "일시적인 오류로 로그인을 할 수 없습니다.";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            // SecurityContext에 인증 개체가 없어 인증 요청이 거부되면 발생합니다.
            errorMessage = "일시적인 오류로 로그인을 할 수 없습니다.";
        } else if (exception instanceof UsernameNotFoundException) {
            // UserDetailsService 구현이 사용자 이름으로 사용자를 찾을 수 없는 경우 발생합니다.
            errorMessage = "등록되지 않은 사용자 ID입니다.";
        } else {
            // 알 수 없는 오류가 발생했습니다.
            errorMessage = "일시적인 오류로 로그인을 할 수 없습니다.";
        }
        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

        setDefaultFailureUrl("/system/account/login?error=" + errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}

