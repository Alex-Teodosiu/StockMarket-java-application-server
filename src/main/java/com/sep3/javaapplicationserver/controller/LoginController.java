package com.sep3.javaapplicationserver.controller;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> login(@RequestParam String username, String password){
        ResponseEntity<Object> responseEntity;

        try {
            Account account = loginService.login(username, password);
            responseEntity = new ResponseEntity<>(account, HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
