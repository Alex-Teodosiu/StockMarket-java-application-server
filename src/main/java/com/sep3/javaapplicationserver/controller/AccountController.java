package com.sep3.javaapplicationserver.controller;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import com.sep3.javaapplicationserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    public AccountService accountService;

    @Autowired
    public AccountRepository accountRepository;

    @GetMapping
    public List<Account> listAll() {
        return accountRepository.findAll();
    }

    @GetMapping("/{accountId}")
    public Account getById(@PathVariable Long accountId) {
        return accountService.findByIdOrFail(accountId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Account add(@RequestBody Account account) {
        accountService.findUniqueUsernameOrFail(account.getUsername());
        return accountService.save(account);
    }

    @PutMapping("/{accountId}")
    public Account update(@PathVariable Long accountId, @RequestBody Account accountToEdit) {

        Account accountCurrent = accountService.findByIdOrFail(accountId);

        if (!accountCurrent.getUsername().equals(accountToEdit.getUsername())) {
            accountService.findUniqueUsernameOrFail(accountToEdit.getUsername());
            return accountService.update(accountToEdit, accountCurrent);
        }

        return accountService.update(accountToEdit, accountCurrent);
    }
}
