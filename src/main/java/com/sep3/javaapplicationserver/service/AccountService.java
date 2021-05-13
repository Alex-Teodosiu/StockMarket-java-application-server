package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;

public interface AccountService {
    void addNewAccount(Account account);
    void editAccount(Account account);
}

