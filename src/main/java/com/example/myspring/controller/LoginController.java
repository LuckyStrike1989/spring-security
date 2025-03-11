package com.example.myspring.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/system/account")
public class LoginController {
    @RequestMapping("/login")
    public String login(HttpServletRequest request, ModelMap model) {
        String queryString = request.getQueryString();

        if (queryString != null && queryString.startsWith("error")) {
            String errorMessage = "로그인이 실패하였습니다.";
            String error = request.getParameter("error");
            if (error != null && !error.isEmpty()) {
                errorMessage = error;
            }
            model.addAttribute("error", errorMessage);
        }


        return "system/account/login";
    }

    @RequestMapping("/loginProcess")
    public String loginProcess() {
        return "system/account/loginProcess";
    }

    @RequestMapping("/loginFailureForward")
    public String loginFailureForward(HttpServletRequest request, ModelMap model) {
        String memberId = request.getParameter("memberId");
        String memberPassword = request.getParameter("memberPassword");

        HashMap<String, String> errors = new HashMap<String, String>();
        if (memberId == null || memberId.isEmpty()) {
            errors.put("memberId", "사용자 ID를 입력하세요.");
        } else if (memberId.length() < 8 || memberId.length() > 25) {
            errors.put("memberId", "사용자 ID는 최소 8자, 최대 25자 이내로 입력하세요.");
        }

        if (memberPassword == null || memberPassword.isEmpty()) {
            errors.put("memberPassword", "비밀번호를 입력하세요.");
        }

        errors.put("message", "입력하신 사용자 ID와 비밀번호로 로그인을 할 수 없습니다.");

        model.addAttribute("errors", errors);


        return "system/account/login";
    }
}
