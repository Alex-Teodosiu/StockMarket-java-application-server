package com.sep3.javaapplicationserver.controller;

import com.sep3.javaapplicationserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sep3.javaapplicationserver.model.Account;

@RestController
public class AccountController {

    public final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Create
    @PostMapping("/account")
    public ResponseEntity<String> registerAccount(@RequestBody Account account) {
        ResponseEntity<String> responseEntity;
        try {
            accountService.registerAccount(account);
            responseEntity = new ResponseEntity<String>("Sucessful registration", HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    //Read
    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestParam String username, String password){
        ResponseEntity<String> responseEntity;

        try {
            accountService.login(username, password);
            responseEntity = new ResponseEntity<String>("Sucessful login", HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    //Update
    @PutMapping("/account")
    public ResponseEntity<String> editAccount(@RequestBody Account account){
        ResponseEntity<String> response;

        try {
            accountService.editAccount(account);
            response = new ResponseEntity<String>("Account edited successfully", HttpStatus.OK);
        }
        catch (Exception e){
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
