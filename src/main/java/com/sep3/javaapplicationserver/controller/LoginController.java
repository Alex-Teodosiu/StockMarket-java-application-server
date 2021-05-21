package com.sep3.javaapplicationserver.controller;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("")
    @ResponseBody
    public Account login(@RequestParam String username, String password){
        return loginService.login(username, password);
    }
}
