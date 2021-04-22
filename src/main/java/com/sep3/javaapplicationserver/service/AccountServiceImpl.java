package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    //private final AccountRepository accountRepository;
    private List<Account> accounts;

    @Autowired
    public AccountServiceImpl() {
        accounts = new ArrayList<>();
    }

    //    @Autowired
//    public AccountServiceImpl(AccountRepository accountRepository) {
//        this.accountRepository = accountRepository;
//    }

    @Override
    public void addNewAccount(Account account) {
//        Optional<Account> accountOptional = accountRepository
//                .findAccountByUsername(account.getUsername());
//        if (accountOptional.isPresent()) {
//            throw new IllegalStateException("email taken");
//        }

        //System.out.println("2: "+ account.toString());
        accounts.add(account);

    }

}
