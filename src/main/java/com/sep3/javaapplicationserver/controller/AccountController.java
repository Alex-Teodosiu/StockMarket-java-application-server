package com.sep3.javaapplicationserver.controller;

import com.sep3.javaapplicationserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shared.Account;

@RestController
@RequestMapping("/account")
public class AccountController {

    public final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("")
    public void addNewAccount(@RequestBody Account account) {
        System.out.println("Controller: "+ account.toString());
        accountService.AddNewAccount(account);
    }
}
