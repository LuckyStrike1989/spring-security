package com.example.myspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/account")
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "system/account/login";
    }

    @RequestMapping("/loginProcess")
    public String loginProcess() {
        return "system/account/loginProcess";
    }
}
