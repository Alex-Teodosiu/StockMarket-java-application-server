package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private List<Account> accounts = new ArrayList<>();

    @Override
    public Account createAccount(Account account) {
        accounts.add(account);
        System.out.println("service: " + account.getUsername() + account.getPassword());
        return accounts.get(0);
    }

}
