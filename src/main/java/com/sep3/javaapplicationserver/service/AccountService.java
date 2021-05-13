package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;
import org.springframework.stereotype.Service;

public interface AccountService {
    void addNewAccount(Account account);
    void editAccount(Account account);
}

