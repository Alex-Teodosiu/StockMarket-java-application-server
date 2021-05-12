package com.sep3.javaapplicationserver.controller;

import com.sep3.javaapplicationserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sep3.javaapplicationserver.model.Account;

@RestController
@RequestMapping("/account")
public class AccountController {

    public final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("")
    public ResponseEntity<String> addNewAccount(@RequestBody Account account) {
        ResponseEntity<String> entity;
        try {
            accountService.registerAccount(account);
            entity = new ResponseEntity<>("ok", HttpStatus.OK);
        }catch (Exception e){
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            System.out.println(entity.getBody());
        }
        return entity;
    }

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<String> updateAccount(@RequestParam String username, String password) {
        ResponseEntity<String> entity;
        try {
            accountService.login(username, password);
            entity = new ResponseEntity<>("ok", HttpStatus.OK);
        }catch (Exception e){
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            System.out.println(entity.getBody());
        }
        return entity;
    }

    @PutMapping("/")
    public ResponseEntity<String> updateAccount(@RequestBody Account account) {
        ResponseEntity<String> entity;
        try {
            accountService.editAccount(account);
            entity = new ResponseEntity<>("ok", HttpStatus.OK);
        }catch (Exception e){
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            System.out.println(entity.getBody());
        }
        return entity;
    }

    @PutMapping("/")
    public ResponseEntity<String> getAccount(@RequestBody Account account) {
        ResponseEntity<String> entity;
        try {
            accountService.getAccount(account.getUsername());
            entity = new ResponseEntity<>("ok", HttpStatus.OK);
        }catch (Exception e){
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            System.out.println(entity.getBody());
        }
        return entity;
    }
}
