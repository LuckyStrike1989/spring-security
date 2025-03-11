package com.example.myspring.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class AuthFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String memberId = request.getParameter("memberId");

        request.setAttribute("memberId", memberId);

        String errorMessage = "";
        if (exception instanceof BadCredentialsException) {
            errorMessage = "등록되지 않은 사용자 ID이거나 사용자 ID 또는 비밀번호를 잘못 입력했습니다.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errorMessage = "일시적인 오류로 로그인을 할 수 없습니다.";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            errorMessage = "일시적인 오류로 로그인을 할 수 없습니다.";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "등록되지 않은 사용자 ID입니다.";
        } else {
            errorMessage = "일시적인 오류로 로그인을 할 수 없습니다.";
        }
        request.setAttribute("error", errorMessage);

        // 요청 재지정
        request.getRequestDispatcher("/system/account/login").forward(request, response);
    }
}
