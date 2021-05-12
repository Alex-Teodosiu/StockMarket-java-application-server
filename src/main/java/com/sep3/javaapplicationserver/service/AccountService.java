package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;

import java.util.Optional;

public interface AccountService {
    void registerAccount(Account account);
    void login(String username, String password);
    void editAccount(Account account);
    Optional<Account> getAccount(String username);

}

